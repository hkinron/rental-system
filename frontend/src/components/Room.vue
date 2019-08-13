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
      <b-alert
        :show="dismissCountDown"
        dismissible
        variant="success"
        @dismissed="dismissCountDown=0"
        @dismiss-count-down="countDownChanged"
      >
        <p>Successfully add room: {{ this.response.name }}, closing in {{ dismissCountDown }} seconds...</p>
        <b-progress
          variant="success"
          :max="dismissSecs"
          :value="dismissCountDown"
          height="4px"
        ></b-progress>
      </b-alert>
    </b-form-row>
    <b-form-row v-if=" roomPage.content !== undefined && roomPage.content.length > 0 ">
      <b-table hover :items="roomPage.content" :fields="fields">

      </b-table>
    </b-form-row>
  </b-container>
</template>

<script>
import { AXIOS } from './http-common'
import { mapState } from 'vuex'

export default {
  name: 'room',

  data () {
    return {
      response: '',
      errors: [],
      showDismissibleAlert: false,
      dismissSecs: 5,
      dismissCountDown: 0,
      room: {
        name: '',
        price: ''
      },
      fields: {
        name: {
          sortable: true
        },
        price: {
          sortable: true
        },
        tenants: {
          key: 'tenants',
          label: 'TenantsName',
          sortable: true,
          formatter: (value, key, item) => {
            let tenantString = ''
            value.forEach(function (tenant) { tenantString += tenant.name + ' ' })
            return tenantString
          }
        }
      }

    }
  },
  methods: {
    createRoom () {
      AXIOS.post(`/room`, this.room)
        .then(response => {
          this.response = response.data
          this.showAlert()
          this.$store.dispatch('getRooms').then(response => {
            console.log('Successfully get rooms' + response)
          }).catch(exceptions => {
            console.log(exceptions)
          })
          this.room.name = ''
          this.room.price = ''
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    countDownChanged (dismissCountDown) {
      this.dismissCountDown = dismissCountDown
    },
    showAlert () {
      this.dismissCountDown = this.dismissSecs
    }
  },
  computed: mapState({
    // 箭头函数可使代码更简练
    roomPage: state => state.roomPage // es6写法，function (state) { return state.count }
  }),
  beforeMount: function () {
    this.$store.dispatch('getRooms').then(response => {
      console.log('Successfully get rooms' + response)
    })
  }
}
</script>
