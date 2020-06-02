<template>
    <div class="tenant" style="width: 98%">
        <!-- 基本信息 -->
        <el-row :gutter="20" style="margin-top: 36px;">
            <el-col :span="5"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="4">
                <div class="grid-content bg-purple">
                    <el-avatar :size="99" src="https://empty" @error="true">
                        <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                    </el-avatar>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <p><el-link :underline="false">马文艺</el-link></p>
                    <p><el-link :underline="false">软件1713</el-link></p>
                    <p><el-link :underline="false">南京工业职业技术学院</el-link></p>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <el-card class="box-card" shadow="hover">
                        <div class="count">
                            <div class="count_item">
                                <p>活动</p>
                                <p>55</p>
                            </div>
                            <div class="count_item">
                                <p>积分</p>
                                <p>55</p>
                            </div>
                            <div class="count_item">
                                <p>贡献</p>
                                <p>55</p>
                            </div>
                        </div>
                    </el-card>
                </div>
            </el-col>
            <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
        <!-- 菜单 -->
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <div class="grid-content bg-purple">
                    <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
                        <el-tab-pane label="办事大厅" name="first">
                            <!-- 快捷入口 -->
                            <div class="cricle">
                                <div class="container">
                                    <div class="box" v-for="i in 5" :key="i">
                                        <div class="img">
                                            <img src="@/assets/icos/录制.png"/>
                                        </div>
                                        <p>常用网站</p>
                                    </div>
                                    <div class="box" v-if="showAdd" @click="drawer = true">
                                        <div class="img">
                                            <i class="el-icon-circle-plus-outline" style="font-size: 50px;"></i>
                                        </div>
                                        <p v-if="sets.showTitle">添加</p>
                                    </div>
                                </div>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="活动管理" name="second">
                            <!-- 快捷入口 -->
                            <div class="cricle">
                                <div class="container">
                                    <div class="box" v-for="(menu, index) in practiceMenus" :key="index" @click="goPage(menu.route)">
                                        <div class="img">
                                            <i :class="menu.icon" style="font-size: 50px;"></i>
                                        </div>
                                        <p>{{menu.name}}</p>
                                    </div>
                                </div>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="我的活动" name="third">
                            <el-row :gutter="12">
                                <el-col :span="8" v-for="o in 4" :key="o" style="margin-bottom: 12px;">
                                    <el-card class="box-card" shadow="hover">
                                        <div v-for="o in 4" :key="o" class="text item">
                                            {{'列表内容 ' + o }}
                                        </div>
                                    </el-card>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="设置" name="fourth">
                            设置
                        </el-tab-pane>
                    </el-tabs>
                </div>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Index",
        data() {
            return {
                activeName2: 'first',
                practiceMenus: [
                    {route: '/tenant/practice/myCreate', name: '我的创建', icon: 'el-icon-guide'},
                    {route: '/tenant/practice', name: '活动大厅', icon: 'el-icon-coin'},
                    {route: '/tenant/practice/create', name: '新建活动', icon: 'el-icon-circle-plus-outline'},
                ]
            }
        },
        methods: {
            /**
             * 去页面
             */
            goPage(route){
                this.$router.push(route)
            },
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    }
</script>

<style scoped>
    .count {
        display: flex;
        justify-content: space-around;
    }
    .count_item {
        align-items: center;
        flex-direction: column;
    }
    .cricle{
        width: 100%;
        margin-top: 88px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .container{
        margin-bottom: 88px;
        width: 80%;
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
        align-items: center;
    }
    .container .box{
        display: flex;
        justify-content: space-around;
        flex-direction: column;
        align-items: center;
        align-content: center;
        width: 100px;
        height: 140px;
        margin: 20px;
    }
    .container .box .img{
        width: 100px;
        height: 160px;
        box-shadow: 18px 18px 30px rgba(0, 0, 0, 0.1),
        -18px -18px 30px rgba(255, 255, 255, 1);
        border-radius: 20px;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #efeeee;
        transition: box-shadow .2s ease-out;
        position: relative;
    }

    .container .box .img  img{
        width: 60px;
        transition: width 0.2s ease-out;
    }
    .container .box p{
        color: slategrey;
    }
    .container .box .img:hover{
        box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.2),
        0px 0px 0px rgba(255, 255, 255, 0.8),
        inset 18px 18px 30px rgba(0, 0, 0, 0.1),
        inset -18px -18px 30px rgba(255, 255, 255, 1);
        transition: box-shadow .2s ease-out;
    }
    .container .box .img:hover img{
        width: 58px;
        transition: width 0.2s ease-out;
    }
</style>