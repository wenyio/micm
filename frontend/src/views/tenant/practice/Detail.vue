<template>
    <div class="app" style="padding: 20px;width: 96%;">
        <el-page-header @back="goBack" content="活动详情">
        </el-page-header>
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <!-- 活动详情 -->
                <div style="display: inline-block;position: sticky;top: 66px;">
                    <el-card shadow="always" style="background-color:white;margin-right:0;display:flex;border-radius: 20px">
                        <i class="el-icon-menu" style="margin-right: 20px"></i>
                        <el-button type="primary" @click="bigCode = !bigCode" round>签到</el-button>
                        <el-button v-if="isApply == 0" type="warning" @click="apply" round>报名</el-button>
                        <el-button v-if="isApply == 1" type="danger" @click="notApply" round>取消报名</el-button>
                    </el-card>
                </div>
                <el-card shadow="never" style="margin-top: 20px;">
                    <div style="display: flex;justify-content: space-around;align-items: center;">
                        <div style="width: 60%;">
                            <h4>{{detail.title}}</h4>
                            <p>
                                <el-link :underline="false">
                                    地点：{{detail.address}}
                                </el-link>
                            </p>
                            <p>
                                <el-link :underline="false">
                                    时间：{{detail.begin}} - {{detail.end}}
                                </el-link>
                            </p>
                        </div>
                        <div style="width: 18%;">
                            <el-tooltip class="item" effect="light" content="扫码签到，点击放大" placement="left">
                                <img @click="bigCode = !bigCode" width="166px;" :src="detail.icon" alt="点击放大"/>
                            </el-tooltip>
                        </div>
                    </div>
                    <el-divider></el-divider>
                    <div style="padding: 0 60px">
                        <h5>简介：</h5>
                        <p>{{detail.description}}</p>
                        <div v-if="bigCode" @click="bigCode = !bigCode" style="width: 100%;display: flex;justify-content: center;">
                            <img :src="practiceQrCode" width="52%"/>
                        </div>
                    </div>
                    <el-divider></el-divider>
                    <div style="display: flex;justify-content: space-around;align-items: center;">
                        <div style="width: 38%;">
                            <p>
                                <el-link :underline="false">
                                    组织：{{detail.organization}}
                                </el-link>
                            </p>
                        </div>
                        <div style="width: 38%;">
                            <p>
                                <el-link :underline="false">
                                    {{detail.organization}}版权所有
                                </el-link>
                            </p>
                        </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    import { getById, judge, apply, notApply, getQrCode } from '@/api/practice.js'

    export default {
        name: "PracticeDetail",
        components: {},
        created() {
            this.detail.id = this.$route.query.id
            this.user = JSON.parse(localStorage.getItem('user'))
            this.getById()
        },
        data() {
            return {
                bigCode: false,
                isApply: 0, //是否报名 0未报名 1报名
                detail: {},
                practiceQrCode: '',
                user: {}
            }
        },
        methods: {
            getById() {
                getById(this.detail.id).then(res => {
                    if (res.code === 20000) {
                        this.detail = res.data
                        this.getQrCode()
                        this.judge()
                        // console.log(this.detail)
                    }
                })

            },
            getQrCode(){
                getQrCode(this.detail.id).then(res => {
                    if (res.code === 20000) {
                        this.practiceQrCode = res.data.file
                        // console.log(this.practiceQrCode)
                    }
                })
            },
            /**
             * 验证是否报名活动
             */
            judge() {
                // console.log(this.isApply, this.detail.userId, this.user.id)
                if (this.detail.userId == this.user.id) {
                    this.isApply = -1
                    return
                }
                judge(this.detail.id).then(res => {
                    // 已报名
                    if (res.code === 20000) {
                        this.isApply = 1
                    }
                })
            },
            /**
             * 报名活动
             */
            apply() {
                apply(this.detail.id).then(res => {
                    // 报名成功
                    if (res.code === 20000) {
                        this.isApply = 1
                        this.$message.success(res.message)
                    }
                })
            },
            /**
             * 取消报名
             */
            notApply() {
                notApply(this.detail.id).then(res => {
                    // 取消报名成功
                    if (res.code === 20000) {
                        this.isApply = 0
                        this.$message.success(res.message)
                    }
                })
            },
            goBack() {
                this.$router.back(-1)
                console.log('go back');
            }
        }
    }
</script>

<style scoped>

</style>