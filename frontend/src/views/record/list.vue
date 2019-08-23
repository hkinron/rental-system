<template>
  <div class="app-container">
    <div class="block">
      <span class="demonstration">Month</span>
      <el-date-picker
        v-model="date"
        type="month"
        placeholder="Pick a month">
      </el-date-picker>
    </div>
    <div class="table">
      <el-table
        v-loading="listLoading"
        :data="list"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column align="center" label="ID" width="95">
          <template slot-scope="scope">
            {{ scope.$index }}
          </template>
        </el-table-column>
        <el-table-column label="Room" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.room.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Water" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.water }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Electric" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.electric }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Date" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.yeahMonth }}</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { getList } from '@/api/record'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      date: new Date(),
      list: null,
      listLoading: true
    }
  },
  created() {
    this.fetchData('{"yeahMonth": this.date}')
  },
  methods: {
    fetchData(date) {
      this.listLoading = true
      getList(date).then(response => {
        this.list = response.data.content
        this.listLoading = false
      })
    }
  }
}
</script>
