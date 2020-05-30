<template>
	<view>
		
		
		<view class="goods-carts">
			<uni-goods-nav :button-group="buttonGroup" @click="onClick" @buttonClick="buttonClick" />
		</view>

	</view>
</template>

<script>
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniGoodsNav from '@/components/uni-goods-nav/uni-goods-nav.vue'
	export default {
		components: {
			uniSection,
			uniGoodsNav
		},
		data() {
			return {
				// options: [{
				// 	icon: 'headphones',
				// 	text: '客服'
				
				// }],
				buttonGroup: [{
						text: '报名',
						backgroundColor: '#ffa200 ',
						color: '#fff'
					},
				]
			}
		},
		onLoad() {},
		methods: {
			onClick(e) {
				uni.showToast({
					title: `点击${e.content.text}`,
					icon: 'none'
				})
			},
			buttonClick(e) {
				console.log(e)
				this.options[2].info++
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


	.goods-carts {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		position: fixed;
		left: 0;
		right: 0;
		bottom: 0;
	}
</style>