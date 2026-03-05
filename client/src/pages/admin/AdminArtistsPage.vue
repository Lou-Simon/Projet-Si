<template>
  <section class="page container py-4">
    <div class="films-header text-center mb-4">
      <h1>Administration Artistes</h1>
      <div class="container d-flex justify-content-center align-items-center gap-2 mt-3 ajout-film">
        Combien d'artistes ajouter ? :
        <input type="number" v-model.number="nbArtists" min="1" max="100" class="form-control d-inline-block w-auto ms-2" />
        <button class="btn btn-primary ms-2" @click="addArtists">Ajouter</button>
      </div>
      <p class="mt-2 mb-0">Nombre saisi : <strong>{{ safeNbArtists }}</strong></p>
    </div>

    <div class="row g-3">
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
  computed: {
    safeNbArtists() {
      return Number.isFinite(this.nbArtists) && this.nbArtists > 0 ? this.nbArtists : 0
    }
  },
  data() {
    return {
      nbArtists: 0,
      artists: [],
      loading: false,
      previewTimer: null
    }
  },
  watch: {
    nbArtists() {
      if (this.previewTimer) {
        clearTimeout(this.previewTimer)
      }

      if (this.safeNbArtists < 1) {
        this.artists = []
        return
      }

      this.previewTimer = setTimeout(() => {
        this.previewArtists()
      }, 300)
    }
  },
  methods: {
    extractArtistList(payload) {
      if (Array.isArray(payload)) return payload
      if (Array.isArray(payload?.artists)) return payload.artists
      if (Array.isArray(payload?.data)) return payload.data
      return []
    },
    normalizeArtist(artist) {
      return {
        ...artist,
        name: artist?.name ?? artist?.nom ?? 'Artiste inconnu',
        biography: artist?.biography ?? artist?.biographie ?? '',
        birthDate: artist?.birthDate ?? artist?.dateNaissance ?? null
      }
    },
    normalizeName(value) {
      return String(value || '').trim().toLowerCase()
    },
    toIsoDateOrNull(value) {
      if (!value || typeof value !== 'string') return null
      const trimmed = value.trim()

      if (/^\d{4}-\d{2}-\d{2}$/.test(trimmed)) return trimmed

      const frMatch = trimmed.match(/^(\d{2})[\/-](\d{2})[\/-](\d{4})$/)
      if (frMatch) {
        const [, dd, mm, yyyy] = frMatch
        return `${yyyy}-${mm}-${dd}`
      }

      const ymdSlashMatch = trimmed.match(/^(\d{4})\/(\d{2})\/(\d{2})$/)
      if (ymdSlashMatch) {
        const [, yyyy, mm, dd] = ymdSlashMatch
        return `${yyyy}-${mm}-${dd}`
      }

      const parsed = new Date(trimmed)
      if (!Number.isNaN(parsed.getTime())) {
        return parsed.toISOString().slice(0, 10)
      }

      return null
    },
    toArtistCreatePayload(artist, index, nextId) {
      const normalized = this.normalizeArtist(artist)
      const fallbackName = `Artiste scrapé ${nextId + index}`
      const name = normalized.name || fallbackName
      const safeDate = this.toIsoDateOrNull(normalized.dateNaissance || normalized.birthDate) || '2000-01-01'

      return {
        id: nextId + index,
        name,
        genre: normalized.genre || 'Inconnu',
        country: normalized.pays || normalized.country || 'Inconnu',
        birthDate: safeDate,
        biography: normalized.biographie || normalized.biography || 'Ajouté depuis le scraping admin.'
      }
    },
    getArtistKey(artist) {
      return artist?.id || `${artist?.name || artist?.nom || ''}::${artist?.birthDate || artist?.dateNaissance || ''}`
    },
    async loadAllArtists() {
      const response = await fetch(API_ENDPOINTS.artists)
      if (!response.ok) {
        throw new Error('Impossible de récupérer les artistes')
      }

      const json = await response.json()
      const list = Array.isArray(json)
        ? json
        : Array.isArray(json.artists)
          ? json.artists
          : []

      return list.map(this.normalizeArtist)
    },
    async createArtist(payload) {
      const endpoint = API_ENDPOINTS.artistCreate || API_ENDPOINTS.artists
      const response = await fetch(endpoint, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      })

      if (response.ok) {
        return response
      }

      const details = await response.text().catch(() => '')
      const lastError = `${endpoint} -> ${details || `HTTP ${response.status}`}`

      throw new Error(lastError || 'Impossible d\'ajouter l\'artiste')
    },
    async previewArtists() {
      const total = this.safeNbArtists
      if (total < 1) {
        this.artists = []
        return
      }

      this.loading = true

      try {
        const batchResponse = await fetch(`${API_ENDPOINTS.artistsBatch}/${total}`)
        if (!batchResponse.ok) {
          throw new Error('Impossible de récupérer les artistes via le batch')
        }

        const batchJson = await batchResponse.json()
        const batchList = this.extractArtistList(batchJson)
        this.artists = batchList.map(this.normalizeArtist)
      } catch {
        this.artists = []
      } finally {
        this.loading = false
      }
    },
    async addArtists() {
      if (this.loading || this.safeNbArtists < 1) return

      if (this.artists.length === 0) {
        await this.previewArtists()
      }

      if (this.artists.length === 0) {
        return
      }

      this.loading = true

      try {
        const artistsBefore = await this.loadAllArtists()
        const beforeKeys = new Set(artistsBefore.map(this.getArtistKey))
        const existingNames = new Set(
          artistsBefore.map((artist) => this.normalizeName(artist.nom || artist.name)).filter(Boolean)
        )

        const previewUnique = []
        const previewSeenNames = new Set()
        for (const artist of this.artists) {
          const n = this.normalizeArtist(artist)
          const keyName = this.normalizeName(n.nom || n.name)
          if (!keyName) continue
          if (existingNames.has(keyName)) continue
          if (previewSeenNames.has(keyName)) continue
          previewSeenNames.add(keyName)
          previewUnique.push(n)
        }

        if (previewUnique.length === 0) {
          return
        }

        const numericIds = artistsBefore
          .map((artist) => Number(artist.id))
          .filter((id) => Number.isFinite(id))
        const nextId = (numericIds.length > 0 ? Math.max(...numericIds) : 0) + 1

        const saveRequests = previewUnique.map(async (artist, index) => {
          const payload = this.toArtistCreatePayload(artist, index, nextId)
          return this.createArtist(payload)
        })

        await Promise.allSettled(saveRequests)

        const artistsAfterSave = await this.loadAllArtists()
        const newlySaved = artistsAfterSave.filter((artist) => !beforeKeys.has(this.getArtistKey(artist)))

        this.artists = newlySaved.length > 0 ? newlySaved : this.artists
      } catch {
        // volontairement silencieux : pas de messages affichés
      } finally {
        this.loading = false
      }
    }
  },
  beforeUnmount() {
    if (this.previewTimer) {
      clearTimeout(this.previewTimer)
    }
  }
}
</script>
