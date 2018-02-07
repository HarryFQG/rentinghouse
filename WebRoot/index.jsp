<%@page import="com.it.entity.House"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="p" uri="/mytags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>短租网 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style1.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">

</HEAD>


<script type="text/javascript">
	window.document.interval();
	

</script>
<BODY>
<%
	
 %>
	<%@ include file="top.jsp" %>
	<DIV id=navbar class=wrap>
		<DL class="search clearfix">
			<FORM id=sform method=post action="${pageContext.request.contextPath}/houseR">
				<DT>
					<UL>
						<LI class=bold>房屋信息<input type="hidden" name="type1" value="listHouse"/> </LI>
						<LI>房屋名：<INPUT class="text" type="text" name="houseName" value="${sessionScope.lookRm.queryStr }">
							<LABEL class="ui-blue">
							<INPUT value="搜索房屋"  type="submit"	name="search"/> </LABEL></LI>
					</UL>
				</DT>
				<DD>
					<UL>
						<LI class=first>价格</LI>
						<LI><SELECT name="price">
								<OPTION value="0-10000" <c:if test="${sessionScope.lookRm.price == '0-10000.0' }">selected="selected" </c:if>  >不限</OPTION>
								<OPTION value="0-300" <c:if test="${sessionScope.lookRm.price == '0-300' }">selected="selected" </c:if> >300元以下</OPTION>
								<OPTION value="300-600" <c:if test="${sessionScope.lookRm.price == '300-600' }">selected="selected" </c:if> >300元—600元</OPTION>
								<OPTION value="600-10000" <c:if test="${sessionScope.lookRm.price == '600-10000' }">selected="selected" </c:if> >600元以上</OPTION>
						</SELECT></LI>
					</UL>
				</DD>
				<DD>
					<UL>
						<LI class=first>房屋位置</LI>
						<LI><SELECT id="street" name="street">
								<OPTION value="" <c:if test="${sessionScope.lookRm.street == '' }">selected="selected" </c:if> >不限</OPTION>
								<OPTION value="一路" <c:if test="${sessionScope.lookRm.street == '一路' }">selected="selected" </c:if> >一路</OPTION>
								<OPTION value="二路" <c:if test="${sessionScope.lookRm.street == '二路' }">selected="selected" </c:if> >二路</OPTION>
								<OPTION value="三路" <c:if test="${sessionScope.lookRm.street == '三路' }">selected="selected" </c:if> >三路</OPTION>
								<OPTION value="四路" <c:if test="${sessionScope.lookRm.street == '四路' }">selected="selected" </c:if> >四路</OPTION>
						</SELECT></LI>
					</UL>
				</DD>
				<DD>
					<UL>
						<LI class=first>房型</LI>
						<LI><SELECT name="type_id">
								<OPTION value=0 <c:if test="${sessionScope.lookRm.houseType==0 }"> selected="selected"</c:if> >不限</OPTION>
								<OPTION value=1 <c:if test="${sessionScope.lookRm.houseType==1 }"> selected="selected"</c:if> >酒店</OPTION>
								<OPTION value=2 <c:if test="${sessionScope.lookRm.houseType==2 }"> selected="selected"</c:if> >别墅</OPTION>
								<OPTION value=3 <c:if test="${sessionScope.lookRm.houseType==3 }"> selected="selected"</c:if> >普通单元</OPTION>
								<OPTION value=4 <c:if test="${sessionScope.lookRm.houseType==4 }"> selected="selected"</c:if> >大洋房</OPTION>
								<OPTION value=5 <c:if test="${sessionScope.lookRm.houseType==5 }"> selected="selected"</c:if> >小洋房</OPTION>
								<OPTION value=6 <c:if test="${sessionScope.lookRm.houseType==6 }"> selected="selected"</c:if> >旅馆</OPTION>
						</SELECT></LI>
					</UL>
				</DD>
				<DD>
					<UL>
						<LI class=first>面积</LI>
						<LI><SELECT name="area">
								<OPTION value="0-10000" <c:if test="${sessionScope.lookRm.area=='0-10000' }"> selected="selected"</c:if> >不限</OPTION>
								<OPTION value="0-90" <c:if test="${sessionScope.lookRm.area=='0.0-90' }"> selected="selected"</c:if> >90以下</OPTION>
								<OPTION value="90-180" <c:if test="${sessionScope.lookRm.area=='90-180' }"> selected="selected"</c:if> >90-180</OPTION>
								<OPTION value="180-10000" <c:if test="${sessionScope.lookRm.area=='180-10000' }"> selected="selected"</c:if>  >180以上</OPTION>
						</SELECT></LI>
					</UL>
				</DD>
			</FORM>
		</DL>
	</DIV>
	<DIV class="main wrap">
		<TABLE class=house-list>
			<TBODY>
				
				<c:forEach items="${sessionScope.houseList }" var="house">
					<c:forEach items="${sessionScope.listFangDong }" var="fangDong">
					
						<c:if test="${house.huid ==fangDong.uid }">
							<TR class=odd>
								<TD class="house-thumb"><span><a href="${pageContext.request.contextPath}/houseR?type1=detailHouse&houseid=${house.hid }" target="_blank">
											<img src="${pageContext.request.contextPath}/${house.hpicture1}" width="100" height="75" alt="">
										 </a> </span>
								</TD>
								<TD>
									<DL>
										<DT>
											<A href="${pageContext.request.contextPath}/houseR?type1=detailHouse&houseid=${house.hid }""
												target="_blank">${house.huname }</A>
										</DT>
										<DD>
											地址：${house.haddress }&nbsp;&nbsp;面积：${house.area }平方米<BR>电话:${fangDong.mobile }
										</DD>
										<DD>
											房东：${fangDong.uname }&nbsp;
											
										</DD>
									</DL>
								</TD>
								<TD class="house-type">
									
									<span>
										<c:choose>
												<c:when test="${house.htype==1 }">酒店</c:when>
												<c:when test="${house.htype==2 }">别墅</c:when>
												<c:when test="${house.htype==3 }">普通单元</c:when>
												<c:when test="${house.htype==4 }">大洋房</c:when>
												<c:when test="${house.htype==5 }">小洋房</c:when>
												<c:otherwise>旅馆</c:otherwise>
										</c:choose>
									</span>
									
									
								</TD>
								<TD class="house-price"><SPAN>${house.hrentPrice }</SPAN>元/天<br>
								</TD>
							</TR>
							</c:if>
						</c:forEach>
					</c:forEach>
			</TBODY>
		</TABLE>
		<div class="clear"></div>
		<div class="pager">
		
			<p:fengye totalRow="${sessionScope.totalRow }" totalPage="${sessionScope.totalPage }" currentPage="${sessionScope.pageCurrent}" redirectUrl="${pageContext.request.contextPath}/houseR?type=listHouse" param="pageCurrent" />
			<!-- <ul class="clearfix">				
					<li>首页</li>
					<li>上一页</li>				
					<li><a href="#">下一页</a>
					</li>
					<li><a href="#">尾页</a>
					</li>				
					<li>第1/2页</li>
			</ul> -->
			
		</div>
	</DIV>

	<DIV id=footer class=wrap>
		<DL>
			<DT>短租网 © 2016 中南财大鄂ICP证*******号</DT>
			<DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
		</DL>
	</DIV>
</BODY>
</HTML>
