<template>
  <b-container class="record">
    <b-form-row>
      <b-col><h1>Create Bill</h1></b-col>
      <b-col>
        <b-form-input type="date" v-model="billDate" @change="getBills()"/>
      </b-col>
    </b-form-row>

    <!--<b-form-row>-->
      <!--<b-table hover :items="bills" :fields="fields"/>-->
    <!--</b-form-row>-->


    <b-form-row v-show="bills.length > 0">
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>Room</th><th>User</th><th>Description</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="bill in bills" :key="bill.nowRecord.id" >
            <td>{{ bill.nowRecord.room.name }}</td>
            <td>{{ bill.nowRecord.room.user.name }}</td>
            <td>电上月示数：{{bill.lastRecord.electric}},本月：{{bill.nowRecord.electric}}<br>
                水上月示数：{{bill.lastRecord.water}},本月：{{bill.nowRecord.water}}<br>
                房租：{{bill.nowRecord.room.price}}元<br>
                总费用：{{ bill.sum }}元</td>
          </tr>

        </tbody>
      </table>
    </b-form-row>

  </b-container>

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import {mapState} from 'vuex'

  const waterfee = 4;
  const electicfee = 1.3

  export default {
    data() {
      return {
        response: [],
        errors: [],
        billDate: '',
        bills: [],
        fields: {
          nowRecord: {
            key: 'nowRecord.room.name',
            label: 'Room',
            sortable: true
          },
          lastRecord: {
            key: 'lastRecord.room.user.name',
            label: 'User',
            sortable: true
          },
          sum: {
            sortable: true
          }

        }
      }
    },
    methods: {
      getBills() {
        let params = new URLSearchParams()
        params.append('yearMonth', this.billDate.split('-')[0] + '-' + this.billDate.split('-')[1])
        AXIOS.get(`/bills`, {'params': params})
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data)
            this.bills = response.data;
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
    },
    computed: mapState({
      rooms: state => state.rooms,
    })
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  div {
    padding: 10px 10px 0px 10px;
  }
</style>
