<template>
	<view>
		<view v-if="type == 'select'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<radio-group class="columnDisplay">
				<view class="xuanxiang" v-for="(v,index) in value" :key="index" :data-value="v" @tap="select">
					<radio :checked="defaultValue.indexOf(v) >= 0"></radio>
					<view class="s">{{v}}</view>
				</view>
				
			</radio-group>
		</view>
		<view v-else-if="type == 'multi-select'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<checkbox-group class="columnDisplay">
				<view class="xuanxiang" v-for="(v,index) in value" :key="index"  :data-value="v" @tap="multiSelect">
					<checkbox :checked="defaultValue.indexOf(v) >= 0" :disabled="result.length == maxSelect && result.indexOf(v+'') < 0"></checkbox>
					<view class="s">{{v}}</view>
				</view>
				
			</checkbox-group>
		</view>
		<view v-else-if="type == 'boolean'" class="rowDisplay borderGrey">
			<view class="boolTitle">
				<view v-if="title.length != 0" class="title">{{title}}</view>
				<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			</view>
			<radio-group class="boolAction">
				<view>
					<view class="xuanxiang" data-value="true" @tap="select">
						<radio :checked="defaultValue.indexOf(true) >= 0 || value[0]==defaultValue"><view>{{this.value[0]?this.value[0]:'true'}}</view></radio>
					</view>
					<view class="xuanxiang" data-value="false" @tap="select">
						<radio :checked="defaultValue.indexOf(false) >= 0 || value[1]==defaultValue"><view>{{this.value[1]?this.value[1]:'false'}}</view></radio>
					</view>
				</view>
			</radio-group>
		</view>
		<view v-else-if="type == 'input'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<input v-model="result[0]" :placeholder="defaultValue==''?'请输入':defaultValue" @input="input" :maxlength="maxSelect>0?maxSelect:30" @confirm="input" @blur="input" />
		</view>
		<view v-else-if="type == 'textarea'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<textarea v-model="result[0]" :placeholder="defaultValue==''?'请输入':defaultValue" @input="input" :maxlength="maxSelect>0?maxSelect:200" @blur="input" @confirm="input" />
		</view>
		<view v-else-if="type == 'level'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<slider :min="value[0]" :max="value[1]" :step="value[2]?value[2]:1" :value="result[0]" @change="slider" @changing="slider"></slider>
			<view class="rowDisplay">
				<view>{{value[0]}}</view>
				<input type="number" v-model="result[0]" @input="sliderChange" class="sliderInput" @confirm="sliderChange" @blur="sliderChange"></input>
				<view>{{value[1]}}</view>
			</view>
		</view>
		<view v-else-if="type == 'date'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<picker mode="date" :value="result[0]" @change="pickerDateSelect"><view class="datePicker">{{result[0]}}</view></picker>
		</view>
		<view v-else-if="type == 'time'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<picker mode="time" :value="result[0]" :start="value2[0]" :end="value2[1]" @change="pickerDateSelect"><view class="datePicker">{{result[0]}}</view></picker>
		</view>
		<view v-else-if="type == 'region'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<picker mode="multiSelector" :range="regionRange" :value="regionValue" @columnchange="pickerColumnChangeRegion" @change="pickerRegionSelect">
				<view class="datePicker">{{result[0]}}</view>
			</picker>
		</view>
		<view v-else-if="type == 'image'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<view class="imageContainer">
				<view v-for="(im,i) in result" :key="i" @longtap="imageMenu(i)" @tap="viewImage(i)">
					<image :src="im" class="imageBlock" mode="aspectFill"></image>
				</view>
				<view v-if="maxSelect > result.length || maxSelect == -1" class="imageBlock" @tap="addImage">
					<view>+</view>
				</view>
			</view>
		</view>
		<view v-else-if="type == 'phone'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<view class="rowDisplay">
				<input class="phone" v-model="result[0]" type="number" maxlength="11" placeholder="手机号" @input="input" @blur="input" @confirm="input" />
				<input class="code" v-model="result[1]" type="number" :maxlength="value2[0]" placeholder="验证码"  @input="input" @blur="input" @confirm="input" />
				<view class="sendButton bg-green" v-if="waitTime == 0" @tap="sendCode">发送验证码</view>
				<view class="sendButton bg-white" v-else>已发送({{waitTime}})</view>
			</view>
		</view>
		<view v-else-if="type == 'work'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<picker mode="multiSelector" :range="workRange" :value="workValue" @columnchange="pickerColumnChangeWork" @change="pickerWorkSelect">
				<view class="datePicker">{{result[0]}}</view>
			</picker>
		</view>
		<view v-else-if="type == 'university'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<picker mode="multiSelector" :range="universityRange" :value="universityValue" @columnchange="pickerColumnChangeUniversity" @change="pickerUniversitySelect">
				<view class="datePicker">{{result[0]}}</view>
			</picker>
		</view>
		<view v-else-if="type == 'single-picker'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<picker mode="selector" :range="value" :value="value2" @change="pickerSingleSelect">
				<view class="datePicker">{{result[0]}}</view>
			</picker>
		</view>
		<view v-else-if="type == 'password'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<view class="rowDisplay" v-if="value2[0] != null">
				<input class="password" :placeholder="defaultValue" v-model="result[0]"
				:maxlength="maxSelect?maxSelect:-1" :password="value2[0]" @input="input" @blur="input" />
				<image class="input-icon" :src="value2[0]?value2[1]:value2[2]" @tap="changePassShow"></image>
			</view>
			<input v-else :placeholder="defaultValue?defaultValue:''" v-model="result[0]" 
			:maxlength="maxSelect?maxSelect:-1" :password="true" @input="input" @blur="input" />
		</view>
		<view v-else-if="type == 'foreign'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<picker mode="selector" :range="countryRange" :value="countryValue" range-key="name" @change="pickerCountrySelect">
				<view class="datePicker">{{result[0]}}</view>
			</picker>
		</view>
		<view v-else-if="type=='local'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<view class="datePicker" @tap="getLocation">{{result[0]==''?(defaultValue==''||!defaultValue?'请点击获取地理位置':defaultValue):result[0]}}</view>
		</view>
		<view v-else-if="type == 'drive'" class="columnDisplay borderGrey">
			<view v-if="title.length != 0" class="title">{{title}}</view>
			<view v-if="subTitle.length != 0" class="subTitle">{{subTitle}}</view>
			<view class="rowDisplay datePicker" style="width: 90%;">
				<picker style="width: 15%;" :range="driveCity" :value="driveValue" @change="pickerDriveSelect">{{result[0]}}</picker>
				<input v-model="result[1]" style="width: 70%;"
				 :style="{letterSpacing:(result[1]==''?0:'2vw')}"
				 :placeholder="defaultValue==''||!defaultValue?'请输入车牌号':defaultValue" 
				 @input="upperCaseInput" :maxlength="7" @confirm="upperCaseInput" @blur="upperCaseInput" />
				<view style="width: 3vw;height:3vw; border-radius: 50%;" 
				 :style="{backgroundColor:result[1].length == 7?'#00d000':result[1].length == 6?'#3090d9':'#ff5500'}"></view>
			</view>
			
		</view>
		<!--view @tap="test">123</view-->
	</view>
