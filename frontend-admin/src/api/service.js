import request from '@/utils/request'

export function list(page, size) {
  return request({
    url: '/admin/services',
    method: 'get',
    params: { page: page, size: size }
  })
}

export function deleteById(id) {
  return request({
    url: '/admin/services/delete/' + id,
    method: 'get',
  })
}
