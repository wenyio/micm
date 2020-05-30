<template>
	<view >
		<view class="content">
		<view class="text-box" scroll-y="true">
			<view>
				<image  class="img" src="../../../static/image/专家讲座.png"></image>
				<text class="a">你的活动积分是：{{integral}}</text>
				<!-- <button class="button">申请</button> -->
			</view>
		</view>
		</view>		
		<view class="list1">
			<view >
				<view @click="scale(1)">
					<uni-section title="活动类" type="line"></uni-section>
				</view>
				<view :animation="animationData1">
					<uni-list class="box" v-if="isShow1">
						<uni-list-item v-for="item in list" :title="item.title + ((item.status==11) ? '' : ' （未结算）')" :rightText="item.integral+''" />
					</uni-list>
				</view>
			</view>
			<view >
				<view @click="scale(2)">
					<uni-section title="申请类" type="line"></uni-section>
				</view>
				<view :animation="animationData2">
					<uni-list class="box" v-if="isShow2">
						...
					</uni-list>
				</view>
			</view>
		</view>
	</view>
	
</template>

<script>
	import {getPracticeIntegral,getPractice} from '@/api/practice.js'
	import cmdCelItem from "@/components/cmd-cell-item/cmd-cell-item.vue"
	import uniCard from '@/components/uni-card/uni-card.vue'
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniList from '@/components/uni-list/uni-list.vue'
	import uniListItem from '@/components/uni-list-item/uni-list-item.vue'
		export default {
			components: {
				uniSection,
				uniList,
				uniListItem,
				uniCard
			},
		data() {
			return {
				integral:'',
				list: [],
				animationData1: '',
				animationData2: '',
				isShow1: true,
				isShow2: true,
			}	
			
		},
		onUnload() {
			this.animationData1 = ''
			this.animationData2 = ''
		},
		onLoad() {
			this.animation = uni.createAnimation()
			this.getPracticeIntegral()
			this.getPractice()
		},
		onShow() {
			this.isLogin()
		},
		methods: {
			scale: function (index) {
				let that = this
				if (index === 1) {
					that.animation.scale(0).step()
					if (!that.isShow1) {
						that.animation.scale(1).step()
					}
					setTimeout(function(){
						that.isShow1 = !that.isShow1
					}, 250)
					
					that.animationData1 = that.animation.export()
				} else if (index === 2) {
					that.animation.scale(0).step()
					if (!that.isShow2) {
						that.animation.scale(1).step()
					}
					setTimeout(function(){
						that.isShow2 = !that.isShow2
					}, 250)
					
					that.animationData2 = that.animation.export()
				}
			},
			getPracticeIntegral(){
				let that =this
				getPracticeIntegral().then (res =>{
					let result = res [res.length-1].data
					// console.log(result)
					if(result.code === 20000){
						that.integral = result.data
					}
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				})
			},
			getPractice(){
				let that = this
				getPractice().then (res =>{
					let result = res [res.length-1].data
					if (result.code === 20000) {
						that.list = result.data
						console.log(that.list)
					} else {
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: result.message
						});
					}
				})
			}
		}
	}
</script>

<style>
	.img{
		width: 150rpx;
		height: 120rpx;
		margin-top: 10rpx;
	}
	.button{
		width: 150rpx;
		height: 80rpx;
		background-color: #1CBBB4;
		margin-right: -100rpx;
	}
	.content{
		display: flex;
		justify-content: center;
	}
	.text-box {
		margin-top: 5rpx;
		width: 80%;
		border-radius: 20rpx;
		display: flex;
		min-height: 280upx;
		background-color: #62ddff;
		justify-content: center;
		/* align-items: center; */
		text-align: center;
		font-size: 40upx;
		color: #ffffff;
		line-height: 1.8;
	}
	
	.a{
		display: flex;
	}
	
</style>
