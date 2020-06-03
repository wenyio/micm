<template>
    <div>
        <el-row :gutter="20" style="margin-top: 36px">
            <el-col :span="3"><div class="grid-content bg-purple">.</div></el-col>
            <el-col :span="18">
                <!-- 申请成为租户 -->
                <el-steps :active="1">
                    <el-step title="步骤 1" description="这是一段很长很长很长的描述性文字"></el-step>
                    <el-step title="步骤 2" description="这是一段很长很长很长的描述性文字"></el-step>
                    <el-step title="步骤 3" description="这段就没那么长了"></el-step>
                </el-steps>
            </el-col>
            <el-col :span="3"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Apply"
    }
</script>

<style scoped>

</style>