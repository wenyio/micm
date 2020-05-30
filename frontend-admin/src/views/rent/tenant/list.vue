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
      <el-table-column label="租户">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>姓名: {{ scope.row.name }}</p>
            <p>住址: {{ scope.row.address }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="创建人" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.userId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.tenantAddress }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="规模" width="88">
        <template slot-scope="scope">
          <span>{{ scope.row.tenantSize }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" width="110" align="center">
        <template slot-scope="scope">
          <!--          <el-tag :type="scope.row.status | statusFilter">-->
          <el-button type="warning" plain size="mini" @click="changeStatus(scope.row.username)" v-if="!scope.row.status">冻结</el-button>
          <el-button type="success" plain size="mini" @click="changeStatus(scope.row.username)" v-if="scope.row.status">正常</el-button>
          <!--          </el-tag>-->
        </template>
      </el-table-column>
      <el-table-column label="余额" width="88" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.balance }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="openEdit(scope.row)">编辑</el-button>
          <el-button type="" size="mini" @click="openRole(scope.row.id)">服务</el-button>
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
    <el-dialog title="修改信息" :visible.sync="dialogEdit">
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
  </div>

</template>

<script>
  import { list} from "@/api/tenant";

  export default {
    name: "Tenant",
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
        form: { // 表单数据
          id: '',
          nickname: '',
          account: '',
          username: '',
          phone: '',
          email: '',
          leven: '',
        },
        dialogEdit: false,
      }
    },
    created() {
      this.fetchData()
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
        })
      },
      /**
       * 修改
       */
      openEdit(row) {
        this.dialogEdit = true
        // this.form = row
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
    }
  }
</script>

<style scoped>

</style>
