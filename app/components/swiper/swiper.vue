<template>
	<view class="content">
		<uni-swiper-dot :info="info" :current="current" :mode="mode" :dots-styles="dotsStyles" field="content">
			<swiper class="swiper-box" @change="change">
				<swiper-item v-for="(item, index) in info" :key="index">
					<view :class="item.colorClass" class="swiper-item">
						<image class="image" :src="item.url" mode="aspectFill" />
					</view>
				</swiper-item>
			</swiper>
		</uni-swiper-dot>
		
	</view>
</template>

<script>
	import uniSwiperDot from '@/components/uni-swiper-dot/uni-swiper-dot.vue'
	
	export default {
		name: 'swiper',
		components: {
			uniSwiperDot
		},
		data() {
			return {
				info: [{
					colorClass: 'uni-bg-red',
					url: 'https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/shuijiao.jpg',
					content: '内容 A'
				},
				{
					colorClass: 'uni-bg-green',
					url: 'https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/muwu.jpg',
					content: '内容 B'
				},
				{
					colorClass: 'uni-bg-blue',
					url: 'https://img-cdn-qiniu.dcloud.net.cn/uniapp/images/cbd.jpg',
					content: '内容 C'
				}],
				current: 0,
				mode: 'dot',
				dotsStyles: {
					backgroundColor: 'rgba(0, 0, 0, .3)',
					border: '1px rgba(0, 0, 0, .3) solid',
					color: '#fff',
					selectedBackgroundColor: 'rgba(0, 0, 0, .9)',
					selectedBorder: '1px rgba(0, 0, 0, .9) solid',
				},
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	
</style>
