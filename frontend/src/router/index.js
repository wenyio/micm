import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

  const routes = [
    {
      path: '/(home|)',
      name: 'Home',
      component: Home
    },
    {
      path: '/about',
      name: 'About',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import(/* webpackChunkName: "about" */ '../views/login/Login')
    },
    {
      path: '/reg',
      name: 'Reg',
      component: () => import(/* webpackChunkName: "about" */ '../views/login/Reg')
    },
    {
      path: '/forget',
      name: 'Forget',
      component: () => import(/* webpackChunkName: "about" */ '../views/login/Forget')
    },
    {
      path: '/service',
      name: 'Service',
      component: () => import(/* webpackChunkName: "about" */ '../views/service/Index')
    },
    {
      path: '/service/feedback',
      name: 'ServiceFeedback',
      component: () => import(/* webpackChunkName: "about" */ '../views/service/FeedBack')
    },
    {
      path: '/tenant',
      name: 'Tenant',
      component: () => import(/* webpackChunkName: "about" */ '../views/tenant/Index')
    },
    {
      path: '/user',
      name: 'User',
      component: () => import(/* webpackChunkName: "about" */ '../views/user/Index')
    },
    {
      path: '/user/schedule',
      name: 'UserAccount',
      component: () => import(/* webpackChunkName: "about" */ '../views/user/Schedule')
    },
    {
      path: '/user/message',
      name: 'UserAccount',
      component: () => import(/* webpackChunkName: "about" */ '../views/user/Message')
    },
    {
      path: '/user/collect',
      name: 'UserAccount',
      component: () => import(/* webpackChunkName: "about" */ '../views/user/Collect')
    },
    {
      path: '/user/account',
      name: 'UserAccount',
      component: () => import(/* webpackChunkName: "about" */ '../views/user/account/Index')
    },
    {
      path: '/user/account/set',
      name: 'AccountSet',
      component: () => import(/* webpackChunkName: "about" */ '../views/user/account/Set')
    },
]

const router = new VueRouter({
  routes
})

export default router
