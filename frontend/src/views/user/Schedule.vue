<!--  -->
<template>
	<div class="Schedule">
		<el-page-header @back="$router.back(-1)" content="日程中心"></el-page-header>
		<el-row :gutter="20">
			<el-col :span="1">.</el-col>
			<el-col :span="14"><el-calendar v-model="date"></el-calendar></el-col>
			<el-col :span="8">
				<el-scrollbar
						wrap-class="list" wrap-style="color: red; height:600px;"
						view-class="view-box" view-style="font-weight: bold;height:100%;max-height: 600px;"
						:native="false">
					<el-timeline style="padding-right: 15px">
						<el-timeline-item timestamp="2018/4/12" placement="top">
							<el-card>
								<h4>更新 Github 模板</h4>
								<p>王小虎 提交于 2018/4/12 20:46</p>
							</el-card>
						</el-timeline-item>
						<el-timeline-item timestamp="2018/4/3" placement="top">
							<el-card shadow="hover">
								<h4>更新 Github 模板</h4>
								<p>王小虎 提交于 2018/4/3 20:46</p>
							</el-card>
						</el-timeline-item>
						<el-timeline-item timestamp="2018/4/2" placement="top">
							<el-card shadow="hover">
								<h4>更新 Github 模板</h4>
								<p>王小虎 提交于 2018/4/2 20:46</p>
							</el-card>
						</el-timeline-item>
						<el-timeline-item timestamp="2018/4/2" placement="top">
							<el-card class="box-card" shadow="hover">
								<h4>标题 <el-tag size="mini">超小标签</el-tag></h4>

								<p><el-link :underline="false">2020-20-20 25.20</el-link></p>
							</el-card>
						</el-timeline-item>
					</el-timeline>
				</el-scrollbar>
			</el-col>
			<el-col :span="1">&nbsp;</el-col>
		</el-row>
	</div>
</template>

<script>
export default {
	name: 'Schedule',
	components: {},
	data() {
		return {
			date: new Date(),
			list: []
		};
	},
	//监听属性 类似于data概念
	computed: {},
	//监控data中的数据变化
	watch: {
		date(val) {
			// 监听 date数据，单点击时触发,,,这里可以触发接口调用，点击的日期查询数据赋值给list
			// 日期对象类型
			console.log(val);
		}
	},
	//方法集合
	methods: {},
	//生命周期 - 创建完成（可以访问当前this实例）
	created() {},
	//生命周期 - 挂载完成（可以访问DOM元素）
	mounted() {},
	beforeCreate() {}, //生命周期 - 创建之前
	beforeMount() {}, //生命周期 - 挂载之前
	beforeUpdate() {}, //生命周期 - 更新之前
	updated() {}, //生命周期 - 更新之后
	beforeDestroy() {}, //生命周期 - 销毁之前
	destroyed() {}, //生命周期 - 销毁完成
	activated() {} //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
	.Schedule {
		padding: 20px;
	}
</style>
