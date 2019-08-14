import Vue from 'vue'
import Vuex from 'vuex'

// import axios from 'axios'
import { AXIOS } from './components/http-common'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    roomPage: [],
    tenantPage: [],
    records: []
  },
  mutations: {
    getRooms (state, payload) {
      state.roomPage = payload
    },
    getTenants (state, payload) {
      state.tenantPage = payload
    }
  },
  actions: {
    getRooms (context) {
      AXIOS.get(`/rooms`).then(response => {
        context.commit('getRooms', response.data)
      }).catch(e => {
        console.log(e)
      })
    },
    getTenants (context) {
      AXIOS.get(`/tenants`).then(response => {
        context.commit('getTenants', response.data)
      }).catch(e => {
        console.log(e)
      })
    }
  }
})
