import request from '@/utils/request'

export function list(page, size) {
  return request({
    url: '/admin/tenants',
    method: 'get',
    params: { page: page, size: size }
  })
}
