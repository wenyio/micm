import Vue from 'vue'
import App from './App'
import Config from '@/api/config.js'

Vue.config.productionTip = false

App.mpType = 'app'

Vue.prototype.baseUrl = Config.baseURL

Vue.prototype.isLogin = function(){
	// 判断缓存中是否登录过，直接登录
	try {
		const value = uni.getStorageSync('token');
		if (value) {
			//有登录信息
			console.log("已登录用户：",value);
			_this.$store.dispatch("token",value); //存入状态
			uni.reLaunch({
				url: '/pages/index',
			});
		}
		else{
			uni.showModal({
				titie:'提醒',
				content:"请登录",
				success:function(){
					uni.navigateTo({
						url:"/pages/mine/login/login"
					});
					
				},
				
			})
		}
	} catch (e) {
		// error
	}
}
const app = new Vue({
    ...App
})
app.$mount()
