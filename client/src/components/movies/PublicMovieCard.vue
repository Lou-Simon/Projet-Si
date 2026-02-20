<script setup>
defineProps({
  data: {
    type: Object,
    default: null
  },
  error: {
    type: Object,
    default: null
  }
})
</script>

<template>
  <section class="pb-5">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-12 col-md-10 col-lg-8 col-xl-7">
          <div v-if="error" class="alert alert-danger shadow-sm" role="alert">
            Impossible de charger le film.
          </div>

          <div v-else-if="data" class="card border-0 shadow-lg overflow-hidden">
            <div class="card-header bg-dark text-white p-4">
              <p class="text-uppercase small mb-2 opacity-75">Fiche film publique</p>
              <h2 class="h3 mb-0">{{ data.title }}</h2>
            </div>

            <div class="card-body p-4 p-md-5">
              <p class="lead mb-4 text-secondary">{{ data.description }}</p>

              <div class="list-group list-group-flush mb-4">
                <div class="list-group-item px-0 d-flex justify-content-between align-items-center">
                  <span class="fw-semibold">Année de sortie</span>
                  <span>{{ data.releaseYear }}</span>
                </div>

                <div class="list-group-item px-0 d-flex justify-content-between align-items-center">
                  <span class="fw-semibold">Réalisateur</span>
                  <span>{{ data.director || 'Non renseigné' }}</span>
                </div>

                <div class="list-group-item px-0 d-flex justify-content-between align-items-center">
                  <span class="fw-semibold">Note moyenne</span>
                  <span class="badge text-bg-warning">{{ data.rating }}/10</span>
                </div>
              </div>

              <div class="d-flex flex-wrap gap-2">
                <button class="btn btn-dark rounded-pill px-4">Voir les évaluations</button>
                <button
                  class="btn btn-outline-secondary rounded-pill px-4"
                  disabled
                  title="Connexion requise"
                >
                  Réserver (connexion requise)
                </button>
              </div>
            </div>
          </div>

          <div v-else class="card border-0 shadow-sm">
            <div class="card-body p-4 text-center text-secondary">Chargement du film...</div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
