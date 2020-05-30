<template>
	<view class="content" >
	<view class="top"></view>
	<view class="banner">
		<dl style="text-align: center;">
			<dt style="text-align: center;"><image :src="user.avatar" mode="" style="width: 160upx;height: 160upx;border-radius:50% ;margin-top: 88rpx;"></image></dt>
			<dd>我的二维码</dd>
		</dl>
		<view class="img">
			<image :src="userQrCode" mode=""></image>
		</view>
		<view class="sharbuttn">
			<view class="btn" @click="save" style="background-color: #62ddff;">保存二维码</view>
		</view>
	</view>
	</view>
</template>

<script>
	export default {
		data(){
			return {
				userQrCode: null,
				user: null,
			}
		},
		onLoad() {
			this.userQrCode = uni.getStorageSync('userQrCode')
			this.user = uni.getStorageSync('user')
		},
		methods:{
			
			//保存图片到相册
			save(){
				uni.showActionSheet({
					itemList:['保存图片到相册'],
					success: () => {
						uni.downloadFile({
						    url: this.userQrCode, //仅为示例，并非真实的资源
						    success: (res) => {
								console.log(res)
						        if (res.statusCode === 200) {
						            console.log('下载成功');
						        }
						    }
						});
					}
				})
			},
			openLink() {
				plus.runtime.openWeb(this.sourceLink)
			}
		}
	}
	
	
</script>

<style>
	.tugurl{
		color: #999;
	}
	.sharbuttn{
		display: flex;
		justify-content: center;
	}
	.shartitle{
		    width: 80%;
			text-align: center;
			margin-left: 10%;
			border-bottom: 1px solid #dddddd;
			position: relative;
			height: 60upx;
	}
	.tgtit{
		text-align: center;
	}
	.shartitle view{
		    height: 50upx;
			line-height: 50upx;
			font-size: 28upx;
			color: #999;
			width: 120upx;
			margin: 32upx auto;
			position: absolute;
			background: #fff;
			left: 50%;
			margin-left: -60upx;
	}
	.sharapk{
	 display: flex;
	 justify-content: center;
	 margin: 20upx auto
	}
	.sharapk view{
		margin: 40upx;
	}
	.sharapk view image{
		height: 80upx;
		width: 80upx;
	}
	.content{
		width: 100%;
		background-color: #ffffff;
	}
	.top{
		width: 100%;
		height:400upx;
		background-image: url(../../../static/image/banner.png);
/* 		background: url(http://pds.jyt123.com/wxtest/banner.png) no-repeat ;
 */		background-size:100% ;
		background-position:center center;
	}
	.banner{
		width: 100% ;
		background-color: #FFFFFF;
		border-radius: 60upx 60upx 0 0;
		margin-top: -60upx;
	}
	.banner dl{
		margin-top: -80upx;
	}
	.banner dl dt{
		text-align: center;
	}
	.banner dl dt image{
		width: 160upx;
		height: 160upx;
		border-radius:50% ;
		margin-top: 88rpx;
	}
	.banner dl dd{
		text-align: center;
	}
	.img{
		width: 300upx;
		height: 300upx;
		background-color: red;
		margin: 0 auto;
		margin-top: 60upx;
	}
	.img image{
		width: 100%;
		height: 100%;
	}
	.btn{
		width: 260upx;
		height: 60upx;
		line-height: 60upx;
		margin: 0 auto;
		margin-top: 60upx;
		border-radius: 40upx;
		border: 0;
		font-size: 26upx;
		outline: 0;
		background: #33b17b;
		color: #FFFFFF;
		text-align: center;
	}
	
</style>
