<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短租网 </title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
</head>
<body>
	

<%@ include file="cmsTop.jsp" %>



	<div class="main">
		<div class="guestbook">
			<h2>消息列表</h2>
			<ul>
			</ul>
		<div class="manage">
			<table class="list">
				<tr>
					<th>消息id</th>
					<th>消息名</th>
					<th>状态</th>
				</tr>
				 
				 
				 <c:forEach items="${sessionScope.ruserList }" var="user1">				 
					<tr>
						<%-- 1.<c:out value="${user1.crid }"></c:out>
						2.<c:out value="${user1.ccid }"></c:out>
						3.<c:out value="${sessionScope.user.uid }"></c:out> --%>
						<c:if test="${sessionScope.user.uid==user1.crid }">
						
							<td class="first w2 c">${user1.ccid }</td>
							<td class="w4 c"><a href="${pageContext.request.contextPath }/letterList?type5=letterDetail&userId=${user1.ccid}&fangDongId=${sessionScope.user.uid }">与  &nbsp;${user1.ccname }&nbsp;的消息</a></td>
							<td class="w1 c">已查看</td>
						</c:if>
						<%-- <c:if test="${sessionScope.user.uid==user1.ccid }">							
							<td class="first w2 c">${user1.crid }</td>
							<td class="w4 c"><a href="${pageContext.request.contextPath }/letterList?type5=letterDetail&userId=${sessionScope.user.uid }&fangDongId=${user1.crid}">与  &nbsp;${user1.crname }&nbsp;的消息</a></td>
							<td class="w1 c">已查看</td>							
						</c:if> --%>
						
						
					</tr>
					<!-- <tr>
						<td class="first w2 c">1</td>
						<td class="w4 c"><a href="letter.jsp">与  &nbsp;李四&nbsp;的消息</a></td>						
						<td class="w1 c">已查看</td>						
					</tr> -->
				</c:forEach>
				
			</table>
		</div>
	</div>
		</div>
	</div>
	<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
</html>
