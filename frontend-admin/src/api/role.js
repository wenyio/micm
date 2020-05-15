import request from "@/utils/request";

export function list() {
  return request({
    url: '/admin/roles',
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/admin/roles/update',
    method: 'post',
    data: data,
  })
}

export function add(data) {
  return request({
    url: '/admin/roles/add',
    method: 'post',
    data: data,
  })
}

export function rDelete(id) {
  return request({
    url: '/admin/roles/delete/' + id,
    method: 'get',
  })
}

export function addPermission(data) {
  return request({
    url: '/admin/role/permission/addList',
    method: 'post',
    data: data,
  })
}

export var rules = {
  parentId: [
    { required: true, message: '请选择角色父级', trigger: 'change' }
  ],
  name: [
    { required: true, message: '请输入角色名称', trigger: 'blur' },
    { min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur' }
  ],
  enname: [
    { required: true, message: '请输入角色英文名称', trigger: 'blur' },
    { min: 3, max: 18, message: '长度在 3 到 18 个字符', trigger: 'blur' }
  ],
}
