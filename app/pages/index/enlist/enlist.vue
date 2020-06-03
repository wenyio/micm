<template>
	<view>
		<view class="search" style="position: sticky; top: 10rpx; z-index: 999;">
			<uni-search-bar @confirm="search" @input="input" @cancel="cancel" />	
		</view>
        <view class="body">
			<uni-list>
				<uni-list-item v-for="item in listData" 
				:show-arrow="false" 
				:title="item.title" @tap="detail(item.id)"  
				:note="item.organization + '\n举办时间: ' + item.begin +'\n举办地点: ' + item.address"/>
			</uni-list>
		</view>
        <view class="uni-fab-box">
        	<uni-fab ref="fab" :pattern="pattern" :content="content" :horizontal="horizontal" :vertical="vertical" :direction="direction" @trigger="trigger" />
        </view>
	</view>
</template>

<script>
	import {search} from '@/api/practice.js'
	import {list} from '@/api/practice.js'
	import uniFab from '@/components/uni-fab/uni-fab.vue'
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniList from '@/components/uni-list/uni-list.vue'
	import uniListItem from '@/components/uni-list-item/uni-list-item.vue'
	// import uniLoadMore from '@/components/uni-load-more/uni-load-more.vue';
	// var dateUtils = require('../../../common/util.js').dateUtils;	
	export default {
		components: {
			uniSearchBar,
			uniSection,
			uniList,
			uniListItem,
			uniFab
		},
		data() {
			return {
				listData: [],
				pattern: {
						color: '#7A7E83',
						backgroundColor: '#fff',
						selectedColor: '#007AFF',
						buttonColor: '#007AFF'
					},
					content: [{
							iconPath: '/static/image/l1.png',
							text: '发布',
							active: false
						},
						{
							iconPath: '/static/image/l2.png',
							text: '签到',
							active: false
						},]
				}
			},
		onLoad() {
			this.practice()
		},
		methods: {
			practice(){
				let that =this
				list().then (res =>{
					let result = res[res.length-1].data
					if(result.code === 20000){
						that.listData  = result.data
						// console.log(result.data)
					}
				})
			},
			detail(id){
				uni.navigateTo({
					url:"/pages/index/list/list?id=" + id
				});
			},
			trigger(e) {
				console.log(e.index)
				if(e.index == 1){
					uni.navigateTo({
						url:"/pages/index/activity/activity"
					});
				}else if(e.index == 0){
					uni.navigateTo({
						url:"/pages/index/new/new"
					});
				}
				this.content[e.index].active = !e.item.active
				uni.showModal({
					title: '提示',
					content: `您选择${this.content[e.index].active ? '发布' : '签到'}`,
					// success: function(res) {
					// 	if (res.confirm) {
					// 		console.log('用户点击确定')
					// 		uni.navigateTo({
					// 			url:"/pages/index/new/new"
					// 		});
					// 	} else if (res.cancel) {
					// 		console.log('用户点击取消')
							
					// 	}
					// }
				})
			},
			search(res) {
				search(res.value).then(res => {
					let result = res[res.length-1].data
					if (result.code === 20000) {
						console.log(result.data)
						this.listData = result.data
					} 
				})
				if (res.value == null || res.value == '') {
					this.practice()
				}
			},
			input(res) {
				this.searchVal = res.value
			},
			cancel(res) {
				this.practice()
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
		.search{
			flex-direction: row;
			flex-wrap: wrap;
			justify-content: center;
			padding: 0;
			font-size: 14px;
			background-color: #ffffff;
		}
		.body{
			/* #ifndef APP-NVUE */
					display: block;
					/* #endif */
					margin-top: 10rpx;
					color: #3b4144;
					background-color: #ffffff;
					font-size: 14rpx;
					line-height: 40rpx;
		}
		.body-text{
			font-size: 14rpx;
			line-height: 20rpx;
			color: #55007f;
		}
	    /* #endif */
		.example {
			padding: 0 15px;
		}
		
   
</style>
