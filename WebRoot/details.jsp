<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>





<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style1.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>

	<DIV id=header class=wrap>
		<DIV id=logo>
			<IMG src="images/logo.gif">
		</DIV>
	</DIV>
	<DIV id=navbar class=wrap></DIV>
	<DIV id=position class=wrap>当前位置：短租网 - 浏览房源
		
	</DIV>
		<c:if test="${!empty sessionScope.userhouseList }">
		<c:forEach items="${sessionScope.userhouseList }" var="house">
			<c:if test="${param.houseid == house.hid }">
				<c:set scope="session" value="${house }" var="houseDetail"></c:set>
				<c:set scope="session" value="${sessionScope.user }" var="fangDong"></c:set>
				
			</c:if>
			
		</c:forEach>
		
	</c:if>
	
		

				<DIV id=view class="main wrap">
					<DIV class=intro>
						<DIV class=lefter>
							<H1>${sessionScope.fangDong.uname }的别墅</H1>
							<DIV class=houseinfo>

								<P>
									户 型：<SPAN>
											<c:choose>
												<c:when test="${sessionScope.houseDetail.htype==1 }">酒店</c:when>
												<c:when test="${sessionScope.houseDetail.htype==2 }">别墅</c:when>
												<c:when test="${sessionScope.houseDetail.htype==3 }">普通单元</c:when>
												<c:when test="${sessionScope.houseDetail.htype==4 }">大洋房</c:when>
												<c:when test="${sessionScope.houseDetail.htype==5 }">小洋房</c:when>
												<c:otherwise>旅馆</c:otherwise>
											</c:choose>										
										</SPAN>
								</P>




								<P>
									面 积：<SPAN>${sessionScope.houseDetail.area }m<SUP>2</SUP></SPAN>
								</P>
								<P>
									位 置：<SPAN>${sessionScope.houseDetail.haddress }</SPAN>
								</P>
								<P>
									日 租 价：<SPAN>${sessionScope.houseDetail.hrentPrice }</SPAN>
								</P>
								<P>
									联系方式：<SPAN>${sessionScope.fangDong.mobile }</SPAN>
								</P>
							</DIV>

							<form id="orderUp" action="orderCreated.jsp?houseId=${sessionScope.houseDetail.hid }" method="post">
								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label class="ui-green"><input
									type="submit" name="submit" value="立刻预定" /></label>
							</form>
							<br />
						</DIV>

						<DIV class=clear></DIV>
						<DIV class=introduction>
							<H2>
								<SPAN><STRONG>房东信息</STRONG></SPAN>
							</H2>
							<DIV class=content>
								<P>房东姓名：${sessionScope.fangDong.uname }</P>
								<P>手机号：${sessionScope.fangDong.mobile }</P>
								<P>Email：${sessionScope.fangDong.email }</P>
							</DIV>
						</DIV>
						<DIV class=clear></DIV>
						<DIV class=introduction>
							<H2>
								<SPAN><STRONG>房屋详细信息</STRONG></SPAN>
							</H2>
							<DIV class=content>
								<IMG src="${pageContext.request.contextPath}/${sessionScope.houseDetail.hpicture1}" width="200" height="200"> <IMG
									src="${pageContext.request.contextPath}/${sessionScope.houseDetail.hpicture2}" width="200" height="200"> <IMG
									src="${pageContext.request.contextPath}/${sessionScope.houseDetail.hpicture3}" width="200" height="200">
								<P>房屋名称：${sessionScope.houseDetail.huname}</P>

								<P>房屋类型：
									<span>
										<c:choose>
												<c:when test="${sessionScope.houseDetail.htype==1 }">酒店</c:when>
												<c:when test="${sessionScope.houseDetail.htype==2 }">别墅</c:when>
												<c:when test="${sessionScope.houseDetail.htype==3 }">普通单元</c:when>
												<c:when test="${sessionScope.houseDetail.htype==4 }">大洋房</c:when>
												<c:when test="${sessionScope.houseDetail.htype==5 }">小洋房</c:when>
												<c:otherwise>旅馆</c:otherwise>
										</c:choose>
									</span>
								</P>




								<P>面积：${sessionScope.houseDetail.area }</P>
								<P>地址：${sessionScope.houseDetail.haddress }</P>
								<P>日租价：${sessionScope.houseDetail.hrentPrice }</P>
								<P>出租类型：<span>
									<c:choose>
												<c:when test="${sessionScope.houseDetail.hleaseTpe==1 }">整租 </c:when>
												<c:when test="${sessionScope.houseDetail.hleaseTpe==0 }">单间 </c:when>												
												<c:otherwise>床位</c:otherwise>
									</c:choose>
								</span></P>


								<P>可住人数：${sessionScope.houseDetail.hlive }</P>
								<P>床位数：${sessionScope.houseDetail.hbed }</P>
								<P>卧室数：${sessionScope.houseDetail.hbedRoom }</P>

								<P>床型：<span>
									<c:choose>												
										<c:when test="${sessionScope.houseDetail.htype==0 }">单人床</c:when>												
										<c:otherwise>双人床</c:otherwise>
									</c:choose>
								</span></P>
								
								<P>卫生间数：${sessionScope.houseDetail.htoilet }</P>
								<P>房屋描述：${sessionScope.houseDetail.hdescribe }</P>
							</DIV>
						</DIV>
						<DIV class=clear></DIV>
						<DIV class=introduction>
							<H2>
								<SPAN><STRONG>住房规则</STRONG></SPAN>
							</H2>
							<DIV class=content>
								<P>入住时间：<fmt:formatDate value="${sessionScope.houseDetail.hcheckIn }" pattern="HH:mm:ss"/></P>
								<P>退房时间：<fmt:formatDate value="${sessionScope.houseDetail.hcheckOut }" pattern="HH:mm:ss"/></P>
								<P>最少天数：${sessionScope.houseDetail.hminDay }</P>
								<P>最多天数：${sessionScope.houseDetail.hmaxDay }</P>
								<P>全额退款日：${sessionScope.houseDetail.hrefundDay }</P>
								<P>使用规则：${sessionScope.houseDetail.huseRule }</P>
								<P>设施服务：${sessionScope.houseDetail.hservice }</P>
								<P>付款规则：<span>
									<c:choose>												
										<c:when test="${sessionScope.houseDetail.hpayRule==0 }">严格</c:when>												
										<c:otherwise>不严格</c:otherwise>
									</c:choose>
								</span></P>
								<P>有无发票：<span>
									<c:choose>												
										<c:when test="${sessionScope.houseDetail.hinvoice==1 }">提供</c:when>												
										<c:otherwise>不提供</c:otherwise>
									</c:choose>
								</span></P>
							</DIV>
		
	
	<DIV id=footer class=wrap>
				<DL>
					<DT>短租网 © 2016 中南财大 鄂ICP证*******号</DT>
					<DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
				</DL>
			</DIV>
</BODY>
</HTML>
