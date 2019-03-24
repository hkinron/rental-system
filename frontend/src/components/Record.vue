<template>
  <b-container class="record">
    <b-form-row>
      <b-col><h1>Create Record</h1></b-col>
      <b-col>
        <b-form-input type="date" v-model="recordDate" @change="updateRecordsDate()"/>
      </b-col>
    </b-form-row>
    <b-form-row v-for="record in records" :key="record.room.id">
      <b-col>
        <b-form-input aria-disabled="true" v-model="record.room.name"/>
      </b-col>
      <b-col>
        <b-form-input type="text" v-model="record.water" placeholder="water"/>
      </b-col>
      <b-col>
        <b-form-input type="text" v-model="record.electric" placeholder="electric"/>
      </b-col>
    </b-form-row>
    <b-form-row>
      <b-col v-show="records.length > 0 " md="auto">
        <b-button variant="success" @click="createRecord()">Create</b-button>
      </b-col>
    </b-form-row>
    <b-form-row>
        <span v-for="item in response">{{item}} 插入成功</span>
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
        recordDate: '',
        records: [],
        recordsInYearMonth: []
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createRecord() {

        AXIOS.post(`/records`, this.records)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data);
            this.initRecords();
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      getRecord() {
        let params = new URLSearchParams();
        params.append('yearMonth', this.recordDate.split('-')[0] + '-' + this.recordDate.split('-')[1]);
        AXIOS.get(`/records`, {'params': params})
          .then(response => {
            // JSON responses are automatically parsed.
            this.response.push(response.data);
            this.recordsInYearMonth = response.data;
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      initRecords() {
        this.records = [];
        for (let i = 0; i < this.rooms.length; i++) {
          let record = {
            room: this.rooms[i],
            water: '',
            electric: '',
            yearMonth: '',
          };
          this.records.push(record)
        }
      },
      updateRecordsDate() {
        for (let i = 0; i < this.records.length; i++) {
          this.records[i].yearMonth = this.recordDate.split('-')[0]+ '-' +this.recordDate.split('-')[1]
        }
      }
    },
    mounted: function () {
      this.initRecords()
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
