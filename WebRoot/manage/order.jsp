<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript">
	(function(){
		//alert("");
		document.getElementById("");
		var totalDay=document.getElementById("totalday");
		var start=document.getElementById("checkin");
		//alert("111"+totalDay+","+start);
		var day=parseInt(totalDay)*24*60*60*1000;
		//alert("---");
		var date1=(new Date(start.replace("-","/"))).valueOf();
		var date2=new Date(day+date1);
		//alert("***");
		document.getElementById("span11").textContent=date2;
	})();	
	/* function accept1(orderId){
		  location.href = "manage-result.htm";
	} */
	
	function back(obj){
		var id=obj.name;
		
		alert("status1:"+id);
		window.location.href="${pageContext.request.contextPath}/orderUser?type4=quxiao&orderUserId="+id;		
	}
	function dealOrder(obj){
		var id=obj.name;
		alert("status2:"+id);
		window.location.href="${pageContext.request.contextPath}/orderUser?type4=chuli&orderUserId="+id;		
	}
</script>
<body>
	



<%@ include file="cmsTop.jsp" %>
	
	
	<div class="main">
		<h2>订单管理</h2>
		<div class="manage">
			<div class="search"></div>
			<div class="spacer"></div>
			<table class="list">
				<c:forEach items="${sessionScope.listOrder }" var="order">
					<c:forEach items="${sessionScope.litsDetail }" var="detail">
					<c:if test="${order.oid==detail.odoid }">
						<c:forEach items="${sessionScope.findByListHouse }" var="house">
							<c:if test="${detail.odhid==house.hid }">
							<c:forEach items="${sessionScope.findUser }" var="user" varStatus="flag">
								<c:if test="${user.uid==house.huid }">
								<tr>
									<th colspan="2">订单号:${flag.count }</th>
									<th>预定时间：${order.ocreateTime }</th>
									<th>用户:${order.ouname }</th>
									<th></th>
									<th></th>
									<th colspan="2">状态: <select name="status" disabled="disabled">
											<option value="0"
												selected="selected">待受理</option>
											<option value="1">已受理</option>
			
											<option value="2">已取消</option>
			
									</select></th>
								</tr>
								<tr>
									<td class="first w4 c"><img width="100" height="100" src="${pageContext.request.contextPath }/${house.hpicture1}" />${user.uname }的大洋房</td>						
										<td>房屋类型：										
											<c:if test="${house.htype==1 }"> 酒店</c:if>
											<c:if test="${house.htype==2 }"> 别墅 </c:if> 
											<c:if test="${house.htype==3 }"> 普通单元</c:if> 
											<c:if test="${house.htype==4 }"> 大洋房 </c:if>
											<c:if test="${house.htype==5 }"> 小洋房 </c:if> 
											<c:if test="${house.htype==6 }">旅馆 </c:if>										
										</td>
									
									
									
									<td>房东：${user.uname }<br />联系方式：${user.mobile }</td>
									<td>入住时间：${order.ocreateTime } <br />退房时间：<span id="span11" >${detail.odday }天之后</span><input type="hidden" id="checkin" value="${order.ocreateTime }"/><input type="hidden" id="totalday" value="${detail.odday }"/> </td>
									<td>
										<c:if test="${order.opayType ==1 }">线上支付</c:if>
										<c:if test="${order.opayType ==1 }">到店支付</c:if>
										 
									</td>
									<td class="w1 c">总金额：${order.ocost }</td>
									<td><label class=ui-green>
										<c:if test="${sessionScope.orderStatus==1 }"><input value="受理" type="button" name="${order.oid }.${sessionScope.user.uid }" onclick="dealOrder(this)"/></c:if>
									</label></td>
									<td><label class=ui-green>
										<c:if test="${sessionScope.orderStatus==0 }">
										<input value="取消" type="button" 	name="${order.oid }.${sessionScope.user.uid }" onclick="back(this)"/></c:if>
										
										</label>
									</td>
								</tr>
								</c:if>
							</c:forEach>
							</c:if>
						</c:forEach>
						</c:if>
					</c:forEach>
				</c:forEach>
					
				
			</table>
			<div class="pager">
				<p:fengye totalRow="${sessionScope.totalRow }" totalPage="${sessionScope.totalPage }" currentPage="${sessionScope.pageCurrent}" redirectUrl="${pageContext.request.contextPath}/orderUser?type4=waitOrder&userId=${sessionScope.user.uid }" param="pageCurrent" />
				<!-- <ul class="clearfix">					
					<li>首页</li>
					<li>上一页</li>					
					<li>下一页</li>
					<li><a>尾页</a></li>					
					<li>当前第1/1页</li>
				</ul> -->
				
			</div>
		</div>
	</div>
	<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
</html>
