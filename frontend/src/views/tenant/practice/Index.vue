<template>
    <div class="app" style="padding: 20px">
        <el-page-header @back="goBack" content="活动大厅">
        </el-page-header>
        <!-- 菜单 -->
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <el-input placeholder="请输入内容" v-model="input3" style="margin-bottom: 20px" class="input-with-select">
                    <el-button slot="append" icon="el-icon-search"></el-button>
                </el-input>
                <el-row :gutter="12">
                    <el-col :span="8" v-for="o in 4" :key="o" style="margin-bottom: 12px;">
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
                </el-row>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Practices",
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