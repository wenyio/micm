<template>
  <div class="app-container">
    <!-- 表单数据 -->
    <el-table
      v-loading="formLoading"
      :data="services"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="服务" width="110" align="center">
<!--          <span>{{ scope.row.serviceId }}</span>-->
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>服务: {{ form.name }}</p>
            <p>描述: {{ form.description }}</p>
            <p>价格: {{ form.price }}</p>
            <p>版本: {{ form.version }}</p>
            <div slot="reference" class="name-wrapper" @mouseover="getByServiceId(scope.row.serviceId)">
              <el-tag size="medium">{{ scope.row.serviceId }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="续费时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.renewalDate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到期时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.expirationDate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="冻结时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.freezeDate }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" width="110" align="center">
        <template slot-scope="scope">
          <el-button type="warning" plain size="mini" @click="changeServiceStatus(scope.row.id)" v-if="!scope.row.status">冻结</el-button>
          <el-button type="success" plain size="mini" @click="changeServiceStatus(scope.row.id)" v-if="scope.row.status">正常</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="clickRenewal(scope.row.serviceId)" >续订</el-button>
          <el-button type="danger" size="mini" @click="deleteService(scope.row.id, scope.row.serviceId)" >取订</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script>
  import { info } from "@/api/profile";
  import { getById } from "@/api/service";
  import { renewal, list as listService, deleteById as deleteService, changeStatus as changeServiceStatus} from "@/api/tenantService";

  export default {
    name: "Tenant",
    filters: {
      statusFilter(status) {
        console.log(status)
        if (status) {
          return "success"
        }
        return "danger"
      }
    },
    data() {
      return {
        formLoading: true,
        services: [],
        data: {
          serviceId: null,
          tenantId: null,
        },
        form: {
          name: '',
          icon: 'http://micm.oss-cn-shanghai.aliyuncs.com/ca61ede3-a470-4bad-885b-da1f6348bf07.png',
          cover: 'http://micm.oss-cn-shanghai.aliyuncs.com/65602976-7866-45d2-bf8c-18cf3a0f954a.png',
          price: '',
          version: '0.0.1',
          description: '',
          status: true,
        },
      }
    },
    created() {
      // 获取用户信息
      this.getUserTenantId()
    },
    methods: {
      getUserTenantId() {
        this.formLoading = true
        info(this.$store.getters.name).then(response => {
          this.data.tenantId  = response.data.tenantId
          this.formLoading = false
          this.listService(); // 拿到用户信息，才能获取相关的数据
        }).catch(() => {
          this.formLoading = false
        })
      },
      /**
       * 点击续费
       */
      clickRenewal(serviceId) {
        this.data.serviceId = serviceId;
        this.$prompt('请输入续费时长/天', '服务续费', {
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
            message: '取消续费'
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
          this.getUserTenantId(); // 重新列表数据
          this.$message({
            message: response.message,
            type: 'success'
          })
        }).catch(() => {
          this.formLoading = false
        })
      },
      listService(){
        listService(this.data).then(response => {
          this.services = response.data
          this.formLoading = false
        }).catch(() => {
          this.formLoading = false
        })
      },
      deleteService(id, serviceId) {
        this.data.serviceId = serviceId;
        this.formLoading = true
        deleteService(this.data, id).then(response => {
          this.formLoading = false
          this.listService()
          this.$message({
            message: response.message,
            type: 'success'
          })
        }).catch(() => {
          this.formLoading = false
        })
      },
      changeServiceStatus(id) {
        this.formLoading = true
        changeServiceStatus(id).then(response => {
          this.formLoading = false
          this.listService()
          this.$message({
            message: response.message,
            type: 'success'
          });
        }).catch(() => {
          this.formLoading = false
        })
      },
      getByServiceId(id) {
        getById(id).then(response => {
          this.form = response.data
        }).catch(() => {
          this.formLoading = false
        })
      }
    }
  }
</script>

<style scoped>

</style>
<!--<el-form-->
<!--  v-loading="formLoading"-->
<!--  :data="form"-->
<!--  element-loading-text="加载中..."-->
<!--  :model="form"-->
<!--  label-width="120px"-->
<!--&gt;-->
<!--  <input type="hidden" v-model="form.id"/>-->
<!--  <el-form ref="form" :model="form" label-width="120px">-->
<!--    <el-form-item label="标志">-->
<!--      <img alt="点击修改标志" @click="iconShow=true" :src="form.icon" width="60" height="60"/>-->
<!--    </el-form-item>-->
<!--    <image-cropper-->
<!--      field="multipartFile"-->
<!--      @crop-success="cropIconSuccess"-->
<!--      @crop-upload-success="cropIconUploadSuccess"-->
<!--      @crop-upload-fail="cropUploadFail"-->
<!--      v-model="iconShow"-->
<!--      :width="300"-->
<!--      :height="300"-->
<!--      :url="url"-->
<!--      :params="params"-->
<!--      :headers="headers"-->
<!--      img-format="png"-->
<!--    />-->
<!--    <el-form-item label="封面">-->
<!--      <img alt="点击修改封面" @click="coverShow=true" :src="form.cover" width="60" height="60" style="width: 200px;height: 100px"/>-->
<!--    </el-form-item>-->
<!--    <image-cropper-->
<!--      field="multipartFile"-->
<!--      @crop-success="cropCoverSuccess"-->
<!--      @crop-upload-success="cropCoverUploadSuccess"-->
<!--      @crop-upload-fail="cropUploadFail"-->
<!--      v-model="coverShow"-->
<!--      :width="300"-->
<!--      :height="300"-->
<!--      :url="url"-->
<!--      :params="params"-->
<!--      :headers="headers"-->
<!--      img-format="png"-->
<!--    />-->
<!--    <el-form-item label="服务">-->
<!--      <el-input v-model="form.name" />-->
<!--    </el-form-item>-->
<!--    <el-form-item label="价格/天">-->
<!--      <el-input type="number" v-model="form.price"/>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="版本">-->
<!--      <el-input v-model="form.version"/>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="描述">-->
<!--      <el-input type="textarea" v-model="form.description" />-->
<!--    </el-form-item>-->
<!--    <el-form-item label="状态">-->
<!--      <el-switch v-model="form.status"></el-switch>-->
<!--    </el-form-item>-->
<!--    <el-form-item>-->
<!--      <el-button type="primary" @click="onSubmit">保存</el-button>-->
<!--      <el-button type="default" @click="reset">重置</el-button>-->
<!--    </el-form-item>-->
<!--  </el-form>-->
<!--</el-form>-->
