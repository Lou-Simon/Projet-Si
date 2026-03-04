<template>
	<header class="navbar-wrap">
		<nav class="navbar">
			<RouterLink to="/" class="brand">LSM</RouterLink>

			<button
				class="menu-btn"
				type="button"
				@click="isMenuOpen = !isMenuOpen"
				:aria-expanded="isMenuOpen"
				aria-label="Ouvrir le menu"
			>
				<span></span>
				<span></span>
				<span></span>
			</button>

			<div class="links" :class="{ open: isMenuOpen }">
				<template v-if="!isConnected">
					<RouterLink to="/films" class="link" active-class="active">Films</RouterLink>
					<RouterLink to="/acteurs-realisateurs" class="link" active-class="active">Artistes</RouterLink>
					<RouterLink to="/evaluations" class="link" active-class="active">Évaluations</RouterLink>
					<RouterLink to="/connexion" class="link auth" active-class="active">Connexion</RouterLink>
					<RouterLink to="/inscription" class="link auth" active-class="active">Inscription</RouterLink>
				</template>

				<template v-else>
					<RouterLink to="/genres" class="link" active-class="active">Genres</RouterLink>
					<RouterLink to="/reservations" class="link" active-class="active">Réservations</RouterLink>
					<RouterLink to="/admin" class="link" active-class="active">Admin</RouterLink>
					<RouterLink to="/deconnexion" class="link auth" active-class="active">Déconnexion</RouterLink>
				</template>
			</div>
		</nav>
	</header>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'

const isMenuOpen = ref(false)
const isConnected = ref(false)
const route = useRoute()
const BYPASS_AUTH_FOR_DEV = true

const updateAuthState = () => {
	isConnected.value = BYPASS_AUTH_FOR_DEV || Boolean(localStorage.getItem('token') || localStorage.getItem('isConnected'))
}

watch(
	() => route.fullPath,
	() => {
		updateAuthState()
	}
)

updateAuthState()
</script>
