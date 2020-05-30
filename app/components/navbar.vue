<template>
	<view>
		<view class="nav_wrap">
			<scroll-view class="nav" scroll-x="true">
				<!-- nav_item默认样式 active活动样式 -->
				<view class="nav_item" :class="activeNavId == nav.id ? 'active' : ''" @click="clickNav(nav.id)" v-for="nav in navList">
					{{nav.name}}
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			activeNavId: {
				type: Number,
				default: 1,
			},
			navList: {
				type: Array,
			}
		},
		methods: {
			clickNav(navId) {
				try{
					this.activeNavId = navId
				}catch(e){
					//TODO handle the exception
				}
			}
		}
	}
</script>

<style scoped>
	.nav {
		white-space: nowrap;
		padding: 5rpx 0;
	}
	.nav_item {
		padding: 20rpx 45rpx;
		font-size: 30rpx;
		display: inline-block;
		color: rgba(63,205,235,1);
	}
	.nav_item.active {
		border-bottom: 5rpx solid rgba(188,226,158,1);
		color: rgba(188,226,158,1);
	}
</style>

<style>
	/* 首页导航 */
	.nav_wrap {
		z-index: 9;
		position: sticky;
		top: 200rpx;
		box-shadow: 0px 2px 6px rgba(63,205,235,0.3);
		background-color: #FFFFFF;
		/* background:linear-gradient(-90deg,rgba(63,205,235,0.3),rgba(188,226,158,0.3)); */
	}
</style>
