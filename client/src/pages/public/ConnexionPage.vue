<template>
  <section class="page container py-4">
    <div class="row justify-content-center">
      <div class="col-12 col-md-6 col-lg-4">
        <div class="card shadow-sm">
          <div class="card-body d-flex flex-column gap-3">
            <h2 class="text-center mb-0">Connexion</h2>
            <hr />

            <div>
              <label class="form-label">Email</label>
              <input v-model="email" type="email" class="form-control" placeholder="admin@vod.fr" />
            </div>

            <div>
              <label class="form-label">Mot de passe</label>
              <input v-model="password" type="password" class="form-control" placeholder="••••••••" @keyup.enter="login" />
            </div>

            <p v-if="error" class="text-danger mb-0">{{ error }}</p>

            <button class="btn btn-primary w-100" @click="login">Se connecter</button>

            <p class="text-center mb-0">
              Pas encore de compte ? <router-link to="/inscription">S'inscrire</router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import mockUsers from '@/data/users.json'

export default {
  name: 'ConnexionPage',
  data() {
    return { email: '', password: '', error: '' }
  },
  methods: {
    login() {
      const user = mockUsers.find(u => u.email === this.email && u.password === this.password)
      if (!user) { this.error = 'Email ou mot de passe incorrect.'; return }
      localStorage.setItem('token', 'mock-token-' + user.username)
      localStorage.setItem('isConnected', 'true')
      localStorage.setItem('role', user.role)
      localStorage.setItem('username', user.username)
      this.$router.push(user.role === 'ADMIN' ? '/admin' : '/')
    }
  }
}
</script>
