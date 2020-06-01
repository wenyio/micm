<template>
    <div class="footer">
        <el-divider><router-link to="/about">关于本站</router-link></el-divider>
        <div><strong>© </strong>2020</div>
        <div><strong>@Author：</strong>马文艺</div>
        <div><strong>@Tutor：</strong>彭建华</div>
        <div>-----</div>
    </div>
</template>

<script>
    export default {
        name: "Footer",
    }
</script>

<style scoped>
    .footer {
        display: flex;
        width: 98%;
        background-color: white;
        justify-content: space-around;
        flex-direction: column;
        align-items: center;
        margin: 10px;
    }
</style>