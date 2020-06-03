import request from "../utils/request";

export function login(data) {
	return request({
		url: '/user/login',
		method: 'POST',
		data: data,
	})
}

export function reg(data) {
	return request({
		url: '/user/reg',
		method: 'POST',
		data: data,
	})
}

export function info() {
	return request({
		url: '/user/account/info/',
		method: 'GET',
	})
}

/**
 * 获取用户信息
 */
export function getUserInfo() {
	info().then(res => {
		if (res.code === 20000) {
			localStorage.setItem("user", JSON.parse(res.data))
		} 
	})
}

export function update(data) {
	return request({
		url: '/user/account/update',
		method: 'POST',
		data: data,
	})
}

export function logout() {
	return request({
		url: '/user/logout?access_token=' + localStorage.getItem("token"),
		method: 'POST',
	})
}

export function modifyPassword(data) {
	return request({
		url: '/user/account/modify/password',
		method: 'POST',
		data: data,
	})
}

export function modifyAvatar(data) {
	return request({
		url: '/user/account/modify/avatar',
		method: 'POST',
		data: data,
	})
}
export function modifyEmail(data) {
	return request({
		url: '/user/account/modify/email',
		method: 'POST',
		data: data,
	})
}

export function emailCode(data) {
	return request({
		url: '/user/account/email/code',
		method: 'POST',
		data: data,
	})
}

export function forgetPassword(data) {
	return request({
		url: '/user/account/forget/password',
		method: 'POST',
		data: data,
	})
}
