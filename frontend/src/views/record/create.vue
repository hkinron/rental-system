<template>
  <div class="app-container">
    <div class="block">
      <span class="demonstration">Month</span>
      <el-date-picker
        v-model="date"
        type="month"
        placeholder="Pick a month"
        @change="changeDate"
      />
    </div>
    <el-form v-for="(room, index) in list" :key="room.id" ref="form" :model="forms[index]" label-width="120px" :inline="true">
      <el-button type="text"> Room {{ forms[index].room.name }} </el-button>
      <el-form-item label="Water">
        <el-input v-model="forms[index].water" />
      </el-form-item>
      <el-form-item label="Electric">
        <el-input v-model="forms[index].electric" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit(index)">Create</el-button>
        <el-button @click="onReset(index)">Reset</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="onSubmit">Create all</el-button>
    <el-button @click="onReset">Reset all</el-button>
  </div>
</template>

<script>
import { getList } from '@/api/room'
import moment from 'moment'
export default {
  data() {
    return {
      date: new Date(),
      list: null,
      forms: [],
      form: {
        room: '',
        electric: '',
        water: '',
        yearMonth: ''
      }
    }
  },
  beforeMount () {
    this.fetchData()
  },
  methods: {
    initForms(length) {
      for (let i = 0; i < length; i++) {
        const form = {
          room: '',
          water: '',
          electric: '',
          yearMonth: ''
        }
        this.forms.push(form)
      }
    },
    fetchData() {
      this.listLoading = true
      getList().then(response => {
        this.list = response.data.content
        for (let i = 0; i < this.list.length; i++) {
          const tmpRecord = {
            room: this.list[i],
            water: '',
            electric: '',
            yearMonth: moment(String(this.date)).format('YYYY-MM')
          }
          this.forms.push(tmpRecord)
        }
        this.listLoading = false
      })
    },
    changeDate() {
      for (let i = 0; i < this.forms.length; i++) {
        this.forms[i].yearMonth = moment(String(this.date)).format('YYYY-MM')
      }
    },
    onSubmit(index) {
      this.$message('submit!' + index)
    },
    onReset(index) {
      this.$message({
        message: 'reset!' + index,
        type: 'warning'
      })
    }
  }
}
</script>
