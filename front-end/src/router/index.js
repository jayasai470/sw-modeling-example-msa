import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home.vue'
import NameAndAddress from '@/components/NameAndAddress.vue'
import Vehicle from '@/components/Vehicle.vue'
import Login from '@/components/Login.vue'

var iam = new IAM('http://iam.uengine.io:8080');
iam.setDefaultClient('e74a9505-a811-407f-b4f6-129b7af1c703','109cf590-ac67-4b8c-912a-913373ada046');

let RouterGuard = require("./RouterGuard.js")(iam);
Vue.use(Router);


/**
 * Vue resource configuration
 */
let VueResource = require('vue-resource-2');
Vue.use(VueResource);


Vue.component('home', Home);
Vue.component('name-and-address', NameAndAddress);
Vue.component('vehicle', Vehicle);


export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/name-and-address',
      name: 'home',
      component: Home,
      props: {iam: iam},
      children: [
        {
          path: 'name-and-address',
          name: 'Name and Address',
          component: NameAndAddress,
          beforeEnter: RouterGuard.requireUser,
        },
        {
          path: 'vehicle',
          name: 'Vehicles',
          component: Vehicle,
          beforeEnter: RouterGuard.requireUser,
        },
      ]
    },
    {
      path: '/auth/:command',
      name: 'login',
      component: Login,
      props: {iam: iam},
      beforeEnter: RouterGuard.requireGuest
    }
  ]
})
