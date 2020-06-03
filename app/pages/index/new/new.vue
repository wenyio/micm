<template>
	<view>	
		<view class="a">
			<view class="title">活动标题 </view> 
			<input class="uni-input" v-model="form.title" placeholder="请输入活动名称" maxlength="20" />
		</view>
		<view class="a">
			<view class="title">活动图片 </view> 
			<view class="uni-input">
				<image  @click="chooseIcon()" :src="form.icon" style="border-radius: 30upx;width: 270upx;height: 200upx; justify-content: left;"></image>
		    </view>
		</view>
		
		<view class="a">
			<view class="title">活动地点 </view> 
			<input class="uni-input" v-model="form.address" placeholder="请输入活动地点" maxlength="20" />
		</view>
		
		<view class="a">
			<view class="title">活动描述</view> 
			<input class="uni-input" v-model="form.description" placeholder="请输入活动描述" maxlength="20" />
		</view>
		<view class="a">
			<view class="title1">开始日期</view> 
			<view class="uni-input1">
				<view class="value">
					<BiaofunDatetimePicker placeholder="请选择日期时间" 
					defaultValue="2020-06-03 9:52" 
					start="2000-01-01" 
					end="2100-01-01" 
					fields="minute" 
					@change="changeBeginDatetimePicker">
					</BiaofunDatetimePicker>
				</view>
			</view>
		</view>
		<view class="a">
			<view class="title1">结束日期</view> 
			<view class="uni-input1">
				<view class="value">
					<BiaofunDatetimePicker placeholder="请选择日期时间" 
					defaultValue="2020-06-03 10:05" 
					start="2000-01-01" 
					end="2100-01-01" 
					fields="minute" 
					@change="changeEndDatetimePicker">
					</BiaofunDatetimePicker>
				</view>
			</view>
		</view>
		<view class="a">
			<view class="title">活动分类</view> 
			<view class="uni-input1">
				<picker @change="chooseCategory" :value="category_index" :range="category" range-key="name">
					<view class="uni-input2">{{category[category_index].name}}</view>
				</picker>
			</view>
		</view>
		<view class="a">
			<view class="title">组织</view> 
			<view class="uni-input1">
				<picker @change="chooseOrigin" :value="organization_index" :range="origination" range-key="name">
					<view class="uni-input2">{{origination[organization_index].name}}</view>
				</picker>
			</view>
		</view>
		<view class="a">
			<view class="title">活动积分</view> 
			<input class="uni-input" v-model="form.integral" type="number" placeholder="请输入活动积分" maxlength="2"/>
		</view>
		<view style="margin-top: 100rpx;display: flex;justify-content: center;">
			<button class="save_info_f" type="default" @click="updateform">提交</button>
			<button class="save_info_f1" type="default" @click="reset" >重置</button>
		</view>
	</view>
</template>

<script>
	import { category, organization, add } from '@/api/practice.js'
	import { uploadFile } from "@/api/sys.js"
	import BiaofunDatetimePicker from '@/components/biaofun-datetime-picker/biaofun-datetime-picker.vue';

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
		components: {
			BiaofunDatetimePicker
		},
		data() {
			return {
				data:{},
				category_index: 0,
				organization_index: 0,
				category: [{name:'默认分类'}],
				origination: [{name:'默认组织'}],
				form: {
					title: '默认活动名',
					icon: 'http://micm.oss-cn-shanghai.aliyuncs.com/068446f8-cd2c-43c5-a414-391ceda16639.jpg',
					address: '默认线上活动',
					description:'默认描述',
					begin:null,
					end:null,
					organization:'',
					organizationId: null,
					categoryId: null,
					integral:'5',
				},
				newIcon:'',
				orderIcon: '',
				
			}
		},
		onLoad() {
			this.getCategory()
			this.getOrigination()
		},
		methods: {
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
						that.orderIcon = that.form.icon
						// 更换当前头像为新头像
						that.form.icon = that.newIcon
						// 是否确认修改
						uni.showModal({
							content: "确定更换吗~",
							success(res) {
								if(res.confirm) {
									// 上传到后台, 拿到 url
									that.uploadAvatar(that.newIcon)
								} else {
									// 恢复旧头像
									that.form.icon = that.orderIcon
								}
							}
						})
					}
				})
			},
			/**
			 * 上传头像
			 */
			uploadAvatar(avatar) {
				// console.log(avatar)
				let that = this
				uploadFile(avatar).then(res => {
					console.log(res)
					try{
						let result = JSON.parse(res[res.length-1].data)
						if (result.code === 20000) {
							// 更新头像
							that.form.icon = result.data.path // 换成服务器路径
						}
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: result.message
						});
					}catch(e){
						//TODO handle the exception
						that.form.icon = that.orderIcon
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '网络异常，请重试'
						});
					}
					
				})
			},
			chooseCategory: function(e) {
				console.log('选择分类' + e.target.value)
				// console.log(this.category[e.target.value])
				this.form.categoryId = this.category[e.target.value].id
				this.category_index = e.target.value
			},
			chooseOrigin: function(e) {
				console.log('选择组织：' + e.target.value)
				// console.log(this.origination[e.target.value])
				this.form.organizationId = this.origination[e.target.value].id
				this.form.organization = this.origination[e.target.value].name
				this.organization_index = e.target.value
			},
			/**
			 * 选择日期时间
			 * @param {Object} date 日期数据
			 */ 
			changeBeginDatetimePicker(date) {
				// console.log('选择日期：', date.fmt3);
				this.form.begin = date.fmt3 + ':00'
			},
			changeEndDatetimePicker(date) {
				// console.log('选择日期：', date.fmt3);
				this.form.end = date.fmt3 + ':00'
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
			updateform(){
				if (this.form.categoryId == null || this.form.organizationId == null) {
					uni.showToast({
					    icon: 'none',
						position: 'bottom',
					    title: '请选择分类/组织'
					});
					return
				}
				
				if (this.form.begin == null || this.form.end == null) {
					uni.showToast({
					    icon: 'none',
						position: 'bottom',
					    title: '请选择结束/开始'
					});
					return
				}
				
				console.log(this.form)
				// 数据没问题，调用接口
				// TODO 调用接口
				add(this.form).then(res =>{
					let result = res [res.length-1].data
					if(result.code === 20000){
						uni.showToast({
						    icon: 'none',
							position: 'bottom',
						    title: result.message
						});
						this.reset() // 重置表单
					}
				})
			},
			reset() {
				this.form = {
					title: '默认活动名',
					icon: 'http://micm.oss-cn-shanghai.aliyuncs.com/068446f8-cd2c-43c5-a414-391ceda16639.jpg',
					address: '默认线上活动',
					description:'默认描述',
					begin:null,
					end:null,
					organization:'',
					organizationId: null,
					categoryId: null,
					integral:'5',
				}
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