<template>
    <div style="padding: 20px">
        <el-row :gutter="20">
            <el-col :span="3">.</el-col>
            <el-col :span="18">
                <div @click="$router.push('/service/apply')">
                    <el-alert
                            title="还不是租户？点击申请!"
                            type="info">
                    </el-alert>
                </div>
                <el-carousel indicator-position="outside">
                    <el-carousel-item v-for="item in 4" :key="item">
                        <img src="http://micm.oss-cn-shanghai.aliyuncs.com/f7da6b99-09cf-4f74-b674-c34332d724e5.png" width="100%" alt="">
                    </el-carousel-item>
                </el-carousel>
                <el-card shadow="hover" v-for="(o, index) in list" :key="index" style="margin: 10px">
                    <div style="display: flex;justify-content: space-around;align-items: center">
                        <div>
                            <img :src="o.cover" width="200px" alt="">
                        </div>
                        <div style="width: 68%;">
                            <h4>{{o.name}}
                                <el-tag v-if="o.status" size="mini" type="" >在售</el-tag>
                                <el-tag v-if="!o.status" size="mini" type="warning" >停售</el-tag>
                            </h4>
                            <p>简介：</p>
                            <p>{{o.description}}</p>
                            <p><el-link :underline="false">￥{{o.price}}/天</el-link></p>
                            <el-button v-if="o.status" @click="clickRenewal(o.id)"  type="text" class="button">订阅</el-button>
                        </div>
                    </div>
                </el-card>
                <br>
                <div class="block">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="1"
                            :page-sizes="[4, 8, 16, 24, 32, 40, 48]"
                            :page-size="10"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="totalElements">
                    </el-pagination>
                </div>
            </el-col>
            <el-col :span="3"></el-col>
        </el-row>

    </div>
</template>

<script>
    import {list} from "@/api/service";
    import {renewal} from "@/api/tenantService";
    import {info} from "@/api/account";

    export default {
        name: "Index",
        data() {
            return {
                page: 0,
                size: 4,
                totalElements: 0,
                list: [],
                data: {
                    serviceId: null,
                    tenantId: null,
                },
            }
        },
        created() {
            this.fetchData()
            this.tip()
        },
        methods: {
            tip() {
                this.$notify({
                    title: '友情提示',
                    message: '关于服务的具体内容可向管理员申请演示！',
                    position: 'bottom-right'
                });
            },
            getUserTenantId() {
                info().then(res => {
                    if (res.code === 20000) {
                        this.data.tenantId  = res.data.tenantId
                    } else {
                        this.$message.error(res.message)
                    }
                })
            },
            /**
             * 获取数据
             */
            fetchData() {
                list(this.page, this.size).then(response => {
                    this.list = response.data.content
                    this.totalElements = response.data.totalElements
                    // console.log(this.list)
                    this.getUserTenantId(); // 获取租户id
                })
            },
            /**
             * 点击订阅
             */
            clickRenewal(serviceId) {
                this.data.serviceId = serviceId;
                this.$prompt('请输入订阅时长/天', '服务订阅', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputType: 'number',
                    inputPattern: /^[0-9]*[1-9][0-9]*$/,
                    inputErrorMessage: '请输入正确的天数'
                }).then(({ value }) => {
                    // 续费接口
                    this.renewalService(value);
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消订阅'
                    });
                });
            },
            /**
             * 服务续费
             */
            renewalService(days) {
                renewal(this.data, days).then(response => {
                    this.services = response.data
                    console.log(this.data)
                    this.$message({
                        message: response.message,
                        type: 'success'
                    })
                })
            },
            /**
             * 每页x条数据
             * @param val
             */
            handleSizeChange(val) {
                this.size = val
                this.fetchData()
                console.log(`每页 ${val} 条`);
            },
            /**
             * 当前x页
             * @param val
             */
            handleCurrentChange(val) {
                this.page = val - 1
                this.fetchData()
                console.log(`当前页: ${val}`);
            },
        }
    }
</script>

<style scoped>

</style>