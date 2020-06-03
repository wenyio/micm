<template>
  <div class="container" >
    <Header :menus="menus" :showMsg="true" :activeIndex="'1'" :login="true"></Header>
    <router-view></router-view>
    <Feedback></Feedback>
    <Footer></Footer>
  </div>
</template>

<script>
  import Header from "@/components/_Header";
  import Footer from "@/components/_Footer";
  import Feedback from "@/components/_Feedback";

  export default {
    components: {
      Header,
      Footer,
      Feedback
    },
    data() {
      return {
        menus: [
            {index: 1, path: '/home', name: '首页'},
            {index: 2, path: '/tenant', name: '我的大学'},
            {index: 2, path: '/service', name: '服务大厅'}
        ],
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
        this.$router.push(key)
      }
    }
  }
</script>

<style scoped>
</style>
