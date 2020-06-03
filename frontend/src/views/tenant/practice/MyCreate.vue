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
                                <el-col :span="8" v-for="o in 9" :key="o" style="margin-bottom: 12px;">
                                    <el-card class="box-card" shadow="hover">
                                        <div style="display: flex;justify-content: space-around;align-items: center">
                                            <div style="width: 90px">
                                                <img src="@/assets/logo.png" width="88px" alt="">
                                            </div>
                                            <div style="width: 60%;">
                                                <h4>标题 <el-tag size="mini">类型</el-tag></h4>
                                                <p><el-link :underline="false">2020-20-20 25.20</el-link></p>
                                                <p><el-link :underline="false">查看详情</el-link></p>
                                            </div>
                                        </div>
                                    </el-card>
                                </el-col>
                            </el-scrollbar>
                        </el-row>
                    </el-tab-pane>
                    <el-tab-pane label="未开始">配置管理</el-tab-pane>
                    <el-tab-pane label="已结束">角色管理</el-tab-pane>
                    <el-tab-pane label="未发布">角色管理</el-tab-pane>
                </el-tabs>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "PracticeMyCreate",
        methods: {
            goBack() {
                this.$router.back(-1)
                console.log('go back');
            }
        }
    }
</script>

<style scoped>

</style>