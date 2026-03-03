<template>
	<article class="film-card">
		<div class="film-card__top">
			<h3 class="film-card__title">{{ props.artist.name }}</h3>
		</div>

		<p class="film-card__description">
			{{ props.artist.biography || 'Aucune biographie disponible.' }}
		</p>

		<div class="film-card__meta">
			<span class="badge">
				{{ props.artist.genre || 'Genre inconnu' }}
			</span>
		</div>

		<p class="film-card__line">
			<strong>Date de naissance :</strong>
			{{ formatDate(props.artist.birthDate) }}
		</p>
	</article>
</template>

<script setup>
const props = defineProps({
	artist: {
		type: Object,
		required: true
	}
})

function formatDate(date) {
	if (!date) return 'Non renseignée'
	return new Date(date).toLocaleDateString('fr-FR')
}
</script>