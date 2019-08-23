<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Tenant name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Tenant phone">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="Tenant room">
        <el-select v-model="form.room" placeholder="please select your room">
          <el-option label="Room 1" value="401" />
          <el-option label="Room 2" value="402" />
        </el-select>
      </el-form-item>
      <el-form-item label="Tenant description">
        <el-input v-model="form.desc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
        <el-button @click="onReset">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        phone: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onReset() {
      this.$message({
        message: 'reset!',
        type: 'warning'
      })
    }
  }
}
</script>
