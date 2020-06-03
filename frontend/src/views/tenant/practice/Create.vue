<template>
    <div class="app" style="padding: 20px">
        <el-page-header @back="goBack" content="创建活动">
        </el-page-header>
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <!-- 新建活动 -->
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="活动名称">
                        <el-input v-model="form.title"></el-input>
                    </el-form-item>
                    <el-form-item label="活动图标">
                        <img :src="form.icon" />
                    </el-form-item>
                    <el-form-item label="活动地点">
                        <el-col :span="5">
                            <el-select v-model="form.address" placeholder="请选择活动地点">
                                <el-option label="线上活动" value="线上活动"></el-option>
                                <el-option label="教四401" value="教四401"></el-option>
                                <el-option label="教四403" value="教四403"></el-option>
                                <el-option label="教四405" value="教四405"></el-option>
                                <el-option label="教四407" value="教四407"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="8">
                            <el-input v-model="form.address"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="活动时间">
                        <el-col :span="5">
                            <el-date-picker
                                    @change="setBegin"
                                    v-model="form.begin"
                                    type="datetime"
                                    placeholder="选择开始日期时间"
                                    align="right"
                                    :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="5">
                            <el-date-picker
                                    @change="setEnd"
                                    v-model="form.end"
                                    type="datetime"
                                    placeholder="选择结束日期时间"
                                    align="right"
                                    :picker-options="pickerOptions">
                            </el-date-picker>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="组织分类">
                        <el-col :span="5">
                            <el-select @change="setOrigin" v-model="form.organizationId" placeholder="请选择活动组织" >
                                <el-option v-for="(o, index) in origination" :key="index" :label="o.name" :value="o.id"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="8">
                            <el-select v-model="form.categoryId" placeholder="请选择活动分类">
                                <el-option v-for="(o, index) in category" :key="index" :label="o.name" :value="o.id"></el-option>
                            </el-select>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="活动积分">
                        <el-select v-model="form.integral" placeholder="请选择活动积分">
                            <el-option label="1" value="1"></el-option>
                            <el-option label="5" value="5"></el-option>
                            <el-option label="10" value="10"></el-option>
                            <el-option label="15" value="15"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="活动描述">
                        <el-input type="textarea" v-model="form.description"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="create">立即创建</el-button>
                        <el-button @click="reset">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    var _format = function (number) {
        return (number < 10 ? ('0' + number) : number);
    };
    import { category, organization, add } from '@/api/practice.js'

    export default {
        name: "PracticeCreate",
        data() {
          return {
              pickerOptions: {
                  shortcuts: [{
                      text: '今天',
                      onClick(picker) {
                          picker.$emit('pick', new Date());
                      }
                  }, {
                      text: '昨天',
                      onClick(picker) {
                          const date = new Date();
                          date.setTime(date.getTime() - 3600 * 1000 * 24);
                          picker.$emit('pick', date);
                      }
                  }, {
                      text: '一周前',
                      onClick(picker) {
                          const date = new Date();
                          date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                          picker.$emit('pick', date);
                      }
                  }]
              },
              category: [{name:'默认分类'}],
              origination: [{name:'默认组织'}],
              form: {
                  title: '默认活动名',
                  icon: 'http://micm.oss-cn-shanghai.aliyuncs.com/068446f8-cd2c-43c5-a414-391ceda16639.jpg',
                  address: '线上活动',
                  description:'默认描述',
                  begin:null,
                  end:null,
                  organization:'',
                  organizationId: null,
                  categoryId: null,
                  integral:'5',
              },
          }
        },
        created() {
            this.getCategory()
            this.getOrigination()
        },
        methods: {
            create() {
                console.log(this.form)
                add(this.form).then(res => {
                    if (res.code == 20000) {
                        this.reset()
                        this.$message.success(res.message)
                    }
                })
            },
            setBegin() {
                let begin = this.form.begin
                this.form.begin = begin.getFullYear() + '-' + _format(begin.getMonth()+1) + '-' + _format(begin.getDate()) + ' ' + _format(begin.getHours()) + ':' + _format(begin.getMinutes()) + ':00'
                // console.log(this.form.begin)
            },
            setEnd() {
                let end = this.form.end
                this.form.end = end.getFullYear() + '-' + _format(end.getMonth()+1) + '-' + _format(end.getDate()) + ' ' + _format(end.getHours()) + ':' + _format(end.getMinutes()) + ':00'
                // console.log(this.form.end)
            },
            getCategory(){
                let that = this
                category().then(res =>{
                    if(res.code === 20000){
                        // console.log(res.data)
                        that.category= res.data
                    }
                })
            },
            setOrigin() {
                this.origination.forEach(item => {
                    if (item.id == this.form.organizationId) {
                        this.form.organization = item.name
                        return
                    }
                })
            },
            getOrigination(){
                let that = this
                organization().then(res =>{
                    if(res.code === 20000){
                        // console.log(res.data)
                        that.origination= res.data
                    }
                })
            },
            goBack() {
                this.$router.back(-1)
                console.log('go back');
            },
            reset() {
                this.form = {
                    title: '默认活动名',
                    icon: 'http://micm.oss-cn-shanghai.aliyuncs.com/068446f8-cd2c-43c5-a414-391ceda16639.jpg',
                    address: '线上活动',
                    description:'默认描述',
                    begin:null,
                    end:null,
                    organization:'',
                    organizationId: null,
                    categoryId: null,
                    integral:'5',
                }
            }
        }
    }
</script>

<style scoped>

</style>