<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="data_list">
	<div class="data_list_title"> 
		<span class="glyphicon glyphicon-volume-up">语音模块</span>
	</div>
	<div align="middle" style="height: 600px;">
		<br /> <font>请输入语音内容</font> <br /> <br />
		<textarea id="text" class="text-info"
			style="height: 200px; width: 600px;">庄圳涛是傻逼</textarea>
		<br /> <input id="baidu" type="button" value="百度语音" /> <input
			id="centos-zh" type="button" value="CentOS中文" /> <input
			id="centos-zhy" type="button" value="CentOS粤语" /> <input id="pinyin"
			type="button" value="拼音阅读" />
		<audio id="audio" controls="controls" hidden="hidden" src="#">
		</audio>
	</div>

	<script>
		$(document).ready(function() {
			$("#baidu").click(function() {
				var src = $.ajax({
					url : "https://www.xiehaiyang.club/speak/baidu.do?text=" + $("#text").val(),
					async : false
				});
				if (src.responseText.indexOf("error") >= 0) {
					alert("语音生产错误！");
				} else {
					$("#audio").attr("src", "https://www.xiehaiyang.club/static/media/" + src.responseText);
					$("#audio")[0].play();
				}
			});
	
			$("#centos-zh").click(function() {
				var src = $.ajax({
					url : "https://www.xiehaiyang.club/speak/centos_zh.do?text=" + $("#text").val(),
					async : false
				});
				$("#audio").attr("src", "https://www.xiehaiyang.club/static/media/" + src.responseText);
				$("#audio")[0].play();
			});
	
			$("#centos-zhy").click(function() {
				var src = $.ajax({
					url : "https://www.xiehaiyang.club/speak/centos_zhy.do?text=" + $("#text").val(),
					async : false
				});
				$("#audio").attr("src", "https://www.xiehaiyang.club/static/media/" + src.responseText);
				$("#audio")[0].play();
			});
	
			$("#pinyin").click(function() {
				var src = $.ajax({
					url : "https://www.xiehaiyang.club/speak/pinyin.do?text=" + $("#text").val(),
					async : false
				});
				$("#audio").attr("src", "https://www.xiehaiyang.club/static/media/" + src.responseText);
				$("#audio")[0].play();
			});
		})
	</script>
</div>