<template>
	<view class="forget">
		
		<view class="content">
			<!-- 主体 -->
			<view class="main">
				<view class="tips">若你忘记了密码，可在此重置新密码。</view>
				<wInput
					v-model="data.email"
					type="text"
					maxlength="30"
					placeholder="请输入邮箱"
				></wInput>
				<wInput
					v-model="data.newPassword"
					type="password"
					maxlength="11"
					placeholder="请输入新密码"
					isShowPass
				></wInput>
				
				<wInput
					v-model="data.captcha"
					type="number"
					maxlength="6"
					placeholder="验证码"
					
					isShowCode
					codeText="获取验证码"
					setTime="30"
					ref="runCode"
					@setCode="getCaptcha()"
				></wInput>
			</view>
			
			<wButton 
				text="重置密码"
				@click.native="startRePass()"
			></wButton>

		</view>
	</view>
</template>

<script>
	var _this;
	import {forgetPassword,emailCode} from '@/api/account.js'
	import wInput from '../../../components/watch-login/watch-input.vue' //input
	import wButton from '../../../components/watch-login/watch-button.vue' //button
	export default {
		data() {
			return {
				data: {
					"email": "",
					"captcha": "",
					"newPassword": ""
				},
			}
		},
		components:{
			wInput,
			wButton
		},
		mounted() {
			_this= this;
		},
		methods: {

			getCaptcha(){
				//获取验证码
				var email = this.data.email;
				console.log(email)
				var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				
				if (!reg.test(email)) {
					uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '邮箱格式不正确'
				    });
				    return false;
				}
				
				
				console.log("获取验证码")
				let data = {
					email: this.data.email
				}
				
				emailCode(data).then(res =>{
					let result = res [res.length-1].data
					if(result.code === 20000){
						this.$refs.runCode.$emit('runCode');
						uni.showToast({
						    icon: 'none',
							position: 'bottom',
						    title: result.message
						});
					}
				})
			},
			startRePass() {
				//重置密码
				var email = this.data.email;
				console.log(email)
				var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				
				if (!reg.test(email)) {
				    uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '邮箱格式不正确'
				    });
				    return false;
				}
			    if (this.data.newPassword.length < 6) {
			        uni.showToast({
			            icon: 'none',
						position: 'bottom',
			            title: '密码不正确'
			        });
			        return false;
			    }
				if (this.data.captcha.length != 6) {
				    uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '验证码不正确'
				    });
				    return false;
				}
				forgetPassword(this.data).then (res =>{
					let result = res[res.length-1].data
					console.log(result)
					// 重置成功，跳转页面，清空倒计时
					if (result.code === 20000) {
						this.$refs.runCode.$emit('runCode',0);//清空倒计时
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '模拟倒计时终止'
						});
						uni.navigateTo({
						  url: '/pages/mine/login/login'
						})
					}
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				})
				
				
			}
		}
	}
</script>

<style>
	@import url("../../../components/watch-login/css/icon.css");
	@import url("../../../components/css/main.css");
</style>

