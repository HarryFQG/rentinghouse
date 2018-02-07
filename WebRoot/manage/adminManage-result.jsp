<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 短租网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
</head>
<body>
     



<%@include file="adminTop.jsp" %>     
	<div class="main">
		<h2>提示信息</h2>
		<div class="manage">
			<div class="shadow">
				<em class="corner lb"></em>
				<em class="corner rt"></em>
				<div class="box">
					<div class="msg">
						<p>恭喜：操作成功！</p>
						<p>正在进入首页...</p>
						<script type="text/javascript">
							setTimeout("location.href='adminCms.htm'", 1000);
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer"> 
	Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
</html>
