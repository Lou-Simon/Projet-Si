<script setup>
const navItems = [
  { label: 'Accueil', to: '/' },
  { label: 'Films', to: '/films' },
  { label: 'Artistes', to: '#' },
  { label: 'Réalisateurs', to: '#' },
  { label: 'Genres', to: '#' },
  { label: 'Évaluations', to: '#' }
]
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-light navbar-glass fixed-top py-3">
    <div class="container">
      <RouterLink class="navbar-brand d-flex align-items-center gap-2" to="/">
        <span class="logo-dot"></span>
        <span class="fw-semibold text-dark">VOD UBO</span>
      </RouterLink>

      <button
        class="navbar-toggler border-0 shadow-none"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#vodNavbar"
        aria-controls="vodNavbar"
        aria-expanded="false"
        aria-label="Basculer la navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div id="vodNavbar" class="collapse navbar-collapse">
        <ul class="navbar-nav mx-auto mb-2 mb-lg-0 gap-lg-2">
          <li v-for="item in navItems" :key="item.label" class="nav-item">
            <RouterLink
              v-if="item.to !== '#'"
              class="nav-link"
              :to="item.to"
              active-class="active"
              exact-active-class="active"
            >
              {{ item.label }}
            </RouterLink>
            <a v-else class="nav-link" href="#">{{ item.label }}</a>
          </li>
        </ul>

        <div class="d-flex align-items-center gap-2 mt-2 mt-lg-0 ms-lg-3">
          <button class="btn btn-outline-dark btn-sm px-3 rounded-pill fw-semibold">Connexion</button>
          <button class="btn btn-gradient btn-sm px-3 rounded-pill fw-semibold">Inscription</button>
        </div>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar-glass {
  background: rgba(255, 255, 255, 0.72);
  backdrop-filter: blur(10px);
  border-bottom: 1px solid rgba(28, 45, 88, 0.1);
}

.logo-dot {
  width: 12px;
  height: 12px;
  border-radius: 999px;
  background: linear-gradient(135deg, #2979ff, #5e35b1);
  box-shadow: 0 0 12px rgba(41, 121, 255, 0.55);
}

.btn-gradient {
  border: 0;
  color: #fff;
  background: linear-gradient(135deg, #2979ff, #5e35b1);
}

.btn-gradient:hover {
  color: #fff;
  opacity: 0.94;
}
</style>
