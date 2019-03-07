<template>
  <div class="room">
    <h1>Create Room</h1>

    <input type="text" v-model="room.name" placeholder="name">
    <input type="text" v-model="room.price" placeholder="price">

    <button @click="createRoom()">Create Room</button>

    <div v-if="showResponse"><h6>Room created with Id: {{ response }}</h6></div>

    <button @click="retrieveAllRooms()">Retrieve room data from database</button>

    <ul id="rooms">
      <li
        v-for="room in retrievedRooms"
        :key="room.id"
      >
        {{ room.id }} + {{ room.name }} + {{ room.price }} + {{ room }}
      </li>
    </ul>

  </div>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    name: 'room',

    data () {
      return {
        response: [],
        errors: [],
        room: {
          name: '',
          price: '',
          id: 0
        },
        showResponse: false,
        retrievedRoom: {},
        retrievedRooms: {},
        showRetrievedRoom: false
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createRoom () {
        var params = new URLSearchParams()
        params.append('name', this.room.name)
        params.append('price', this.room.price)

        AXIOS.post(`/room`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            this.room.id = response.data
            console.log(response.data)
            this.showResponse = true
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      retrieveRoom () {
        AXIOS.get(`/room/` + this.room.id)
          .then(response => {
            // JSON responses are automatically parsed.
            this.retrievedUser = response.data
            console.log(response.data)
            this.showRetrievedRoom = true
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      retrieveAllRooms () {
        AXIOS.get(`/rooms`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.retrievedRooms = response.data
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    /*display: inline-block;*/
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
