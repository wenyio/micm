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
                                    <div class="box" v-for="(menu, index) in sets.hallMenu" :key="index">
                                        <div class="img">
<!--                                            <img src="@/assets/icos/录制.png"/>-->
                                            <i :class="menu.icon" style="font-size: 50px;"></i>
                                        </div>
                                        <p>{{menu.name}}</p>
                                    </div>
                                    <div class="box" @click="activeName2 = 'fourth'" v-if="sets.showAdd">
                                        <div class="img">
                                            <i class="el-icon-circle-plus-outline" style="font-size: 50px;"></i>
                                        </div>
                                        <p>添加</p>
                                    </div>
                                </div>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="活动管理" name="second">
                            <!-- 快捷入口 -->
                            <div class="cricle">
                                <div class="container">
                                    <div class="box" v-for="(menu, index) in sets.practiceMenus" :key="index" @click="goPage(menu.route)">
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
                                        <div style="display: flex;justify-content: space-around;align-items: center">
                                            <div style="width: 90px">
                                                <img src="@/assets/logo.png" width="88px" alt="">
                                            </div>
                                            <div style="width: 60%;">
                                                <h4>标题</h4>
                                                <el-tag size="mini">超小标签</el-tag>
                                                <p><el-link :underline="false">2020-20-20 25.20</el-link></p>
                                            </div>
                                        </div>
                                    </el-card>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="设置" name="fourth">
                            <el-form ref="form" :model="sets" label-width="80px">
                                <el-form-item label="办事大厅:">
                                    <el-checkbox-group v-model="sets.hallType">
                                        <el-checkbox :label="menu.name" name="type" v-for="(menu, index) in sets.hallMenus" :key="index"  @change="addOpp(menu)"></el-checkbox>
                                    </el-checkbox-group>
                                </el-form-item>
                            </el-form>
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
                sets: {
                    showAdd: true,
                    hallType: ['日程', '我的创建', '活动大厅', '账号中心'],
                    hallMenu: [
                        {id: '1',route: '', name: '日程', icon: 'el-icon-s-order'},
                        {id: '2',route: '', name: '我的创建', icon: 'el-icon-guide'},
                        {id: '3',route: '', name: '活动大厅', icon: 'el-icon-coin'},
                        {id: '4',route: '', name: '账号中心', icon: 'el-icon-user'},
                    ],
                    hallMenus: [
                        {id: '1',route: '', name: '首页', icon: 'el-icon-s-home'},
                        {id: '2',route: '', name: '服务大厅', icon: 'el-icon-s-goods'},
                        {id: '3',route: '', name: '日程', icon: 'el-icon-s-order'},
                        {id: '4',route: '', name: '我的创建', icon: 'el-icon-guide'},
                        {id: '5',route: '', name: '新建活动', icon: 'el-icon-circle-plus-outline'},
                        {id: '6',route: '', name: '活动大厅', icon: 'el-icon-coin'},
                        {id: '7',route: '', name: '账号中心', icon: 'el-icon-user'},
                    ],
                    practiceMenus: [
                        {route: '/tenant/practice/myCreate', name: '我的创建', icon: 'el-icon-guide'},
                        {route: '/tenant/practice', name: '活动大厅', icon: 'el-icon-coin'},
                        {route: '/tenant/practice/create', name: '新建活动', icon: 'el-icon-circle-plus-outline'},
                    ],
                },
            }
        },
        methods: {
            /**
             * 添加快捷入口
             */
            addOpp(menu) {
                let that = this
                that.sets.hallMenu.push(menu)
                console.log(that.sets.hallMenu)
                if (that.sets.hallMenu.length === 5) {
                    that.showAdd = false
                } else if (that.sets.hallMenu.length > 5) {
                    // let type = []
                    // this.sets.hallType = []
                    // 如果大于5 去除数组最后一位
                    // that.sets.hallMenu.
                    // this.sets.hallMenu.forEach(item => {
                    //     type.push(item)
                    //     this.sets.hallType.push(item.name)
                    // })
                    // type.splice(type.length-1, 1)
                    // this.sets.hallMenu = type
                    this.$message({
                        message: '最多添加5条数据',
                        type: 'warning'
                    });
                } else {
                    that.showAdd = true
                }
            },
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