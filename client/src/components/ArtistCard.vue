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

		<div class="container">
        <img src="mr_bios.jpg" alt="Publicité Mr. Bios - L'alternative écologique" class="ad-image">
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

<style>
        .container {
            display: flex;
            justify-content: center;
            padding: 50px;
        }
        .ad-image {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        }
    </style>
