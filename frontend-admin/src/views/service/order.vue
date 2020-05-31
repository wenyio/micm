<template>
  <div>
    <div>
      <el-row style="">
        <el-col style="margin-top: 10px" :span="4" v-for="(o, index) in list" v-if="o.status" :key="index" :offset="1">
          <el-card :body-style="{ padding: '0px' }" shadow="hover">
            <img :src="o.cover" class="image" :alt="o.description">
            <div style="padding: 14px;">
              <el-popover
                placement="top-start"
                title="简介"
                width="200"
                trigger="hover"
                :content="o.description">
<!--                <el-button slot="reference">hover 激活</el-button>-->
                <span slot="reference">{{o.name}}</span>
              </el-popover>
              <div class="bottom clearfix">
                <time class="time">￥{{o.price}}/天</time><br>
                <el-button type="text" class="button" @click="clickRenewal(o.id)">订阅</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <!-- 分页组件 -->
    <br>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="1"
        :page-sizes="[8, 16, 24, 32, 40, 48]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalElements">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {list} from "@/api/service";
  import {renewal} from "@/api/tenantService";
  import {info} from "@/api/profile";
  export default {
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
        listLoading: true,
        page: 0,
        size: 8,
        totalElements: 0,
        list: [],
        data: {
          serviceId: null,
          tenantId: null,
        },
      };
    },
    created() {
      this.fetchData()
      this.tip()
    },
    methods: {
      tip() {
        const h = this.$createElement;

        this.$notify({
          title: '友情提示',
          message: h('i', { style: 'color: teal'}, '关于服务的具体内容可向管理员申请演示！')
        });
      },
      getUserTenantId() {
        this.formLoading = true
        info(this.$store.getters.name).then(response => {
          this.data.tenantId  = response.data.tenantId
          this.formLoading = false
        }).catch(() => {
          this.formLoading = false
        })
      },
      /**
       * 获取数据
       */
      fetchData() {
        list(this.page, this.size).then(response => {
          this.list = response.data.content
          this.totalElements = response.data.totalElements
          // console.log(this.list)
          this.getUserTenantId(); // 获取租户id
          this.listLoading = false
        })
      },
      /**
       * 点击订阅
       */
      clickRenewal(serviceId) {
        this.data.serviceId = serviceId;
        this.$prompt('请输入订阅时长/天', '服务订阅', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputType: 'number',
          inputPattern: /^[0-9]*[1-9][0-9]*$/,
          inputErrorMessage: '请输入正确的天数'
        }).then(({ value }) => {
          // 续费接口
          this.renewalService(value);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消订阅'
          });
        });
      },
      /**
       * 服务续费
       */
      renewalService(days) {
        renewal(this.data, days).then(response => {
          this.services = response.data
          console.log(this.data)
          this.$message({
            message: response.message,
            type: 'success'
          })
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
    }
  }
</script>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
