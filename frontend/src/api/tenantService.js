import request from '@/utils/request'

export function list(data) {
  return request({
    url: '/admin/tenant/service',
    method: 'POST',
    data: data,
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

export function deleteById(data, id) {
  return request({
    url: '/admin/tenant/service/delete/' + id,
    method: 'post',
    data: data
  })
}
