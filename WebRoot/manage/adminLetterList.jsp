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
		<div class="guestbook">
			<h2>消息列表</h2>
			<ul>
			</ul>
		<div class="manage">
			<table class="list">
				<tr>
					<th>消息id</th>
					<th>发送人</th>
					<th>收件人</th>
					<th>发送内容</th>
					<th>发送时间</th>
				</tr>
				 
					<tr>
						<td class="first w2 c">1</td>
						<td class="w4 c">李四</td>
						<td class="w4 c">张三</td>
						<td class="w4 c">你好，请问您的大洋房是在什么位置？</td>
						<td class="w4 c">2016-08-06</td>
					</tr>
				
					<tr>
						<td class="first w2 c">2</td>
						<td class="w4 c">张三</td>
						<td class="w4 c">李四</td>
						<td class="w4 c">在一路哦，亲~</td>
						<td class="w4 c">2016-08-06</td>
					</tr>
				
					<tr>
						<td class="first w2 c">3</td>
						<td class="w4 c">李四</td>
						<td class="w4 c">张三</td>
						<td class="w4 c">那么大概每天多少钱呢？</td>
						<td class="w4 c">2016-08-06</td>
					</tr>
				
					<tr>
						<td class="first w2 c">4</td>
						<td class="w4 c">张三</td>
						<td class="w4 c">李四</td>
						<td class="w4 c">每天300元呢，亲~</td>
						<td class="w4 c">2016-08-06</td>
					</tr>
				
					<tr>
						<td class="first w2 c">5</td>
						<td class="w4 c">张三</td>
						<td class="w4 c">李四</td>
						<td class="w4 c">欢迎惠顾~</td>
						<td class="w4 c">2016-08-06</td>
					</tr>
								
			</table>
		</div>
	</div>
		</div>
	</div>
	<div class="clear">
<div class="pager">

				<ul class="clearfix">
				
					<li>首页</li>
					<li>上一页</li>
				
				
				
				
                    <li>下一页</li>
					<li><a >尾页</a></li>
				 
				
			     
				<li>当前第1/1页</li>
				</ul>
				
			</div>
</div>
	</div>
	<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
</html>
