import request from "../utils/request";

export function detailInfo() {
	return request({
		url: '/practice/user/detail/info',
		method: 'GET',
	}).then(res => {
		if (res.code === 20000) {
			localStorage.setItem("userDetail", JSON.stringify(res.data))
		} else {
			this.$message.error(res.message)
		}
	})
}

export function getPractice() {
	return request({
		url: '/practice/user/getPractice',
		method: 'GET',
	})
}

export function getPracticeNum() {
	return request({
		url: '/practice/user/getPracticeNum',
		method: 'GET',
	})
}

export function getPracticeIntegral() {
	return request({
		url: '/practice/user/getPracticeIntegral',
		method: 'GET',
	})
}

export function search(key) {
	return request({
		url: '/practice/search/' + key,
		method: 'GET',
	})
}

export function recommend() {
	return request({
		url: '/practice/recommend/' ,
		method: 'GET',
	})
}

export function list() {
	return request({
		url: '/practice/' ,
		method: 'GET',
	})
}

export function add(data) {
	return request({
		url: '/practice/add' ,
		method: 'POST',
		data:data
	})
}

export function organization() {
	return request({
		url: '/practice/organization/' ,
		method: 'GET',
	})
}

export function category() {
	return request({
		url: '/practice/category/' ,
		method: 'GET',
	})
}

export function getById(id) {
	return request({
		url: '/practice/' + id,
		method: 'GET',
	})
}

/**
 * 验证是否报名此活动
 * @param {Object} id
 */
export function judge(id) {
	return request({
		url: '/practice/option/judge/' + id,
		method: 'POST',
	})
}

/**
 * 报名活动
 * @param {Object} id
 */
export function apply(id) {
	return request({
		url: '/practice/option/apply/' + id,
		method: 'GET',
	})
}

/**
 * 取消报名
 * @param {Object} id
 */
export function notApply(id) {
	return request({
		url: '/practice/option/notApply/' + id,
		method: 'POST',
	})
}
// 活动二维码
export function getQrCode(id) {
	return request({
		url: '/practice/option/qrcode/' + id,
		method: 'GET',
	})
}

/**
 * 管理员扫
 * @param {Object} id
 */
export function adminCheck(data) {
	return request({
		url: '/practice/option/check',
		method: 'POST',
		data: data,
	})
}

/**
 * 用户扫码
 * @param {Object} id
 */
export function userCheck(checkCode) {
	return request({
		url: '/practice/option/check/' + checkCode,
		method: 'GET',
	})
}