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
                        <h3>{{ item }}</h3>
                    </el-carousel-item>
                </el-carousel>
                <el-card shadow="hover" v-for="i in 4" :key="i" style="margin: 10px">
                    <div style="display: flex;justify-content: space-around;align-items: center">
                        <div>
                            <img src="@/assets/logo.png" width="200px" alt="">
                        </div>
                        <div style="width: 68%;">
                            <h4>更新 Github 模板</h4>
                            <p>简介：</p>
                            <p>四点三十分是的速度s四点三十分是的速度sdfsds sdfssfs sdfsdfsdfdfsds 四点三十分是的速度sdfsds sdfssfs sdfsdfsdfsdfssfs sdfsdfsdf</p>
                            <p><el-link :underline="false">￥100/天</el-link></p>
                            <el-button type="text" class="button">订阅</el-button>
                        </div>
                    </div>
                </el-card>
                <br>
                <div class="block">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="1"
                            :page-sizes="[8, 16, 24, 32, 40, 48]"
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
    export default {
        name: "Index",
        date() {
            return {
                page: 0,
                size: 8,
                totalElements: 0,
            }
        },
        methods: {
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