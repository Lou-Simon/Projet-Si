<script setup>
import { computed, ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import PublicMovieCard from '../components/movies/PublicMovieCard.vue'

const API_BASE_URL = 'http://localhost:12062/movies'
const DEFAULT_MOVIE = 'fiasco'

const route = useRoute()
const router = useRouter()

const data = ref(null)
const error = ref(null)
const movieSearch = ref('')

const currentMovieSlug = computed(() => {
  const slug = route.params.movieSlug
  return slug ? decodeURIComponent(String(slug)) : DEFAULT_MOVIE
})

const apiPreviewUrl = computed(() => {
  const term = movieSearch.value.trim() || DEFAULT_MOVIE
  return `${API_BASE_URL}/${encodeURIComponent(term)}`
})

function submitMovieSearch() {
  const term = movieSearch.value.trim()
  router.push(term ? `/films/${encodeURIComponent(term)}` : '/films')
}

async function loadMovie(term) {
  data.value = null
  error.value = null

  try {
    const response = await fetch(`${API_BASE_URL}/${encodeURIComponent(term)}`)

    if (!response.ok) {
      throw new Error('Film introuvable')
    }

    data.value = await response.json()
  } catch (err) {
    error.value = err
  }
}

watch(
  currentMovieSlug,
  (slug) => {
    movieSearch.value = slug === DEFAULT_MOVIE ? '' : slug
    loadMovie(slug)
  },
  { immediate: true }
)
</script>

<template>
  <section class="pt-7">
    <div class="container pb-3">
      <div class="card border-0 shadow-sm mb-4">
        <div class="card-body p-3 p-md-4">
          <div class="d-flex flex-column flex-lg-row align-items-lg-center justify-content-between gap-2 mb-3">
            <h1 class="h5 mb-0">Recherche film (page Films)</h1>
            <small class="text-secondary">Requête API : {{ apiPreviewUrl }}</small>
          </div>

          <form class="d-flex flex-column flex-sm-row gap-2" @submit.prevent="submitMovieSearch">
            <input
              v-model="movieSearch"
              class="form-control"
              type="search"
              placeholder="Tape un nom de film (ex: fiasco)"
              aria-label="Nom du film"
            >
            <button class="btn btn-dark px-4" type="submit">Rechercher</button>
          </form>

          <p class="small text-secondary mt-3 mb-0">
            URL client courante :
            <strong>/films/{{ route.params.movieSlug || 'fiasco' }}</strong>
          </p>
        </div>
      </div>
    </div>

    <PublicMovieCard :data="data" :error="error" />
  </section>
</template>

<style scoped>
.pt-7 {
  padding-top: 8rem;
}
</style>