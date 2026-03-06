<template>
	<header class="sticky-top bg-dark bg-opacity-75 border-bottom border-secondary-subtle py-2 px-3">
		<nav class="navbar navbar-expand-md navbar-dark">
			<RouterLink to="/" class="navbar-brand fw-bold">LSM</RouterLink>

			<button
				class="navbar-toggler"
				type="button"
				@click="isMenuOpen = !isMenuOpen"
				:aria-expanded="isMenuOpen"
				aria-controls="main-navbar"
				aria-label="Ouvrir le menu"
			>
				<span class="navbar-toggler-icon"></span>
			</button>

			<div id="main-navbar" class="collapse navbar-collapse justify-content-end" :class="{ show: isMenuOpen }">
				<ul class="navbar-nav flex-wrap gap-1">

					<!-- Non connecté -->
					<template v-if="!isConnected">
						<li class="nav-item">
							<RouterLink to="/films" class="nav-link px-3" active-class="active">Films</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink to="/acteurs-realisateurs" class="nav-link px-3" active-class="active">Artistes</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink to="/evaluations" class="nav-link px-3" active-class="active">Évaluations</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink to="/connexion" class="nav-link px-3 border rounded" active-class="active">Connexion</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink to="/inscription" class="nav-link px-3 border rounded" active-class="active">Inscription</RouterLink>
						</li>
					</template>

					<!-- Admin -->
					<template v-else-if="isAdmin">
						<li class="nav-item">
							<RouterLink to="/admin" class="nav-link px-3" active-class="active">Admin Films</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink to="/admin/artistes" class="nav-link px-3" active-class="active">Admin Artistes</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink to="/deconnexion" class="nav-link px-3 border rounded" active-class="active">Déconnexion</RouterLink>
						</li>
					</template>

					<!-- User connecté -->
					<template v-else>
						<li class="nav-item">
							<span class="nav-link px-3">Bonjour, {{ username }}</span>
						</li>
						<li class="nav-item">
							<RouterLink to="/deconnexion" class="nav-link px-3 border rounded" active-class="active">Déconnexion</RouterLink>
						</li>
					</template>

				</ul>
			</div>
		</nav>
	</header>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'

const isMenuOpen = ref(false)
const isConnected = ref(false)
const isAdmin = ref(false)
const username = ref('')
const route = useRoute()

const updateAuthState = () => {
	isConnected.value = Boolean(localStorage.getItem('token') || localStorage.getItem('isConnected'))
	isAdmin.value = localStorage.getItem('role') === 'ADMIN'
	username.value = localStorage.getItem('username') || ''
}

watch(
	() => route.fullPath,
	() => {
		updateAuthState()
		isMenuOpen.value = false
	}
)

updateAuthState()
</script>
