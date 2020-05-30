<template>
	<view class="modify">
		
		<view class="content">
			<!-- 主体 -->
			<view class="main">
				<view class="tips">若你想修改密码，可在此修改新密码。</view>
				<wInput
					v-model="data.username"
					type="text"
					maxlength="11"
					placeholder="请输入用户名"
				></wInput>
				
				<wInput
					v-model="data.oldPassword"
					type="password"
					maxlength="18"
					placeholder="请输入原始密码"
					isShowPass
				></wInput>
				<wInput
					v-model="data.newPassword"
					type="password"
					maxlength="18"
					placeholder="请输入新密码"
					isShowPass
				></wInput>
			</view>
			
			<wButton 
				text="提交"
				@click.native="startRePass()"
			></wButton>

		</view>
	</view>
</template>

<script>
	var _this;
	import { modifyPassword } from '@/api/account.js'
	import wInput from '../../../components/watch-login/watch-input.vue' //input
	import wButton from '../../../components/watch-login/watch-button.vue' //button
	export default {
		data() {
			return {
				data: {
					"username": uni.getStorageSync("user").username,
					"oldPassword": "",
					"newPassword": ""
				}
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
			startRePass() {
				if (this.data.username != uni.getStorageSync("user").username) {
				    uni.showToast({
				        icon: 'none',
						position: 'bottom',
				        title: '用户名不正确'
				    });
				    return false;
				}
			    if (this.data.newPassword.length < 6) {
			        uni.showToast({
			            icon: 'none',
						position: 'bottom',
			            title: '新密码长度小于6'
			        });
			        return false;
			    }
				// console.log("重置密码成功")
				// 调用修改密码接口
				modifyPassword(this.data).then(res =>{
					let result = res [res.length-1].data
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

