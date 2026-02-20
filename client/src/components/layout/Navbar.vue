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
				<RouterLink to="/" class="link" exact-active-class="active">Accueil</RouterLink>
				<RouterLink to="/films" class="link" active-class="active">Films</RouterLink>
				<RouterLink to="/acteurs-realisateurs" class="link" active-class="active">Artistes</RouterLink>
				<RouterLink to="/genres" class="link" active-class="active">Genres</RouterLink>
				<RouterLink to="/reservations" class="link" active-class="active">Réservations</RouterLink>
				<RouterLink to="/evaluations" class="link" active-class="active">Évaluations</RouterLink>
				<RouterLink to="/connexion" class="link auth" active-class="active">Connexion</RouterLink>
				<RouterLink to="/inscription" class="link auth" active-class="active">Inscription</RouterLink>
			</div>
		</nav>
	</header>
</template>

<script setup>
import { ref } from 'vue'

const isMenuOpen = ref(false)
</script>
