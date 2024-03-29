import request from '@/utils/request'

export function list(page, size) {
  return request({
    url: '/admin/tenants',
    method: 'get',
    params: { page: page, size: size }
  })
}

export function save(data) {
  return request({
    url: '/admin/tenants/save',
    method: 'post',
    data: data,
  })
}

export function changeStatus(id) {
  return request({
    url: '/admin/tenants/status/' + id,
    method: 'get'
  })
}

export function deleteById(id) {
  return request({
    url: '/admin/tenants/delete/' + id,
    method: 'get',
  })
}
