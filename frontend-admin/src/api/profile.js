import request from '@/utils/request'
import da from "element-ui/src/locale/lang/da";

/**
 * 获取个人信息
 * @param username
 * @returns {AxiosPromise}
 */
export function info(username) {
  return request({
    url: 'admin/profile/info/' + username,
    method: 'get'
  })
}

/**
 * 更新个人信息
 * @param data
 * @returns {AxiosPromise}
 */
export function update(data) {
  return request({
    url: 'admin/profile/update/',
    method: 'post',
    data: data
  })
}

/**
 * 更新密码
 * @param data
 * @returns {AxiosPromise}
 */
export function modifyPassword(data) {
  return request({
    url: 'admin/profile/modify/password',
    method: 'post',
    data: data
  })
}

/**
 * 更新头像
 * @param data
 * @returns {AxiosPromise}
 */
export function modifyAvatar(data) {
  return request({
    url: 'admin/profile/modify/avatar',
    method: 'post',
    data: data
  })
}
