<template>
  <section class="pt-7 pb-4">
    <div class="container">
      <div class="hero-card rounded-4 p-4 p-lg-5 mb-4">
        <div class="d-flex flex-wrap gap-2 mb-3">
          <span class="badge rounded-pill text-bg-light">Accès public</span>
          <span class="badge rounded-pill badge-soft">Sans connexion</span>
        </div>

        <h1 class="display-6 fw-bold mb-3 text-dark">Découvre le catalogue VOD avant de te connecter</h1>

        <p class="mb-4 text-secondary fs-5">
          Tu peux explorer les films, artistes, réalisateurs, genres et évaluations.
          La réservation et les commentaires restent accessibles après connexion.
        </p>

        <div class="d-flex flex-wrap gap-2">
          <RouterLink class="btn btn-dark rounded-pill px-4" to="/films">Voir les films</RouterLink>
          <button class="btn btn-outline-dark rounded-pill px-4">Parcourir les artistes</button>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.hero-card {
  background: linear-gradient(145deg, #ffffff, #eef4ff);
  border: 1px solid #dbe6ff;
  box-shadow: 0 18px 45px rgba(78, 106, 166, 0.16);
}

.badge-soft {
  background: #dfe9ff;
  color: #29406d;
}

.pt-7 {
  padding-top: 8rem;
}
</style>
