<template>
  <div class="app">
    <!-- 设置 -->
    <i class="el-icon-setting set" @click="drawer = true"></i>
    <el-drawer
            title="一页通设置"
            :visible.sync="drawer"
            direction="rtl"
            :with-header="false">
      <div style="padding: 20px;">
        <h2>设置</h2>
        <el-form ref="form" :model="sets" label-width="80px">
          <el-form-item label="标题显示">
            <el-switch v-model="sets.showTitle"></el-switch>
          </el-form-item>
          <el-form-item label="常用入口">
            <el-checkbox-group v-model="sets.type">
              <el-checkbox :label="i" name="type" v-for="i in 16" :key="i"  @change="addOpp"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <!--        <el-form-item>-->
          <!--          <el-button type="primary">确定</el-button>-->
          <!--          <el-button>取消</el-button>-->
          <!--        </el-form-item>-->
        </el-form>
      </div>
    </el-drawer>
    <!-- 搜索框 -->
    <div class="cricle">
      <form class="search-box" @click="goToPractice" action="https://www.baidu.com/s">
        <div class="search" >Search/</div>
        <input type="text"/>
        <i class="el-icon-thumb search-i" onsubmit="this"></i>
      </form>
    </div>
    <!-- 快捷入口 -->
    <div class="cricle">
      <div class="container">
        <div class="box" v-for="i in sets.type.length" :key="i">
          <div class="img">
            <img src="@/assets/icos/录制.png"/>
          </div>
          <p v-if="sets.showTitle">常用网站</p>
        </div>
        <div class="box" v-if="showAdd" @click="drawer = true">
          <div class="img">
            <i class="el-icon-circle-plus-outline" style="font-size: 50px;"></i>
          </div>
          <p v-if="sets.showTitle">添加</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {
  },
  created() {
    this.$notify({
      title: '通知',
      message: '【一页通】服务暂未开放，敬请期待~',
      position: 'bottom-right'
    });
  },
  data() {
    return {
      input1: '',
      select: '',
      direction: 'rtl',
      drawer: false,
      showAdd: true,
      sets: {
        showTitle: true,
        type: [1, 2, 3, 4]
      },
    }
  },
  methods: {
    /**
     * 添加快捷入口
     */
    addOpp() {
      let that = this
      if (that.sets.type.length === 5) {
        that.showAdd = false
      } else if (that.sets.type.length > 5) {
        let type = []
        this.sets.type.forEach(item => {
          type.push(item)
        })
        type.splice(type.length-1, 1)
        this.sets.type = type
        this.$message({
          message: '最多添加5条数据',
          type: 'warning'
        });
      } else {
        that.showAdd = true
      }
    },
    // 去活动页
    goToPractice() {
      // 判断用户是否绑定了租户
      let isTenant = true
      // 如果绑定， 放行
      if (isTenant) {
        this.$router.push('/tenant/practice')
        return
      }
      // 否则
      this.$message({
        message: '一页通服务暂未开通',
        type: 'warning'
      });
    }
  }
}
</script>

<style scoped>
  .app {
    width: 100%;
  }

  .set {
    width:99%;
    font-size: 21px;
    padding: 5px;
    text-align: right;
  }

  .set:hover {
    font-size: 22px;
    color: #10aeff;
  }

  .search-box {
    border-radius: 10px;
    justify-content: center;
    align-items: center;
    width: 66%;
    height: 72px;
    display:flex;
    border: solid #CCCCCC 1px;
  }

  .search-box input {
    width: 80%;
    font-size: 36px;
    border:0;
    outline:none;
  }

  .search-box i {
    margin-right: 0px;
    display: flex;
    width: 96px;
    height: 100%;
    align-items: center;
    justify-content: center;
    border-radius: 10px;
    font-size: 36px;
    border:0;
    outline:none;
  }

  .search-box i:hover {
    /*background-color: #CCCCCC;*/
    box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.2),
    0px 0px 0px rgba(255, 255, 255, 0.8),
    inset 18px 18px 30px rgba(0, 0, 0, 0.1),
    inset -18px -18px 30px rgba(255, 255, 255, 1);
    transition: box-shadow .2s ease-out;
  }

  .search {
    width: 166px;
    font-size: 36px;
    display:flex;
    justify-content: center;
  }

  *{
    margin: 0;
    padding: 0;
  }
  .cricle{
    width: 100%;
    margin-top: 105px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .container{
    margin-bottom: 115px;
    width: 80%;
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    align-items: center;
  }
  .container .box{
    display: flex;
    justify-content: space-around;
    flex-direction: column;
    align-items: center;
    align-content: center;
    width: 100px;
    height: 140px;
    margin: 20px;
  }
  .container .box .img{
    width: 160px;
    height: 160px;
    box-shadow: 18px 18px 30px rgba(0, 0, 0, 0.1),
    -18px -18px 30px rgba(255, 255, 255, 1);
    border-radius: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #efeeee;
    transition: box-shadow .2s ease-out;
    position: relative;
  }

  .container .box .img  img{
    width: 60px;
    transition: width 0.2s ease-out;
  }
  .container .box p{
    color: slategrey;
  }
  .container .box .img:hover{
    box-shadow: 0px 0px 0px rgba(0, 0, 0, 0.2),
    0px 0px 0px rgba(255, 255, 255, 0.8),
    inset 18px 18px 30px rgba(0, 0, 0, 0.1),
    inset -18px -18px 30px rgba(255, 255, 255, 1);
    transition: box-shadow .2s ease-out;
  }
  .container .box .img:hover img{
    width: 58px;
    transition: width 0.2s ease-out;
  }
</style>