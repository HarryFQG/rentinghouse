<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="/mytags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 短租网</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/function.js"></script>
</head>
<script type="text/javascript">
	function delete1(houseId1){
		  location.href = "adminManage-result.jsp";
	}	
	function func1(){
		
			var pagego=document.getElementById("zhiding");
			window.location.href="${pageContext.request.contextPath}/houseR?type1=listHouseAdmin&pageCurrent="+pagego.value;
		}
</script>
</script>
<script type="text/javascript">
	function fabu(houseId2){
		  location.href = "adminManage-result.jsp";
	}
</script>
<body>





<%@include file="adminTop.jsp" %>
	<div class="main">
		<h2>房屋管理</h2>
		<div class="manage">
			<TABLE class="list">
  <TBODY>
  
  
 		<c:forEach items="${sessionScope.houseList1 }" var="house">
			<c:forEach items="${sessionScope.listFangDong }" var="fangDong">
				<c:if test="${house.huid ==fangDong.uid }">
					  <TR>
					    <TD class=house-thumb><SPAN><A href="${pageContext.request.contextPath }/details.jsp" target="_blank"><img src="${pageContext.request.contextPath }/${house.hpicture1}" width="100" height="75" alt=""></A></SPAN></TD>
					    <TD>
					      <DL>
					        <DT><A href="${pageContext.request.contextPath }/details.jsp" target="_blank">${fangDong.uname }的单元房</A></DT>
					        <DD>地址：二路,&nbsp;&nbsp;${house.area }平方米<BR>联系电话：${fangDong.mobile }</DD></DL></TD>
					    <c:if test="${empty sessionScope.readliHouseAdmin }">  
					     <TD class="house-price"><LABEL class="ui-green"><a href="${pageContext.request.contextPath }/houseR?type1=deleteHouseAdmin&id=${house.hid}">不通过审核</a></LABEL></TD>
						 <TD class="house-price"><LABEL class="ui-green"><a href="${pageContext.request.contextPath }/houseR?type1=fabuHouseAdmin&id=${house.hid}">通过审核</a></LABEL></TD>
						</c:if>
							    
					   </TR> 
   				</c:if>
			</c:forEach>
		</c:forEach>
   
  </TBODY></TABLE>
		</div>
	</div>
	<div class="clear"></div>
     <div class="pager">
     	<c:choose>
     		<c:when test="${empty sessionScope.readliHouseAdmin  }">     			
     			<p:fengye totalRow="${sessionScope.totalRow1 }" totalPage="${sessionScope.totalPage1 }" currentPage="${sessionScope.pageCurrent1}" redirectUrl="${pageContext.request.contextPath}/houseR?type1=listHouseAdmin" param="pageCurrent1" />
			</c:when>
			<c:otherwise>
				<p:fengye totalRow="${sessionScope.totalRow }" totalPage="${sessionScope.totalPage }" currentPage="${sessionScope.pageCurrent}" redirectUrl="${pageContext.request.contextPath}/houseR?type1=readlyHouseAdmin" param="pageCurrent" />
			</c:otherwise>
		</c:choose>
				<!-- <ul class="clearfix">			
					<li>首页</li>
					<li>上一页</li>		     
                    <li><a href="#">下一页</a></li>
					<li><a href="#">尾页</a></li>			
					<li>当前第1/2页</li>
				</ul> -->
				
			</div>
</div>
<div id="footer"> 
	Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号 
</div>
</body>



</html>
