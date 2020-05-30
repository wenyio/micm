<template>
	<view class="calendar-content" v-if="showCalendar">
		<view>
			<!-- 插入模式 -->
			<uni-calendar :selected="info.selected" :showMonth="true" @change="clickCalendar" @monthSwitch="monthSwitch" />
		</view>
		
		<timeline>
			<timelineItem v-for="item in list" :leftTime='item.created' v-if="clickdate == item.ymd || clickdate == null">
				<view class="tripItem" >
					<view class="title">{{item.title}}</view>
					<view class="tips">
						<view class="tag-view">
							<view class="tag-view-item0" >{{item.time}}</view>
							<uni-tag class="tag-view-item1" v-if="item.status == 11" :circle="true" text="完成" type="success" size="small" />
							<uni-tag class="tag-view-item1" v-if="item.status == 0" :circle="true" text="缺席" type="error" size="small" />
							<uni-tag class="tag-view-item1" v-if="item.status == 1" :circle="true" text="迟到" type="warning" size="small" />
							<uni-tag class="tag-view-item1" v-if="item.status == 10" :circle="true" text="早退" type="warning" size="small" />
						</view>
					</view>
				</view>
			</timelineItem>
			<view style="color: #8799A3;font-size: 24rpx;">
				<text>呀！我也是有底线的~</text>
			</view>
		</timeline>	
	</view>
</template>

<script>
	import { getPractice, } from "@/api/practice.js"
	import uniSection from '@/components/uni-section/uni-section.vue'
	import uniCalendar from '@/components/uni-calendar/uni-calendar.vue'
	import timeline from '@/components/chenbin-timeline/timeLine.vue'
	import timelineItem from '@/components/chenbin-timeline/timelineItem.vue'
	import uniTag from '@/components/uni-tag/uni-tag.vue'
	var dateUtils = require('../../common/util.js').dateUtils;
	export default {
		components: {
			uniSection,
			uniCalendar,
			timeline,
			timelineItem,
			uniTag
		},
		data() {
			return {
				showCalendar: false,
				list: null,
				info: {
					// date: '2019-08-15',
					// startDate: '2019-06-15',
					// endDate: '2019-010-15',
					lunar: true,
					range: true,
					insert: false,
					selected: []
				},
				today: new Date(), // 今天时间
				clickdate: null, // 点击的时间
			}
		},
		onShow() {
			this.isLogin();
			this.getPractice()
		},
		onReady() {
			this.$nextTick(() => {
				this.showCalendar = true
			})
		},
		methods: {
			open() {
				this.$refs.calendar.open()
			},
			confirm(e) {
				console.log('confirm 返回:', e)
			},
			monthSwitch(e) {
				console.log('monthSwitchs 返回:', e)
			},
			/**
			 * 点击日历时间
			 * @param {Object} e
			 */
			clickCalendar(e) {
				this.clickdate = e.fulldate
				console.log(this.clickdate)
			},
			getPractice() {
				let that = this
				getPractice().then (res =>{
					let result = res [res.length-1].data
					console.log(result)
					if(result.code === 20000){
						// TODO create 改成begin
						that.list = result.data
						that.list.forEach(item => {
							item.ymd = dateUtils.ymd(item.created)
							item.time = dateUtils.time(item.created)
							item.created = dateUtils.approach(item.created)
							console.log(item.created)
						})
						console.log(that.list)
					}
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: result.message
					});
				});
			},
		},
		/**
		 * 监听导航栏按钮
		 * @param {Object} e
		 */
		onNavigationBarButtonTap(e) {
			if (e.index === 0) {
				this.clickdate = null
			} else if (e.index === 1) {
				uni.navigateTo({
					url:"/pages/index/search/search"
				});
			}
		},
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

	view {
		font-size: 14px;
		line-height: inherit;
	}

	.example {
		padding: 0 15px 15px;
	}

	.example-body {
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
		padding: 0;
		font-size: 14px;
		background-color: #ffffff;
	}

	/* #endif */
	.example {
		padding: 0 15px;
	}
	
	.tripItem {
		height:140rpx;
		padding: 20rpx 30rpx;
		box-sizing: border-box;
		background:rgba(255,255,255,1);
		box-shadow:0px 0px 20px 0px rgba(0,0,0,0.08);
		border-radius:10px;
		margin-bottom: 30rpx;
		.title {
			font-size:28rpx;
			font-family:PingFangSC-Medium,PingFang SC;
			font-weight:500;
			color:rgba(51,51,51,1);
		}
		.tips {
			font-size:22rpx;
			font-family:PingFangSC-Regular,PingFang SC;
			font-weight:400;
			color:rgba(153,153,153,1);
			margin-top: 20rpx;

		}
	}
	
	.tag-view {
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		/* flex-direction: column; */
		margin: 10rpx 15rpx;
		width: 100%;
		justify-content: baseline;
	}
	.tag-view-item0 {
		width: 16%;
		margin-left: -18rpx;
	}
	.tag-view-item1 {
		width: 16%;
		margin-left: 278rpx;
	}
</style>