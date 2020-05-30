  
<template>
	<view>
		<view class="u_info">
			<view class="title">姓名: </view>
			<input class="uni-input" v-model="data.name" maxlength="10" @input="onKeyInput" />
		</view>
		<view class="u_info">
			<view class="title">国籍: </view>
			<input class="uni-input" v-model="data.country" maxlength="10" @input="onKeyInput" />
		</view>
		<view class="u_info">
			<view class="title">性别: </view>
			<input class="uni-input" v-model="data.sex" maxlength="10" @input="onKeyInput" />
		</view>
		<view class="u_info">
			<view class="title">年龄: </view>
			<input class="uni-input" v-model="data.age" maxlength="10" @input="onKeyInput" />
		</view>
		<view class="u_info">
			<view class="title">生日: </view>
			<input class="uni-input" v-model="data.birthday" maxlength="10" @input="onKeyInput" />
		</view>
		<view class="u_info">
			<view class="title">学历: </view>
			<input class="uni-input" v-model="data.highestOfEducation" maxlength="10" @input="onKeyInput" />
		</view>
		<view>
			<view style="margin-top: 100rpx;display: flex;justify-content: center;">
				<button class="save_info_f" type="default" >提交</button>
			</view>
		</view>
	</view>
</template>
<script>
	
	import {detailInfo} from '@/api/practice.js'
	export default {
		data() {
			return {
				data: {},
			}
		},
		onShow() {
			// this.data = uni.getStorageSync("user")
			this.getDetailInfo()
			// this.onKeyInput()
		},
	
		methods: {
			onKeyInput(){
				
			},
			getDetailInfo() {
				let that = this
				detailInfo().then (res =>{
					let result = res [res.length-1].data
					// console.log(result)
					if(result.code === 20000){
						that.data = result.data
					}
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				});
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
	
	.save_info_f{
	   
		width: 30%;
		background:linear-gradient(-90deg,rgba(63,205,235,1),rgba(63,205,235,1));
		
	}
</style>