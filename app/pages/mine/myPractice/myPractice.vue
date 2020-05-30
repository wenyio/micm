<template>
	<view>
		<view class="header" style="position: sticky; top: 10rpx; z-index: 999;">
			<view class="uni-padding-wrap uni-common-mt" >
				<uni-segmented-control :current="current" :values="items" style-type="button" active-color="#007aff" @clickItem="onClickItem" />
			</view>
		</view>	
		<view class="content">
			<view v-if="current === 0">
				<view class="example-body">
					<view v-for="item in list" :key="item.id" class="example-box" v-if="item.status == 0 || item.status == 10">
						<uni-card :title="item.created" :is-shadow="true" note="点击出示签到码" 
						@click="showCode" :extra="item.category_name" 
						:thumbnail="item.thumbnail" ><text class="content-box-text">{{ item.title }}</text></uni-card>
					</view>
				</view>		
	
			</view>
		</view>
		<view class="content">
			<view v-if="current === 1">
				<view class="example-body">
				    <view v-for="item in list" :key="item.id" class="example-box" v-if="item.status == 1">
						<uni-card :title="item.created" :is-shadow="true" note="点击出示签到码"
							@click="showCode" :extra="item.category_name" 
							:thumbnail="item.thumbnail" ><text class="content-box-text">{{ item.title }}</text></uni-card>
					</view>
				</view>				
			</view>
		</view>
		<uni-popup ref="showcode" type="center" :mask-click="true">
			<view class="uni-image">
				<image class="image" :src="code" mode="scaleToFill" />
				<view class="uni-image-close" @click="hideCode">
					<uni-icons type="clear" color="#fff" size="30" />
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import { getPractice, } from "@/api/practice.js"
	import { getUserQrCode } from "@/api/user.js"
	import uniPopup from "@/components/uni-popup/uni-popup.vue"
	import uniSegmentedControl from '@/components/uni-segmented-control/uni-segmented-control.vue'
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniCard from '@/components/uni-card/uni-card.vue'
	import uniIcons from '@/components/uni-icons/uni-icons.vue'
	export default {
		components: {
			uniSection,
			uniSegmentedControl,
			uniCard,
			uniPopup,
			uniIcons,
		},
		data() {
			return {
				data:{},
				code: null,
				list: [],
				items: ['待签到', '已签到',],
				current: 0,
				colorIndex: 0,
				title: 'uni-fab',
				directionStr: '垂直',
				horizontal: 'left',
				vertical: 'bottom',
				direction: 'horizontal',
				pattern: {
					color: '#7A7E83',
					backgroundColor: '#fff',
					selectedColor: '#007AFF',
					buttonColor: '#007AFF'
				},
			}
		},
		onBackPress() {
			if (this.$refs.fab.isShow) {
				this.$refs.fab.close()
				return true
			}
			return false
		},
		methods: {
			showCode() {
				this.code = uni.getStorageSync("userQrCode")
				this.$refs['showcode'].open()
			},
			hideCode() {
				this.code = null
				this.$refs['showcode'].close()
			},
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex
				}
			},
			onLoad(){
				getUserQrCode() // 获取用户二维码
				this.getPractice()
			},
			clickCard() {
				uni.showToast({
					title: '加载中 ',
					image:'../../static/image/wike.png'
					// icon: '../static/image/wike.png'
				})
			},
			getPractice() {
				let that = this
				getPractice().then (res =>{
					let result = res [res.length-1].data
					console.log(result)
					if(result.code === 20000){
						that.list = result.data
						console.log(that.list)
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
		background-color: #ffffff;
	}

	/* #endif */
	.example {
		padding: 0 15rpx;
	}
	.uni-common-mt {
		margin-top: 2rpx;
	}
	
	.uni-padding-wrap {
		width: 700rpx;
		padding: 0rpx 10rpx;
	}
	.header{
		line-height: 20rpx;
/* 		background-color: #ffffff; */
		margin-top: 20rpx;
		display: flex;
		justify-content: center;
	}
	.content-box-text{
		font-size: 30rpx;
	}
	
	
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