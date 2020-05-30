<template>
	<view>	
		<view class="a">
			<view class="title">活动标题 </view> 
			<input class="uni-input" placeholder="请输入活动名称" maxlength="20" @input="onKeyInput" />
		</view>
		<view class="a">
			<view class="title">活动图片 </view> 
			<view class="uni-input">
				<image  @click="chooseIcon()" :src="practiceIcon" style="border-radius: 30upx;width: 270upx;height: 200upx; justify-content: left;"></image>
		    </view>
		</view>
		
		<view class="a">
			<view class="title">活动地点 </view> 
			<input class="uni-input" placeholder="请输入活动地点" maxlength="20" @input="onKeyInput" />
		</view>
		
		<view class="a">
			<view class="title">活动描述</view> 
			<input class="uni-input" placeholder="请输入活动描述" maxlength="20" @input="onKeyInput" />
		</view>
		<view class="a">
			<view class="title1">开始日期</view> 
			<view class="uni-input1">
				<picker mode="date" :value="date" :start="startDate" :end="endDate" @change="bindBeginDateChange">
					<view class="uni-input">{{date}}</view>
				</picker>
			</view>
			
			<view class="title1">开始时间</view>
			<view class="uni-input1">
				<picker mode="time" :value="time" start="09:01" end="21:01" @change="bindBeginTimeChange">
					<view class="uni-input">{{time}}</view>
				</picker>
			</view>
		</view>
		<view class="a">
			<view class="title1">结束日期</view> 
			<view class="uni-input1">
				<picker mode="date" :value="date" :start="startDate" :end="endDate" @change="bindBeginTimeChange">
					<view class="uni-input">{{date}}</view>
				</picker>
			</view>
			
			<view class="title1">结束时间</view>
			<view class="uni-input1">
				<picker mode="time" :value="time" start="09:01" end="21:01" @change="bindEndTimeChange">
					<view class="uni-input">{{time}}</view>
				</picker>
			</view>
		</view>
		<view class="a">
			<view class="title">活动分类</view> 
			<view class="uni-input1">
				<picker @change="chooseCategory" :value="index" :range="category" range-key="name">
					<view class="uni-input2">{{category[index].name}}</view>
				</picker>
			</view>
		</view>
		<view class="a">
			<view class="title">组织</view> 
			<view class="uni-input1">
				<picker @change="chooseOrigin" :value="index" :range="origination" range-key="name">
					<view class="uni-input2">{{origination[index].name}}</view>
				</picker>
			</view>
		</view>
		<view class="a">
			<view class="title">活动描述</view> 
			<input class="uni-input" type="number" placeholder="请输入活动积分" maxlength="2" @input="onKeyInput" />
		</view>
		<view style="margin-top: 100rpx;display: flex;justify-content: center;">
			<button class="save_info_f" type="default" >提交</button>
			<button class="save_info_f1" type="default" >重置</button>
		</view>
	</view>
</template>

<script>
	import { category, organization } from '@/api/practice.js'

	function getDate(type) {
		const date = new Date();
	
		let year = date.getFullYear();
		let month = date.getMonth() + 1;
		let day = date.getDate();
	
		if (type === 'start') {
			year = year - 60;
		} else if (type === 'end') {
			year = year + 2;
		}
		month = month > 9 ? month : '0' + month;;
		day = day > 9 ? day : '0' + day;
	
		return `${year}-${month}-${day}`;
	}
	export default {
		data() {
			return {
				data:{},
				index: 0,
				category: [{name:'默认分类'}],
				origination: [{name:'默认组织'}],
				array1: [{name:'5'},{name: '10'}, {name:'20'}, {name:'30'},{name:'50'}],
				date: getDate({
					format: true
				}),
				startDate:getDate('start'),
				startTime: '12:01',
				endDate:getDate('end'),
				endTime: '11:01',
				practiceIcon: '../../../static/image/beijing.jpg'
			}
		},
		onLoad() {
			this.getCategory()
			this.getOrigination()
		},
		methods: {
			chooseCategory: function(e) {
				console.log('选择分类' + e.target.value)
				console.log(this.category[e.target.value])
				this.index = e.target.value
			},
			chooseOrigin: function(e) {
				console.log('选择组织：' + e.target.value)
				this.index = e.target.value
			},
			bindBeginDateChange: function(e) {
				console.log('选择开始日期：' + e.target.value)
				console.log(e.target.value)
			},
			bindBeginTimeChange: function(e) {
				console.log('选择开始时间' + e.target.value)
			},
			bindEndDateChange: function(e) {
				console.log('选择结束日期：' + e.target.value)
			},
			bindEndTimeChange: function(e) {
				console.log('选择结束时间' + e.target.value)
			},
			getCategory(){
				let that = this
				category().then(res =>{
					let result = res [res.length-1].data
					if(result.code === 20000){
						// console.log(result.data)
						that.category= result.data
					}
				})
			},
			getOrigination(){
				let that = this
				organization().then(res =>{
					let result = res [res.length-1].data
					if(result.code === 20000){
						// console.log(result.data)
						that.origination= result.data
					}
				})
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
						that.orderIcon = that.practiceIcon
						// 更换当前头像为新头像
						that.practiceIcon = that.newIcon
						// 是否确认修改
						uni.showModal({
							content: "确定更换吗~",
							success(res) {
								if(res.confirm) {
									// 上传到后台
									// that.updateIcon(that.newIcon)
									uni.showToast({
									    icon: 'none',
										position: 'bottom',
									    title: '假装上传到后台'
									});
								} else {
									// 恢复旧头像
									that.practiceIcon = that.orderIcon
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
	/* 头条小程序组件内不能引入字体 */
	/* #ifdef MP-TOUTIAO */
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}

	/* #endif */

	/* #ifndef APP-NVUE */
	page {
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
		background-color: #ffffff;
		min-height: 100%;
		height: auto;
		margin-top: 6upx;
	}
	.a{
		border-bottom: 1rpx solid #F5F2F0;
		padding: 30rpx;
		display: flex;
		justify-content: center;
		background-color: #FFFFFF;
		font-size: 30rpx;
	}
	
	.title {
		flex: 2;
		margin-top: -5upx;
	}
	.uni-input {
		flex: 6;
		display: flex;
		justify-content: right;
		font-size: 25rpx;
	}
	.uni-input1{
		flex: 2;;
		margin-left: 50rpx;
		width: 25%;
	}
	.uni-input2{
		display: flex;
		margin-left: -190rpx;
		margin-top: -5rpx;
	}
	
	.save_info_f{
	   
		width: 30%;
		background:linear-gradient(-90deg,rgba(63,205,235,1),rgba(63,205,235,1));
		
	}
	.save_info_f1{
	   
		width: 30%;
		background:linear-gradient(-90deg,rgba(229, 235, 213, 1),rgba(229, 235, 213, 1));
		
	}
	

	
</style>