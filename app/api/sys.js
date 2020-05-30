import http from './base.request.js'
import config from '@/api/config.js'

const url = config.baseURL
const token = uni.getStorageSync('token');

export function uploadFile(avatar) {
	return wx.uploadFile({
		url: url + '/cloud/upload',
		filePath: avatar,
		header: {
			'authorization' : 'Bearer ' + token
		},
		name:'multipartFile'
	}) 
}