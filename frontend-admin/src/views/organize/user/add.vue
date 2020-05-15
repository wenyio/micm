<template>
  <div class="app-container">
    <el-form
      v-loading="formLoading"
      :data="form"
      element-loading-text="加载中..."
      :model="form"
      label-width="120px"
    >
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="头像">
          <img alt="点击修改头像" src="https://profile.csdnimg.cn/B/4/D/2_weixin_40663067" width="60" height="60"/>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" />
        </el-form-item>
        <el-form-item label="账号">
          <el-input v-model="form.account"/>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.username"/>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.password" :show-password="true"/>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone"/>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status"></el-switch>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存</el-button>
        </el-form-item>
      </el-form>
    </el-form>
  </div>

</template>

<script>
  import { add } from "@/api/user";

  export default {
    name: "User",
    filters: {
      statusFilter(status) {
        console.log(status)
        if (status) {
          return "success"
        }
        return "danger"
      }
    },
    data() {
      return {
        formLoading: false,
        form: {
          nickname: '',
          username: '',
          password: '',
          phone: '',
          email: '',
          account: '',
          status: true,
        },
      }
    },
    created() {
    },
    methods: {
      onSubmit() {
        // console.log(this.form)
        add(this.form).then(response => {
          this.formLoading = false
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.form = {
            nickname: '',
            username: '',
            password: '',
            phone: '',
            email: '',
            account: '',
            status: true,
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
