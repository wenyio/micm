<template>
  <div class="components-container">
    <pan-thumb :image="avatar" />

    <el-button type="primary" icon="el-icon-upload" style="position: absolute;bottom: 15px;margin-left: 40px;" @click="show=true">
      上传头像
    </el-button>

    <image-cropper
      field="multipartFile"
      @crop-success="cropSuccess"
      @crop-upload-success="cropUploadSuccess"
      @crop-upload-fail="cropUploadFail"
      v-model="show"
      :width="300"
      :height="300"
      :url="url"
      :params="params"
      :headers="headers"
      img-format="png"
    />
  </div>
</template>

<script>
  import PanThumb from '@/components/PanThumb'
  import ImageCropper from 'vue-image-crop-upload'
  import { getToken } from "../../utils/auth";
  import { modifyAvatar } from "@/api/profile";

  export default {
    name: 'ProfileAvatar',
    components: {
      ImageCropper, PanThumb
    },
    data() {
      return {
        url: process.env.VUE_APP_BASE_API + '/cloud/upload',
        show: false,
        avatar: this.$store.getters.avatar,
        params: {
          access_token: getToken()
        },
        headers: {
          smail: '*_~'
        }
      }
    },
    methods: {
      /**
       * crop success
       *
       * [param] imgDataUrl
       * [param] field
       */
      cropSuccess(image, field){
        console.log('-------- crop success --------');
        this.image = image;
      },
      /**
       * upload success
       *
       * [param] jsonData  server api return data, already json encode
       * [param] field
       */
      cropUploadSuccess(jsonData, field){
        console.log('-------- upload success --------');
        console.log(jsonData);
        console.log('field: ' + field);

        // 更新头像
        modifyAvatar({
          username: this.$store.getters.name,
          path: jsonData.data.path
        }).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.$store.dispatch('user/setAvatar', jsonData.data.path)
          this.avatar = this.$store.getters.avatar
        }).catch(() => {
        })
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
  .avatar{
    width: 200px;
    height: 200px;
    border-radius: 50%;
  }
</style>
