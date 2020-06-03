<template>
    <div class="tenant" style="width: 98%">
        <!-- 基本信息 -->
        <el-row :gutter="20" style="margin-top: 36px;">
            <el-col :span="5"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="4">
                <div class="grid-content bg-purple">
                    <el-avatar :size="99" :src="user.avatar" @error="true">
                        <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                    </el-avatar>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <p><el-link :underline="false">{{userDetail.name}}</el-link></p>
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
                                <p>{{count.num}}</p>
                            </div>
                            <div class="count_item">
                                <p>积分</p>
                                <p>{{count.integral}}</p>
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
                                    <div class="box" v-for="(menu, index) in sets.hallMenu" :key="index" @click="goPage(menu.route)">
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
                                <el-col :span="8" v-for="(o, index) in list" :key="index" style="margin-bottom: 12px;">
                                    <el-card class="box-card" shadow="hover">
                                        <div style="display: flex;justify-content: space-around;align-items: center">
                                            <div style="width: 90px">
                                                <img :src="o.icon" width="88px" alt="">
                                            </div>
                                            <div style="width: 60%;">
                                                <h4>{{o.title}}</h4>
                                                <el-tag v-if="o.status == 10" size="mini" type="warning">早退</el-tag>
                                                <el-tag v-if="o.status == 1" size="mini" type="warning">迟到</el-tag>
                                                <el-tag v-if="o.status == 0" size="mini" type="danger">缺席</el-tag>
                                                <el-tag v-if="o.status == 11" size="mini" type="success">完成</el-tag>
                                                <p><el-link :underline="false">{{o.begin}}</el-link></p>
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
                                        <el-checkbox :label="menu.name" name="type" v-for="(menu, index) in sets.hallMenus" :key="index"  @change="addOpp"></el-checkbox>
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
    import { getPracticeIntegral,getPractice,getPracticeNum } from '@/api/practice.js'

    export default {
        name: "Index",
        data() {
            return {
                user: {},
                userDetail: {},
                activeName2: 'first',
                count: {},
                list: [],
                sets: {
                    showAdd: true,
                    hallType: ['日程', '我的创建', '活动大厅', '账号中心'],
                    hallMenu: [
                        {id: '1',route: '/user/schedule', name: '日程', icon: 'el-icon-s-order'},
                        {id: '2',route: '/tenant/practice/myCreate', name: '我的创建', icon: 'el-icon-guide'},
                        {id: '3',route: '/tenant/practice', name: '活动大厅', icon: 'el-icon-coin'},
                        {id: '4',route: '/user/account', name: '账号中心', icon: 'el-icon-user'},
                    ],
                    hallMenus: [
                        {id: '1',route: '/', name: '首页', icon: 'el-icon-s-home'},
                        {id: '2',route: '/service', name: '服务大厅', icon: 'el-icon-s-goods'},
                        {id: '3',route: '/user/schedule', name: '日程', icon: 'el-icon-s-order'},
                        {id: '4',route: '/tenant/practice/myCreate', name: '我的创建', icon: 'el-icon-guide'},
                        {id: '5',route: '/tenant/practice/create', name: '新建活动', icon: 'el-icon-circle-plus-outline'},
                        {id: '6',route: '/tenant/practice', name: '活动大厅', icon: 'el-icon-coin'},
                        {id: '7',route: '/user/account', name: '账号中心', icon: 'el-icon-user'},
                    ],
                    practiceMenus: [
                        {route: '/tenant/practice/myCreate', name: '我的创建', icon: 'el-icon-guide'},
                        {route: '/tenant/practice', name: '活动大厅', icon: 'el-icon-coin'},
                        {route: '/tenant/practice/create', name: '新建活动', icon: 'el-icon-circle-plus-outline'},
                    ],
                },
            }
        },
        created() {
            this.user = JSON.parse(localStorage.getItem('user'))
            this.userDetail = JSON.parse(localStorage.getItem('userDetail'))
            // console.log(this.userDetail)
            this.getPractice()
            this.getPracticeIntegral()
            this.getPracticeNum()
        },
        mounted() {
            if (localStorage.getItem('hallType') != null) {
                this.sets.hallType = JSON.parse(localStorage.getItem('hallType'))
                // console.log('初始化时',this.sets.hallType)
            }
            this.addOpp()
        },
        methods: {
            getPracticeNum() {
                let that =this
                getPracticeNum().then (res =>{
                    // console.log(res)
                    if(res.code === 20000){
                        that.count.num = res.data
                    }
                })
            },
            getPracticeIntegral(){
                let that =this
                getPracticeIntegral().then (res =>{
                    // console.log(res)
                    if(res.code === 20000){
                        that.count.integral = res.data
                    }
                })
            },
            getPractice(){
                let that = this
                getPractice().then (res =>{
                    if (res.code === 20000) {
                        that.list = res.data
                        // console.log(that.list)
                    }
                })
            },
            /**
             * 添加快捷入口
             */
            addOpp() {
                let hallType = this.sets.hallType // 1. 赋值

                if (hallType.length == 5) {
                    // 如果等于 5 添加选项不显示
                    this.sets.showAdd = false
                } else if (hallType.length >= 5) {
                    // 如果大于5 去除数组最后一位, 添加选项不显示
                    this.sets.showAdd = false
                    hallType.splice(hallType.length-1, 1)
                    this.$message({
                        message: '最多添加5条数据',
                        type: 'warning'
                    });
                    return
                } else {
                    this.sets.showAdd = true
                }

                // 将hallType 存入缓存
                // console.log('存入缓存', JSON.stringify(hallType))
                localStorage.setItem('hallType', JSON.stringify(hallType))
                // 通过 hallType，去hallMenus里 拿hallMenu的值
                this.sets.hallMenu = [] // 设为空，下面重新取值

                hallType.forEach(type => {
                    // console.log(type)
                    this.sets.hallMenus.forEach(menu => {
                        if (menu.name == type) {
                            this.sets.hallMenu.push(menu)
                            // console.log(this.sets.hallMenu)
                        }
                    })
                })

                this.sets.hallType = hallType // 2. 取值
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