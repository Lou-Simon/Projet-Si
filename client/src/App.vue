<script setup>
import { ref, onMounted } from 'vue'

const film = ref(null)

onMounted(async () => {
  const response = await fetch('http://localhost:8004/movies/fiasco')
  film.value = await response.json()
})
</script>

<template>
  <h1>Bienvenue sur l'application les jeunes</h1>
  <p>Affichage des infos du film "fiasco" gnehein <= cariou</p>

  <div v-if="film">
    <h2>{{ film.title }}</h2>
    <p>{{ film.overview }}</p>
    <p><strong>Date de sortie :</strong> {{ film.releaseDate }}</p>
    <img
      v-if="film.posterPath"
      :src="`https://image.tmdb.org/t/p/w300${film.posterPath}`"
      :alt="film.title"
    />
  </div>
</template>

<style scoped></style>