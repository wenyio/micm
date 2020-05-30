<template>
	<view>
		<view style="position: sticky; top: 0; z-index: 999;">
			<view class="header">
				<view class="userinfo">
					<view class="face"><image :src="userinfo.avatar" @click="chooseIcon()"></image></view>
					<view class="info">
						<view class="username" @click="information">{{userinfo.nickname}}</view>
						<view class="id">学号:{{userinfo.account}}</view>	
					</view>
				</view>
				<view class="setting"><image src="../../static/image/setting.png" @click="settings"></image></view>
			</view>
			<view class="orders">
				<view class="box">
					<view class="label" v-for="(row,index) in orderTypeLise" :key="row.name" hover-class="hover"  @tap="toOrderType(index)">
						<view class="icon"><view class="badge" v-if="row.badge>0">{{row.badge}}</view><image :src="'../../static/HM-PersonalCenter/'+row.icon"></image></view>
						{{row.name}}
					</view>
				</view>
			</view> 
		</view>
		<view class="list" v-for="(list,list_i) in severList" :key="list_i">
			<view class="li" v-for="(li,li_i) in list" @tap="toPage(list_i,li_i)" v-bind:class="{'noborder':li_i==list.length-1}"  hover-class="hover" :key="li.name" >
				<view class="icon"><image :src="'../../static/HM-PersonalCenter/sever/'+li.icon"></image></view>
				<view class="text">{{li.name}}</view>
				<image class="to" src="../../static/HM-PersonalCenter/to.png"></image>
			</view>
		</view>
	</view>
</template>
<script>
	import { modifyAvatar, getUserInfo } from '@/api/account.js'
	import { uploadFile } from "@/api/sys.js"
	
	export default {
		data() {
			return {
				data:{
					"username": "",
					"path": " "
				},
				
				//#ifdef APP-PLUS
				isH5Plus:true,
				//#endif
				//#ifndef APP-PLUS
				isH5Plus:false,
				//#endif
				userinfo:{},
				orderIcon: '',
				orderTypeLise:[
					//name-标题 icon-图标 badge-角标
					{name:'积分统计',icon:'../../static/image/xuefen.png',badge:1},
					{name:'我的活动',icon:'../../static/image/huodong.png',badge:6},
					{name:'活动报名',icon:'../../static/image/baoming.png',badge:2},
					{name:'我的问卷',icon:'../../static/image/kaoshi.png',badge:2},
					// {name:'我的简历',icon:'../../static/image/简历.png',badge:9},
				
				],
				severList:[
					[
						{name:'我的二维码',icon:'../../../static/image/erwei.png'},
						{name:'我的学分',icon:'../../../static/image/xuefen1.png'},
						{name:'我的赛事',icon:'../../../static/image/saishi.png'},
						{name:'我的考试',icon:'../../../static/image/kaoshi.png'},
						{name:'我的专业',icon:'../../../static/image/zhuanye.png'},
						{name:'我的班级',icon:'../../../static/image/banji.png'},
						
					],
					[
						{name:'好友来访',icon:'../../../static/image/haoyou.png'},
						{name:'我的收藏',icon:'../../../static/image/shoucang.png'},
						{name:'申请',icon:'../../../static/image/shenqing.png'},
						{name:'帮助与反馈',icon:'../../../static/image/bangzhu.png'},
						
					]
				],
			};
		},
		onLoad() {
			this.init();
		},
		onShow() {
			this.isLogin();
		},
		methods: {
			init() {
				//用户信息
				this.userinfo = uni.getStorageSync('user')
			},
			//用户点击订单类型
			toOrderType(index){
				// 活动报名
				if (index === 2) {
					uni.navigateTo({
						url:"/pages/index/enlist/enlist"
					});
				}
				// 我的活动
				if (index === 1) {
					uni.navigateTo({
						url: './myPractice/myPractice'
					})
				}
				//积分统计
				if (index  === 0) {
					uni.navigateTo({
						url: './count/count'
					})
				}
				//我的问卷
				if (index  === 3) {
					uni.navigateTo({
						url: './question/question'
					})
				}
				// uni.showToast({title: this.orderTypeLise[index].name});
			},
			//用户点击列表项
			toPage(list_i,li_i){
				// uni.showToast({title: this.severList[list_i][li_i].name});
				if (this.severList[list_i][li_i].name == '我的二维码') {
					uni.navigateTo({
						url: './code/code'
					})
				}
				
				if (this.severList[list_i][li_i].name == '我的学分') {
					uni.navigateTo({
						url: './count/count'
					})
				}
				
			},
			information() {
				uni.navigateTo({
				  url: '/pages/mine/information/information'
				})
			},
		    settings(){
				uni.navigateTo({
				  url: '/pages/mine/settings/settings'
				})
			},
			chooseIcon(){
				let that = this
				uni.chooseImage({
					count: 1, // 一张头像
					sizeType: ['original'], // 原图
					sourceType:['album'], // 从相册选择
					success: function (res) {
						that.newIcon = res.tempFilePaths[0];
						// 备份院头像路径
						// console.log(res.tempFilePaths[0])
						that.orderIcon = that.userinfo.avatar
						// 更换当前头像为新头像
						that.userinfo.avatar = that.newIcon
						// 是否确认修改
						uni.showModal({
							content: "确定更换吗~",
							success(res) {
								if(res.confirm) {
									// 上传到后台, 拿到 url
									that.uploadAvatar(that.newIcon)
								} else {
									// 恢复旧头像
									that.userinfo.avatar = that.orderIcon
								}
							}
						})
					}
				})
			},
			/**
			 * 上传头像
			 */
			uploadAvatar(avatar) {
				// console.log(avatar)
				let that = this
				uploadFile(avatar).then(res => {
					try{
						let result = JSON.parse(res[res.length-1].data)
						// if (result.code === 20000) {
							// 更新头像
							that.data.username = that.userinfo.username
							that.data.path = result.data.path // 换成服务器路径
							that.updateAvatar() // 更新头像
						// }
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: result.message
						});
					}catch(e){
						//TODO handle the exception
						that.userinfo.avatar = that.orderIcon
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '网络异常，请重试'
						});
					}
					
				})
			},
			/**
			 * 更新头像
			 */
			updateAvatar() {
				modifyAvatar(this.data).then(res =>{
					let result = res [res.length-1].data
					if(result.code === 20000){
						// 重新获取新的信息
						getUserInfo()
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: result.message
						});
					}
				});  
			}
		}
	}
