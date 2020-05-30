<template>
  <div class="app-container">
    <el-form
      v-loading="formLoading"
      :data="form"
      element-loading-text="加载中..."
      :model="form"
      label-width="120px"
    >
      <input type="hidden" v-model="form.id"/>
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="标志">
          <img alt="点击修改标志" @click="iconShow=true" :src="form.icon" width="60" height="60"/>
        </el-form-item>
        <image-cropper
          field="multipartFile"
          @crop-success="cropIconSuccess"
          @crop-upload-success="cropIconUploadSuccess"
          @crop-upload-fail="cropUploadFail"
          v-model="iconShow"
          :width="300"
          :height="300"
          :url="url"
          :params="params"
          :headers="headers"
          img-format="png"
        />
        <el-form-item label="封面">
          <img alt="点击修改封面" @click="coverShow=true" :src="form.cover" width="60" height="60" style="width: 200px;height: 100px"/>
        </el-form-item>
        <image-cropper
          field="multipartFile"
          @crop-success="cropCoverSuccess"
          @crop-upload-success="cropCoverUploadSuccess"
          @crop-upload-fail="cropUploadFail"
          v-model="coverShow"
          :width="300"
          :height="300"
          :url="url"
          :params="params"
          :headers="headers"
          img-format="png"
        />
        <el-form-item label="服务">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="价格/天">
          <el-input type="number" v-model="form.price"/>
        </el-form-item>
        <el-form-item label="版本">
          <el-input v-model="form.version"/>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.description" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status"></el-switch>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存</el-button>
          <el-button type="default" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-form>
  </div>

</template>

<script>
  import PanThumb from '@/components/PanThumb'
  import ImageCropper from 'vue-image-crop-upload'
  import { getToken } from "@/utils/auth";
  import { save } from "@/api/service";

  export default {
    name: "ServiceAdd",
    components: {
      ImageCropper, PanThumb
    },
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
        formLoading: false,
        form: {
          name: '',
          icon: 'http://micm.oss-cn-shanghai.aliyuncs.com/ca61ede3-a470-4bad-885b-da1f6348bf07.png',
          cover: 'http://micm.oss-cn-shanghai.aliyuncs.com/65602976-7866-45d2-bf8c-18cf3a0f954a.png',
          price: '',
          version: '0.0.1',
          description: '',
          status: true,
        },
        url: process.env.VUE_APP_BASE_API + '/cloud/upload',
        iconShow: false,
        coverShow: false,
        params: {
          access_token: getToken()
        },
        headers: {
          smail: '*_~'
        }
      }
    },
    created() {
      // 接收url传来的值
      this.form = JSON.parse(this.$route.query.form)
    },
    methods: {
      onSubmit() {
        // console.log(this.form)
        save(this.form).then(response => {
          this.formLoading = false
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.reset(); // 重置表单
        })
      },
      /**
       * 重置表单
       */
      reset() {
        this.form = {
          name: '',
          icon: 'http://micm.oss-cn-shanghai.aliyuncs.com/ca61ede3-a470-4bad-885b-da1f6348bf07.png',
          cover: 'http://micm.oss-cn-shanghai.aliyuncs.com/65602976-7866-45d2-bf8c-18cf3a0f954a.png',
          price: '',
          version: '0.0.1',
          description: '',
          status: true,
        }
      },
      /**
       * crop success
       *
       * [param] imgDataUrl
       * [param] field
       */
      cropIconSuccess(image, field){
        console.log('-------- crop success --------');
        this.form.icon = image;
      },
      /**
       * upload success
       *
       * [param] jsonData  server api return data, already json encode
       * [param] field
       */
      cropIconUploadSuccess(jsonData, field){
        console.log('-------- upload success --------');
        console.log(jsonData);
        console.log('field: ' + field);

        // jsonData.data.path
        this.form.icon = jsonData.data.path
      },
      /**
       * crop success
       *
       * [param] imgDataUrl
       * [param] field
       */
      cropCoverSuccess(image, field){
        console.log('-------- crop success --------');
        this.form.cover = image;
      },
      /**
       * upload success
       *
       * [param] jsonData  server api return data, already json encode
       * [param] field
       */
      cropCoverUploadSuccess(jsonData, field){
        console.log('-------- upload success --------');
        console.log(jsonData);
        console.log('field: ' + field);

        // jsonData.data.path
        this.form.cover = jsonData.data.path
      },
      /**
       * upload fail
       *
       * [param] status    server api return error status, like 500
       * [param] field
       */
      cropUploadFail(status, field){
        console.log('-------- upload fail --------');
        console.log(status);
        console.log('field: ' + field);
      }
    }
  }
</script>

<style scoped>

</style>
