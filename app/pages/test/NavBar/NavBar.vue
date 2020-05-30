<template>
	<view>
		<nav-bar activeNavId='1' :navList='navList'></nav-bar>
	</view>
</template>

<script>
	import navBar from '@/components/navbar.vue'
	export default {
		components: {
			navBar
		},
		data() {
			return {
				navList: [
					{id: 1, name: '活动'},
					{id: 2, name: '热门'},
					{id: 3, name: '帖子'},
					{id: 4, name: '小熊'},
					{id: 5, name: '的'},
					{id: 6, name: '舟舟'},
				]
			}
		},
		methods: {
		}
	}
</script>

<style>
</style>
