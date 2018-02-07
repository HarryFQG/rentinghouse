<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="/mytags" %>
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
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>用户id</th>
					<th>用户名</th>
					<th>手机</th>
					<th>Email</th>
					<th>操作</th>
				</tr>
				 	<c:forEach var="user" items="${sessionScope.userList }" varStatus="flag">
						<tr>
							<td class="first w4 c">${flag.count }</td>
							<td class="w2 c">${user.uname }</td>
							<td>${user.mobile }</td>
							<td class="w4 c">${user.email }</td>
							<td class="w1 c"><a href="adminUser-modify.jsp?id=${user.uid }">修改</a>
							     <a class="manageDel" href="${pageContext.request.contextPath}/addUser?type=deleteUser&id=${user.uid }">删除</a></td>
						</tr>
					</c:forEach>
					
				
					
				
				
			</table>
		</div>
	</div>
	<div class="clear"></div>
     <div class="pager">
     
     			
     	<p:fengye totalRow="${sessionScope.totalRow }" totalPage="${sessionScope.totalPage }" currentPage="${sessionScope.pageCurrent}" redirectUrl="${pageContext.request.contextPath}/addUser?type=listUser" param="pageCurrent" />
				<%-- <ul class="clearfix">
				
					<li><a href="${pageContext.request.contextPath}/addUser?type=listUser&pageCurrent=1">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/addUser?type=listUser&pageCurrent=${sessionScope.pageCurrent -1 }">上一页</a></li>				
                    <li><a href="${pageContext.request.contextPath}/addUser?type=listUser&pageCurrent=${sessionScope.pageCurrent +1 }">下一页</a></li>
					<li><a ><a href="${pageContext.request.contextPath}/addUser?type=listUser&pageCurrent=${sessionScope.totalPage }">尾页</a></a></li>
				 	<li><input type="text" id="pageGo" style="width:50px;" /><a onclick="func1();">GO</a></li>			     
					<li>当前第${sessionScope.pageCurrent}/${sessionScope.totalPage }页</li>
				</ul> --%>
				
			</div>
</div>
<div id="footer"> 
	Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号 
</div>
</body>
<script type="text/javascript">
function func1(){
		
			var pagego=document.getElementById("zhiding");
			window.location.href="${pageContext.request.contextPath}/addUser?type=listUser&pageCurrent="+pagego.value;
		}
</script>
</html>
