<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>

<%-- 因为采用了CDN资源加速所以就不要用这些了，但是断网的时候还要用到那就不删除，暂时注释掉就行了
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jquery-3.3.1.min/jquery-3.3.1.min.js"></script> --%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/blog.css">
<link
	href="${pageContext.request.contextPath}/static/images/favicon.ico"
	rel="SHORTCUT ICON">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
}
</style>
<script type="application/javascript">
var path=window.location.pathname;
$(function() {
	if(path!="/index.html"){
		$("html").scrollTop($(".navbar-header").offset().top);
	}
	$('#qrcode').qrcode({text: "https://www.xiehaiyang.club",width: 190,height: 190,});
});
</script>
</head>
<body>
	<script src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
	<!-- 使用了canvas-nest做背景动画 -->
	<div class="container">
		<jsp:include page="/foreground/common/head.jsp" />
		<jsp:include page="/foreground/common/menu.jsp" />
		<div class="row">
			<div class="col-md-9">
				<jsp:include page="${mainPage }"></jsp:include>
			</div>
			<div class="col-md-3">
				<div class="data_list">
					<div class="data_list_title">
						<span class="glyphicon glyphicon-qrcode">&nbsp;博客网站二维码</span>
					</div>
					<div class="user_image">
						<%-- <img style="size: auto;"
							src="${pageContext.request.contextPath}/static/images/cli_300px.png" /> --%>
						<br>
							<div id="qrcode" style="height: auto; width: auto;"></div>
						<br>
					</div>
				</div>
				<div class="data_list">
					<div class="data_list_title">
						<span class="glyphicon glyphicon-duplicate">&nbsp;按日志类别</span>
					</div>
					<div class="datas">
						<ul>
							<c:forEach var="blogTypeCount" items="${blogTypeCountList }">
								<li><span><a
										href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="data_list">
					<div class="data_list_title">
						<span class="glyphicon glyphicon-calendar">&nbsp;按日志日期</span>
					</div>
					<div class="datas">
						<ul>
							<c:forEach var="blogCount" items="${blogCountList }">
								<li><span><a
										href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr }">${blogCount.releaseDateStr }(${blogCount.blogCount })</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="data_list">
					<div class="data_list_title">
						<span class="glyphicon glyphicon-link">&nbsp;友情链接</span>
					</div>
					<div class="datas">
						<ul>
							<c:forEach var="link" items="${linkList }">
								<li><span><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/foreground/common/foot.jsp" />
	</div>
</body>
</html>