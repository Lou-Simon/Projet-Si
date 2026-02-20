<template>
  <main class="app-shell">
    <Navbar />

    <section class="page-shell">
      <RouterView />
    </section>
  </main>
</template>

<script setup>
import Navbar from './components/layout/Navbar.vue'
</script>