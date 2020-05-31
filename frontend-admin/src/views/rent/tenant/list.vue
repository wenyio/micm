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
            <p>租户: {{ scope.row.name }}</p>
            <p>地区: {{ scope.row.tenantArea }}</p>
            <p>地址: {{ scope.row.tenantAddress }}</p>
            <p>电话: {{ scope.row.tenantPhone }}</p>
            <p>邮箱: {{ scope.row.mailbox }}</p>
            <p>规模: {{ scope.row.tenantSize }}</p>
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
          <el-button type="warning" plain size="mini" @click="changeStatus(scope.row.id)" v-if="!scope.row.status">冻结</el-button>
          <el-button type="success" plain size="mini" @click="changeStatus(scope.row.id)" v-if="scope.row.status">正常</el-button>
          <!--          </el-tag>-->
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="审核状态" width="110" align="center">
        <template slot-scope="scope">
          <!--          <el-tag :type="scope.row.status | statusFilter">-->
          <el-button type="default" plain size="mini" v-if="scope.row.auditState == 0">未审核</el-button>
          <el-button type="success" plain size="mini" v-if="scope.row.auditState == 1">已通过</el-button>
          <el-button type="warning" plain size="mini" v-if="scope.row.auditState == -1">未通过</el-button>
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
          <el-button type="primary" size="mini" @click="goEdit(scope.row)">审核</el-button>
          <el-button type="" size="mini" @click="openService(scope.row.id)">服务</el-button>
          <el-button type="danger" size="mini" @click="deleteById(scope.row.id)" >删除</el-button>
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
    <!-- 服务 -->
    <el-dialog title="租户服务" :visible.sync="dialogService">
      <el-table
        v-loading="listLoading"
        :data="services"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column label="服务" width="110" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.userId }}</span>
          </template>
        </el-table-column>
        <el-table-column label="到期时间" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.tenantAddress }}</span>
          </template>
        </el-table-column>
        <el-table-column class-name="status-col" label="状态" width="110" align="center">
          <template slot-scope="scope">
            <!--          <el-tag :type="scope.row.status | statusFilter">-->
            <el-button type="warning" plain size="mini" @click="changeStatus(scope.row.id)" v-if="!scope.row.status">冻结</el-button>
            <el-button type="success" plain size="mini" @click="changeStatus(scope.row.id)" v-if="scope.row.status">正常</el-button>
            <!--          </el-tag>-->
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="deleteTenantService(scope.row.id)" >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogService = false">取 消</el-button>
        <el-button type="primary" @click="dialogService = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  import { list, changeStatus, deleteById} from "@/api/tenant";

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
        dialogService: false,
        services: []
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
       * 改变状态
       */
      changeStatus(id) {
        this.formLoading = true
        changeStatus(id).then(response => {
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
      deleteById(id) {
        this.formLoading = true
        deleteById(id).then(response => {
          this.formLoading = false
          this.fetchData()
          this.$message({
            message: response.message,
            type: 'success'
          })
        }).catch(() => {
          this.formLoading = false
        })
      },
      /**
       * 去修改
       */
      goEdit(row) {
        this.$router.push('/tenant/tenant/audit?form=' + JSON.stringify(row))
      },
      /**
       * 打开租户的服务界面, 并且查询相关数据
       */
      openService(id) {
        this.dialogService = true
      },
      deleteTenantService() {

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
