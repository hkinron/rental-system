<template>
  <b-container class="user">
    <b-form-row>
      <b-col><h1>Create User</h1></b-col>
    </b-form-row>
    <b-form-row>
      <b-col>
        <b-form-input type="text" v-model="user.name" placeholder="name"/>
      </b-col>
      <b-col>
        <b-form-input type="text" v-model="user.phone" placeholder="phone"/>
      </b-col>
      <b-col>
        <b-form-select v-model="user.roomId">
          <!-- This slot appears above the options from 'options' prop -->
          <template slot="first">
            <option :value="null" disabled>-- Please select an option --</option>
          </template>
           These options will appear after the ones from 'options' prop
          <option v-for="room in rooms" :value="room.id"
                  :key="room.id">
            {{ room.name }}
          </option>
        </b-form-select>
      </b-col>
      <b-col md="auto">
        <b-button variant="success" @click="createUser()">Create User</b-button>
      </b-col>
    </b-form-row>

    <b-form-row>
      <b-col>
        <b-button variant="success" @click="retrieveAllUsers()">Retrieve all user data from database</b-button>
      </b-col>
    </b-form-row>

    <b-form-row>
      <b-table hover :items="users"/>
    </b-form-row>
  </b-container>

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import Room from "@/components/Room";

  export default {
    name: 'user',
    props: {
      rooms: Array
    },
    data() {
      return {
        response: [],
        errors: [],
        user: {
          name: '',
          phone: '',
          roomId: ''
        },
        users: [],
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createUser() {
        var params = new URLSearchParams()
        params.append('name', this.user.name)
        params.append('phone', this.user.phone)
        params.append('roomId', this.user.roomId)

        AXIOS.post(`/user`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            this.user.id = response.data
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      retrieveAllUsers() {
        AXIOS.get(`/users`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.users = response.data
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
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
