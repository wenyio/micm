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
                        <el-input v-model="form.email"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码">
                        <el-input type="password" v-model="form.newPassword"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码">
                        <el-input style="width: 55%" v-model="form.captcha"></el-input>
                        <el-button icon="el-icon-mobile-phone" @click="send" style="width: 33%" type="primary" :disabled="disabled=!show" >
                            <span v-show="show">获取验证码</span>
                            <span v-show="!show" class="count">{{count}} s</span>
                        </el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-radio v-model="form.apply" label="1">《校织网用户隐私协议》</el-radio>
                        <el-radio v-model="form.apply" label="0">拒绝</el-radio>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="forget">提交</el-button>
                        <el-button @click="$router.push('/login')">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    import { forgetPassword, emailCode } from '@/api/account.js'
    const TIME_COUNT = 60; //更改倒计时时间

    export default {
        name: "Forget",
        data() {
            return {
                show: true,  // 初始启用按钮
                count: '',   // 初始化次数
                timer: null,
                form: {
                    "email": "",
                    "captcha": "",
                    "newPassword": "",
                    "apply": "1"
                }
            }
        },
        methods: {
            forget() {
                if (this.form.apply == 1) {
                    forgetPassword(this.form).then(res => {
                        if (res.code === 20000) {
                            this.$message.success(res.message)
                            this.$router.push('/login')
                        } else {
                            this.$message.error(res.message)
                        }
                    })
                } else {
                    this.$message('您没有勾选协议')
                }
            },
            send(){
                if (!this.timer) {
                    this.count = TIME_COUNT;
                    this.show = false;
                    this.timer = setInterval(() => {
                        if (this.count > 0 && this.count <= TIME_COUNT) {
                            this.count--;
                        } else {
                            this.show = true;
                            clearInterval(this.timer);  // 清除定时器
                            this.timer = null;
                        }
                    }, 1000)

                    let data = {
                        email: this.form.email
                    }

                    // 发送验证码
                    emailCode(data).then(res =>{
                        if(res.code === 20000){
                            this.$message.success(res.message)
                        } else {
                            this.$message.error(res.message)
                        }
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>