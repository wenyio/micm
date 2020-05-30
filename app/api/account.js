import http from './base.request.js'
import config from '@/api/config.js'

const url = config.baseURL
// return [null, {res}]

export function login(data) {
	return http.request({
		url: url + '/user/login',
		method: 'POST',
		data: data,
	})
}

export function info() {
	return http.request({
		url: url + '/user/account/info/',
		method: 'GET',
	})
}

/**
 * 获取用户信息
 */
export function getUserInfo() {
	info().then(res => {
		let result = res[res.length-1].data
		if (result.code === 20000) {
			uni.setStorageSync("user", result.data)
		} 
		uni.showToast({
			icon: 'none',
			position: 'bottom',
			title: result.message
		});
	})
}

export function update(data) {
	return http.request({
		url: url + '/user/account/update',
		method: 'POST',
		data: data,
	})
}

export function logout() {
	return http.request({
		url: url + '/user/logout?access_token=' + uni.getStorageSync("token"),
		method: 'POST',
	})
}

export function modifyPassword(data) {
	return http.request({
		url: url + '/user/account/modify/password',
		method: 'POST',
		data: data,
	})
}

export function modifyAvatar(data) {
	return http.request({
		url: url + '/user/account/modify/avatar',
		method: 'POST',
		data: data,
	})
}

export function emailCode(data) {
	return http.request({
		url: url + '/user/account/email/code',
		method: 'POST',
		data: data,
	})
}

export function forgetPassword(data) {
	return http.request({
		url: url + '/user/account/forget/password',
		method: 'POST',
		data: data,
	})
}
