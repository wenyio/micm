import request from "@/utils/request";

export function list() {
  return request({
    url: 'admin/permission/api',
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/admin/permission/api/update',
    method: 'post',
    data: data,
  })
}

export function add(data) {
  return request({
    url: '/admin/permission/api/add',
    method: 'post',
    data: data,
  })
}

export function pDelete(id) {
  return request({
    url: '/admin/permission/api/delete/' + id,
    method: 'get',
  })
}

export var rules = {
  parentId: [
    { required: true, message: '请选择权限父级', trigger: 'change' }
  ],
  name: [
    { required: true, message: '请输入权限名称', trigger: 'blur' },
    { min: 2, max: 32, message: '长度在 2 到 32 个字符', trigger: 'blur' }
  ],
  url: [
    { required: true, message: '请输入权限对应的URL', trigger: 'blur' },
    { min: 6, max: 64, message: '长度在 6 到 64 个字符', trigger: 'blur' }
  ],
}
