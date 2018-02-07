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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
</head>
<script type="text/javascript">
	function delete2(houseId2){
		  location.href = "house-modify.jsp";
	}
	function updateList(houseId){
		  location.href = "house-modify.jsp";
	}

	function func1(){
		
			var pagego=document.getElementById("zhiding");
			window.location.href="${pageContext.request.contextPath}/manage/userHouse?type2=userhouselist&pageCurrent="+pagego.value;
	}
	function tijiao(obj){
		var id=obj.name;
		alert("status1:"+id);
		window.location.href="${pageContext.request.contextPath}/manage/userHouse?type2=status1&houseId="+id;		
	}
	function updateHouse(obj){
		var id=obj.name;
		alert("修改:"+id);
		window.location.href="${pageContext.request.contextPath}/manage/house-modify.jsp?type2=xiugaiHouse&houseId1="+id;		
	}
	function deleteHouse(obj){
		var id=obj.name;
		alert("status1:"+id);
		window.location.href="${pageContext.request.contextPath}/manage/userHouse?type2=shanchuHouse&houseId="+id;		
	}
	function chexiao(obj){
		var id=obj.name;
		alert("status1:"+id);
		window.location.href="${pageContext.request.contextPath}/manage/userHouse?type2=chexiaoHouse&houseId="+id;		
	}
	
</script>
<body>
<%@ include file="cmsTop.jsp" %>

	<div class="main">
		<h2>房屋管理</h2>
		<marquee scrollamount="10" bgcolor="aquamarine">${sessionScope.Msg }</marquee>
		<div class="manage">
			<table class="list">
				<tbody>
				 
					<c:forEach items="${sessionScope.userhouseList }" var="userHouse">
					
						<tr>
							<td class="house-thumb"><span><a href="${pageContext.request.contextPath}/details.jsp?houseid=${userHouse.hid}"
									target="_blank"><img src="${pageContext.request.contextPath}/${userHouse.hpicture1}" width="100"
										height="75" alt=""/>
								</a> </span>
							</td>
							<td width="320">
								<dl>
									<dt>
										<a href="${pageContext.request.contextPath}/details.jsp?houseid=${userHouse.hid}" target="_blank">${sessionScope.user.uname }的小洋房${userHouse.hid}</a>
									</dt>
									<dd>
										地址：一路,&nbsp;&nbsp;${userHouse.area }平方米<br>联系电话：${sessionScope.user.mobile }
									</dd>
								</dl>
							</td>
							<td>
							
							
							
							<c:choose>
						<c:when test="${userHouse.hstatus==0}">
						 
						 <center>
						 <font size="2" >草稿状态</font>
						 </center>
						  
						</c:when>
						<c:when test="${userHouse.hstatus==1}">
						 
						 <center>
						 <font size="2" >正在审核</font>
						 </center>
						  
						</c:when>
						<c:when test="${userHouse.hstatus==2}">
						 
						 <center>
						 <font size="2" >审核通过</font>
						 </center>
						  
						</c:when>
						<c:when test="${userHouse.hstatus==3}">
						 
						 <center>
						 <font size="2" >审核未通过</font>
						 </center>
						  
						</c:when>
						<c:when test="${userHouse.hstatus==-1}">
						 
						 <center>
						 <font size="2" >撤销发布</font>
						 </center>
						  
						</c:when>
						<c:otherwise>
						 
						 <center>
						 <font size="2" >已预订</font>
						 </center>
						  
						</c:otherwise>
						</c:choose>
						</dd>
						</td>
						<td class="house-type">
					  <c:if test="${userHouse.hstatus==0||userHouse.hstatus==3||userHouse.hstatus==-1}">
					       <!-- <label class="ui-gray">
					       <input value="撤销发布" type="button" name="delete"/>
							</label> -->
							
						   	<label class="ui-green"><input value="提交申请" type="button"
								name="${userHouse.hid }" onclick="tijiao(this)" />
					     	</label>
						    <label class="ui-green">
						    <input onclick="updateHouse(this)" value="修    改" type="button" name="${userHouse.hid }" />
						    </label>
						    <label class="ui-green">
						    	<input value="删    除" type="button" name="${userHouse.hid }" onclick="deleteHouse(this)"/>
						    </label>
					  </c:if>
					  <c:if test="${userHouse.hstatus==2}">
						<label class="ui-green">
						<input value="撤销发布" type="button" name="${userHouse.hid }" onclick="chexiao(this)"  />
						</label>
						<!-- <label class="ui-gray"><input value="提交申请" type="button" name="delete"/>
					    </label>
					    <label class="ui-gray"><input value="修 改" type="button" name="update" />
						</label>
						<label class="ui-gray"><input value="删除" type="button" name="delete"  />
						</label> -->
					  </c:if>
						</td>
					</tr>
					</c:forEach>
							
							
							
							
							
							
						
							
							
							
							
							
							







						
					
					 
				</tbody>
			</table>
		</div>
	</div>
	<div class="clear"></div>
	<div class="pager">
	<p:fengye totalRow="${sessionScope.totalRow }" totalPage="${sessionScope.totalPage }" currentPage="${sessionScope.pageCurrent}" redirectUrl="${pageContext.request.contextPath}/manage/userHouse?type2=userhouselist" param="pageCurrent" />
		
		<!-- <ul class="clearfix">			
				<li>首页</li>
				<li>上一页</li>			
				<li><a href="#">下一页</a>
				</li>
				<li><a href="#">尾页</a>
				</li>		
			<li>当前第1/2页</li>
		</ul> -->
		
	</div>
	</div>
	<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
</html>
