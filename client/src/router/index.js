import { createRouter, createWebHistory } from 'vue-router'
import PublicHomePage from '../pages/PublicHomePage.vue'
import PublicFilmsPage from '../pages/PublicFilmsPage.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: PublicHomePage
    },
    {
      path: '/films/:movieSlug?',
      name: 'films',
      component: PublicFilmsPage
    }
  ]
})

export default router