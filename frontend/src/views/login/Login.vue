<template>
    <div style="padding: 20px;">
        <el-page-header @back="$router.push('/')" content=""></el-page-header>
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="8"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="8">
                <el-form ref="form" :model="form" label-width="80px">
                    <div style="width: 100%;display: flex;justify-content: center;">
                        <h2>用户登录</h2>
                    </div>
                    <br/><br/>
                    <el-form-item label="用户名">
                        <el-input v-model="form.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input type="password" v-model="form.password"></el-input>
                        <el-link type="warning" :underline="false" @click="$router.push('/forget')">
                            忘记密码？
                        </el-link>
                    </el-form-item>
                    <el-form-item>
                        <el-radio v-model="form.apply" label="1">《校织网用户隐私协议》</el-radio>
                        <el-radio v-model="form.apply" label="0">拒绝</el-radio>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="login_pwd">登录</el-button>
                        <el-button @click="$router.push('/reg')">注册</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    import { login, info } from '@/api/account.js'
    import { getUserQrCode } from '@/api/user.js'
    import { detailInfo } from '@/api/practice.js'

    export default {
        name: "Login",
        data() {
            return {
                form: {
                    username: '',
                    password: '',
                    apply: '1',
                }
            }
        },
        methods: {
            login_pwd() {
                if (this.form.apply == 1) {
                    login(this.form).then(res => {
                        if (res.code === 20000) {
                            localStorage.setItem("token", res.data.token)
                            // 存一个用户信息
                            this.getUserInfo()
                            getUserQrCode() // 用户的二维码
                            detailInfo()
                            this.$router.push('/')
                        } else {
                            this.$message.error(res.message)
                        }
                    })
                } else {
                    this.$message('您没有勾选协议')
                }
            },
            /**
             * 获取用户信息
             */
            getUserInfo() {
                info().then(res => {
                    if (res.code === 20000) {
                        localStorage.setItem("user", JSON.stringify(res.data))
                    } else {
                        this.$message.error(res.message)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>