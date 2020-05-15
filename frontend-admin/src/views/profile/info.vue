<template>
  <div class="app-container">
    <el-form
      v-loading="formLoading"
      :data="form"
      element-loading-text="加载中..."
      :model="form"
      label-width="120px"
    >
      <el-input type="hidden" v-model="form.id" />
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="头像">
          <a href="#/profile/avatar">
            <img alt="点击修改头像" :src="form.avatar" width="60" height="60"/>
          </a>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" />
        </el-form-item>
        <el-form-item label="账号">
          <el-input v-model="form.account" :disabled="true"/>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.username" :disabled="true"/>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" :disabled="true"/>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" :disabled="true"/>
        </el-form-item>
        <el-form-item label="等级">
          <el-input v-model="form.level" :disabled="true"/>
        </el-form-item>
        <el-form-item label="组织">
          <el-input v-model="form.tenantName" :disabled="true"/>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input v-model="form.created" :disabled="true"/>
        </el-form-item>
        <el-form-item label="更新时间">
          <el-input v-model="form.created" :disabled="true"/>
        </el-form-item>
        <el-form-item label="最近登录">
          <el-input v-model="form.loginTime" :disabled="true"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存</el-button>
        </el-form-item>
      </el-form>
    </el-form>
  </div>
</template>

<script>
  import { info, update } from "@/api/profile";

  export default {
    name: "ProfileInfo",
    data() {
      return {
        formLoading: true,
        form: {
          id: '',
          username: '',
          nickname: '',
          phone: '',
          email: ''
        }
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        info(this.$store.getters.name).then(response => {
          this.form  = response.data
          this.formLoading = false
        })
      },
      onSubmit() {
        this.formLoading = true
        update(this.form).then(response => {
          this.formLoading = false
          this.$message({
            message: response.message,
            type: 'success'
          });
        }).catch(() => {
          this.formLoading = false
        })
      }
    }
  }
</script>

<style scoped>

</style>
