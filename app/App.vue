<script>
// global.isLogin = function(){
// 	try{
// 		var setUserData  = uni.getStorageSync('setUserData');
// 	}catch(e){
		
// 	}
// 	if(setUserData == '' || setUserData == null){
// 		return false;
// 	}else{
// 		return setUserData;
// 	}
// };
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show');
			this.isLogin();
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/* @import "./components/main.css";
	@import "./components/icon.css"; */
	/*每个页面公共css */
	/* uni.css - 通用组件、模板样式库，可以当作一套ui库应用 */
	/* @import './common/uni.css'; */
</style>
