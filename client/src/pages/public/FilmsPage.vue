<template>
  <section class="page">
    <div class="films-header">
      <h1>Films</h1>
      <h2>Découvrez des centaines de films.</h2>
    </div>

    <p v-if="loading">Chargement des films...</p>
    <p v-else-if="error">{{ error }}</p>

    <div v-else class="films-grid">
      <FilmCard v-for="movie in movies" :key="movie.title" :movie="movie" />
    </div>
  </section>
</template>

<script>
import FilmCard from '@/components/FilmCard.vue'
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
    fetch('http://localhost:12062/movies/batch/10')
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
