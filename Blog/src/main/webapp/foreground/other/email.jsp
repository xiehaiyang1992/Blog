<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="data_list">
	<div class="data_list_title">
		<span class="glyphicon glyphicon-envelope">POP3邮箱注册</span>
	</div>
	<div align="center"  style="height: 600px;">
		<br /> 
		<font>新用户名</font> <input class="input" id="name" type="text" />
		<font>用户密码</font> <input class="input" id="passowrd" type="password" />
		<br /> <br /> <input id="zhuce" type="button" class="btn btn"
			value="邮箱注册" />
	</div>
	<script>
			$(document).ready(function() {
				$("#zhuce").click(function() {
					var ajax = $.ajax({
						url: "https://www.xiehaiyang.club/email/adduser.do?name=" + $("#name").val() + "&password=" + $("#passowrd").val(),
						async: false
					});
					alert(ajax.responseText);
				});
			});
	</script>
</div>