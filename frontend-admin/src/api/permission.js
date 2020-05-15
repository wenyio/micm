import request from "@/utils/request";

export function list() {
  return request({
    url: '/admin/permissions',
    method: 'get'
  })
}

export function getArrayByRoleId(roleId) {
  return request({
    url: '/admin/permissions/role/'+ roleId +'/array',
    method: 'get'
  })
}
