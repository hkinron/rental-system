import Vue from 'vue'
import Router from 'vue-router'
import Tenant from './components/Tenant'
import Room from './components/Room'
import Record from './components/Record'
import Bill from './components/Bill'

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      redirect: 'room'
    },
    {
      path: '/tenant',
      name: 'Tenant',
      component: Tenant
    },
    {
      path: '/room',
      name: 'Room',
      component: Room
    },
    {
      path: '/record',
      name: 'Record',
      component: Record
    },
    {
      path: '/bill',
      name: 'Bill',
      component: Bill
    }
  ]
})
