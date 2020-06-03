<template>
  <view class="bg">
   
        <view class="content">
          <cmd-cel-item title="校园身份管理" @click="manager" arrow></cmd-cel-item>
          <cmd-cel-item title="账号绑定"  arrow></cmd-cel-item>
          <cmd-cel-item title="消息通知"  arrow></cmd-cel-item>
          <cmd-cel-item title="黑名单"  arrow></cmd-cel-item>
          <cmd-cel-item title="分享应用"  arrow></cmd-cel-item>
          <cmd-cel-item title="清楚缓存" addon="47M" ></cmd-cel-item>
          <cmd-cel-item title="检测新版本" ></cmd-cel-item>
		  <cmd-cel-item title="关于我们" ></cmd-cel-item>
          <button class="btn-logout" @click="logout">退出当前账号</button>
        </view>
     
  </view>
</template>

<script>
 
  import cmdCelItem from "@/components/cmd-cell-item/cmd-cell-item.vue"
  import {logout} from "@/api/account.js"

  export default {
    components: {
    
      cmdCelItem,
      
    },

    data() {
      return {};
    },

    mounted() {},
    onShow() {
    	this.isLogin();
    },
    methods:{
		/**
		* 点击触发
		* @param {Object} type 跳转页面名或者类型方式
		*/
		fnClick(type){
			if(type == 'modify'){
			uni.navigateTo({
			url:'/pages/mine/modify/modify'
			})
		}
		},
		logout(){
			let that = this
			uni.clearStorage(); // 请除缓存
			logout().then(res => {
			  let result = res[res.length-1].data
			  if(result.code === 20000){
				uni.clearStorage(); // 请除缓存
				uni.navigateTo({
				  url: '../login/login'
				})  
			  }
			  uni.showToast({
				  icon: 'none',
					position: 'bottom',
				  title: result.message
			  });
			})
		},
		manager(){
			uni.navigateTo({
			url: '/pages/mine/manager/manager'
			})  
		}
  }
 } 
</script>

<style>
	.content{
		padding-top: 20upx;
		padding-left: 10upx;
	}
  .btn-logout {
    margin-top: 100upx;
    width: 80%;
    border-radius: 50upx;
    font-size: 16px;
    color: #fff;
    background: linear-gradient(to right, #365fff, #36bbff);
  }

  .btn-logout-hover {
    background: linear-gradient(to right, #365fdd, #36bbfa);
  }
</style>
