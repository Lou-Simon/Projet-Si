<template>
  <section class="page container py-4 f-flex flex-column gap-4">
    <div class="films-header text-center mb-4">
      <h1>Artistes</h1>
      <h2>Découvrez des centaines d'artistes.</h2>
    </div>

    <p v-if="loading" class="text-center">Chargement des artistes...</p>
    <p v-else-if="error" class="text-center text-danger">{{ error }}</p>

    <div v-else class="row g-3">
      <div v-for="artist in artists" :key="artist.id || artist.name" class="col-12 col-md-6 col-lg-4">
        <ArtistCard :artist="artist" />
      </div>
    </div>
  </section>
</template>

<script>
import ArtistCard from '@/components/ArtistCard.vue'
import { API_ENDPOINTS } from '@/config/api'

export default {
  name: 'ArtistsPage',
  components: {
    ArtistCard
  },
  data() {
    return {
      artists: [],
      loading: true,
      error: ''
    }
  },
  created() {
    fetch(API_ENDPOINTS.artists)
      .then((response) => {
        if (!response.ok) {
          throw new Error('Impossible de récupérer les artistes.')
        }
        return response.json()
      })
      .then((json) => {
        this.artists = Array.isArray(json)
          ? json
          : Array.isArray(json.artists)
            ? json.artists
            : []
      })
      .catch(() => {
        this.error = 'Erreur de chargement des artistes.'
      })
      .finally(() => {
        this.loading = false
      })
  }
}
</script>
