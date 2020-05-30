<template>
	<view class="login">
		<view class="content">
			<!-- 头部logo -->
			<view class="header">
				<image src="../../../static/image/logo1.png"></image>
			</view>
			<!-- 主体表单 -->
			<view class="main">
				<wInput
					v-model="phoneData"
					type="text"
					maxlength="11"
					placeholder="请输入用户名"
				></wInput>
				<wInput
					v-model="passData"
					type="password"
					maxlength="11"
					placeholder="请输入密码"
				></wInput>
			</view>
			<wButton 
				text="登 录"
				:rotate="isRotate" 
				@click.native="startLogin()"
			></wButton>
			
			<!-- 其他登录 -->
			<!-- <view class="other_login cuIcon">
				<view class="login_icon">
					<view class="cuIcon-weixin" @tap="login_weixin"></view>
				</view>
				<view class="login_icon">
					<view class="cuIcon-weibo" @tap="login_weibo"></view>
				</view>
				<view class="login_icon">
					<view class="cuIcon-github" @tap="login_github"></view>
				</view>
			</view> -->
			
			<!-- 底部信息 -->
			<view class="footer">
				<navigator url="/pages/mine/forget/forget" open-type="navigate">找回密码</navigator>
				<text>|</text>
				<navigator url="/pages/mine/register/register" open-type="navigate">注册账号</navigator>
			</view>
		</view>
	</view>
</template>

<script>
	var _this;
	import wInput from '../../../components/watch-login/watch-input.vue' //input
	import wButton from '../../../components/watch-login/watch-button.vue' //button
	import { login, info } from '@/api/account.js'
	import { getUserQrCode } from '@/api/user.js'
	
	export default {
		data() {
			return {
				phoneData:'', //用户/电话
				passData:'', //密码
				isRotate: false, //是否加载旋转
			};
		},
		components:{
			wInput,
			wButton,
		},
		mounted() {
			_this = this;
			if (uni.getStorageSync("token")) {
				uni.reLaunch({
					url: '/pages/index/index',
				});
			}
		},
		methods: {
		    startLogin(){
				//登录
				if(this.isRotate){
					//判断是否加载中，避免重复点击请求
					return false;
				}
				if (this.phoneData.length == "") {
				     uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '用户名不能为空'
				    });
				    return;
				}
		        if (this.passData.length == "") {
		            uni.showToast({
		                icon: 'none',
						position: 'bottom',
		                title: '密码不能为空'
		            });
		            return;
		        }
				// 调用登录方法
				this.login_pwd()
		    },
			login_pwd() {
				let data = {
					'username': _this.phoneData,
					'password': _this.passData
				}
				login(data).then(res => {
					let result = res[res.length-1].data
					if (result.code === 20000) {
						uni.setStorageSync("token", result.data.token)
						// 存一个用户信息
						_this.getUserInfo()
						getUserQrCode() // 用户的二维码
						
						uni.reLaunch({
							url: '/pages/index/index',
						});
					} 
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				})
			},
			/**
			 * 获取用户信息
			 */
			getUserInfo() {
				info().then(res => {
					let result = res[res.length-1].data
					if (result.code === 20000) {
						uni.setStorageSync("user", result.data)
					} else {
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: result.message
						});
					}
				})
			}
			// login_weixin() {
			// 	//微信登录
			// 	uni.showToast({
			// 		icon: 'none',
			// 		position: 'bottom',
			// 		title: '...'
			// 	});
			// },
			// login_weibo() {
			// 	//微博登录
			// 	uni.showToast({
			// 		icon: 'none',
			// 		position: 'bottom',
			// 		title: '...'
			// 	});
			// },
			// login_github() {
			// 	//github登录
			// 	uni.showToast({
			// 		icon: 'none',
			// 		position: 'bottom',
			// 		title: '...'
			// 	});
			// }
		}
	}
</script>

<style>
	@import url("../../../components/watch-login/css/icon.css");
	@import url("../../../components/css/main.css");

</style>
