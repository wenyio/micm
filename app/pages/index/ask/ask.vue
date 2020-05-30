<template>
	<view>
		
		<uni-list>
			<uni-list-item  thumb="https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png" show-arrow="true" title="前端开发" note="江苏省-南京市玄武区-不限-不限
			南京光际信息科技有限公司"
			 rightText="2020.04.04申请" />
			<uni-list-item  thumb="https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png" show-arrow="true" title="web前端开发" note="江苏省-南京市-不限-不限
			 南京网燃网络有限公司"
			  rightText="2020.04.04申请" />
			<uni-list-item  thumb="https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png" show-arrow="true" title="web前端开发实习生" note="江苏省-南京市-全职-大专
			 南京正之印软件技术有限公司"
			  rightText="2020.03.01申请" />
		</uni-list>

	</view>
</template>

<script>
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniList from '@/components/uni-list/uni-list.vue'
	import uniListItem from '@/components/uni-list-item/uni-list-item.vue'
	export default {
		components: {
			uniSection,
			uniList,
			uniListItem
		},
		data() {
			return {
				extraIcon1: {
					color: '#007aff',
					size: '22',
					type: 'gear-filled'
				},
				extraIcon2: {
					color: '#4cd964',
					size: '22',
					type: 'image'
				}
			}
		},
		methods: {
			switchChange(e) {
				uni.showToast({
					title: 'change:' + e.value,
					icon: 'none'
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
		background-color: #efeff4;
		min-height: 100%;
		height: auto;
		margin-top: 10rpx;
	}

	

	


	

	/* #endif */
	.example {
		padding: 0 15rpx;
	}

	


	


	
</style>