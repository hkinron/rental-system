<template>
  <b-container class="room">
    <b-form-row>
      <b-col><h1>Create Room</h1></b-col>
    </b-form-row>
    <b-form-row>
      <b-col>
        <b-form-input type="text" v-model="room.name" placeholder="name"></b-form-input>
      </b-col>
      <b-col>
        <b-form-input type="text" v-model="room.price" placeholder="price"></b-form-input>
      </b-col>
      <b-col>
        <b-button variant="success" @click="createRoom()">Create Room</b-button>
      </b-col>
    </b-form-row>
    <b-form-row>
      <span v-for="item in response">id：{{ item }}</span>
    </b-form-row>
    <b-form-row>
      <b-table hover :items="rooms" :fields="fields"/>
    </b-form-row>

  </b-container>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import {mapState} from 'vuex'

  export default {
    name: 'room',

    data() {
      return {
        response: [],
        errors: [],
        room: {
          name: '',
          price: ''
        },
        fields: {
          id: {
            sortable: true
          },
          name: {
            sortable: true
          },
          price: {
            sortable: true
          },
          user: {
            key: 'user.name',
            label: 'Username',
            sortable: true
          }

        }

      }
    },
    methods: {
      // Fetches posts when the component is created.
      createRoom() {
        AXIOS.post(`/room`, this.room)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data)
            this.$store.dispatch('getRooms')
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
    },
    computed: mapState({
      // 箭头函数可使代码更简练
      rooms: state => state.rooms,   // es6写法，function (state) { return state.count }
    }),
    beforeMount: function () {
      this.$store.dispatch('getRooms')
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  div {
    padding: 10px 10px 0px 10px;
  }

</style>
