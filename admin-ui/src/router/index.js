import Vue from 'vue'
import VueRouter from 'vue-router'

import Admin from '../views/Admin.vue'
import Landscaper from '../views/Landscaper.vue'
import Registry from '../views/Registry.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Admin, 
  },
  {
    path: '/landscaper',
    component: Landscaper, 
  },
  {
    path: '/registry',
    component: Registry, 
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
