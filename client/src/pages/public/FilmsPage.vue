<template>
  <section class="page container py-4 f-flex flex-column gap-4">
     <div class="films-header text-center mb-4">
      <h1>Films</h1>
      <h2>Découvrez des centaines de films.</h2>
    </div>

    <p v-if="loading" class="text-center">Chargement des films...</p>
    <p v-else-if="error" class="text-center text-danger">{{ error }}</p>

    <div v-else class="row g-3">
      <div v-for="movie in movies" :key="movie.id || movie.title" class="col-12 col-md-6 col-lg-4">
        <FilmCard :movie="movie" />
      </div>
    </div>
  </section>
</template>

<script>
import FilmCard from '@/components/FilmCard.vue'
import { API_ENDPOINTS } from '@/config/api'

export default {
  name: 'FilmsPage',
  components: {
    FilmCard
  },
  data() {
    return {
      movies: [],
      loading: true,
      error: ''
    }
  },
  created() {
    fetch(API_ENDPOINTS.movies)
      .then((response) => {
        if (!response.ok) {
          throw new Error('Impossible de récupérer les films.')
        }
        return response.json()
      })
      .then((json) => {
        this.movies = Array.isArray(json)
          ? json
          : Array.isArray(json.movies)
            ? json.movies
            : []
      })
      .catch(() => {
        this.error = 'Erreur de chargement des films.'
      })
      .finally(() => {
        this.loading = false
      })
  },
  computed: {
    nbMovies() {
      return this.movies.length
    }
  }
}
</script>
