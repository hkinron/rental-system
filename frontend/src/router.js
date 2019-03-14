import Vue from 'vue'
import Router from 'vue-router'
import Hello from './components/Hello'
import User from './components/User'
import Room from "./components/Room"
import Record from "./components/Record"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/user',
      name: 'User',
      component: User,
      props: true
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
    }
  ]
})
