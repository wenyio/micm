import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

export function getRole(id) {
  return request({
    url: '/admin/users/' + id + '/getRole',
    method: 'get'
  })
}

export function logout(token) {
  return request({
    url: '/user/logout',
    method: 'post',
    params: { access_token: token }
  })
}

export function list(page, size) {
  return request({
    url: '/admin/users',
    method: 'get',
    params: { page: page, size: size }
  })
}

export function changeStatus(username) {
  return request({
    url: '/admin/users/status/' + username,
    method: 'get'
  })
}

export function changeDeleted(username) {
  return request({
    url: '/admin/users/delete/' + username,
    method: 'get'
  })
}

export function add(data) {
  // console.log(data)
  return request({
    url: 'admin/users/add',
    method: 'post',
    data: data
  })
}
