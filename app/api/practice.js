import http from './base.request.js'
import config from '@/api/config.js'

const url = config.baseURL
// return [null, {res}]

export function detailInfo() {
	return http.request({
		url: url + '/practice/user/detail/info',
		method: 'GET',
	})
}

export function getPractice() {
	return http.request({
		url: url + '/practice/user/getPractice',
		method: 'GET',
	})
}

export function getPracticeNum() {
	return http.request({
		url: url + '/practice/user/getPracticeNum',
		method: 'GET',
	})
}

export function getPracticeIntegral() {
	return http.request({
		url: url + '/practice/user/getPracticeIntegral',
		method: 'GET',
	})
}

export function search(key) {
	return http.request({
		url: url + '/practice/search/' + key,
		method: 'GET',
	})
}

export function recommend() {
	return http.request({
		url: url + '/practice/recommend/' ,
		method: 'GET',
	})
}

export function list() {
	return http.request({
		url: url + '/practice/' ,
		method: 'GET',
	})
}

export function add(data) {
	return http.request({
		url: url + '/practice/add' ,
		method: 'POST',
		data:data
	})
}

export function organization() {
	return http.request({
		url: url + '/practice/organization/' ,
		method: 'GET',
	})
}

export function category() {
	return http.request({
		url: url + '/practice/category/' ,
		method: 'GET',
	})
}

export function getById(id) {
	return http.request({
		url: url + '/practice/' + id,
		method: 'GET',
	})
}

/**
 * 验证是否报名此活动
 * @param {Object} id
 */
export function judge(id) {
	return http.request({
		url: url + '/practice/option/judge/' + id,
		method: 'POST',
	})
}

/**
 * 报名活动
 * @param {Object} id
 */
export function apply(id) {
	return http.request({
		url: url + '/practice/option/apply/' + id,
		method: 'GET',
	})
}

/**
 * 取消报名
 * @param {Object} id
 */
export function notApply(id) {
	return http.request({
		url: url + '/practice/option/notApply/' + id,
		method: 'POST',
	})
}
// 活动二维码
export function getQrCode(id) {
	return http.request({
		url: url + '/practice/option/qrcode/' + id,
		method: 'GET',
	})
}

/**
 * 管理员扫
 * @param {Object} id
 */
export function adminCheck(data) {
	return http.request({
		url: url + '/practice/option/check',
		method: 'POST',
		data: data,
	})
}

/**
 * 用户扫码
 * @param {Object} id
 */
export function userCheck(checkCode) {
	return http.request({
		url: url + '/practice/option/check/' + checkCode,
		method: 'GET',
	})
}