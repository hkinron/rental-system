<template>
  <b-container class="tenant">
    <b-form-row>
      <b-col><h1>Create User</h1></b-col>
    </b-form-row>
    <b-form-row>
      <b-col>
        <b-form-input type="text" v-model="tenant.name" placeholder="name"/>
      </b-col>
      <b-col>
        <b-form-input type="text" v-model="tenant.phone" placeholder="phone"/>
      </b-col>
      <b-col>
        <b-form-select v-model="tenant.room">
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
      <b-col v-if="editUserFlag == true" md="auto">
        <b-button variant="success" @click="createUser()">Update User</b-button>
      </b-col>
      <b-col v-else md="auto">
        <b-button variant="success" @click="createUser()">Create User</b-button>
      </b-col>
    </b-form-row>
    <b-form-row>
      <span v-for="item in response">id：{{ item }}</span>
    </b-form-row>
    <b-form-row>
      <b-table hover :items="users" :fields="fields">
        <template slot="id" slot-scope="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <a href="javascript:;" @click="editUser(data.value)"><span class="icon-pencil"></span>编辑</a>
          <a href="javascript:;" @click="deleteUser(data.value.id)"><span class="icon-cancel-circle"></span>删除</a>
        </template>
      </b-table>
    </b-form-row>

  </b-container>

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import {mapState} from 'vuex'

  export default {
    name: 'tenant',
    // props: {
    //   rooms: Array
    // },
    data() {
      return {
        response: [],
        errors: [],
        tenant: {
          id: '',
          name: '',
          phone: ''
        },
        editUserFlag: false,
        fields: [
          // {
          //   key: "id",
          //   label: 'Id'
          // },
          "name",
          {
            key: 'phone',
            label: '电话号码'
          },
          {
            key: 'id',
            label: '操作',
            formatter: (value, key, item) => {
              return item
            }
          }
        ]
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createUser() {
        AXIOS.post(`/tenant`, this.tenant)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data);
            this.$store.dispatch('getUsers');
            this.tenant.id = '';
            this.tenant.name = '';
            this.tenant.phone = '';
            this.tenant.room = '';
            this.editUserFlag = false;
            console.log(response.data)
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      editUser (tenant){
        this.tenant = tenant
        this.editUserFlag = true
      },
      deleteUser (userid){

        AXIOS.delete(`/tenant`, { params:{'userId': userid}})
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data);
            this.$store.dispatch('getUsers');
            this.tenant.name = '';
            this.tenant.phone = '';
            this.tenant.room = '';
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
