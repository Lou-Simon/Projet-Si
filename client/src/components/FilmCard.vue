<template>
	<article class="card h-100 bg-dark text-light border-secondary">
		<div class="card-body d-flex flex-column gap-2">
			<div class="d-flex justify-content-between align-items-start gap-2">
				<h3 class="h5 card-title mb-0">{{ props.movie.title }}</h3>
				<span class="rounded-pill px-2 py-1 small bg-secondary text-light">{{ props.movie.releaseYear }}</span>
			</div>

			<p class="card-text mb-0">
				<strong>Réalisateur :</strong> {{ props.movie.director || 'Non renseigné' }}
			</p>

			<div v-if="props.movie.genres?.length" class="d-flex flex-wrap gap-1 mt-auto">
				<span v-for="genre in props.movie.genres" :key="genre" class="rounded-pill px-2 py-1 small bg-primary text-light">{{ genre }}</span>
			</div>
		</div>
	</article>
</template>

<script setup>
const props = defineProps({
	movie: {
		type: Object,
		required: true
	}
})
</script>
