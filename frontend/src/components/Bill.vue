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
      <table class="table table-hover">
        <thead>
          <tr>
            <th>Room</th><th>Description</th><th>Pay</th>
          </tr>
        </thead>
        <tbody>
          <!--<div v-for="bill in bills" :key="bill.nowRecord.id" >-->
            <tr v-for="bill in bills" :key="bill.nowRecord.id" >
              <td class="cell">{{ bill.nowRecord.room.name }}<br>
                  -----------------<br>
                  {{ bill.nowRecord.room.user == null ? null : bill.nowRecord.room.user.name }}<br>
                  -----------------<br>
                  {{ bill.nowRecord.room.user == null ? null : bill.nowRecord.room.user.phone }}
              </td>
              <td id="description" class="cell">
                  本次收取{{ billDate.split('-')[1] }}月份租金及上月份水电<br>
                  电：上月示数{{bill.lastRecord.electric}}度，本月{{bill.nowRecord.electric}}度，电费：{{( bill.nowRecord.electric - bill.lastRecord.electric) * electricFee}}元<br>
                  水：上月示数{{bill.lastRecord.water}}吨，本月{{bill.nowRecord.water}}吨，水费：{{( bill.nowRecord.water - bill.lastRecord.water) * waterFee}}元<br>
                  房租：{{bill.nowRecord.room.price}}元<br>
                  总费用：{{ bill.sum }}元
              </td>
              <td class="cell">
                <img class="alipay" src="../assets/alipay.jpg">
              </td>
            </tr>
            <!--<tr>-->
              <!--<td>{{ bill.nowRecord.room.user.name }}</td>-->
            <!--</tr>-->
          <!--</div>-->
        </tbody>
      </table>
    </b-form-row>

  </b-container>

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import {mapState} from 'vuex'


  export default {
    data() {
      return {
        response: [],
        errors: [],
        billDate: '',
        bills: [],
        waterFee : 4,
        electricFee : 1.3,
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

  td {
    text-align: center;
  }

  #description {
    text-align: left;
  }

  .alipay {
    width: 55%;
  }

  .cell {
    display: table-cell;
    vertical-align: middle;
    text-align: center;
    border:1px solid ;
    font-size: x-large;
  }
</style>
