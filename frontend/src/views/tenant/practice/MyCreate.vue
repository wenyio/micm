<template>
    <div class="app" style="padding: 20px">
        <el-page-header @back="goBack" content="我的创建">
        </el-page-header>
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <!-- 菜单 -->
                <el-tabs tab-position="left" style="height: 510px;">
                    <el-tab-pane label="全部">
                        <el-row :gutter="12">
                            <el-scrollbar
                                    wrap-class="list" wrap-style="color: red; height:500px;"
                                    view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 500px;"
                                    :native="false">
                                <el-col :span="8" v-for="(o, index) in list" v-show="o.userId == user.id" :key="index" style="margin-bottom: 12px;">
                                    <el-card class="box-card" shadow="hover">
                                        <div style="display: flex;justify-content: space-around;align-items: center">
                                            <div style="width: 90px">
                                                <img :src="o.icon" width="88px" alt="">
                                            </div>
                                            <div style="width: 60%;">
                                                <h4>{{o.title}} <el-tag size="mini">{{o.integral}}分</el-tag></h4>
                                                <p><el-link :underline="false">{{o.begin}}</el-link></p>
                                                <p><el-link :underline="false" @click="detail(o.id)">查看详情</el-link></p>
                                            </div>
                                        </div>
                                    </el-card>
                                </el-col>
                            </el-scrollbar>
                        </el-row>
                    </el-tab-pane>
                    <el-tab-pane label="已结束">
                        <el-row :gutter="12">
                            <el-scrollbar
                                    wrap-class="list" wrap-style="color: red; height:500px;"
                                    view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 500px;"
                                    :native="false">
                                <el-col :span="8" v-for="(o, index) in list" v-show="o.userId == user.id &&  Date.parse(o.begin) < new Date().getTime()" :key="index" style="margin-bottom: 12px;">
                                    <el-card class="box-card" shadow="hover">
                                        <div style="display: flex;justify-content: space-around;align-items: center">
                                            <div style="width: 90px">
                                                <img :src="o.icon" width="88px" alt="">
                                            </div>
                                            <div style="width: 60%;">
                                                <h4>{{o.title}} <el-tag size="mini">{{o.integral}}分</el-tag></h4>
                                                <p><el-link :underline="false">{{o.begin}}</el-link></p>
                                                <p><el-link :underline="false" @click="detail(o.id)">查看详情</el-link></p>
                                            </div>
                                        </div>
                                    </el-card>
                                </el-col>
                            </el-scrollbar>
                        </el-row>
                    </el-tab-pane>
                    <el-tab-pane label="未开始">
                        <el-row :gutter="12">
                            <el-scrollbar
                                    wrap-class="list" wrap-style="color: red; height:500px;"
                                    view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 500px;"
                                    :native="false">
                                <el-col :span="8" v-for="(o, index) in list" v-show="o.userId == user.id && Date.parse(o.begin) > new Date().getTime()" :key="index" style="margin-bottom: 12px;">
                                    <el-card class="box-card" shadow="hover">
                                        <div style="display: flex;justify-content: space-around;align-items: center">
                                            <div style="width: 90px">
                                                <img :src="o.icon" width="88px" alt="">
                                            </div>
                                            <div style="width: 60%;">
                                                <h4>{{o.title}} <el-tag size="mini">{{o.integral}}分</el-tag></h4>
                                                <p><el-link :underline="false">{{o.begin}}</el-link></p>
                                                <p><el-link :underline="false" @click="detail(o.id)">查看详情</el-link></p>
                                            </div>
                                        </div>
                                    </el-card>
                                </el-col>
                            </el-scrollbar>
                        </el-row>
                    </el-tab-pane>
                    <el-tab-pane label="未开始">
                        <el-row :gutter="12">
                            <el-scrollbar
                                    wrap-class="list" wrap-style="color: red; height:500px;"
                                    view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 500px;"
                                    :native="false">
                                <el-col :span="8" v-for="(o, index) in list" v-show="o.userId == user.id && o.status == 0" :key="index" style="margin-bottom: 12px;">
                                    <el-card class="box-card" shadow="hover">
                                        <div style="display: flex;justify-content: space-around;align-items: center">
                                            <div style="width: 90px">
                                                <img :src="o.icon" width="88px" alt="">
                                            </div>
                                            <div style="width: 60%;">
                                                <h4>{{o.title}} <el-tag size="mini">{{o.integral}}分</el-tag></h4>
                                                <p><el-link :underline="false">{{o.begin}}</el-link></p>
                                                <p><el-link :underline="false" @click="detail(o.id)">查看详情</el-link></p>
                                            </div>
                                        </div>
                                    </el-card>
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
    import { list } from '@/api/practice';

    export default {
        name: "PracticeMyCreate",
        created() {
            this.listPractice()
            this.user = JSON.parse(localStorage.getItem('user'))
        },
        data() {
            return {
                list: [],
                user: null
            }
        },
        methods: {
            listPractice() {
                list().then(res => {
                    if (res.code === 20000) {
                        this.list = res.data
                        // console.log(this.list)
                    }
                })
            },
            detail(id){
                this.$router.push({ path: '/tenant/practice/detail', query: { id: id }});
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