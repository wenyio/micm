<template>
    <div class="app" style="padding: 20px">
        <el-page-header @back="goBack" content="活动大厅">
        </el-page-header>
        <!-- 菜单 -->
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <el-input @confirm="search" placeholder="请输入内容" v-model="searchKey" style="margin-bottom: 20px" class="input-with-select">
                    <el-button @click="search" slot="append" icon="el-icon-search"></el-button>
                </el-input>
                <el-row :gutter="12">
                    <el-col :span="8" v-for="(o, index) in list" :key="index" style="margin-bottom: 12px;">
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
                </el-row>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    import { list, search } from '@/api/practice';

    export default {
        name: "Practices",
        created() {
            this.listPractice()
        },
        data() {
          return {
              list: [],
              searchKey: '',
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
            search() {
                if (this.searchKey == null || this.searchKey == '') {
                    this.listPractice()
                } else {
                    search(this.searchKey).then(res => {
                        if (res.code === 20000) {
                            // console.log(res.data)
                            this.list = res.data
                            this.searchKey = ''
                            this.$message.success(res.message)
                        }
                    })
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