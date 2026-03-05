<template>
  <section class="page container py-4 d-flex flex-column gap-4">
    <div class="text-center mb-4">
      <h1>Utilisateurs</h1>
      <h2>Liste des membres inscrits.</h2>
    </div>
    <p v-if="loading" class="text-center">Chargement des utilisateurs...</p>
    <p v-else-if="error" class="text-center text-danger">{{ error }}</p>
    <div v-else class="row g-3">
      <div v-for="user in users" :key="user.pseudo" class="col-12 col-md-6 col-lg-4">
        <UserCard :user="user" />
      </div>
    </div>
  </section>
</template>

<script>
import UserCard from '@/components/UserCard.vue'
import { USER_API } from '@/config/api'

export default {
  name: 'UsersListPage',
  components: { UserCard },
  data() {
    return {
      users: [],
      loading: true,
      error: ''
    }
  },
  created() {
    const token = localStorage.getItem('token')
    if (!token) {
      this.error = 'Vous devez être connecté pour accéder à cette page.'
      this.loading = false
      return
    }

    fetch(USER_API.baseUrl, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
      .then(response => {
        if (response.status === 401 || response.status === 403) {
          throw new Error('Accès non autorisé.')
        }
        if (!response.ok) {
          throw new Error('Impossible de récupérer les utilisateurs.')
        }
        return response.json()
      })
      .then(json => {
        this.users = Array.isArray(json)
          ? json
          : Array.isArray(json.users)
            ? json.users
            : []
      })
      .catch(err => {
        this.error = err.message || 'Erreur de chargement des utilisateurs.'
      })
      .finally(() => {
        this.loading = false
      })
  }
}
</script>
