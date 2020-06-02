<template>
    <div class="app" style="padding: 20px">
        <el-page-header @back="goBack" content="活动大厅/活动详情">
        </el-page-header>
    </div>
</template>

<script>
    export default {
        name: "PracticeDetail",
        methods: {
            goBack() {
                this.$router.back(-1)
                console.log('go back');
            }
        }
    }
</script>

<style scoped>

</style>