</template>

<script>
	export default{
		data(){
			return{
				result:[""],
				value2:[],
				init:false,
				regionRange:[[],[],[]],
				regionValue:[0,0,0],
				//加载区域
				regionJSON:{},
				//短信等待时间
				waitTime:0,
				//加载工作分类
				workJSON:{},
				workRange:[[],[]],
				workValue:[0,0],
				//加载学校分类
				universityJSON:{},
				universityRange:[[],[]],
				universityValue:[0,0],
				zoneList:[],
				//密码可见图标
				passwordImages:[
					"data:image/png;base64,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",
					"data:image/png;base64,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"
				],
				//国家信息
				countryJSON:{},
				countryRange:[],
				countryValue:0,
				//车牌省会
				driveCity:[
					'京','沪','浙','苏','粤','鲁','晋','冀','豫','川',
					'渝','辽','吉','黑','皖','鄂','津','贵','云','桂','琼',
					'青','新','藏','蒙','宁','甘','陕','闽','赣','湘'
				],
				driveValue:0,
			}
		},
		mounted() {
			this.$set(this,'regionJSON',require('@/components/gwh-wenjuan/province.json'));
			this.$set(this,'workJSON',require('@/components/gwh-wenjuan/work.json'));
			this.$set(this,'universityJSON',require('@/components/gwh-wenjuan/university.json'));
			this.$set(this,'countryJSON',require('@/components/gwh-wenjuan/country.json'));
			//console.log(this.type);
			if(this.type == "multi-select"){
				//console.log(this.defaultValue);
				if(typeof this.defaultValue == 'string'){
					//this.result[0] = this.defaultValue
					console.log(typeof this.defaultValue);
					this.$set(this.result,0,this.defaultValue)
				}
				else{
					this.result = this.defaultValue
					//console.log(typeof this.defaultValue)
					this.$set(this,'result',this.defaultValue)
				}
				//console.log(this.result)
			}
			else if(this.type == "level"){
				//console.log(this.defaultValue);
				if(typeof this.defaultValue == 'string' && !isNaN(parseInt(this.defaultValue)) &&
				parseInt(this.defaultValue) >= parseInt(this.value[0] + "") && 
				parseInt(this.defaultValue) <= parseInt(this.value[1] + "")
				){
					//console.log('default data is OK')
					this.$set(this.result,0,parseInt(this.defaultValue))
				}
				else{
					this.$set(this.result,0,parseInt(this.value[0]+""))
				}
				//console.log(this.result);
			}
			else if(this.type == 'date'){
				var date = new Date();
				if(this.defaultValue != undefined && this.defaultValue != null){
					date = new Date(this.defaultValue);
				}
				var dateString = date.getFullYear() + '-' + 
					((date.getMonth()+1) >= 10 ? (date.getMonth()+1) : ("0"+(date.getMonth()+1))) + '-' + 
					(date.getDate() >= 10 ? date.getDate() : ("0"+date.getDate()))
				this.$set(this.result,0,dateString);
				//console.log(this.result);
			}
			else if(this.type == 'time'){
				if(this.defaultValue != undefined && this.defaultValue != null){
					//console.log(this.defaultValue);
					var times = (this.defaultValue).split(':');
					if(times.length != 2 || isNaN(parseInt(times[0])) || isNaN(parseInt(times[1])) || 
					parseInt(times[0]) < 0 || parseInt(times[0]) > 23 || parseInt(times[1]) < 0 || parseInt(times[1]) > 59){
						var date = new Date();
						var hour = date.getHours() < 10 ? "0"+date.getHours():date.getHours();
						var minute = date.getMinutes() < 10 ? "0"+date.getMinutes():date.getMinutes();
						this.$set(this.result,0,hour+":"+minute);
						console.log(this.result)
					}
					else{
						this.$set(this.result,0,this.defaultValue);
						//console.log(this.result)
					}
				}
				else{
					var date = new Date();
					var hour = date.getHours() < 10 ? "0"+date.getHours():date.getHours();
					var minute = date.getMinutes() < 10 ? "0"+date.getMinutes():date.getMinutes();
					this.$set(this.result,0,hour+":"+minute);
					console.log(this.result)
				}
				if(this.value == undefined || this.value == null || this.value.length != 2){
					this.$set(this,'value2',['00:00','23:59'])
				}
				else{
					this.$set(this,'value2',this.value)
				}
				//console.log(this.value2);
				//console.log(this.result);
			}
			else if(this.type == 'region'){
				//console.log(this.regionJSON);
				//获取一级列表
				var province = this.regionJSON.province;
				var pRange = [];
				var p0 = null;
				for(var i in province){
					pRange.push(i);
					if(p0 == null){
						p0 = province[i];
					}
				}
				this.$set(this.regionRange,0,pRange);
				//获取初始的二级列表
				var cRange = [];
				var a0 = null;
				for(var i in p0){
					cRange.push(i);
					if(a0 == null){
						a0 = p0[i]
					}
				}
				this.$set(this.regionRange,1,cRange);
				//获取初始的三级列表
				this.$set(this.regionRange,1,a0);
				//console.log(this.regionRange);
				//初始化result0
				var regionString = this.regionRange[0][this.regionValue[0]]+'-'+
					this.regionRange[1][this.regionValue[1]];
				if(this.regionRange[2].length > 0){
					regionString += "-"+this.regionRange[2][this.regionValue[2]];
				}
				this.$set(this.result,0,regionString);
			}
			else if(this.type == "image"){
				this.$set(this,'result',this.result.splice(1,1));
				//console.log(this.result);
			}
			else if(this.type == 'phone'){
				var initValue = [];
				if(this.value != undefined && this.value != null && typeof this.value == 'object'){
					if(this.value.length > 0 && !isNaN(parseInt(this.value[0]))){
						//第一个参数是有几位验证码
						initValue.push(Math.max(parseInt(this.value[0]),2))
					}
					else{
						initValue.push(6)
					}
					if(this.value.length > 1 && !isNaN(parseInt(this.value[1]))){
						//第2个参数是等待时间S
						initValue.push(Math.max(parseInt(this.value[1]),1))
					}
					else{
						initValue.push(60)
					}
				}
				else{
					initValue.push(6)
					initValue.push(60)
				}
				this.$set(this,'value2',initValue);
				this.$set(this,'result',["",""]);
			}
			else if(this.type == 'work'){
				//console.log(this.workJSON);
				//获取一级列表
				var work = this.workJSON.work;
				var pRange = [];
				var p0 = null;
				for(var i in work){
					pRange.push(i);
					if(p0 == null){
						p0 = work[i];
					}
				}
				this.$set(this.workRange,0,pRange);
				//获取初始的二级列表
				this.$set(this.workRange,1,p0);
				//console.log(this.regionRange);
				//初始化result0
				var workString = this.workRange[0][this.workValue[0]]+'-'+
					this.workRange[1][this.workValue[1]];
				this.$set(this.result,0,workString);
			}
			else if(this.type == 'university'){
				var zone = this.universityJSON.zone;
				var zoneList = this.zoneList;
				var zoneRange = [];
				for(var i=0;i<zone.length;i++){
					zoneRange.push(zone[i].name)
					zoneList.push({
						id:zone[i].id,
						name:zone[i].name,
						schoolList:[]
					})
				}
				//console.log(zoneList);
				this.$set(this.universityRange,0,zoneRange);
				var school = this.universityJSON.university;
				//console.log(school)
				for(var i=0;i<school.length;i++){
					var asc = school[i];
					for(var j=0;j<zoneList.length;j++){
						if(zoneList[j].id == asc.zone){
							zoneList[j].schoolList.push(asc.name)
							break;
						}
					}
				}
				this.$set(this,'zoneList',zoneList);
				this.$set(this.universityRange,1,zoneList[0].schoolList);
				var universityString = this.universityRange[0][this.universityValue[0]]+'-'+
					this.universityRange[1][this.universityValue[1]];
				this.$set(this.result,0,universityString);
			}
			else if(this.type == 'single-picker'){
				if(!(isNaN(parseInt(this.defaultValue)))){
					this.$set(this,'value2',Math.min(this.value.length-1,Math.max(parseInt(this.defaultValue),0)));
				}
				else this.$set(this,'value2',0);
				this.$set(this.result,0,this.value[this.value2]);
			}
			else if(this.type == 'password'){
				if(typeof this.value == 'object' && this.value.length > 0 && (this.value[0] == "1" || this.value[0] == "true" || this.value[0] == true)){
					this.$set(this.value2,0,true);
				}
				else if(typeof this.value == 'object' && this.value.length > 0 && (this.value[0] == "0" || this.value[0] == "false" || this.value[0] == false)){
					this.$set(this.value2,0,false);
				}
				else if(typeof this.value == 'object' && this.value.length > 0 && this.value[0] == 'none'){
					this.$set(this.value2,0,null);
				}
				else this.$set(this.value2,0,true);
				this.$set(this.result,0,"");
				if(typeof this.value == 'object' && this.value.length > 2){
					this.$set(this.value2,1,this.value[1]);
					this.$set(this.value2,2,this.value[2]);
				}
				else{
					this.$set(this.value2,1,this.passwordImages[0]);
					this.$set(this.value2,2,this.passwordImages[1]);
				}
			}
			else if(this.type == 'foreign'){
				this.$set(this,'countryRange',this.countryJSON.country);
				var countryString = this.countryRange[this.countryValue].name+"("+
					this.countryRange[this.countryValue].ename+")";
				this.$set(this.result,0,countryString);
			}
			else if(this.type == 'local'){
				this.$set(this.result,0,'');
			}
			else if(this.type == 'drive'){
				this.$set(this.result,0,this.driveCity[this.driveValue]);
				this.$set(this.result,1,'');
			}
			
			//初始化返回结果
			this.$emit('change',this.result);
		},
		methods:{
			select(e){
				console.log(e);
				this.$set(this.result,0,e.currentTarget.dataset.value);
				this.$emit('change',this.result);
			},
			changePassShow(){
				this.$set(this.value2,0,!this.value2[0]);
			},
			multiSelect(e){
				//console.log(e);
				var value = e.currentTarget.dataset.value+"";
				var rs = this.result
				//console.log(this.result);
				var index = rs.map(String).indexOf(value)
				if(index>=0){
					rs.splice(index,1)
				}
				else if(this.maxSelect == -1 || rs.length < this.maxSelect){
					rs.push(value)
				}
				else{
					wx.showToast({
						title:'最多不超过：'+this.maxSelect,
						icon:'none'
					})
					
				}
				this.$set(this,'result',rs);
				//console.log(this.result);
				this.$emit('change',this.result);
			},
			input(e){
				//console.log(e);
				this.$nextTick(function(){
					this.$emit('change',this.result);
				})
			},
			upperCaseInput(e){
				this.$nextTick(function(){
					let val = e.detail.value;
					if (/[^a-zA-Z0-9]/g.test(val)) { // 先过滤不需要的字符，只保留数字和字母
						val = val.replace(/[^a-zA-Z0-9]/g, '');
					}
					if (!/^[A-Z\d]+$/.test(val)) {// 再进行转换，小写转为大写
						val = val.toUpperCase();
					}
					this.$set(this.result,1,val);
					this.$emit('change',this.result);
				})
			},
			slider(e){
				//console.log(e);
				this.$set(this.result,0,e.detail.value)
				this.result = this.result;
				this.$emit('change',this.result);
			},
			sliderClick(e){
				console.log(e);
			},
			sliderChange(){
				var result = this.result[0];
				if(result.length == 0) {
					this.$set(this.result,0,"");
					return;
				}
				result = parseInt(result+"");
				if(result < this.value[0]){
					this.$set(this.result,0,this.value[0])
				}
				else if(result > this.value[1]){
					this.$set(this.result,0,this.value[1])
				}
				else{
					this.$set(this.result,0,result);
				}
				console.log(this.result[0]);
				this.$emit('change',this.result);
			},
			pickerDateSelect(e){
				this.$set(this.result,0,e.detail.value);
				this.$emit('change',this.result);
			},
			pickerColumnChangeRegion(e){
				//console.log(e);
				//修改一级
				if(e.detail.column == 0){
					//更新2级
					//console.log(this.regionRange[0][e.detail.value])
					var newP = this.regionJSON.province[this.regionRange[0][e.detail.value]];
					//console.log(newP);
					var cRange = [];
					var a0 = null;
					for(var i in newP){
						cRange.push(i)
						if(a0 == null){
							a0 = newP[i]
						}
					}
					this.$set(this.regionValue,0,e.detail.value);
					this.$set(this.regionRange,1,cRange);
					this.$set(this.regionValue,1,0);
					//更新3级
					this.$set(this.regionRange,2,a0);
					this.$set(this.regionValue,2,0);
				}
				//修改2级
				else if(e.detail.column == 1){
					var newA = this.regionJSON.province[this.regionRange[0][this.regionValue[0]]][this.regionRange[1][e.detail.value]];
					this.$set(this.regionValue,1,e.detail.value);
					//更新3级
					this.$set(this.regionRange,2,newA);
					this.$set(this.regionValue,2,0);
				}
				else{
					this.$set(this.regionValue,2,e.detail.value);
				}
			},
			pickerColumnChangeWork(e){
				//console.log(e);
				//修改一级
				if(e.detail.column == 0){
					//更新2级
					//console.log(this.regionRange[0][e.detail.value])
					var newP = this.workJSON.work[this.workRange[0][e.detail.value]];
					//console.log(newP);
					this.$set(this.workValue,0,e.detail.value);
					this.$set(this.workRange,1,newP);
					this.$set(this.workValue,1,0);
				}
				else{
					this.$set(this.workValue,1,e.detail.value);
				}
			},
			pickerColumnChangeUniversity(e){
				//console.log(e);
				//修改一级
				if(e.detail.column == 0){
					//更新2级
					//console.log(this.regionRange[0][e.detail.value])
					var newP = this.zoneList[e.detail.value].schoolList;
					//console.log(newP);
					this.$set(this.universityValue,0,e.detail.value);
					this.$set(this.universityRange,1,newP);
					this.$set(this.universityValue,1,0);
				}
				else{
					this.$set(this.universityValue,1,e.detail.value);
				}
			},
			pickerRegionSelect(e){
				var regionString = this.regionRange[0][this.regionValue[0]]+'-'+
					this.regionRange[1][this.regionValue[1]];
				if(this.regionRange[2] != undefined && typeof this.regionRange == 'object' && this.regionRange[2].length > 0){
					regionString += "-"+this.regionRange[2][this.regionValue[2]];
				}
				this.$set(this.result,0,regionString);
				this.$emit('change',this.result);
			},
			pickerWorkSelect(e){
				var workString = this.workRange[0][this.workValue[0]]+'-'+
					this.workRange[1][this.workValue[1]];
				this.$set(this.result,0,workString);
				this.$emit('change',this.result);
			},
			pickerUniversitySelect(e){
				var universityString = this.universityRange[0][this.universityValue[0]]+'-'+
					this.universityRange[1][this.universityValue[1]];
				this.$set(this.result,0,universityString);
				this.$emit('change',this.result);
			},
			pickerSingleSelect(e){
				//console.log(e);
				this.$set(this,'value2',e.detail.value);
				this.$set(this.result,0,this.value[this.value2]);
				this.$emit('change',this.result);
			},
			pickerCountrySelect(e){
				//console.log(e);
				this.$set(this,'countryValue',e.detail.value);
				var countryString = this.countryRange[e.detail.value].name+"("+
					this.countryRange[e.detail.value].ename+")";
				this.$set(this.result,0,countryString);
				this.$emit('change',this.result);
			},
			pickerDriveSelect(e){
				//console.log(e);
				this.$set(this,'driveValue',e.detail.value);
				this.$set(this.result,0,this.driveCity[this.driveValue]);
				this.$emit('change',this.result);
			},
			addImage(){
				var that = this;
				if(this.maxSelect > 0 && this.maxSelect <= this.result.length){
					uni.showToast({
						title:'图数('+this.result.length+'/'+this.maxSelect+'),不得插入',
						complete:function(res){
							that.$emit('change',that.result);
						}
					})
				}
				else{
					console.log(this.maxSelect);
					uni.chooseImage({
						count:this.maxSelect!=-1?(this.maxSelect-this.result.length):9,
						success:function(res){
							var newResult = [];
							for(var i=0;i<that.result.length;i++){
								newResult.push(that.result[i])
							}
							if(that.maxSelect > 0){
								for(var i=0;i<Math.min(that.maxSelect - that.result.length, res.tempFilePaths.length);i++){
									newResult.push(res.tempFilePaths[i])
								}
							}
							else{
								for(var i=0;i<res.tempFilePaths.length;i++){
									newResult.push(res.tempFilePaths[i])
								}
							}
							that.$set(that,'result',newResult);
							that.$emit('change',that.result);
						},
						fail:function(err){
							that.$emit('change',that.result);
						}
					})
				}
			},
			imageMenu(i){
				//console.log(i);
				var that = this;
				uni.showActionSheet({
					itemList:["向前移动","向后移动","替换图片","删除图片"],
					success:function(res){
						console.log(res.tapIndex);
						if(res.tapIndex == 0){
							//向前
							if(i > 0){
								var result = that.result;
								var img = result[i];
								result[i] = result[i-1];
								result[i-1] = img;
								var newResult = [];
								for(var j=0;j<result.length;j++){
									newResult.push(result[j]);
								}
								that.$set(that,'result',newResult);
								that.$emit('change',that.result);
							}
							else{
								uni.showToast({
									title:'无可替换',
									icon:'none',
									complete:function(res){
										that.$emit('change',that.result);
									}
								})
							}
						}
						else if(res.tapIndex == 1){
							//向后
							if(i < that.result.length - 1){
								var result = that.result;
								var img = result[i];
								result[i] = result[i+1];
								result[i+1] = img;
								var newResult = [];
								for(var j=0;j<result.length;j++){
									newResult.push(result[j]);
								}
								that.$set(that,'result',newResult);
								that.$emit('change',that.result);
							}
							else{
								uni.showToast({
									title:'无可替换',
									icon:'none',
									complete:function(res){
										that.$emit('change',that.result);
									}
								})
							}
						}
						else if(res.tapIndex == 2){
							uni.chooseImage({
								count:1,
								success:function(res){
									var result = that.result;
									//console.log(result);
									var newResult = [];
									for(var j=0;j<result.length;j++){
										if(i!=j){
											newResult.push(result[j]);
										}
										else{
											newResult.push(res.tempFilePaths[0])
										}
									}
									//console.log(newResult);
									that.$set(that,'result',newResult);
									/*that.$nextTick(()=>{
										console.log("refresh");
									});*/
									that.$emit('change',that.result);
								},
								fail:function(err){
									that.$emit('change',that.result);
								}
							})
						}
						else if(res.tapIndex == 3){
							uni.showModal({
								title:'消息',
								content:'您确认删除该图片吗？',
								success:(res)=>{
									if(res.confirm){
										var result = that.result;
										//console.log(result);
										var newResult = [];
										for(var j=0;j<result.length;j++){
											if(i!=j){
												newResult.push(result[j]);
											}
										}
										//console.log(newResult);
										that.$set(that,'result',newResult);
										//console.log(that.result);
										that.$emit('change',that.result);
									}
									else{
										that.$emit('change',that.result);
									}
								},
								fail:err=>{
									that.$emit('change',that.result);
								}
							})
						}
					}
				})
			},
			viewImage(i){
				uni.previewImage({
					urls:this.result,
					current:i
				})
			},
			sendCode(e){
				if(this.result[0].length == 11 && /^1[3456789]\d{9}$/.test(this.result[0])){
					this.$emit('send',this.result);
					this.$set(this,'waitTime',this.value2[1]);
					this.wait();
				}
				else{
					uni.showToast({
						title:'手机号错误',
						icon:'none'
					})
				}
			},
			wait(){
				if(this.waitTime > 0){
					setTimeout(()=>{
						this.$set(this,'waitTime',this.waitTime - 1);
						this.wait()
					},1000);
				}
			},
			getLocation(){
				uni.showLoading({
					title:'获取地理位置中'
				})
				uni.request({
					url:'http://ip-api.com/json/?lang=zh-CN',
					method:'GET',
					success:(res)=>{
						uni.hideLoading();
						//console.log(res);
						var locString=res.data.country+"-"+
							res.data.regionName+"-"+
							res.data.city;
						this.$set(this.result,0,locString);
						this.$set(this.result,1,[res.data.lat,res.data.lon]);
						this.$set(this.result,2,res.data.query);
						this.$emit('change',this.result);
					}
				})
			}
		},
		//name:'wenjuan',
		props:{
			type:{
				type:String,
				default:"select"
			},
			title:{
				type:String,
				default:""
			},
			subTitle:{
				type:String,
				default:""
			},
			value:{
				type:[String,Array],
				default:()=>{}
			},
			defaultValue:{
				type:[String,Array],
				default:()=>{}
			},
			maxSelect:{
				type:Number,
				default:-1
			}
		},
	}
