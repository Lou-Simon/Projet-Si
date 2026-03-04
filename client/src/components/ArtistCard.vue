<template>
	<article class="card h-100 bg-dark text-light border-secondary">
		<div class="card-body d-flex flex-column gap-2">
			<h3 class="h5 card-title mb-0">{{ props.artist.name }}</h3>

			<p class="card-text mb-1">
				{{ props.artist.biography || 'Aucune biographie disponible.' }}
			</p>

			<div class="d-flex flex-wrap gap-1 mt-auto">
				<span class="rounded-pill px-2 py-1 small bg-primary text-light">
					{{ props.artist.genre || 'Genre inconnu' }}
				</span>
			</div>

			<p class="card-text mb-0">
				<strong>Date de naissance :</strong> {{ formatDate(props.artist.birthDate) }}
			</p>
		</div>
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
