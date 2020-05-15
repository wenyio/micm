<template>
  <div class="app-container">
    <!-- 表单数据 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" sortable="custom" label="序号" width="95">
        <template slot-scope="scope">
          {{ scope.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="用户名" width="110">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column label="手机号" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="租户名" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.tenantName }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" width="110" align="center">
        <template slot-scope="scope">
<!--          <el-tag :type="scope.row.status | statusFilter">-->
          <el-button type="warning" plain size="mini" @click="changeStatus(scope.row.username)" v-if="!scope.row.status">禁用</el-button>
          <el-button type="success" plain size="mini" @click="changeStatus(scope.row.username)" v-if="scope.row.status">可用</el-button>
<!--          </el-tag>-->
        </template>
      </el-table-column>
      <el-table-column align="center" label="最近登录" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.loginTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="openEdit(scope.row)">编辑</el-button>
          <el-button type="" size="mini" @click="openRole(scope.row.id)">角色</el-button>
          <el-button type="danger" size="mini" @click="changeDeleted(scope.row.username)" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <br>
    <!-- 分页组件 -->
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="1"
        :page-sizes="[10, 20, 30, 40, 100, 200]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalElements">
      </el-pagination>
    </div>
    <!-- 修改框 -->
    <el-dialog title="修改用户信息" :visible.sync="dialogEdit">
      <el-form :model="form">
        <el-input type="hidden" v-model="form.id" />
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.username" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogEdit = false">取 消</el-button>
        <el-button type="primary" @click="userEdit">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 权限框 -->
    <el-dialog title="用户角色" :visible.sync="dialogRole">
      <el-tree
        :data="roles"
        show-checkbox
        node-key="id"
        :props="{ label: 'name'}"
        @check="setRole"
        ref="tree"
        :check-strictly="true"
        :default-expanded-keys="userRoles"
        :default-checked-keys="userRoles">
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogRole = false">取 消</el-button>
        <el-button type="primary" @click="userEdit">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  import { list, changeDeleted, changeStatus, getRole } from "@/api/user";
  import { list as rList } from "@/api/role"
  import { negate } from "@/api/userRole"
  import { update } from "@/api/profile";

  export default {
    name: "User",
    filters: {
      statusFilter(status) {
        console.log(status)
        if (status) {
          return "success"
        }
        return "danger"
      }
    },
    data() {
      return {
        list: null,
        listLoading: true,
        page: 0,
        size: 10,
        totalElements: 0,
        dialogEdit: false,
        dialogRole: false,
        form: { // 用户表单数据
          id: '',
          nickname: '',
          account: '',
          username: '',
          phone: '',
          email: '',
          leven: '',
        },
        userRole: {
          userId: null,
          roleId: null,
        },
        roles: [], // 角色列表
        userRoles: [], // 用户角色id
      }
    },
    created() {
      this.dialogRole = true // 先渲染出组件, 以免报错
      this.fetchData()
      this.getRoleList()
    },
    methods: {
      /**
       * 获取数据
       */
      fetchData() {
        list(this.page, this.size).then(response => {
          this.list = response.data.content
          this.totalElements = response.data.totalElements
          // console.log(this.list)
          this.listLoading = false
          this.dialogRole = false
        })
      },
      /**
       * 修改用户
       */
      openEdit(row) {
        this.dialogEdit = true
        this.form = row
      },
      userEdit() {
        this.formLoading = true
        update(this.form).then(response => {
          this.formLoading = false
          this.dialogEdit = false
          this.$message({
            message: response.message,
            type: 'success'
          });
        }).catch(() => {
          this.formLoading = false
          this.dialogEdit = false
        })
      },
      /**
       * 改变用户状态
       */
      changeStatus(username) {
        this.formLoading = true
        changeStatus(username).then(response => {
          this.formLoading = false
          this.fetchData()
          this.$message({
            message: response.message,
            type: 'success'
          });
        }).catch(() => {
          this.formLoading = false
        })
      },
      /**
       * 假删除用户
       */
      changeDeleted(username) {
        this.formLoading = true
        this.fetchData()
        changeDeleted(username).then(response => {
          this.formLoading = false
          this.$message({
            message: response.message,
            type: 'success'
          });
        }).catch(() => {
          this.formLoading = false
        })
      },
      /**
       * 每页x条数据
       * @param val
       */
      handleSizeChange(val) {
        this.size = val
        this.fetchData()
        console.log(`每页 ${val} 条`);
      },
      /**
       * 当前x页
       * @param val
       */
      handleCurrentChange(val) {
        this.page = val - 1
        this.fetchData()
        console.log(`当前页: ${val}`);
      },
      /**
       * 获取角色列表
       */
      getRoleList() {
        rList().then(response => {
          this.formLoading = false
          this.roles = response.data
          this.$message({
            message: response.message,
            type: 'success'
          });
        })
      },
      /**
       * 获取用户权限
       */
      getRole(id) {
        this.$refs.tree.setCheckedKeys([]); // 先清空
        // console.log(id)
        getRole(id).then(response => {
          this.formLoading = false
          this.userRoles = response.data
        })
      },
      /**
       * 设置角色
       * @param id
       */
      openRole(id) {
        this.dialogRole = true
        this.userRole.userId = id
        this.getRole(id)
      },
      /**
       * 设置角色, 取反操作
       * @param data
       */
      setRole(data) {
        // console.log(data);
        this.userRole.roleId = data.id
        negate(this.userRole).then(response => {
          this.formLoading = false
          this.roles = response.data
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.getRoleList()
          this.getRole(this.userRole.userId)
        })
      }
    }
  }
</script>

<style scoped>

</style>
