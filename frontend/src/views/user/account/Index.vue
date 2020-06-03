<template>
    <div style="padding: 20px">
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <!-- 菜单 -->
                <el-tabs tab-position="left" style="height: 520px;">
                    <el-tab-pane label="个人信息">
                        <el-row :gutter="12">
                            <el-scrollbar
                                    wrap-class="list" wrap-style="color: red; height:480px;"
                                    view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 480px;"
                                    :native="false">
                                <el-col :span="2">.</el-col>
                                <el-col :span="8" style="margin-bottom: 12px;">
                                    <el-form ref="form" :model="form" label-width="80px">
                                        <el-form-item label="头像">
                                            <el-avatar style="margin: 20px" :size="80" :src="form.avatar" @error="true">
                                                <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                                            </el-avatar>
                                        </el-form-item>
                                        <el-form-item label="昵称">
                                            <el-input v-model="form.nickname"></el-input>
                                        </el-form-item>
                                        <el-form-item label="用户名">
                                            <el-input v-model="form.username"></el-input>
                                        </el-form-item>
                                        <el-form-item label="邮箱">
                                            <el-input v-model="form.email" disabled></el-input>
                                        </el-form-item>
                                        <el-form-item label="手机">
                                            <el-input v-model="form.phone" disabled></el-input>
                                        </el-form-item>
                                        <el-form-item>
                                            <el-button type="primary" @click="updateInfo">保存</el-button>
                                            <el-button >重置</el-button>
                                        </el-form-item>
                                    </el-form>
                                </el-col>
                            </el-scrollbar>
                        </el-row>
                    </el-tab-pane>
                    <el-tab-pane label="更改密码">
                        <el-row :gutter="12">
                            <el-scrollbar
                                    wrap-class="list" wrap-style="color: red; height:500px;"
                                    view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 500px;"
                                    :native="false">
                                <el-col :span="2">.</el-col>
                                <el-col :span="8" style="margin-bottom: 12px;">
                                    <el-form ref="form" :model="mPswForm" label-width="80px">
                                        <el-form-item label="用户名">
                                            <el-input v-model="mPswForm.username"></el-input>
                                        </el-form-item>
                                        <el-form-item label="原始密码">
                                            <el-input type="password" v-model="mPswForm.oldPassword"></el-input>
                                        </el-form-item>
                                        <el-form-item label="新密码">
                                            <el-input type="password" v-model="mPswForm.newPassword"></el-input>
                                        </el-form-item>
                                        <el-form-item>
                                            <el-button type="primary" @click="updatePassword">保存</el-button>
                                            <el-button>重置</el-button>
                                        </el-form-item>
                                    </el-form>
                                </el-col>
                            </el-scrollbar>
                        </el-row>
                    </el-tab-pane>
                    <el-tab-pane label="邮箱绑定">
                        <el-row :gutter="12">
                            <el-scrollbar
                                    wrap-class="list" wrap-style="color: red; height:500px;"
                                    view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 500px;"
                                    :native="false">
                                <el-col :span="2">.</el-col>
                                <el-col :span="8" style="margin-bottom: 12px;">
                                    <el-form ref="form" :model="mEmForm" label-width="80px">
                                        <el-form-item label="用户名">
                                            <el-input v-model="mEmForm.username" disabled></el-input>
                                        </el-form-item>
                                        <el-form-item label="邮箱">
                                            <el-input v-model="mEmForm.email"></el-input>
                                        </el-form-item>
                                        <el-form-item label="验证码">
                                            <el-input style="width: 55%" v-model="mEmForm.captcha"></el-input>
                                            <el-button icon="el-icon-mobile-phone" @click="send" style="width: 40%" type="primary" :disabled="disabled=!show" >
                                                <span v-show="show">验证码</span>
                                                <span v-show="!show" class="count">{{count}} s</span>
                                            </el-button>
                                        </el-form-item>
                                        <el-form-item>
                                            <el-button type="primary" @click="updateEmail">保存</el-button>
                                            <el-button>重置</el-button>
                                        </el-form-item>
                                    </el-form>
                                </el-col>
                            </el-scrollbar>
                        </el-row>
                    </el-tab-pane>
                    <el-tab-pane label="租户绑定">租户管理</el-tab-pane>
                    <el-tab-pane label="设置">
                        <el-row :gutter="12">
                            <el-scrollbar
                                    wrap-class="list" wrap-style="color: red; height:500px;"
                                    view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 500px;"
                                    :native="false">
                                <el-col :span="2">.</el-col>
                                <el-col :span="8" style="margin-bottom: 12px;">
                                    <el-button>登出</el-button>
                                </el-col>
                            </el-scrollbar>
                        </el-row>
                    </el-tab-pane>
                </el-tabs>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    import {update, modifyPassword, modifyEmail, getUserInfo, emailCode} from "@/api/account";
    const TIME_COUNT = 60; //更改倒计时时间

    export default {
        name: "Index",
        created() {
            this.form = JSON.parse(localStorage.getItem('user'))
            this.mPswForm.username = this.form.username
            this.mEmForm.username = this.form.username
        },
        data() {
            return {
                show: true,  // 初始启用按钮
                count: '',   // 初始化次数
                timer: null,
                form: {
                    id: null,
                    avatar: 'http://micm.oss-cn-shanghai.aliyuncs.com/068446f8-cd2c-43c5-a414-391ceda16639.jpg',
                    nickname: '',
                    username:'',
                    email: null,
                    phone: null,
                },
                mPswForm: {
                    username: '',
                    oldPassword: '',
                    newPassword: ''
                },
                mEmForm: {
                    username: '',
                    captcha: '',
                    email: ''
                }
            }
        },
        methods: {
            updateInfo() {
                update(this.form).then(res => {
                    if (res.code == 20000) {
                        getUserInfo()
                        this.$message.success(res.message)
                    }
                })
            },
            updatePassword() {
                modifyPassword(this.mPswForm).then(res => {
                    if (res.code == 20000) {
                        this.$message.success(res.message)
                    }
                })
            },
            updateEmail() {
                modifyEmail(this.mEmForm).then(res => {
                    if (res.code == 20000) {
                        this.$message.success(res.message)
                    }
                })
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
                        email: this.mEmForm.email
                    }
                    console.log(data)

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