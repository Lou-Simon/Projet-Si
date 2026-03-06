import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/pages/public/HomePage.vue'
import FilmsPage from '@/pages/public/FilmsPage.vue'
import ArtistsPage from '@/pages/public/ArtistsPage.vue'
import GenresPage from '@/pages/public/GenresPage.vue'
import ReservationsPage from '@/pages/public/ReservationsPage.vue'
import EvaluationsPage from '@/pages/public/EvaluationsPage.vue'
import ConnexionPage from '@/pages/public/ConnexionPage.vue'
import InscriptionPage from '@/pages/public/InscriptionPage.vue'
import AdminPage from '@/pages/admin/AdminPage.vue'
import AdminArtistsPage from '@/pages/admin/AdminArtistsPage.vue'
import UsersListPage from '@/pages/public/UsersListPage.vue'
import UserProfilePage from '@/pages/public/UserProfilePage.vue'
import UserEditPage from '@/pages/admin/UserEditPage.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: HomePage },
    { path: '/films',                  name: 'films',         component: FilmsPage },
    { path: '/acteurs-realisateurs',   name: 'artists',       component: ArtistsPage },
    { path: '/genres',                 name: 'genres',        component: GenresPage },
    { path: '/reservations',           name: 'reservations',  component: ReservationsPage },
    { path: '/evaluations',            name: 'evaluations',   component: EvaluationsPage },
    { path: '/connexion',              name: 'connexion',     component: ConnexionPage },
    { path: '/inscription',            name: 'inscription',   component: InscriptionPage },
    {
      path: '/deconnexion',
      name: 'deconnexion',
      beforeEnter: () => {
        localStorage.removeItem('token')
        localStorage.removeItem('isConnected')
        return '/connexion'
      }
    },
    { path: '/admin',          name: 'admin',         component: AdminPage,        meta: { adminOnly: true } },
    { path: '/admin/artistes', name: 'admin-artists', component: AdminArtistsPage, meta: { adminOnly: true } },
    { path: '/users',                  name: 'users',        component: UsersListPage },
    { path: '/users/:pseudo',          name: 'user-profile', component: UserProfilePage },
    { path: '/users/:pseudo/edit',     name: 'user-edit',    component: UserEditPage }
  ]
})

router.beforeEach((to) => {
  if (to.meta.adminOnly && localStorage.getItem('role') !== 'ADMIN') {
    return '/connexion'
  }
})

export default router