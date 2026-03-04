<template>
  <section class="page container py-4">
    <div class="films-header text-center mb-4">
      <h1>Administration Films</h1>
      <div class="container d-flex justify-content-center align-items-center gap-2 mt-3 ajout-film">
        Combien de films ajouter ? (API de scraping) :
        <input type="number" v-model.number="nbMovies" min="1" max="100" class="form-control d-inline-block w-auto ms-2" />
        <button class="btn btn-primary ms-2" @click="addMovies">Ajouter</button>
      </div>
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
  name: 'AdminPage',
  components: {
    FilmCard
  },
  data() {
    return {
      nbMovies: 0,
      movies: [],
      loading: false,
      error: ''
    }
  },
  methods: {
    addMovies() {
      this.loading = true
      this.error = ''

      fetch(`${API_ENDPOINTS.moviesBatch}/${this.nbMovies}`)
        .then((response) => {
          if (!response.ok) {
            throw new Error('Impossible d\'ajouter les films')
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
          this.error = 'Erreur lors de l\'ajout des films'
        })
        .finally(() => {
          this.loading = false
        })
    }
  },
  created() {
    this.addMovies()
  }
}

</script>
