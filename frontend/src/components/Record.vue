<template>
  <b-container class="record">
    <b-form-row>
      <b-col><h1>Create Record</h1></b-col>
      <b-col>
        <b-form-input type="date" v-model="recordDate"/>
      </b-col>
    </b-form-row>
    <b-form-row v-for="record in records">
      <b-col>
        <b-form-input aria-disabled="true" v-model="record.roomId"/>
      </b-col>
      <b-col>
        <b-form-input type="text" v-model="record.water" placeholder="water"/>
      </b-col>
      <b-col>
        <b-form-input type="text" v-model="record.electric" placeholder="electric"/>
      </b-col>
    </b-form-row>
    <b-form-row>
      <b-col md="auto">
        <b-button variant="success" @click="createRecord()">Create Records</b-button>
      </b-col>
    </b-form-row>
    <br/>
  </b-container>

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'
  import Room from "@/components/Room";

  export default {
    props: {
      rooms: Array
    },
    data() {
      return {
        response: [],
        errors: [],
        records: [],
        recordDate: '',
        record: {
          roomId: '',
          water: '',
          electric: '',
        }
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createRecord() {
        var params = new URLSearchParams()
        params.append('roomId', this.record.roomId)
        params.append('water', this.record.water)
        params.append('electric', this.user.electric)

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
      initRecords() {
        for ( let i = 0; i < this.rooms.length; i++ ) {
          let record = {
            roomId: this.rooms[i].id,
            water: '',
            electric: '',
          }
          this.records.push(record)
        }
      }
    },
    beforeMount() {
      this.initRecords()
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
