<template>
    <div class="app" style="padding: 20px">
        <el-page-header @back="goBack" content="活动大厅">
        </el-page-header>
    </div>
</template>

<script>
    export default {
        name: "Practices",
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