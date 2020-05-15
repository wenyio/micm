<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!-- 角色管理 -->
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>角色</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="clearRoleForm">添加角色</el-button>
            </div>
            <div class="text item">
              <el-tree
                :data="list"
                :props="{ label: 'name' }"
                default-expand-all
                @node-click="handleNodeClick">
              </el-tree>
            </div>
          </el-card>
        </div>
      </el-col>
      <!-- 角色详情信息 -->
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <div class="grid-content bg-purple">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>{{this.roleForm.id ? '角色详情': '添加角色'}}</span>
                <router-link to="/organize/permission/api">
                  <el-button style="float: right; padding: 3px 0" type="text">管理权限</el-button>
                </router-link>
              </div>
              <div class="text item">
                <el-form ref="roleForm"
                         v-loading="formLoading"
                         element-loading-text="加载中..."
                        :model="roleForm"
                         :rules="rules"
                         label-width="50px">
                  <el-input type="hidden" v-model="roleForm.id" />
                  <el-form-item label="父级" prop="parentId">
                    <!-- 父角色选择 -->
                    <el-cascader
                      v-model="roleForm.parentId"
                      :options="list"
                      :props="{ checkStrictly: true, value: 'id', label: 'name', children: 'children' }"
                      @change="setParentId"
                      placeholder="/"
                      clearable style="width: 40%"></el-cascader>
<!--                      title="至多选取一个父级, 默认无父级!"-->
                  </el-form-item>
                  <el-form-item label="权限">
                    <!-- 权限选择 -->
                    <el-cascader
                      v-model="permissionId"
                      :options="permission"
                      :props="{ multiple: true, checkStrictly: true, value: 'id', label: 'name' }"
                      placeholder="无权限"
                      @change="choosePermission"
                      clearable style="width: 80%"></el-cascader>
                  </el-form-item>
                  <el-form-item label="名称" prop="name">
                    <el-input v-model="roleForm.name"/>
                  </el-form-item>
                  <el-form-item label="英文" prop="enname">
                    <el-input v-model="roleForm.enname" placeholder="必须由字母组成"/>
                  </el-form-item>
                  <el-form-item label="描述">
                    <el-input v-model="roleForm.description" type="textarea"/>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="submitForm('roleForm')">保存</el-button>
                    <el-popconfirm
                      title="删除这个角色可能带来意想不到的后果!"
                      @onConfirm="deleteRole(roleForm)"
                      v-if="roleForm.id"
                    >
                      <el-button type="danger"  slot="reference">删除</el-button>
                    </el-popconfirm>
                    <el-button @click="resetForm('roleForm')" v-if="!roleForm.id">重置</el-button>
                    <el-button v-if="roleForm.id" @click="clearRoleForm">取消</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-card>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { list, rules, update, add, rDelete, addPermission } from "@/api/role"
  import { list as plist, getArrayByRoleId } from "@/api/permission"

  export default {
    name: "Role",
    data() {
      return {
        formLoading: true,
        roleForm: {
          id: null,
          parentId: 0, // 默认为根权限
          name: '',
          enname: '',
          description: '',
        },
        permissionId: [], // 权限id
        rolePermission: { // 角色和权限
          roleId: null,
          list: []
        },
        // 表单验证规则
        rules: rules,
        // 角色树
        list: [],
        // 权限
        permission: [],
      };
    },
    created() {
      this.fetchData()
      this.getPermission()
    },
    methods: {
      /**
       * 获取数据
       */
      fetchData() {
        list().then(response => {
          this.list = response.data
          this.formLoading = false
          console.log(this.list)
        })
      },
      /**
       * 提交 更新或添加
       */
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 更新操作
            if (this.roleForm.id != null) {
              // alert('submit!'+this.roleForm.parentId+'|'+this.permissionId);
              // console.log(this.roleForm.parentId)
              this.updateRole(this.roleForm)
            }
            // 添加操作
            else {
              this.addRole(this.roleForm)
            }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      /**
       * 获取权限树
       */
      getPermission() {
        plist().then(response => {
          this.permission = response.data
          console.log(this.list)
        })
      },
      /**
       * 重值表单
       * @param formName
       */
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      /**
       * 清除表单数据
       */
      clearRoleForm() {
        this.roleForm = {
          id: null,
          parentId: 0,
        }
      },
      /**
       * 获取角色对应的权限
       * @param roleId
       */
      getPermissionArray(roleId) {
        getArrayByRoleId(roleId).then(response => {
          this.permissionId = response.data
        })
      },
      /**
       * 更新角色信息
       * @param data
       */
      updateRole(data) {
        // TODO 后端判断父id!=id
        if (this.roleForm.parentId == this.roleForm.id) {
          this.$message({
            message: '父角色不可以是本身',
            type: 'error'
          });
        } else {
          this.rolePermission.roleId = data.id
          // console.log(this.rolePermission)
          update(data).then(response => {
            this.formLoading = false
            this.$message({
              message: response.message,
              type: 'success'
            });

            // 更新角色权限
            this.addPermission(this.rolePermission)
          })
        }
      },
      /**
       * 添加
       * @param data
       */
      addRole(data) {
        add(data).then(response => {
          this.formLoading = false
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.rolePermission.roleId = response.data.id
          console.log(this.rolePermission)
          console.log( response.data)
          // 更新角色权限
          this.addPermission(this.rolePermission)
        })
      },
      /**
       * 删除角色
       * @param id
       */
      deleteRole(data) {
        // console.log(data.children)
        if (data.children.length != 0) {
          this.$message({
            message: '请先删除下级角色',
            type: 'error'
          });
        }
        else {
          rDelete(data.id).then(response => {
            this.formLoading = false
            this.fetchData()
            this.$message({
              message: response.message,
              type: 'success'
            });
          })
        }
      },
      /**
       * 为角色添加权限
       * @param data
       */
      addPermission(data) {
        addPermission(data).then(response => {
          this.formLoading = false
          this.fetchData()
          this.$message({
            message: response.message,
            type: 'success'
          });
        })
      },
      /**
       * 角色级联change事件
       * @param data
       */
      setParentId(data) {
        if (data) {
          this.roleForm.parentId = data[data.length-1]
        }

        else {
          this.roleForm.parentId = 0
        }
      },
      /**
       * 权限级联change事件
       * @param data
       */
      choosePermission(data) {
        console.log(data);
        let per = []
        this.permissionId.forEach(permission => {
          // 提取出权限节点的最后一个
          per.push(permission[permission.length-1])
        })
        this.rolePermission.list = per
        console.log(this.rolePermission)
      },
      /**
       * 角色节点点击事件
       */
      handleNodeClick(data) {
        this.roleForm = data
        this.getPermissionArray(data.id)
        // console.log(data);
      },
    }
  };
</script>

<style scoped>

</style>
