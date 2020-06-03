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
                        <el-button v-if="!isApply" type="warning" @click="apply" round>报名</el-button>
                        <el-button v-if="isApply" type="danger" @click="apply" round>取消报名</el-button>
                    </el-card>
                </div>
                <el-card shadow="never" style="margin-top: 20px;">
                    <div style="display: flex;justify-content: space-around;align-items: center;">
                        <div style="width: 60%;">
                            <h4>活动标题</h4>
                            <p>
                                <el-link :underline="false">
                                    地点：{士大夫士大夫士大夫}
                                </el-link>
                            </p>
                            <p>
                                <el-link :underline="false">
                                    时间：2020-02-08 80.20 - 2020-02-08 80.20
                                </el-link>
                            </p>
                        </div>
                        <div style="width: 18%;">
                            <el-tooltip class="item" effect="light" content="扫码签到，点击放大" placement="left">
                                <img @click="bigCode = !bigCode" width="166px;" src="@/assets/logo.png" alt="点击放大"/>
                            </el-tooltip>
                        </div>
                    </div>
                    <el-divider></el-divider>
                    <div style="padding: 0 60px">
                        <h5>简介：</h5>
                        <p>sfsdfsdfffffffffffffffffffffffffffffffffffffffffffffffff</p>
                        <div v-if="bigCode" @click="bigCode = !bigCode" style="width: 100%;display: flex;justify-content: center;">
                            <img src="@/assets/logo.png" width="52%">
                        </div>
                    </div>
                    <el-divider></el-divider>
                    <div style="display: flex;justify-content: space-around;align-items: center;">
                        <div style="width: 38%;">
                            <p>
                                <el-link :underline="false">
                                    组织：{士大夫士大夫士大夫}
                                </el-link>
                            </p>
                        </div>
                        <div style="width: 38%;">
                            <p>
                                <el-link :underline="false">
                                    {xxxx}版权所有
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
    export default {
        name: "PracticeDetail",
        components: {},
        data() {
            return {
                bigCode: false,
                isApply: false, //是否报名
            }
        },
        methods: {
            apply() {
                if (this.isApply) {
                    // 已报名， 做取消操作
                    this.isApply = false // 请求成功
                } else {
                    // 未报名， 做报名操作
                    this.isApply = true // 请求成功
                }
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