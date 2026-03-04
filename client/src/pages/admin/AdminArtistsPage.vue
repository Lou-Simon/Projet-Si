<template>
  <section class="page container py-4">
    <div class="films-header text-center mb-4">
      <h1>Administration Artistes</h1>
      <div class="container d-flex justify-content-center align-items-center gap-2 mt-3 ajout-film">
        Combien d'artistes ajouter ? (API de scraping) :
        <input type="number" v-model.number="nbArtists" min="1" max="100" class="form-control d-inline-block w-auto ms-2" />
        <button class="btn btn-primary ms-2" @click="addArtists">Ajouter</button>
      </div>
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
  name: 'AdminArtistsPage',
  components: {
    ArtistCard
  },
  data() {
    return {
      nbArtists: 0,
      artists: [],
      loading: false,
      error: ''
    }
  },
  methods: {
    addArtists() {
      this.loading = true
      this.error = ''

      fetch(`${API_ENDPOINTS.artistsBatch}/${this.nbArtists}`)
        .then((response) => {
          if (!response.ok) {
            throw new Error('Impossible d\'ajouter les artistes')
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
          this.error = 'Erreur lors de l\'ajout des artistes'
        })
        .finally(() => {
          this.loading = false
        })
    }
  },
  created() {
    this.addArtists()
  }
}
</script>
