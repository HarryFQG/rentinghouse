<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h2><font face="Adobe Caslon Pro Bold">修改用户</font></h2>
		<div class="manage">
			<form action="${pageContext.request.contextPath }/addUser" method="post">
			<font color="red"></font>
			<!-- form表单隐藏域，用于修改用户时 根据id修改，其中id不能修改 -->
			<font face="Adobe Caslon Pro Bold"> 
			   <input type="hidden" name="userId" value="1" /></font>
				
				
					
				<table class="form">
				<c:forEach var="user" items="${sessionScope.userList }" varStatus="flag">
					<c:if test="${user.uid ==param.id }">
							<font face="Adobe Caslon Pro Bold"> <input type="hidden" name="type" value="updateUser" />
			   				<input type="hidden" name="userId" value="${user.uid }" /></font>
					<tr>
						<td class="field"><font face="Adobe Caslon Pro Bold">用户名(*)：</font></td>
						<td><font face="Adobe Caslon Pro Bold"><input type="text" class="text" name="userName" value="${user.uname }" readonly="readonly" /></font></td>
					</tr>
				 
					<tr>
						<td class="field"><font face="Adobe Caslon Pro Bold">登录密码(*)：</font></td>
						<td><font face="Adobe Caslon Pro Bold"><input type="text" class="text" name="password" value="123" /></font></td>
					</tr>
                    <tr>
						<td class="field"><font face="Adobe Caslon Pro Bold">确认密码(*)：</font></td>
						<td><font face="Adobe Caslon Pro Bold"><input type="text" class="text" name="password1" value="123" /></font></td>
					</tr>
					<tr>
						<td class="field"><font face="Adobe Caslon Pro Bold">手机(*)：</font></td>
						<td><font face="Adobe Caslon Pro Bold"><input type="text" class="text" name="mobile" value="${user.mobile }" /></font></td>
					</tr>
					<tr>
						<td class="field"><font face="Adobe Caslon Pro Bold">邮箱(*)：</font></td>
						<td><font face="Adobe Caslon Pro Bold"><input type="text" class="text" name="email" value="${user.email }" /></font></td>
					</tr>				
					<tr>
						<td><br /></td>
						<td><font face="Adobe Caslon Pro Bold"><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></font></td>
					</tr>
						</c:if>
				</c:forEach>
				</table>
			</form>
		</div>
	</div>
	

<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
</html>
