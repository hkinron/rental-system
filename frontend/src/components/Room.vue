<template>
  <div class="room">
    <h1>Create Room</h1>

    <input type="text" v-model="room.name" placeholder="name">
    <input type="text" v-model="room.price" placeholder="price">

    <button @click="createRoom()">Create Room</button>

    <button @click="retrieveAllRooms()">Retrieve room data from database</button>

    <ul id="rooms">
      <li
        v-for="room in rooms"
        :key="room.id"
      >
        {{ room.id }} + {{ room.name }} + {{ room.price }}
      </li>
    </ul>

  </div>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    name: 'room',

    data() {
      return {
        response: [],
        errors: [],
        room: {
          name: '',
          price: '',
          id: 0
        },
        rooms: []
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createRoom() {
        var params = new URLSearchParams()
        params.append('name', this.room.name)
        params.append('price', this.room.price)

        AXIOS.post(`/room`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            this.room.id = response.data
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      retrieveAllRooms() {
        AXIOS.get(`/rooms`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.rooms = response.data
            this.$emit('rooms', this.rooms)
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
    },
    beforeMount() {
      this.retrieveAllRooms()
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
