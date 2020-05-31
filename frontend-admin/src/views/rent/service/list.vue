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
      <el-table-column label="服务名" width="110">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="价格/天" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.price }}</span>
        </template>
      </el-table-column>
      <el-table-column label="版本" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.version }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" width="110" align="center">
        <template slot-scope="scope">
          <!--          <el-tag :type="scope.row.status | statusFilter">-->
          <el-button type="warning" plain size="mini" @click="changeStatus(scope.row.id)" v-if="!scope.row.status">未售</el-button>
          <el-button type="success" plain size="mini" @click="changeStatus(scope.row.id)" v-if="scope.row.status">在售</el-button>
          <!--          </el-tag>-->
        </template>
      </el-table-column>
      <el-table-column align="center" label="最近更新" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.updated }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="goEdit(scope.row)">编辑</el-button>
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
  </div>
</template>

<script>
  import { list, deleteById, changeStatus } from "@/api/service";

  export default {
    name: "Service",
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
        this.$router.push('/tenant/service/add?form=' + JSON.stringify(row))
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
