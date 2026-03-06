<template>
	<article class="card h-100 bg-dark text-light border-secondary">
		<div class="card-body d-flex flex-column gap-2">
			<h3 class="h5 card-title mb-0">{{ props.artist.name }}</h3>

			<p class="card-text mb-1">
				{{ displayedBiography }}
				<button
					v-if="biography.length > 100"
					class="btn btn-link btn-sm p-0 text-primary"
					@click="showFull = !showFull"
				>
					{{ showFull ? 'voir moins' : 'voir plus' }}
				</button>
			</p>
		</div>
	</article>
</template>

<script setup>
import { ref, computed } from 'vue'

const props = defineProps({
	artist: {
		type: Object,
		required: true
	}
})

const showFull = ref(false)

const biography = computed(() => props.artist.biography || 'Aucune biographie disponible.')

const displayedBiography = computed(() =>
	showFull.value || biography.value.length <= 100
		? biography.value
		: biography.value.slice(0, 100) + '...'
)
</script>
