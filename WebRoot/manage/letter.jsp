<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短租网</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../scripts/function.js"></script>
<script type="text/javascript" src="../scripts/ajax.js"></script>
</head>
<script type="text/javascript">
	//添加定时器，不断属性聊天记录	
	self.setInterval("funcAjax1()", 1000*10);//每隔一秒钟调用函数一次	
	function funcAjax1(){
	 	document.getElementById("receiveContent").scrollTop = document.getElementById("receiveContent").scrollHeight;
		document.getElementById("receiveContent").value="";
		//alert("11");
		var userId=document.getElementById("userId").value;
		var fangDongId=document.getElementById("fangDongId").value;
		//var userName=document.getElementById("userName").value;
		var type5="addLetter";
		var content="";
		//alert("22:"+userId+","+fangDongId);
		data="userId="+userId+"&fangDongId="+fangDongId+"&type5=addLetter"+"&sendContent="+content;//t+"&userName="+userName;
		window.getElement.Post("../letterList",data,display,"JSON2");		
	}
	
	
	
	
	function funcAjax(){
		document.getElementById("receiveContent").value="";
		//alert("11");
		var userId=document.getElementById("userId").value;
		var fangDongId=document.getElementById("fangDongId").value;
		//var userName=document.getElementById("userName").value;
		var type5="addLetter";
		var content=document.getElementById("sendContent").value;
		document.getElementById("sendContent").value="";
		//alert("22:"+userId+","+fangDongId);
		data="userId="+userId+"&fangDongId="+fangDongId+"&type5=addLetter"+"&sendContent="+content;//t+"&userName="+userName;
		window.getElement.Post("../letterList",data,display,"JSON2");		
	}
	function display(obj){
		for(var o in obj){
			
			var date=new Date(obj[o].ccreateTime.time);
			//alert(""+date.getFullYear()+(new Date(obj[o].ccreateTime.time)).getHours()+",---------,"+(new Date(obj[o].ccreateTime.time)).getFullYear());
			document.getElementById("receiveContent").value += obj[o].ccname+"  "+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getHours()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+""+"\r\n"+obj[o].ccontent+"\r\n\r\n";
		}
		
	}
		
	
</script>
<body>
<%@include file="cmsTop.jsp" %>

	<div class="main">
		<div class="guestbook">
			<h2>与李四的消息记录</h2>
			<ul>
			</ul>
			<div id="content" >
				<form id="content" >
					<table width="512px" >
						<tr  >
							<td ><textarea name="receiveContent" readonly="readonly" id="receiveContent" cols="80" rows="12" >									
									<c:forEach items="${sessionScope.listComment }" var="comment">
										${comment.ccname }&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${comment.ccreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>						
										${comment.ccontent }
									</c:forEach>									
								</textarea>
							<!--使滚动条默认在底部  -->
							<script>
								window.onload=function()
								{
								 document.getElementById("receiveContent").scrollTop = document.getElementById("receiveContent").scrollHeight;
								}; 
							</script>
							</td>
							
							
								
							
							
						</tr>
					</table>
				</form>
			</div>
			<div id="reply-box">
				<form id="guestBook" action="${pageContext.request.contextPath }/letterList" method="post">
					<table>
						<tr>
							<td class="field">收信方昵称：</td>
							<td>
								<%-- <input class="text" type="text" name="guestName" id="userName" value="${sessionScope.user.uname }" readonly="readonly" />
								 --%>
								 <c:choose>
									<c:when test="${!empty param.fangDongId }">
										<input class="text" type="text" name="fangDongId" id="fangDongId"   value="${param.fangDongId }" readonly="readonly" />
									</c:when>
									<c:when test="${(!empty sessionScope.fangDongId) && (sessionScope.fangDongId==sessionScope.user.uid) }">
										<input class="text" type="text" name="fangDongId" id="fangDongId"   value="${sessionScope.letteruserId }"  readonly="readonly"/>
									</c:when>
									<c:when test="${(!empty sessionScope.letteruserId) && (sessionScope.letteruserId==sessionScope.user.uid) }">
										<input class="text" type="text" name="fangDongId" id="fangDongId"   value="${sessionScope.fangDongId }"  readonly="readonly"/>
									</c:when>
								</c:choose>
								<input class="text" type="hidden" name="userId" id="userId" value="${sessionScope.user.uid }"/>
								
								<c:choose>
									<c:when test="${!empty param.fangDongId }">
										<input class="text" type="hidden" name="fangDongId" id="fangDongId"   value="${param.fangDongId }" />
									</c:when>
									<c:when test="${!empty sessionScope.fangDongId }">
										<input class="text" type="hidden" name="fangDongId" id="fangDongId"   value="${sessionScope.fangDongId }" />
									</c:when>
								</c:choose>
															
								<input class="text" type="hidden" name="type5"   value="addLetter" />
								
							</td>
						</tr>
						<tr >
							<td class="field">回复内容：</td>
							<td ><textarea name="sendContent" id="sendContent"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="button"	name="submit" onclick="funcAjax();" value="提交留言" />
							</label>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
</html>