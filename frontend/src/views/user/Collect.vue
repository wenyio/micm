<template>
    <div>收藏中心</div>
</template>

<script>
    export default {
        name: "Collect",
        created() {
            this.$confirm('此页面暂未开放, 返回首页?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$router.push('/')
            }).catch(() => {
                this.$router.back(-1)
            });
        },
    }
</script>

<style scoped>

</style>