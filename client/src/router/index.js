import { createRouter, createWebHistory } from 'vue-router'
import FilmsPage from '@/pages/public/FilmsPage.vue'
import ArtistsPage from '@/pages/public/ArtistsPage.vue'
import GenresPage from '@/pages/public/GenresPage.vue'
import ReservationsPage from '@/pages/public/ReservationsPage.vue'
import EvaluationsPage from '@/pages/public/EvaluationsPage.vue'
import ConnexionPage from '@/pages/public/ConnexionPage.vue'
import InscriptionPage from '@/pages/public/InscriptionPage.vue'
import AdminPage from '@/pages/admin/AdminPage.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/films' },
    { path: '/films', name: 'films', component: FilmsPage },
    { path: '/acteurs-realisateurs', name: 'artists', component: ArtistsPage },
    { path: '/genres', name: 'genres', component: GenresPage },
    { path: '/reservations', name: 'reservations', component: ReservationsPage },
    { path: '/evaluations', name: 'evaluations', component: EvaluationsPage },
    { path: '/connexion', name: 'connexion', component: ConnexionPage },
    { path: '/inscription', name: 'inscription', component: InscriptionPage },
    { path: '/admin', name: 'admin', component: AdminPage }
  ]
})

export default router