</script>

<style scoped lang="scss">
	.columnDisplay{
		display: flex;
		flex-direction: column;
		margin-left: 2.5%;
		margin-right: 2.5%;
		width: 95%;
	}
	
	view{
		word-break: break-all;
	}
	
	.borderGrey{
		padding-bottom: 10upx;
		border-bottom: #ececec solid 1px;
		margin-bottom: 10upx;
	}
	
	.rowDisplay{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		margin-left: 2.5%;
		margin-right: 2.5%;
		width: 95%;
		align-items: center;
	}
	
	.boolTitle{
		min-width: 50%;
		max-width: 70%;
		display: flex;
		flex-direction: column;
		word-break: break-all;
		
	}
	
	.boolAction view{
		display: flex;
		flex-direction: column;
		word-break: break-all;
	}
	
	.boolAction{
		max-width: 50%;
		min-width: 30%;
		display: flex;
		flex-direction: row-reverse;
	}
	
	.title{
		font-size: 40upx;
		font-weight: bold;
		margin-bottom: 5upx;
	}
	
	.subTitle{
		font-size: 35upx;
		color: #a6a6a6;
		margin-bottom: 5upx;
	}
	
	input{
		background-color: #F8F8F8;
		padding-left: 10upx;
		padding-right: 10upx;
		padding-top: 10upx;
		padding-bottom: 10upx;
	}
	
	textarea{
		background-color: #F8F8F8;
		padding-left: 2.5%;
		padding-right: 2.5%;
		padding-top: 10upx;
		padding-bottom: 10upx;
		width: 95%;
		max-lines: 5;
		min-height: 50upx;
		max-height: 150upx;
		display: flex;
	}
	
	.sliderInput{
		text-align: center;
		width: 30%;
	}
	
	.xuanxiang{
		width: 100%;
		display: flex;
		flex-direction: row;
		align-items: flex-start;
		justify-content: space-between;
	}
	
	.xuanxiang .s{
		width:90%;
		word-break: break-all;
		font-size: 36upx;
	}
	
	.xuanxiang .s2{
		width:50%;
		word-break: break-all;
	}
	
	.datePicker{
		display: flex;
		flex-direction: row;
		justify-content: center;
		background-color: #F8F8F8;
		padding-left: 2.5%;
		padding-right: 2.5%;
		padding-top: 10upx;
		padding-bottom: 10upx;
		width: 95%;
	}
	
	.imageContainer{
		display: block;
		width: 95%;
		padding-left: 2.5%;
		padding-right: 2.5%;
		padding-top: 10upx;
		padding-bottom: 10upx;
	}
	
	.imageBlock{
		width: 21.6vw;
		margin-right: 0.4%;
		margin-left: 0.4%;
		margin-bottom: 0.4%;
		margin-top: 0.4%;
		display: flex;
		height: 21.6vw;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		background-color: #F1F1F1;
		float: left;
		line-height: 23%;
	}
	
	.phone{
		max-width:40%;
		background-color: #FFFFFF;
		border-bottom: 1px solid #C0C0C0;
	}
	
	.code{
		max-width:25%;
		text-align: center;
	}
	
	.sendButton{
		max-width: 25%;
		font-size: 25upx;
		text-align: center;
		padding-left: 15upx;
		padding-right: 15upx;
		padding-top: 15upx;
		padding-bottom: 15upx;
		border-radius: 10upx;
	}
	
	.password{
		min-width: 80%;
		max-width: 90%;
		background-color: #FFFFFF;
		border-bottom: 1px solid #C0C0C0;
	}
	
	.bg-green{
		background-color: #4CD964;
		color: #FFFFFF;
	}
	
	.bg-white{
		background-color: #F8F8F8;
		color: #2C405A;
	}
	
	.input-icon{
		width: 55upx;
		height: 55upx;
	}
</style>
