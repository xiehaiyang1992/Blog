<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="data_list">
	<div class="data_list_title">
		<span class="glyphicon glyphicon-plus-sign">系统监控</span>
	</div>
	<div align="middle" style="height: 800px;">
		<div >
			<select id="select" style="width:200px;background-color:width:200px;blue;height: 30px">
				<option  value="5min">5分钟</option>
				<option value="1day">一天</option>
				<option value="1week">一周</option>
			</select>
		</div>
		<div id="container1" style="height: 45%"></div>
		<div id="container2" style="height: 45%"></div>
	</div>
		<script src="https://cdn.bootcss.com/echarts/4.1.0.rc2/echarts.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
		<script>
			var dom1 = document.getElementById("container1");
			var myChart1 = echarts.init(dom1);
			var app1 = {};
			option1 = {
				title: {
					text: '内存情况',
					x: '15%',
					y: '10',
					textStyle: {
						fontWeight: 'normal',
						fontSize: 24,
						color: "#000"
					},
				},
				tooltip: {
					trigger: 'axis',
					formatter: "{b}<br/>{a}:{c}g"
				},
				toolbox: {
					feature: {
						saveAsImage: {}
					}
				},
				legend: {
					data: ['内存使用情况']
				},
				dataZoom: [{
					show: false,
					realtime: true,
					start: 0,
					end: 100
				}, {
					type: 'inside',
					realtime: true
				}],
				xAxis: {
					type: 'category',
					boundaryGap: false,
				},
				yAxis: {
					name: '单位G',
					type: 'value',
					min: 0.0,

				},
				color: ['blue'],
				series: [{
					name: "内存使用情况",
					data: [0],
					type: 'line',
					smooth: true,
					areaStyle: {}
				}]
			}

			function dateFormat_2(longTypeDate) {
				var dateType = "";
				var date = new Date();
				date.setTime(longTypeDate);
				dateType = date.getFullYear() + "-" + ('0' + (date.getMonth(date) + 1)).substr(-2) + "-" + ('0' + date.getDate(date)).substr(-2); //yyyy-MM-dd格式日期
				dateType += "\n" + ('0' + date.getHours(date)).substr(-2) + ":" + ('0' + date.getMinutes(date)).substr(-2) + ":" + ('0' + date.getSeconds()).substr(-2);
				return dateType;
			}
			var dom2 = document.getElementById("container2");
			var myChart2 = echarts.init(dom2);
			var app2 = {};
			option2 = {
				title: {
					text: 'CPU使用率',
					x: '15%',
					y: '10',
					textStyle: {
						fontWeight: 'normal',
						fontSize: 24,
						color: "#000"
					},
				},
				tooltip: {
					trigger: 'axis',
					formatter: "{b}<br/>{a}:{c}%"
				},
				legend: {
					data: ['cpu使用率']
				},
				dataZoom: [{
						show: false,
						realtime: true,
						start: 0,
						end: 100,
					},
					{
						type: 'inside',
						realtime: true
					}
				],
				xAxis: {
					type: 'category',
					boundaryGap: false,
					data: []
				},
				yAxis: {
					name: '单位%',
					type: 'value'
				},
				color: ['red'],
				series: [{
					name: "cpu使用率",
					data: [],
					type: 'line',
					areaStyle: {}
				}]
			}
			$(document).ready(update());

			$(document).ready(function() {
				$("#select").change(function() {
					update();
				});
			})

			function update() {
				var scope = {
					scope: $("#select").val()
				};
				$.ajax({
					type: "post",
					//url: "./cpu.json", 
					url: "${pageContext.request.contextPath}/system/cpu.do",
					data: scope,
					async: true,
					dataType: "json", //返回数据形式为json
					success: function(ajax, status) {
						var xdata = [];
						var cpu0 = [];
						var ymax = 100;
						for(var i = 0; i < ajax.length; i++) {
							xdata.push(dateFormat_2(ajax[i].time));
							cpu0.push(parseFloat(ajax[i].cpu0 * 100).toFixed(2));

						}
						option2.series[0].data = cpu0.reverse();
						option2.xAxis.data = xdata.sort();
						option2.yAxis.max = ymax;
						myChart2.setOption(option2, true);
					},
					error: function() {
						alert("error");
					}
				});
				$.ajax({
					type: "get",
					//url: "./men.json",
					url: "${pageContext.request.contextPath}/system/mem.do",
					async: true,
					data: scope,
					dataType: "json", //返回数据形式为json
					success: function(ajax, status) {
						var xdata = [];
						var ymax = Math.round(ajax[0].total);
						var sdata = [];
						for(var i = 0; i < ajax.length; i++) {
							xdata.push(dateFormat_2(ajax[i].time));
							sdata.push(parseFloat(ajax[i].used).toFixed(2));
						}
						option1.series[0].data = sdata.reverse();
						option1.xAxis.data = xdata.sort();
						option1.yAxis.max = ymax;
						myChart1.setOption(option1, true);
					},
					error: function() {
						alert("error");
					}
				});
			}
		</script>
</div>