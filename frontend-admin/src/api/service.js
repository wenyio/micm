import request from '@/utils/request'

export function list(page, size) {
  return request({
    url: '/admin/services',
    method: 'get',
    params: { page: page, size: size }
  })
}

export function save(data) {
  return request({
    url: '/admin/services/save',
    method: 'post',
    data: data,
  })
}

export function changeStatus(id) {
  return request({
    url: '/admin/services/status/' + id,
    method: 'get'
  })
}

export function deleteById(id) {
  return request({
    url: '/admin/services/delete/' + id,
    method: 'get',
  })
}

export function getById(id) {
  return request({
    url: '/admin/services/' + id,
    method: 'get',
  })
}

