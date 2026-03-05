<template>
  <section class="page container py-4">
    <p v-if="loading" class="text-center">Chargement du profil...</p>
    <p v-else-if="error" class="text-center text-danger">{{ error }}</p>
    <div v-else class="row justify-content-center">
      <div class="col-12 col-md-8 col-lg-6">
        <div class="card shadow-sm profile-card">
          <div class="card-body d-flex flex-column gap-3">

            <!-- Header avatar + pseudo -->
            <div class="d-flex align-items-center gap-4">
              <div class="profile-avatar">
                {{ initials }}
              </div>
              <div>
                <h2 class="mb-0">{{ user.pseudo }}</h2>
                <span class="text-muted">{{ user.email }}</span>
              </div>
            </div>

            <!-- Infos supplémentaires -->
            <hr />
            <div class="d-flex flex-column gap-2">
              <div v-if="user.role">
                <strong>Rôle :</strong>
                <span class="badge ms-2" :class="roleBadgeClass">{{ user.role }}</span>
              </div>
              <div v-if="user.createdAt">
                <strong>Membre depuis :</strong> {{ formattedDate }}
              </div>
            </div>

            <!-- Bouton édition si c'est le profil courant -->
            <div v-if="isCurrentUser" class="mt-2">
              <router-link
                :to="`/users/${user.pseudo}/edit`"
                class="btn btn-primary"
              >
                Modifier mon profil
              </router-link>
            </div>

          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { USER_API } from '@/config/api'

export default {
  name: 'UserProfilePage',
  data() {
    return {
      user: null,
      loading: true,
      error: ''
    }
  },
  computed: {
    initials() {
      return (this.user?.pseudo ?? '?').slice(0, 2).toUpperCase()
    },
    roleBadgeClass() {
      const role = this.user?.role?.toLowerCase()
      if (role === 'admin') return 'bg-danger'
      if (role === 'moderator') return 'bg-warning text-dark'
      return 'bg-secondary'
    },
    formattedDate() {
      if (!this.user?.createdAt) return ''
      return new Date(this.user.createdAt).toLocaleDateString('fr-FR')
    },
    isCurrentUser() {
      const token = localStorage.getItem('token')
      if (!token) return false
      // Décode le pseudo depuis le JWT (payload base64)
      try {
        const payload = JSON.parse(atob(token.split('.')[1]))
        return payload.sub === this.user?.pseudo || payload.pseudo === this.user?.pseudo
      } catch {
        return false
      }
    }
  },
  created() {
    const pseudo = this.$route.params.pseudo
    fetch(`${USER_API.baseUrl}/${pseudo}`)
      .then(response => {
        if (response.status === 404) throw new Error('Utilisateur introuvable.')
        if (!response.ok) throw new Error('Erreur lors du chargement du profil.')
        return response.json()
      })
      .then(json => {
        this.user = json
      })
      .catch(err => {
        this.error = err.message || 'Erreur de chargement.'
      })
      .finally(() => {
        this.loading = false
      })
  }
}
</script>

<style scoped>
.profile-card {
  border-radius: 16px;
}
.profile-avatar {
  width: 72px;
  height: 72px;
  border-radius: 50%;
  background: linear-gradient(135deg, #667eea, #764ba2);
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  font-size: 1.5rem;
  flex-shrink: 0;
}
</style>
