import request from '@/utils/request'

export function list() {
  return request({
    url: '/admin/tenant/service',
    method: 'get',
  })
}

export function renewal(data, days) {
  return request({
    url: '/admin/tenant/service/renewal/' + days,
    method: 'post',
    data: data,
  })
}

export function changeStatus(id) {
  return request({
    url: '/admin/tenant/service/status/' + id,
    method: 'get'
  })
}

export function deleteById(id) {
  return request({
    url: '/admin/tenant/service/delete/' + id,
    method: 'get',
  })
}