</script>

<style lang="scss">
	
page{
	background-color:#fff
	}
.header{
	&.status{
		padding-top:var(--status-bar-height);}
	background-color:#62ddff;
	width:92%;
	height:40vw;
	padding:0 4%;
	display:flex;
	align-items:center;
	.userinfo{
		width:90%;
		display:flex;
		.face{
			flex-shrink:0;
			width:15vw;
			height:15vw;
			image{
				width:100%;
				height:100%;
				border-radius:100%
				}
		}
		.info{
			display:flex;
			flex-flow:wrap;
			padding-left:30upx;
			.username{
				width:100%;
				color:#fff;
				font-size:40upx
				}
			.id{
				width: 100%;
				color: #fff;
				font-size: 25upx;
				}
		}
	}
	.setting{
		flex-shrink:0;
		width:6vw;
		height:6vw;
		image{
			width:100%;
			height:100%
			}
	}
}
.hover{
	background-color:#eee
	}
.orders{
	background-color:#62ddff;
	width:92%;
	height:8vw;
	padding:0 4%;
	margin-bottom:calc(11vw + 40upx);
	display:flex;
	align-items:flex-start;
	border-radius:0 0 100% 100%;
	margin-top: -3upx;
	.box{
		width:98%;
		padding:0 1%;
		height:22vw;
		background-color:#fefefe;
		border-radius:24upx;
		box-shadow:0 0 20upx rgba(0,0,0,0.15);
		margin-bottom:40upx;
		display:flex;
		align-items:center;
		justify-content:center;
		.label{
			display:flex;
			align-items:center;
			justify-content:center;
			flex-flow:wrap;
			width:100%;
			height:16vw;
			color:#666666;
			font-size:26upx;
			.icon{
				position:relative;
				width:7vw;
				height:7vw;
				margin:0 1vw;
				.badge{
					position:absolute;
					width:4vw;
					height:4vw;
					background-color:#ec6d2c;
					top:-1vw;
					right:-1vw;
					border-radius:100%;
					font-size:20upx;
					color:#fff;
					display:flex;
					align-items:center;
					justify-content:center;
					z-index: 10;
					}
				image{
					width:7vw;
					height:7vw;
					z-index: 9;
					}
			}
		}
	}
}
.list{
	width:100%;
	border-bottom:solid 26upx #f1f1f1;
	.li{
		width:92%;
		height:100upx;
		padding:0 4%;
		border-bottom:solid 1upx #e7e7e7;
		display:flex;
		align-items:center;
	&.noborder{border-bottom:0}
		.icon{
			flex-shrink:0;
			width:50upx;
			height:50upx;
			image{
				width:48upx;
				height:50upx;
			}
		}
		.text{
			font-size: 30upx;
			padding-left:10upx;
			width:100%;
			color:#666
		}
		.to{
			flex-shrink:0;
			width:40upx;
			height:40upx
		}
	}
}
</style>
