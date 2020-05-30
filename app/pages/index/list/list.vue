<template>
	<view class="content">
		<uni-list>
			<uni-list-item :show-arrow="false" :title="detail.title" 
			:note="detail.organization + '\n活动积分: ' + detail.integral +'\n举办地点: ' + detail.address" />
		</uni-list>
		<view class="a">
			<uni-list>
				<uni-list-item :show-arrow="false" :title="detail.description" />
				<uni-list-item :show-arrow="false" title="时间安排"
				:note="'开始时间: ' + detail.begin +'\n结束时间: ' + detail.end" />
				<view class="menu">
					<!-- 获取活动签到图片 -->
					<image :src="practiceQrCode"></image>
				</view>
				<uni-list-item :show-arrow="false" title="注意:" note="此二维码是活动二维码, 不用于签到" />
			</uni-list>
		</view>
		<view class="goods-carts" style="position: sticky; bottom: 0rpx; z-index: 999;">
			<uni-goods-nav :options="options" :fill="true" :button-group="buttonGroup" @click="onClick" @buttonClick="buttonClick" />
		</view>
		<!-- 插屏弹窗 -->
		<uni-popup ref="qrCode" type="center" :mask-click="true">
			<view class="uni-image">
				<image class="image" :src="userQrCode" mode="scaleToFill" />
				<view class="uni-image-close" @click="closeQrCode">
					<uni-icons type="clear" color="#fff" size="30" />
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import { getById, judge, apply, notApply, getQrCode, adminCheck } from '@/api/practice.js'
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniList from '@/components/uni-list/uni-list.vue'
	import uniListItem from '@/components/uni-list-item/uni-list-item.vue'
	import uniGoodsNav from '@/components/uni-goods-nav/uni-goods-nav.vue'
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import uniIcons from '@/components/uni-icons/uni-icons.vue'
	export default {
		components: {
			uniSection,
			uniList,
			uniListItem,
			uniGoodsNav,
			uniPopup,
			uniIcons
		},
		data() {
			return {
				baoming:'报名',
				cancel:'取消报名',
				detail: {
				},
				options:[{
					icon: 'star',
					text: '扫码'
				},],
				buttonGroup: [{
					text: '报名',
					backgroundColor: '#ffa200 ',
					color: '#fff'
				},],
				userQrCode: null,
				practiceQrCode: null,
			}
		},
		onLoad(obj) {
			// console.log(obj.id)
			this.detail.id = obj.id
			this.getById()
			this.judge()
			this.getQrCode()
		},
		/**
		 * 监听导航栏按钮
		 * @param {Object} e
		 */
		onNavigationBarButtonTap(e) {
			if (e.index === 0) {
				//扫一扫
				this.scan()
			} 
		},
		methods: {
			scan(){
				if (this.detail.userId == uni.getStorageSync('user').id) {
					// 如果帖子的创建人id和当前用户id相同，调用扫码功能
					uni.scanCode({
						onlyFromCamera: true,
						success:(res)=>{
							// 扫成功了， 调用签到方法
							// console.log('条码内容：' + res.result);
							// TODO 实体机测试
							let data =  {
								"userId": res.result,
								"practiceId": this.detail.id
							}
							this.adminScanUser(data)
						}
					});
				} else {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '你不是创建人，无法给人签到'
					});
				}
			},
			/**
			 * 管理扫用户
			 */
			adminScanUser(data) {
				adminCheck(data).then(res => {
					let result = res[res.length-1].data
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				})
			},
			qrCode() {
				this.$refs['qrCode'].open()
			},
			closeQrCode() {
				this.$refs['qrCode'].close()
			},
			onClick(e) {
				if(e.index == 0){
					// 管理员扫码接口
					this.scan()
				} else {
					// 签到接口
					this.qrCode()
					this.userQrCode = uni.getStorageSync("userQrCode") 
				}
			},
			getById() {
				getById(this.detail.id).then(res => {
					let result = res[res.length-1].data
					if (result.code === 20000) {
						this.detail = result.data
					} 
				})
				
			},
			getQrCode(){
				getQrCode(this.detail.id).then(res => {
					let result = res[res.length-1].data
					if (result.code === 20000) {
						this.practiceQrCode = result.data.file
					} 
				})
			},
			/**
			 * 验证是否报名活动
			 */
			judge() {
				judge(this.detail.id).then(res => {
					let result = res[res.length-1].data
					// 已报名
					if (result.code === 20000) {
						this.buttonGroup[0].backgroundColor = '#ff0000 ';
						this.buttonGroup[0].text = this.cancel;
						this.options=[{
							icon: 'star',
							text: '扫码'
						},{
							icon: 'headphones',
							text: '签到'
						},]
					} 
				})
			},
			/**
			 * 报名活动
			 */
			apply() {
				apply(this.detail.id).then(res => {
					let result = res[res.length-1].data
					// 报名成功
					if (result.code === 20000) {
						this.buttonGroup[0].backgroundColor = '#ff0000 ';
						this.buttonGroup[0].text = this.cancel;
						this.options=[{
							icon: 'star',
							text: '扫码'
						},{
							icon: 'headphones',
							text: '签到'
						},]
					} 
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				})
			},
			/**
			 * 取消报名
			 */
			notApply() {
				notApply(this.detail.id).then(res => {
					let result = res[res.length-1].data
					// 取消报名成功
					if (result.code === 20000) {
						this.buttonGroup[0].backgroundColor = '#ffa200 ';
						this.buttonGroup[0].text = this.baoming;
						this.options = [{
							icon: 'star',
							text: '扫码'
						},]
					} 
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				})
			},
			buttonClick(e) {
				console.log(e.content);
				
				if(e.content.text == this.cancel){
					// 取消报名
					this.notApply()
				}	
				else{
					// 报名操作
					this.apply()
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
		background-color: #efeff4;
		min-height: 100%;
		height: auto;
	}
	/* #endif */
		.example {
			padding: 0 15px;
		}
	.content{
		margin-top: 10rpx;
	}
	.a{
		margin-top: 10rpx;
	}
	.menu {
		display: flex;
		justify-content: center;
	}
	/* 插屏广告 */
	.uni-image {
		position: relative;
	}
	
	.image {
		width: 200px;
		height: 200px;
	}
	
	.uni-image-close {
		margin-top: 20px;
		text-align: center;
	}
	
</style>
