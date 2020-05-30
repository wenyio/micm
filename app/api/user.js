import http from './base.request.js'
import config from '@/api/config.js'

const url = config.baseURL
// return [null, {res}]

export function userQrCode() {
	return http.request({
		url: url + '/practice/user/qrcode',
		method: 'GET',
	})
}

/**
 * 获取用户二维码
 */
export function getUserQrCode() {
	console.log("获取用户二维码")
	userQrCode().then(res => {
		let result = res[res.length-1].data
		if (result.code === 20000) {
			uni.setStorageSync("userQrCode", result.data.file)
		} 
		uni.showToast({
			icon: 'none',
			position: 'bottom',
			title: result.message
		});
	})
}
