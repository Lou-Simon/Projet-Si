<template>
	<article class="film-card">
		<div class="film-card__top">
			<h3 class="film-card__title">{{ props.movie.title }}</h3>
			<span class="film-card__year">{{ props.movie.releaseYear }}</span>
		</div>

		<p class="film-card__description">
			{{ props.movie.description || 'Aucune description disponible.' }}
		</p>

		<div class="film-card__meta">
			<span class="badge">
				⭐
				{{
					typeof props.movie.rating === 'number'
						? `${props.movie.rating.toFixed(1)}/5`
						: 'N/A'
				}}
			</span>
		</div>

		<p class="film-card__line">
			<strong>Réalisateur :</strong> {{ props.movie.director }}
		</p>

		<p class="film-card__line">
			<strong>Acteurs :</strong>
			{{
				props.movie.actors?.length
					? props.movie.actors.length <= 3
						? props.movie.actors.join(', ')
						: `${props.movie.actors.slice(0, 3).join(', ')}...`
					: 'Non renseigné'
			}}
		</p>

		<div class="film-card__genres">
			<span v-for="genre in props.movie.genres" :key="genre" class="genre-chip">{{ genre }}</span>
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
