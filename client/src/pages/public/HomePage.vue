<template>
  <section class="page container py-4 text-center">
    <h1>Bienvenue, {{ username }} !</h1>
  </section>
</template>

<script>
export default {
  name: 'HomePage',
  computed: {
    username() {
      return localStorage.getItem('username') || 'utilisateur'
    }
  }
}
</script>
