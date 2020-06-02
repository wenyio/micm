<template>
    <div class="app" style="padding: 20px">
        <el-page-header @back="goBack" content="我的创建">
        </el-page-header>
    </div>
</template>

<script>
    export default {
        name: "PracticeMyCreate",
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