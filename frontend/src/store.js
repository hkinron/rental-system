import Vue from 'vue'
import Vuex from 'vuex'

// import axios from 'axios'
import {AXIOS} from './components/http-common'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    rooms: [],
    users: [],
    records: [],
    count: 1
  },
  mutations: {
    replaceRooms (state, payload) {
      state.rooms = payload
    },
    getRooms (state, payload) {
      state.rooms = payload
    },
    getUsers (state, payload) {
      state.users = payload
    },
    addRoom (state, payload) {
      state.rooms.push(payload)
    },
    addUser (state, payload) {
      state.users.push(payload)
    },
    increment (state) {
      state.count++
    }
  },
  actions: {
    increment (context) {
      context.commit('increment')
    },
    addRoom (context, payload) {
      context.commit('addRoom', payload)
    },
    addUser (context, payload) {
      context.commit('addUser', payload)
    },
    getRooms (context) {
      AXIOS.get(`/rooms`).then(response => {
        context.commit('getRooms', response.data)
      }).catch(e => {
        console.log(e)
      })
    },
    getUsers (context) {
      AXIOS.get(`/users`).then(response => {
        context.commit('getUsers', response.data)
      }).catch(e => {
        console.log(e)
      })
    }
  }
})
