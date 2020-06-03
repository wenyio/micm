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
					<el-tag @click="clickDate = null" size="mini" >全部</el-tag>
					<el-timeline style="padding-right: 15px">
						<el-timeline-item v-for="(o, index) in list" v-show="o.ymd == clickDate || clickDate == null" :key="index" :timestamp="o.begin" placement="top">
							<el-card class="box-card" shadow="hover">
								<h4>{{o.title}}
									<el-tag v-if="o.status == 10" size="mini" type="warning">早退</el-tag>
									<el-tag v-if="o.status == 1" size="mini" type="warning">迟到</el-tag>
									<el-tag v-if="o.status == 0" size="mini" type="danger">缺席</el-tag>
									<el-tag v-if="o.status == 11" size="mini" type="success">完成</el-tag>
								</h4>
								<p><el-link :underline="false">{{o.time}}</el-link></p>
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
var dateUtils = require('@/utils/util.js').dateUtils;
import { getPractice } from "@/api/practice.js";
export default {
	name: 'Schedule',
	components: {},
	data() {
		return {
			clickDate: null,
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
			console.log(val.getFullYear() + '-' + (val.getMonth()+1) + '-' + (val.getDate()) + ' ' + val.getHours() + ':' + val.getMinutes() + ':00');
			// this.clickDate = val.getFullYear() + '-' + (val.getMonth()+1) + '-' + val.getDate() + ' ' + val.getHours() + ':' + val.getMinutes() + ':00')
			// this.clickDate = dateUtils.approach(this.clickDate)
			var _format = function (number) {
				return (number < 10 ? ('0' + number) : number);
			};
			this.clickDate = val.getFullYear() + '-' + _format(val.getMonth()+1) + '-' + _format(val.getDate())
			console.log(this.clickDate)
		}
	},
	//方法集合
	methods: {
		getPractice() {
			let that = this
			getPractice().then (res =>{
				if(res.code === 20000){
					that.list = res.data
					that.list.forEach(item => {
						console.log(item.begin )
						item.ymd = dateUtils.ymd(item.begin)
						item.time = dateUtils.time(item.begin)
						item.begin = dateUtils.approach(item.begin)
						console.log(item.time, item.ymd, item.begin )
					})
					console.log(that.list)
				}
			});
		},
	},
	//生命周期 - 创建完成（可以访问当前this实例）
	created() {
		this.getPractice()
	},
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
