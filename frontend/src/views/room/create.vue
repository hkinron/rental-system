<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Room name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Room price">
        <el-input-number v-model="form.price" />
      </el-form-item>
      <el-form-item label="Room description">
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
        price: '',
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
