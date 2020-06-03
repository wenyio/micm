<template>
    <div>
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <!-- 申请成为租户 -->
                <el-steps :active="activity">
                    <el-step title="基本信息" description="填写您的学习的基本信息"></el-step>
                    <el-step title="营业执照" description="需要上传有效的营业执照"></el-step>
                    <el-step title="提交审核" description="提交审核，2-3天内审核完成"></el-step>
                </el-steps>
                <el-form ref="form" :model="form" label-width="120px">
                    <div v-show="activity == 1">
                        <el-form-item label="标志">
                            <img alt="点击修改标志" @click="iconShow=true" :src="form.icon" width="60" height="60"/>
                        </el-form-item>
                        <el-form-item label="租户">
                            <el-input v-model="form.name" />
                        </el-form-item>
                        <el-form-item label="地区">
                            <el-input v-model="form.tenantArea"/>
                        </el-form-item>
                        <el-form-item label="地址">
                            <el-input type="text" v-model="form.tenantAddress" />
                        </el-form-item>
                        <el-form-item label="电话">
                            <el-input type="phone" v-model="form.tenantPhone" />
                        </el-form-item>
                        <el-form-item label="邮箱">
                            <el-input type="email" v-model="form.mailbox" />
                        </el-form-item>
                        <el-form-item label="规模">
                            <el-input type="text" v-model="form.tenantSize" />
                        </el-form-item>
                        <el-form-item label="法定代表人">
                            <el-input type="text" v-model="form.legalRepresentative" />
                        </el-form-item>
                    </div>
                    <el-form-item label="营业执照" v-if="activity == 2">
                        <img alt="点击修改封面" @click="coverShow=true" :src="form.businessLicenseId" width="60" height="60" style="width: 300px;height: 400px"/>
                    </el-form-item>
                    <el-form-item v-show="activity == 3" >
                        <el-radio v-model="apply1" label="1">《校织网用户隐私协议》</el-radio>
                        <el-radio v-model="apply1" label="0">拒绝</el-radio>
                    </el-form-item>
                    <el-form-item v-show="activity == 3" >
                        <el-radio v-model="apply2" label="1">《信息真实性协议》</el-radio>
                        <el-radio v-model="apply2" label="0">拒绝</el-radio>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="up">上一步</el-button>
                        <el-button @click="down">下一步</el-button>
                        <el-button v-show="activity == 3" type="primary" @click="onSubmit">提交</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Apply",
        data() {
            return {
                activity: 1,
                apply1: '1',
                apply2: '1',
                form: {
                    name: '',
                    icon: 'http://micm.oss-cn-shanghai.aliyuncs.com/ca61ede3-a470-4bad-885b-da1f6348bf07.png',
                    tenantArea: '',
                    tenantAddress: '',
                    businessLicenseId: 'http://micm.oss-cn-shanghai.aliyuncs.com/65602976-7866-45d2-bf8c-18cf3a0f954a.png',
                    legalRepresentative: '',
                    tenantPhone: '',
                    mailbox: '',
                    tenantSize: '',
                },
            }
        },
        methods: {
            up() {
                if (this.activity > 1) {
                    this.activity -= 1
                    return
                }
            },
            down() {
                if (this.activity < 3) {
                    this.activity += 1
                    return
                }
            }
        }
    }
</script>

<style scoped>

</style>