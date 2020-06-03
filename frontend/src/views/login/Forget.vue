<template>
    <div style="padding: 20px;">
        <el-page-header @back="$router.push('/')" content=""></el-page-header>
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="8"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="8">
                <el-form ref="form" :model="form" label-width="80px">
                    <div style="width: 100%;display: flex;justify-content: center;">
                        <h2>找回密码</h2>
                    </div>
                    <br/>
                    <el-form-item label="邮箱">
                        <el-input v-model="form.username"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码">
                        <el-input type="password" v-model="form.newPassword"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码">
                        <el-input type="password" v-model="form.captcha"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-radio v-model="form.apply" label="1">《校织网用户隐私协议》</el-radio>
                        <el-radio v-model="form.apply" label="0">拒绝</el-radio>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary">提交</el-button>
                        <el-button @click="$router.push('/login')">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Forget",
        data() {
            return {
                form: {
                    "email": "",
                    "captcha": "",
                    "newPassword": "",
                    "apply": "1"
                }
            }
        }
    }
</script>

<style scoped>

</style>