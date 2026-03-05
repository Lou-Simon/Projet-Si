<template>
  <section class="page container py-4 d-flex flex-column gap-4">
    <div class="films-header text-center mb-4">
      <h1>Films</h1>
      <h2>Découvrez des centaines de films.</h2>
    </div>

    <p v-if="loading" class="text-center">Chargement des films...</p>

    <!-- Message serveur down -->
    <div v-else-if="serverDown" class="alert alert-warning d-flex align-items-center gap-3" role="alert">
      <span style="font-size: 2rem;">🚧</span>
      <div>
        <strong>Service temporairement indisponible</strong><br />
        Le serveur de films est actuellement inaccessible. Il est peut-être en cours de maintenance ou de démarrage. Veuillez réessayer dans quelques instants.
      </div>
    </div>

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
  components: { FilmCard },
  data() {
    return {
      movies: [],
      loading: true,
      error: '',
      serverDown: false
    }
  },
  created() {
    fetch(API_ENDPOINTS.movies)
      .then(response => {
        if (response.status === 500 || response.status === 502 || response.status === 503 || response.status === 504) {
          this.serverDown = true
          return null
        }
        if (!response.ok) throw new Error('Impossible de récupérer les films.')
        return response.json()
      })
      .then(json => {
        if (!json) return
        this.movies = Array.isArray(json)
          ? json
          : Array.isArray(json.movies)
            ? json.movies
            : []
      })
      .catch(() => {
        // Timeout réseau ou serveur totalement injoignable
        this.serverDown = true
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