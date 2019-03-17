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
        <b-form-select v-model="user.room">
          <!-- This slot appears above the options from 'options' prop -->
          <template slot="first">
            <option :value="null" disabled>-- Please select an option --</option>
          </template>
          These options will appear after the ones from 'options' prop
          <option v-for="room in this.rooms" :value="room"
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
      <span v-for="item in response">id：{{ item }}</span>
    </b-form-row>
    <b-form-row>
      <b-table hover :items="users"/>
    </b-form-row>

  </b-container>

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import {mapState} from 'vuex'

  export default {
    name: 'user',
    // props: {
    //   rooms: Array
    // },
    data() {
      return {
        response: [],
        errors: [],
        user: {
          name: '',
          phone: '',
          roomId: ''
        }
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createUser() {
        AXIOS.post(`/user`, this.user)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data)
            this.user.id = response.data
            this.$store.dispatch('getUsers')
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
      users: state => state.users
    }),
    mounted: function(){
      this.$store.dispatch('getUsers')
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

div {
  padding: 10px 10px 0px 10px;
}

</style>
