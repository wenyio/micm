  
<template>
	<view>
		<view class="u_info">
			<view class="title">昵称: </view>
			<input class="uni-input" v-model="data.nickname" maxlength="10" @input="onKeyInput" />
		</view>
		<view class="u_info">
			<view class="title">用户名: </view>
			<input class="uni-input" v-model="data.username" maxlength="10" @input="onKeyInput" />
		</view>
		<view class="u_info" style="color: #666;" @click="nothing()">
			<view class="title">邮箱: </view>
			<input class="uni-input" v-model="data.email" disabled="" maxlength="32" @input="onKeyInput" />
		</view>
		<view class="u_info" style="color: #666;" @click="nothing()">
			<view class="title">手机: </view>
			<input class="uni-input" v-model="data.phone"  disabled="true" maxlength="10" @input="onKeyInput" />
		</view>

		<view>
			<view style="margin-top: 100rpx;display: flex;justify-content: center;">
				<button class="save_info_f" type="default"  @click="updateUser">提交</button>
			</view>
		</view>
	</view>
</template>
<script>
	import { update, info } from '@/api/account.js'
	
	export default {
		data() {
			return {
				data: {
					"id": null,
					"nickname": '',
					"username": '',
					"phone": '',
					"email": ''
				}
			}
		},
		onShow() {
			this.data = uni.getStorageSync("user")
		},
		methods: {
			updateUser() {
				let that = this 
				update(that.data).then(res => {
					let result = res[res.length-1].data
					// console.log(result.code)
					if(result.code === 20000){
						that.getUserInfo()
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
					} 
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				})
			},
			bindPickerChange: function(e) {
				// console.log('picker发送选择改变，携带值为：' + e.target.value)
				this.user.sex = e.target.value
			},
			nothing() {
				uni.showToast({
				    icon: 'none',
					position: 'bottom',
				    title: '此字段需要独立修改！'
				});
			},
			onKeyInput: function(event) {
				this.inputValue = event.target.value
			},
			chooseIcon(){
				let that = this
				uni.chooseImage({
					count: 1, // 一张头像
					sizeType: ['original'], // 原图
					sourceType:['album'], // 从相册选择
					success: function (res) {
						that.newIcon = res.tempFilePaths[0];
						// 备份院头像路径
						// console.log(res.tempFilePaths[0])
						that.orderIcon = that.postIcon
						// 更换当前头像为新头像
						that.postIcon = that.newIcon
						// 是否确认修改
						uni.showModal({
							content: "确定更换吗~",
							success(res) {
								if(res.confirm) {
									// 上传到后台
									// that.updateIcon()
									uni.showToast({
									    icon: 'none',
										position: 'bottom',
									    title: '假装上传到后台'
									});
								} else {
									// 恢复旧头像
									that.postIcon = that.orderIcon
								}
							}
						})
					}
				})
			}
		}
	}
	
</script>

<style>
	.u_info{
		border-bottom: 1rpx solid #F5F2F0;
		padding: 30rpx;
		display: flex;
		justify-content: center;
	}
	
	.title {
		flex: 2;
		margin-top: 12upx;
	}
	.uni-input {
		flex: 6;
		margin-top: 15upx;
	}
	
	.save_info{
		margin-top: 100upx;
		background:linear-gradient(-90deg,rgba(63,205,235,1),rgba(188,226,158,1));
	}
	.face{
		
		flex-shrink:0;
		width:15vw;
		height:15vw;
		image{
			width:100%;
			height:100%;
			border-radius:100%
			}
		
	}
	.a{
		display: flex;
		justify-content: center;
		margin-left: -350rpx;
		margin-top: -15rpx;
	}
	.save_info_f{
	   
		width: 30%;
		background:linear-gradient(-90deg,rgba(63,205,235,1),rgba(63,205,235,1));
		
	}
</style>