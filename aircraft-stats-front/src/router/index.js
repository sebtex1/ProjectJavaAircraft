import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../pages/HomePage.vue'
import History from '../pages/HistoryPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/history',
    name: 'History',
    component: History
  },
]

const router = new VueRouter({
  routes
})

export default router