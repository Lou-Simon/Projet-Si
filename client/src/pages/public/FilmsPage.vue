<template>
  <section class="page container py-4 d-flex flex-column gap-4">
    <div class="films-header text-center mb-4">
      <h1>Films</h1>
      <h2>Découvrez des centaines de films.</h2>
    </div>

    <p v-if="loading" class="text-center">Chargement des films...</p>

    <template v-else>
      <div v-if="serverDown" class="alert alert-warning d-flex align-items-center gap-3" role="alert">
          <p>Serveur indisponible</p>        
         <p> Affichage des données de démonstration.</p>
      </div>

      <p v-else-if="error" class="text-center text-danger">{{ error }}</p>

      <div class="row g-3">
        <div v-for="movie in movies" :key="movie.id || movie.title" class="col-12 col-md-6 col-lg-4">
          <FilmCard :movie="movie" />
        </div>
      </div>
    </template>
  </section>
</template>

<script>
import FilmCard from '@/components/FilmCard.vue'
import { API_ENDPOINTS } from '@/config/api'
import mockMovies from '@/data/movies.json'

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
        if ([500, 502, 503, 504].includes(response.status)) {
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
        this.serverDown = true
        this.movies = mockMovies
      })
      .finally(() => {
        this.loading = false
      })
  }
}
</script>
