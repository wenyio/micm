<template>
	<view class="wrapper">
		<view v-if="isHistory" class="history-box">
			<view v-if="historyList.length > 0">
				<view class="history-title">
					<text>搜索历史</text>
					<text class="uni-icon uni-icon-trash" @click="clearSearch"></text>
				</view>
				<view class="history-content">
					<view class="history-item" v-for="(item, index) in historyList" :key="index">
						{{ item.name }}
					</view>
				</view>
			</view>
			<view v-else class="no-data">您还没有历史记录</view>
		</view>
		<view v-else class="history-box">
			<view v-if="historyList.length > 0" class="history-list-box">
				<view
					class="history-list-item"
					v-for="(item, index) in historyList"
					:key="index"
					@click="listTap(item)"
				>
					<rich-text :nodes="item.title" @click="goDetail(item.id)"></rich-text>
				</view>
			</view>
			<view v-else class="no-data">没有搜索到相关内容</view>
		</view>
	</view>
</template>

<script>
import util from '@/components/amap-wx/js/util.js';
import uniSection from '@/components/uni-section/uni-section.vue'
import {search} from '@/api/practice.js'

export default {
	data() {
		return {
			historyList: [],
			isHistory: true,
			list: [],
			flng: true,
			timer: null
		};
	},
	onLoad() {
		// 本示例用的是高德 sdk ，请根据具体需求换成自己的服务器接口。
		this.amapPlugin = util.mapInit();
		this.historyList = uni.getStorageSync('search:history');
	},
	methods: {
		/**
		 * 列表点击
		 */
		listTap(item) {
			item = JSON.parse(JSON.stringify(item));
			// 如果当前是历史搜索页面 ，那么点击不储存,直接 跳转
			if (this.history) {
				return;
			} else {
				this.isHistory = true;

				// 去做一些相关搜索功能 ，这里直接返回到上一个页面
				// 点击列表存储搜索数据
				util.setHistory(item);
				uni.navigateBack();
			}
		},
		/**
		 * 清理历史搜索数据
		 */
		clearSearch() {
			uni.showModal({
				title: '提示',
				content: '是否清理全部搜索历史？该操作不可逆。',
				success: res => {
					if (res.confirm) {
						this.historyList = util.removeHistory();
					}
				}
			});
		},
		/**
		 * 关键字搜索
		 * 调用高德地图关键字搜索api
		 */
		getInputtips(val) {
			search(val).then(res => {
				let result = res[res.length-1].data
				if (result.code === 20000) {
					this.historyList = result.data
					util.setHistory(val);
				} 
			})
		},
		/**
		 * 去活动详情页
		 * @param {Object} detail
		 */
		goDetail: function(id){
			console.log(id)
			// 去详情页，把id传过去
			// uni.navigateTo({
			// 	url: '../detail/detail_game?detailDate=' + encodeURIComponent(JSON.stringify(detail))
			// })
		},
	},
	/**
	 * 当 searchInput 输入时触发
	 */
	onNavigationBarSearchInputChanged(e) {
		let text = e.text;
		if (!text) {
			this.isHistory = true;
			this.historyList = [];
			this.historyList = uni.getStorageSync('search:history');
			
			return;
		} else {
			this.isHistory = false;
			this.getInputtips(text);
		}
	},
	/**
	 * 点击软键盘搜索按键触发
	 */
	onNavigationBarSearchInputConfirmed(e) {
		let text = e.text;
		if (!text) {
			this.isHistory = true;
			this.historyList = [];
			this.historyList = uni.getStorageSync('search:history');
			uni.showModal({
				title: '提示',
				content: '请输入内容。',
				success: res => {
					if (res.confirm) {
					}
				}
			});
			return;
		} else {
			// 记录到历史
			util.setHistory(text);
			uni.navigateBack();
		}
	},
	/**
	 *  点击导航栏 buttons 时触发
	 */
	onNavigationBarButtonTap() {
		// uni.showModal({
		// 	title: '提示',
		// 	content: '点击确定，修改输入框的内容为abc',
		// 	success: res => {
		// 		if (res.confirm) {
		// 			const currentWebview = this.$mp.page.$getAppWebview();
		// 			currentWebview.setTitleNViewSearchInputText("abc");
		// 		}
		// 	}
		// });
	},
	components: {
		uniSection
	},
};
</script>

<style>
.history-title {
	display: flex;
	justify-content: space-between;
	padding: 20upx 30upx;
	padding-bottom: 0;
	font-size: 34upx;
	color: #333;
}
.history-title .uni-icon {
	font-size: 40upx;
}
.history-content {
	display: flex;
	flex-wrap: wrap;
	padding: 15upx;
}
.history-item {
	padding: 4upx 35upx;
	border: 1px #f1f1f1 solid;
	background: #fff;
	border-radius: 50upx;
	margin: 12upx 10upx;
	color: #999;
}
.history-list-box {
	/* margin: 10upx 0; */
}
.history-list-item {
	padding: 30upx 0;
	margin-left: 30upx;
	border-bottom: 1px #EEEEEE solid;
	font-size: 28upx;
}

.no-data {
	text-align: center;
	color: #999;
	margin: 100upx;
}
</style>
