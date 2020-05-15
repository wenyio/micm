import request from "@/utils/request";

export function negate(data) {
  return request({
    url: 'admin/user/role/negate',
    method: 'post',
    data: data,
  })
}
