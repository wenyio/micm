<template>
  <div class="app-container">
    <el-form
      v-loading="formLoading"
      :data="form"
      element-loading-text="加载中..."
      :model="form"
      label-width="120px"
    >
      <el-form-item label="旧密码">
        <el-input type="password" v-model="form.oldPassword" show-password="true"/>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input type="password" v-model="form.newPassword" show-password="true"/>
      </el-form-item>
      <el-form-item label="确认">
        <el-input type="password" v-model="newPassword1" show-password="true"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { modifyPassword } from "@/api/profile";

  export default {
    name: "ProfilePassword",
    data() {
      return {
        formLoading: false,
        form: {
          username: this.$store.getters.name,
          newPassword: '',
          oldPassword: ''
        },
        newPassword1: ''
      }
    },
    methods: {
      onSubmit() {
        this.formLoading = true
        // 输入框为空
        if (this.form.newPassword == '' || this.form.oldPassword == '') {
          this.formLoading = false
          this.$message({
            message: '输入框不能为空',
            type: 'warning'
          });
        }

        // 两次密码相同
        else if (this.newPassword1 === this.form.newPassword) {
          // 请求
          modifyPassword(this.form).then(response => {
            this.formLoading = false
            this.$message({
              message: response.message,
              type: 'success'
            })
          }).catch(() => {
            this.formLoading = false
          })
        }

        // 两次密码不同
        else {
          this.formLoading = false
          this.$message({
            message: '两次密码不同',
            type: 'warning'
          });
        }
      }
    }
  }
</script>

<style scoped>

</style>
