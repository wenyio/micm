<template>
	<view class="page">
		<view class="header" style="position: sticky; top: 10rpx; z-index: 999;">
			<view class="uni-padding-wrap uni-common-mt" >
				<uni-segmented-control :current="current" :values="items" style-type="text" active-color="#007aff" @clickItem="onClickItem" />
			</view>
		</view>	
		<view class="search" style="position: sticky; top: 10rpx; z-index: 999;">
			<uni-search-bar @confirm="search" @input="input" @cancel="cancel" />	
		</view>
		
		<view class="content">
			<view v-if="current === 0">
				<view class="example-body">
					<view v-for="item in list" :key="item.id" class="example-box" v-if="item.status == 0">
						<uni-card :title="item.title" :is-shadow="item.shadow" :note="item.note" :extra="item.extra" :thumbnail="item.thumbnail" @click="clickCard"><text class="content-box-text">{{ item.content }}</text></uni-card>
					</view>
				</view>		
			</view>
			<view v-if="current === 1">
				<view class="example-body">
					<view v-for="item in list" :key="item.id" class="example-box" v-if="item.status == 1">
						<uni-card :title="item.title" :is-shadow="item.shadow" :note="item.note" :extra="item.extra" :thumbnail="item.thumbnail" @click="clickCard"><text class="content-box-text">{{ item.content }}</text></uni-card>
					</view>
				</view>		
			</view>
		</view>
		
	</view>
</template>

<script>
    import uniCard from '@/components/uni-card/uni-card.vue'	
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniSegmentedControl from '@/components/uni-segmented-control/uni-segmented-control.vue'
	export default {
		components: {
			uniSection,
			uniSegmentedControl,
		    uniSearchBar,
			uniCard
		},
		
		data() {
			return {
				list: [{
						id: 0,
						title: '',
						content: '优秀团员',
						shadow: true,
						note: '申请时间：2020-04-22',
						extra: '',
						thumbnail: 'https://img-cdn-qiniu.dcloud.net.cn/new-page/uni.png',
						status:1,
	
					},],
				
			items: ['待审核', '已通过',],
			current: 0,
			colorIndex: 0,	
			}
		},
		methods: {
			search(res) {
				uni.showToast({
					title: '搜索：' + res.value,
					icon: 'none'
				})
			},
			input(res) {
				this.searchVal = res.value
			},
			cancel(res) {
				uni.showToast({
					title: '点击取消，输入值为：' + res.value,
					icon: 'none'
				})
			},
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex
				}
			},
		}
	}
</script>

<style>
	.page{
		background-color: #efeff4;
		line-height: 40rpx;
	}
	.search{
		line-height: 50rpx;
	}

</style>
