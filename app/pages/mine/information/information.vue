<template>
  <view >
    <cmd-nav-bar back title="信息设置"></cmd-nav-bar>
    <cmd-page-body type="top">
		<cmd-transition name="fade-up">
        <view class="header">
			<cmd-cel-item title="头像" slot-right >
				<cmd-avatar :src="user.avatar" ></cmd-avatar>
			</cmd-cel-item>
            <cmd-cel-item title="昵称" :addon="user.nickname" arrow></cmd-cel-item>
            <cmd-cel-item title="邮箱" :addon="user.email" arrow></cmd-cel-item>
            <cmd-cel-item title="手机" :addon="user.phone" arrow></cmd-cel-item>
            <cmd-cel-item title="修改密码" @click="fnClick('modify')" arrow></cmd-cel-item>
		    <cmd-cel-item title="修改个人信息" @click="fnClick1('alter')" arrow></cmd-cel-item>
            <button class="btn-logout" @click="logout">退出登录</button>
        </view>
		</cmd-transition>
    </cmd-page-body>
  </view>
</template>

<script>
  import cmdNavBar from "@/components/cmd-nav-bar/cmd-nav-bar.vue"
  import cmdPageBody from "@/components/cmd-page-body/cmd-page-body.vue"
  import cmdTransition from "@/components/cmd-transition/cmd-transition.vue"
  import cmdCelItem from "@/components/cmd-cell-item/cmd-cell-item.vue"
  import cmdAvatar from "@/components/cmd-avatar/cmd-avatar.vue"

  export default {
    components: {
      cmdNavBar,
      cmdPageBody,
      cmdTransition,
      cmdCelItem,
      cmdAvatar
    },

    data() {
      return {
		  user: null,
	  };
    },

    mounted() {},
    onShow() {
    	this.isLogin();
		this.user = uni.getStorageSync("user")
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
	  fnClick1(type){
	    if(type == 'alter'){
	      uni.navigateTo({
	        url:'/pages/mine/alter/alter'
	      })
	    }
	  },
	  logout(){
		  uni.clearStorage(); // 请除缓存
		  
		  uni.navigateTo({
		    url: '../login/login'
		  })
	  }
    }
  }
</script>

<style>
	.content{
		padding-top: 0upx;
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
  .header{
	  margin-top: -60rpx;
  }
</style>
