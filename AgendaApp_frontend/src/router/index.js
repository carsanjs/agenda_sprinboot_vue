import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'HomeView',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/registrocontacto',
      name: 'RegistroContacto',
      component: () => import('../views/RegistroContacto.vue')
    },
    {
      path: '/login',
      name: 'LoginOn',
      component: () => import('../views/LoginOn.vue')
    },
    {
      path: '/registronotas',
      name: 'RegistroNotas',
      component: () => import('../views/RegistroNotas.vue')
    },
    {
      path: '/registrar',
      name: 'Registrarse',
      component: () => import('../views/Registrarse.vue')
    },
    {
      path: '/recuperar',
      name: 'Recuperar',
      component: () => import('../views/Recuperar.vue')
    },
    {
      path: '/misnotas',
      name: 'MisNotas',
      component: () => import('../views/MisNotas.vue')
    }
  ]
})

export default router
