<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短租网</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<img src="images/logo.gif" />
		</div>
	</div>

	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1 align="center">欢迎访问短租网</h1>
				<ul class="steps clearfix">
					<li class="last"><em></em>登录状态</li>
					<li class="current"><em></em>注销成功</li>
				</ul>
				<form id="regForm" >
					<table width="509" height="300" bgcolor="#ddffdd">
						<tr>
							<td bgcolor="#ddffdd">
								<center>
									<font size="5">恭喜：注销成功！</font>
									<p>
										<font size="5">正在跳转......</font>
									</p>
								</center> <script type="text/javascript">
									setTimeout("location.href='loginUser?type=loginOut'",
											3000);
								</script></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
</html>