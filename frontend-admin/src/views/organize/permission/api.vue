<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!-- 角色管理 -->
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>接口权限</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="clearPermissionForm">添加权限</el-button>
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
                <span>{{this.permissionForm.id ? '权限详情': '添加权限'}}</span>
                <router-link to="/organize/role">
                  <el-button style="float: right; padding: 3px 0" type="text">管理角色</el-button>
                </router-link>
              </div>
              <div class="text item">
                <el-form ref="permissionForm"
                         v-loading="formLoading"
                         element-loading-text="加载中..."
                         :model="permissionForm"
                         :rules="rules"
                         label-width="50px">
                  <el-input type="hidden" v-model="permissionForm.id" />
                  <el-form-item label="父级" prop="parentId">
                    <!-- 父角色选择 -->
                    <el-cascader
                      v-model="permissionForm.parentId"
                      :options="list"
                      :props="{ checkStrictly: true, value: 'id', label: 'name', children: 'children' }"
                      @change="setParentId"
                      placeholder="/"
                      clearable style="width: 40%"></el-cascader>
                    <!--                      title="至多选取一个父级, 默认无父级!"-->
                  </el-form-item>
                  <el-form-item label="名称" prop="name">
                    <el-input v-model="permissionForm.name"/>
                  </el-form-item>
                  <el-form-item label="地址" prop="url">
                    <el-input v-model="permissionForm.url" placeholder="必须由字母和'/'组成"/>
                  </el-form-item>
                  <el-form-item label="描述">
                    <el-input v-model="permissionForm.description" type="textarea"/>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="submitForm('permissionForm')">保存</el-button>
                    <el-popconfirm
                      title="删除这个角色可能带来意想不到的后果!"
                      @onConfirm="deletePermission(permissionForm)"
                      v-if="permissionForm.id"
                    >
                      <el-button type="danger"  slot="reference">删除</el-button>
                    </el-popconfirm>
                    <el-button @click="resetForm('permissionForm')" v-if="!permissionForm.id">重置</el-button>
                    <el-button v-if="permissionForm.id" @click="clearPermissionForm">取消</el-button>
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
  import { list, rules, update, add, pDelete } from "@/api/permissionApi"

  export default {
    name: "PermissionApi",
    data() {
      return {
        formLoading: true,
        permissionForm: {
          id: null,
          parentId: 0, // 默认为根权限
          name: '',
          url: '',
          description: '',
        },
        // 表单验证规则
        rules: rules,
        // 角色树
        list: [],
      };
    },
    created() {
      this.fetchData()
    },
    methods: {
      /**
       * 获取数据
       */
      fetchData() {
        list().then(response => {
          this.list = response.data
          this.formLoading = false
          // console.log(this.list)
        })
      },
      /**
       * 提交 更新或添加
       */
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 更新操作
            if (this.permissionForm.id != null) {
              // alert('submit!'+this.roleForm.parentId+'|'+this.permissionId);
              // console.log(this.roleForm.parentId)
              this.updatePermission(this.permissionForm)
            }
            // 添加操作
            else {
              // console.log(this.permissionForm)
              this.addPermission(this.permissionForm)
            }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
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
      clearPermissionForm() {
        this.permissionForm = {
          id: null,
          parentId: 0,
        }
      },
      /**
       * 更新权限信息
       * @param data
       */
      updatePermission(data) {
        // TODO 后端判断父id!=id
        if (this.permissionForm.parentId == this.permissionForm.id) {
          this.$message({
            message: '父角色不可以是本身',
            type: 'error'
          });
        } else {
          update(data).then(response => {
            this.formLoading = false
            this.$message({
              message: response.message,
              type: 'success'
            });
            this.fetchData()
          })
        }
      },
      /**
       * 添加
       * @param data
       */
      addPermission(data) {
        add(data).then(response => {
          this.formLoading = false
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.fetchData()
        })
      },
      /**
       * 删除权限
       * @param id
       */
      deletePermission(data) {
        // console.log(data.children)
        if (data.children.length != 0) {
          this.$message({
            message: '请先删除下级角色',
            type: 'error'
          });
        }
        else {
          pDelete(data.id).then(response => {
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
       * 权限级联change事件
       * @param data
       */
      setParentId(data) {
        if (data) {
          this.permissionForm.parentId = data[data.length-1]
        }

        else {
          this.permissionForm.parentId = 0
        }
      },
      /**
       * 权限节点点击事件
       */
      handleNodeClick(data) {
        this.permissionForm = data
        // console.log(data);
      },
    }
  };
</script>

<style scoped>

</style>
