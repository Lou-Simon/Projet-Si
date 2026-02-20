<script setup>
import PublicNavbar from './components/layout/PublicNavbar.vue'
</script>

<template>
  <main class="app-shell min-vh-100">
    <div class="bg-blob bg-blob-left"></div>
    <div class="bg-blob bg-blob-right"></div>

    <PublicNavbar />
    <RouterView />
  </main>
</template>

<style scoped>
.app-shell {
  position: relative;
  overflow: hidden;
  background: linear-gradient(180deg, #f7f9ff 0%, #edf3ff 40%, #f8fbff 100%);
}

.bg-blob {
  position: absolute;
  border-radius: 999px;
  filter: blur(25px);
  z-index: 0;
}

.bg-blob-left {
  top: 6rem;
  left: -7rem;
  width: 18rem;
  height: 18rem;
  background: rgba(93, 135, 255, 0.2);
}

.bg-blob-right {
  top: 3rem;
  right: -6rem;
  width: 20rem;
  height: 20rem;
  background: rgba(104, 225, 253, 0.18);
}

.app-shell :deep(.container),
.app-shell :deep(nav) {
  position: relative;
  z-index: 1;
}
</style>