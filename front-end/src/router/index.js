import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home.vue'
import NameAndAddress from '@/components/NameAndAddress.vue'
import Vehicle from '@/components/Vehicle.vue'
import Login from '../../node_modules/metaworks4/src/components/Login.vue'
import Metaworks4 from '../../node_modules/metaworks4'


/**
 * Iam && Vue Router
 * @type {IAM}
 */

var clientKey = "my-client-key";

//This required for managing user rest api (avatar upload, curl user data, etc..)
//If the client type is not public,(described in iam yaml setting) the rest api will rejected.
var clientSecret = "my-client-secret";

//window.profile will be autowired by uengine-cloud-server. It can be local,dev,stg,prod. default is 'local'.
var profile = window.profile;


//Change the url your IAM application's vcap service's profile url.
//For example, 'http://' + config.vcap.services['your-iam-server'][profile].external;
var iamUrl = 'http://iam.pas-mini.io';

//Define iam client
var iam = new IAM(iamUrl);

//Set clientKey, clientSecret(optional).
iam.setDefaultClient(clientKey, clientSecret);

//Mark in window
window.iam = iam;

let RouterGuard = require("./RouterGuard.js")(iam);
Vue.use(Router);



/**
 * Vue resource configuration
 */
let VueResource = require('vue-resource-2');
Vue.use(VueResource);
Vue.use(Metaworks4);


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
      props: {
        iamServer: 'http://iam.pas-mini.io',
        scopes: "cloud-server"
      },
      beforeEnter: RouterGuard.requireGuest
    }
  ]
})
