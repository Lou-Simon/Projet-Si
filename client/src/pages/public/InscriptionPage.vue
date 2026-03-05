<template>
  <section class="page container py-4">
    <div class="row justify-content-center">
      <div class="col-12 col-md-6 col-lg-4">
        <div class="card shadow-sm">
          <div class="card-body d-flex flex-column gap-3">
            <h2 class="text-center mb-0">Inscription</h2>
            <hr />

            <!-- Message serveur down -->
            <div v-if="serverDown" class="alert alert-warning d-flex align-items-center gap-2" role="alert">
              <span style="font-size: 1.5rem;">🚧</span>
              <div>
                <strong>Service indisponible</strong><br />
                Le serveur d'authentification est inaccessible. Veuillez réessayer dans quelques instants.
              </div>
            </div>

            <template v-else>
              <!-- Username -->
              <div>
                <label class="form-label">Nom d'utilisateur</label>
                <input
                  v-model="form.username"
                  type="text"
                  class="form-control"
                  :class="{ 'is-invalid': errors.username }"
                  placeholder="le bios"
                />
                <div v-if="errors.username" class="invalid-feedback">{{ errors.username }}</div>
              </div>

              <!-- Email -->
              <div>
                <label class="form-label">Email</label>
                <input
                  v-model="form.email"
                  type="email"
                  class="form-control"
                  :class="{ 'is-invalid': errors.email }"
                  placeholder="user@example.com"
                />
                <div v-if="errors.email" class="invalid-feedback">{{ errors.email }}</div>
              </div>

              <!-- Mot de passe -->
              <div>
                <label class="form-label">Mot de passe</label>
                <input
                  v-model="form.password"
                  type="password"
                  class="form-control"
                  :class="{ 'is-invalid': errors.password }"
                  placeholder="••••••••"
                />
                <div v-if="errors.password" class="invalid-feedback">{{ errors.password }}</div>
              </div>

              <!-- Confirmation -->
              <div>
                <label class="form-label">Confirmer le mot de passe</label>
                <input
                  v-model="form.passwordConfirm"
                  type="password"
                  class="form-control"
                  :class="{ 'is-invalid': errors.passwordConfirm }"
                  placeholder="••••••••"
                  @keyup.enter="handleSubmit"
                />
                <div v-if="errors.passwordConfirm" class="invalid-feedback">{{ errors.passwordConfirm }}</div>
              </div>

              <!-- Erreur globale -->
              <p v-if="submitError" class="text-danger mb-0">{{ submitError }}</p>

              <!-- Bouton -->
              <button
                class="btn btn-primary w-100"
                :disabled="submitting"
                @click="handleSubmit"
              >
                {{ submitting ? 'Inscription en cours...' : "S'inscrire" }}
              </button>

              <p class="text-center mb-0">
                Déjà un compte ?
                <router-link to="/connexion">Se connecter</router-link>
              </p>
            </template>

          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { AUTH_API } from '@/config/api'

export default {
  name: 'InscriptionPage',
  data() {
    return {
      form: { username: '', email: '', password: '', passwordConfirm: '' },
      errors: {},
      submitting: false,
      submitError: '',
      serverDown: false
    }
  },
  methods: {
    validate() {
      this.errors = {}
      if (!this.form.username || this.form.username.trim().length < 2) {
        this.errors.username = "Le nom d'utilisateur doit faire au moins 2 caractères."
      }
      if (!this.form.email) {
        this.errors.email = "L'email est requis."
      } else if (!/\S+@\S+\.\S+/.test(this.form.email)) {
        this.errors.email = "Email invalide."
      }
      if (!this.form.password || this.form.password.length < 6) {
        this.errors.password = "Le mot de passe doit faire au moins 6 caractères."
      }
      if (this.form.password !== this.form.passwordConfirm) {
        this.errors.passwordConfirm = "Les mots de passe ne correspondent pas."
      }
      return Object.keys(this.errors).length === 0
    },

    isServerError(status) {
      return [500, 502, 503, 504].includes(status)
    },

    handleSubmit() {
      if (!this.validate()) return

      this.submitting = true
      this.submitError = ''
      this.serverDown = false

      fetch(`${AUTH_API.baseUrl}${AUTH_API.register}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          username: this.form.username,
          email: this.form.email,
          password: this.form.password
        })
      })
        .then(response => {
          if (this.isServerError(response.status)) { this.serverDown = true; return null }
          if (response.status === 409) throw new Error('Un compte avec cet email existe déjà.')
          if (response.status === 400) throw new Error('Données invalides.')
          if (!response.ok) throw new Error("Erreur lors de l'inscription.")
          return response.json()
        })
        .then(data => {
          if (!data) return
          // Connexion automatique après inscription
          return fetch(`${AUTH_API.baseUrl}${AUTH_API.login}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: this.form.email, password: this.form.password })
          })
        })
        .then(response => {
          if (!response) return null
          if (this.isServerError(response.status)) { this.serverDown = true; return null }
          if (!response.ok) { this.$router.push('/connexion'); return null }
          return response.json()
        })
        .then(data => {
          if (!data) return
          localStorage.setItem('token', data.accessToken)
          localStorage.setItem('isConnected', 'true')
          this.$router.push('/admin')
        })
        .catch(err => {
          if (err instanceof TypeError) { this.serverDown = true; return }
          this.submitError = err.message
        })
        .finally(() => { this.submitting = false })
    }
  }
}
</script>