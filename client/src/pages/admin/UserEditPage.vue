<template>
  <section class="page container py-4">
    <div class="row justify-content-center">
      <div class="col-12 col-md-8 col-lg-6">
        <div class="card shadow-sm">
          <div class="card-body d-flex flex-column gap-3">
            <h2 class="mb-0">Modifier mon profil</h2>
            <hr />

            <p v-if="loadError" class="text-danger">{{ loadError }}</p>

            <div v-else>
              <!-- Email -->
              <div class="mb-3">
                <label class="form-label">Email</label>
                <input
                  v-model="form.email"
                  type="email"
                  class="form-control"
                  :class="{ 'is-invalid': errors.email }"
                />
                <div v-if="errors.email" class="invalid-feedback">{{ errors.email }}</div>
              </div>

              <!-- Mot de passe -->
              <div class="mb-3">
                <label class="form-label">Nouveau mot de passe <span class="text-muted">(laisser vide pour ne pas changer)</span></label>
                <input
                  v-model="form.password"
                  type="password"
                  class="form-control"
                  :class="{ 'is-invalid': errors.password }"
                />
                <div v-if="errors.password" class="invalid-feedback">{{ errors.password }}</div>
              </div>

              <!-- Feedback global -->
              <p v-if="successMessage" class="text-success">{{ successMessage }}</p>
              <p v-if="submitError" class="text-danger">{{ submitError }}</p>

              <!-- Actions -->
              <div class="d-flex gap-2">
                <button
                  class="btn btn-primary"
                  :disabled="submitting"
                  @click="handleSubmit"
                >
                  {{ submitting ? 'Enregistrement...' : 'Enregistrer' }}
                </button>
                <button class="btn btn-danger" @click="handleDelete">
                  Supprimer mon compte
                </button>
                <router-link :to="`/users/${pseudo}`" class="btn btn-outline-secondary">
                  Annuler
                </router-link>
              </div>
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
  name: 'UserEditPage',
  data() {
    return {
      pseudo: this.$route.params.pseudo,
      form: {
        email: '',
        password: ''
      },
      errors: {},
      submitting: false,
      successMessage: '',
      submitError: '',
      loadError: ''
    }
  },
  created() {
    const token = localStorage.getItem('token')
    if (!token) {
      this.loadError = 'Vous devez être connecté pour modifier votre profil.'
      return
    }

    // Pré-remplir avec les données actuelles
    fetch(`${USER_API.baseUrl}/${this.pseudo}`, {
      headers: { 'Authorization': `Bearer ${token}` }
    })
      .then(res => {
        if (!res.ok) throw new Error('Impossible de charger le profil.')
        return res.json()
      })
      .then(json => {
        this.form.email = json.email ?? ''
      })
      .catch(err => {
        this.loadError = err.message
      })
  },
  methods: {
    validate() {
      this.errors = {}
      if (!this.form.email) {
        this.errors.email = 'L\'email est requis.'
      } else if (!/\S+@\S+\.\S+/.test(this.form.email)) {
        this.errors.email = 'Email invalide.'
      }
      if (this.form.password && this.form.password.length < 6) {
        this.errors.password = 'Le mot de passe doit faire au moins 6 caractères.'
      }
      return Object.keys(this.errors).length === 0
    },

    handleSubmit() {
      if (!this.validate()) return

      this.submitting = true
      this.submitError = ''
      this.successMessage = ''

      const token = localStorage.getItem('token')
      const body = { email: this.form.email }
      if (this.form.password) body.password = this.form.password

      fetch(`${USER_API.baseUrl}/${this.pseudo}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(body)
      })
        .then(res => {
          if (res.status === 401 || res.status === 403) throw new Error('Non autorisé.')
          if (!res.ok) throw new Error('Erreur lors de la mise à jour.')
          return res.json()
        })
        .then(() => {
          this.successMessage = 'Profil mis à jour avec succès !'
          this.form.password = ''
        })
        .catch(err => {
          this.submitError = err.message
        })
        .finally(() => {
          this.submitting = false
        })
    },

    handleDelete() {
      if (!confirm('Êtes-vous sûr de vouloir supprimer votre compte ? Cette action est irréversible.')) return

      const token = localStorage.getItem('token')
      fetch(`${USER_API.baseUrl}/${this.pseudo}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${token}` }
      })
        .then(res => {
          if (!res.ok) throw new Error('Erreur lors de la suppression.')
          localStorage.removeItem('token')
          this.$router.push('/')
        })
        .catch(err => {
          this.submitError = err.message
        })
    }
  }
}
</script>
