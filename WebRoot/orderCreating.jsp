<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style1.css">



 <script type="text/javascript"
	src="scripts/My97DatePicker/WdatePicker.js"></script> 
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>


<script type="text/javascript" src="scripts/function.js"></script>


<style type="text/css">
<!--
#Layer1 {
	position: absolute;
	width: 200px;
	height: 115px;
	z-index: 1;
	left: 207px;
	top: 150px;
}

#Layer2 {
	position: absolute;
	width: 200px;
	height: 115px;
	z-index: 2;
	left: 860px;
	top: 151px;
}

#Layer3 {
	position: absolute;
	width: 378px;
	height: 115px;
	z-index: 3;
	left: 861px;
	top: 320px;
}

#Layer4 {
	position: absolute;
	width: 200px;
	height: 115px;
	z-index: 4;
	left: 213px;
	top: 787px;
}
-->
</style>
</HEAD>


	<style type="text/css">
/* reset */
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

:focus {
	outline: 0;
}

img {
	vertical-align: middle;
}

a,img {
	border: 0;
}

a,a:visited {
	color: #6a6a6a;
	text-decoration: none;
}

a:hover {
	color: #7bbb05;
	text-decoration: none;
}

.f-l {
	float: left;
}

.f-r {
	float: right;
}

body {
	font: 12px/180% Helvetica, Arial, sans-serif, "宋体";
	color: #636363;
}
/* focus */
.focus {
	width: 622px;
	overflow: hidden;
	position: relative;
	margin: 0 auto;
}

.focus .focuspic {
	height: 340px;
	width: 999em;
	position: relative;
	float: left;
}

.focus .focuspic .indexbanner {
	float: left;
	width: 620px;
	position: relative;
	height: 340px;
	overflow: hidden;
	border: solid 1px #3366cc;
}
/* scrollbox */
.scrollbox {
	height: 190px;
	padding: 20px 0;
	float: left;
}

.scrollbox .scrollbtn {
	width: 12px;
	height: 37px;
	margin: 38px 0 0 0;
	overflow: hidden;
	background: url(images/arrow.gif) no-repeat;
	cursor: pointer;
}

.scrollbox #prev {
	background-position: 0 0;
}

.scrollbox #prev.disabled {
	background-position: 0 -37px;
	cursor: default;
}

.scrollbox #next {
	background-position: -12px 0;
}

.scrollbox #next.disabled {
	background-position: -12px -37px;
	cursor: default;
}

.scrollbox .scrollpic {
	float: left;
	margin: 0 16px 0 16px;
	display: inline;
	width: 562px;
	height: 190px;
	overflow: hidden;
}

.scrollbox .scrollpic ul {
	width: 999em;
	float: left;
	display: inline;
}

.scrollbox .scrollpic li {
	float: left;
	width: 188px;
	text-align: center;
}

.scrollbox .scrollpic li a img {
	border: solid 1px #ddd;
	padding: 5px;
}

.scrollbox .scrollpic li a span {
	display: block;
	height: 30px;
	line-height: 30px;
	cursor: pointer;
}

.scrollbox .scrollpic li a:hover span,.scrollbox .scrollpic li.current a span
	{
	color: #3366cc;
	font-weight: 800;
}

.scrollbox .scrollpic li.current a img {
	border: solid 1px #3366cc;
}
</style>
	<script type="text/javascript" src="scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
		alert("1111");
		function checkInDate1(){			
			checkinDates();
		}
		function checkinDates(){			
			var checkInstr=document.getElementById("birthday");			
			if(checkInstr.value.length<2){
				document.getElementById("span11").style.color='red';
				document.getElementById("span11").textContent="×：你还未选择入住时间";
				checkoutDate1();
				return false;
			}else{
				document.getElementById("span11").style.color='#7FFF00';
				document.getElementById("span11").textContent="√";
				checkoutDate1();
				return true;		
			}
			//var checkInDate=new Date(checkInstr.value.replace("-","/"));			
		}
		function checkOutDate1(){			
			checkoutDate1();
		}
		function checkoutDate1(){			
			var checkoutstr=document.getElementById("birthday1");
			var checkInstr=document.getElementById("birthday");
			var checkInDate=(new Date(checkInstr.value.replace("-","/"))).valueOf();
			var checkOutDate=(new Date(checkoutstr.value.replace("-","/"))).valueOf();
			alert("差："+(checkOutDate-checkInDate));
			var cha=checkOutDate-checkInDate;			
			if(checkoutstr.value.length>2&&cha>0){
				var priceV=document.getElementById("price");
				var price=parseFloat(priceV.value);
				document.getElementById("totalDay").value=cha/1000/60/60/24*price;			
				document.getElementById("span22").style.color='#7FFF00';
				document.getElementById("span22").textContent="√";
				return true;
			}else{
				document.getElementById("span22").style.color='red';
				document.getElementById("span22").textContent="×：你还未选择正确时间";
				return false;		
			}
			//var checkInDate=new Date(checkInstr.value.replace("-","/"));			
		}
		function submit1(){
			var flag1=checkoutDate1();
			var flag2=checkinDates();
			if(flag1&&flag2){
				return true;
			}else{
				return false;
			}
			
		}
		
		
		
		
		
		$(function() {

			var animate_time = 200;//动画时间：0.3秒
			var interval_time = 3000;//播放间隔时间：5秒
			var picnumber = $(".scrollpic li").size() - 1;//计算广告的数量
			var slideclick = 0;//初始化

			//循环函数
			function autoplay() {
				slideclick++;
				if (slideclick <= picnumber) {
					$(".scrollpic li").eq(slideclick).trigger("dblclick");
				} else {
					$(".scrollpic li").eq(0).trigger("dblclick");
					slideclick = 0
				}
			}

			//设置循环时间，自动开始循环
			var auto_setInterval = setInterval(autoplay, interval_time);

			//悬停广告区域时播放停止
			$(".focus").hover(function() {
				clearInterval(auto_setInterval);
			}, function() {
				auto_setInterval = setInterval(autoplay, interval_time);
			});

			//小图点击时，开始动画
			$(".scrollpic li").dblclick(function() {

				slideclick = $(".scrollpic li").index($(this));

				$(".scrollpic li").removeClass("current");
				$(this).addClass("current");

				//大图上移
				$(".focus .focuspic").animate({
					"marginLeft" : slideclick * -622
				}, {
					duration : animate_time,
					queue : false
				});

				//控制缩略图显示个数
				if (slideclick < picnumber) {
					if (slideclick == 0 || slideclick == 1) {
						$(".scrollpic ul").animate({
							"marginLeft" : "0px"
						}, {
							duration : animate_time,
							queue : false
						});
					} else {
						$(".scrollpic ul").animate({
							"marginLeft" : (slideclick - 1) * -188
						}, {
							duration : animate_time,
							queue : false
						});//显示最后4张图片不在滚动
					}
				}

				//判断上下按钮是否可点击
				if (slideclick == 0) {
					$(".scrollbox #prev").addClass("disabled");
					$(".scrollbox #next").removeClass("disabled");
				} else if (slideclick == picnumber) {
					$(".scrollbox #prev").removeClass("disabled");
					$(".scrollbox #next").addClass("disabled");
				} else {
					$(".scrollbox #prev").removeClass("disabled");
					$(".scrollbox #next").removeClass("disabled");
				}

			});

			//鼠标在小图上悬停时，相关移动
			$(".scrollpic li").mouseover(function() {

				var slidebtn_hover_chi = $(".scrollpic li").index($(this));

				$(".scrollpic li").removeClass("current");
				$(this).addClass("current");

				$(".focuspic").animate({
					"marginLeft" : slidebtn_hover_chi * -622
				}, {
					duration : animate_time,
					queue : false
				});

				if (slidebtn_hover_chi != slideclick)
					;

				slideclick = slidebtn_hover_chi;

				//判断上下按钮是否可点击
				if (slideclick == 0) {
					$(".scrollbox #prev").addClass("disabled");
					$(".scrollbox #next").removeClass("disabled");
				} else if (slideclick == picnumber) {
					$(".scrollbox #prev").removeClass("disabled");
					$(".scrollbox #next").addClass("disabled");
				} else {
					$(".scrollbox #prev").removeClass("disabled");
					$(".scrollbox #next").removeClass("disabled");
				}

			});

			//点击向上按钮时
			$(".scrollbox #prev").click(function() {
				slideclick = slideclick - 1;
				if (slideclick < 0) {
					slideclick = 0;
				}
				$(".scrollpic li").eq(slideclick).trigger("dblclick");
			});

			//点击向下按钮时
			$(".scrollbox #next").click(function() {
				slideclick = slideclick + 1;
				if (slideclick <= picnumber) {
					slideclick = picnumber;
					$(".scrollpic li").eq(slideclick).trigger("dblclick");
					$(".scrollpic ul").animate({
						"marginLeft" : (slideclick - 2) * -188
					}, {
						duration : animate_time,
						queue : false
					});// 缩略图最后滚动的左侧距离位置 正好4张缩略图显示
				} else {
					$(".scrollpic li").eq(0).trigger("dblclick");
					slideclick = 0
				}
				$(".scrollpic li").eq(slideclick).trigger("dblclick");
			});

		}); 
		
		
		
	</script>

<BODY>


	<DIV id=header class=wrap>
		<DIV id=logo>
			<IMG src="images/logo.gif">
		</DIV>
	</DIV>
	<DIV id=navbar class=wrap></DIV>
	<DIV id=position class=wrap>当前位置：青鸟租房网 - 浏览房源</DIV>


	<c:forEach items="${sessionScope.houseList }" var="house">
		<c:if test="${param.houseId eq house.hid }">

			<c:forEach items="${sessionScope.listFangDong }" var="fangDong">

				<c:if test="${house.huid ==fangDong.uid }">
					<div id="Layer1">
						<div class="focus">
							<div class="focuspic">

								<div class="indexbanner">
									<a target="_blank" href="" title=""><img width="620"
										height="340" alt="" src="${pageContext.request.contextPath }/${house.hpicture1}"></a>
								</div>
								<div class="indexbanner">
									<a target="_blank" href="" title=""><img width="620"
										height="340" alt="" src="${pageContext.request.contextPath }/${house.hpicture2}"></a>
								</div>
								<div class="indexbanner">
									<a target="_blank" href="" title=""><img width="620"
										height="340" alt="" src="${pageContext.request.contextPath }/${house.hpicture3}"></a>
								</div>


							</div>
							<!--focuspic end-->

							<div class="scrollbox">
								<div id="prev" class="f-l scrollbtn disabled"></div>
								<div class="f-l scrollpic">
									<ul>
										<li class="current"><a target="_blank" href="" title=""><img
												width="150" height="150" alt="" src="${pageContext.request.contextPath }/${house.hpicture1}"><span></span></a></li>
										<li><a target="_blank" href="" title=""><img
												width="150" height="150" alt="" src="${pageContext.request.contextPath }/${house.hpicture2}"><span></span></a></li>
										<li><a target="_blank" href="" title=""><img
												width="150" height="150" alt="" src="${pageContext.request.contextPath }/${house.hpicture3}"><span></span></a></li>

									</ul>
								</div>
								<div id="next" class="f-r scrollbtn"></div>
							</div>
							<!--scrollbox end-->

						</div>
						<!--focus end-->
					</div>

					<DIV id=view class="main wrap">
						<DIV class=intro>
							<div id="Layer2">
								<H1>
									<font color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${fangDong.uname }的别墅</font>
								</H1>
								<br />
								<DIV class=houseinfo>

									<P>
										<font size="+1">户 型：</font><SPAN><font size="+1">									
										<c:choose>
												<c:when test="${house.htype==1 }">酒店</c:when>
												<c:when test="${house.htype==2 }">别墅</c:when>
												<c:when test="${house.htype==3 }">普通单元</c:when>
												<c:when test="${house.htype==4 }">大洋房</c:when>
												<c:when test="${house.htype==5 }">小洋房</c:when>
												<c:otherwise>旅馆</c:otherwise>
										</c:choose>											
										</font></SPAN>
									</P>




									<P>
										<font size="+1">面 积：</font><SPAN><font size="+1">${house.area }m<SUP>2</SUP></font></SPAN>
									</P>
									<P>
										<font size="+1">位 置：</font><SPAN><font size="+1">${house.haddress }</font></SPAN>
									</P>
									<P>
										<font size="+1">日 租 价：</font><SPAN><font size="+1">${house.hrentPrice }</font></SPAN>
									</P>
									<td><P>
											<font size="+1">联系方式：</font></td>
									<td><SPAN><font size="+1">${fangDong.mobile }</font></SPAN></td>
									</P>
								</DIV>
							</div>

							<div id="Layer3">
								<font color="red" size="+2"></font><br /> <br />

								<form id="orderUp" action="orderUser" method="post" onsubmit="return submit1();">
									<input type="hidden" name="type4" value="creatingOrder"/>
									<input type="hidden" name="houseId" value="13" /> 
									<input type="hidden" name="userId" value="2" />
									<tr>
										<td><font size="+2">入住时间:</font><br /></td>
										<td>
										<input value="" name="checkInDate" id="birthday" height="200" onblur="checkInDate1()"/><span id="span11"></span>
										</td>
										<br />
										<br />
									</tr>
									<tr>
										<td><font size="+2">退房时间:</font><br /></td>
										<td><input value="" name="checkOutDate" id="birthday1" height="200" onblur="checkOutDate1()" /><span id="span22"></span></td>
										<br />
										<br />
									</tr>
									<input type="hidden" name="price" id="price" value="${house.hrentPrice }"/>
									<input type="hidden" name="ocost" id="totalDay" value=""/>
									<input type="hidden" name="ouid" value="${sessionScope.user.uid}" />
									<input type="hidden" name="ouname" value="${sessionScope.user.uname}" />
									<input type="hidden" value="${house.hpayRule }" name="opayType">
									<%-- <input type="hidden" value="${house.hstatus }" name="ostatus" /> --%>

									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="ui-green"><input type="submit"
										name="submit" value="立刻预定" /></label>
								</form>
							</div>


							<div id="Layer4">

								<DIV class=clear></DIV>
								<DIV class=introduction>
									<H2>
										<SPAN><STRONG>房东信息</STRONG></SPAN>
									</H2>
									<DIV class=content>
										<P>房东姓名：${fangDong.uname }</P>
										<P>手机号：${fangDong.mobile }</P>
										<P>Email：${fangDong.email }</P>
									</DIV>
								</DIV>
								<DIV class=clear></DIV>
								<DIV class=introduction>
									<H2>
										<SPAN><STRONG>房屋详细信息</STRONG></SPAN>
									</H2>
									<DIV class=content>
										<IMG src="${pageContext.request.contextPath}/${house.hpicture1}" width="200" height="200">
										<IMG src="${pageContext.request.contextPath}/${house.hpicture1}" width="200" height="200">
										<IMG src="${pageContext.request.contextPath}/${house.hpicture1}" width="200" height="200">
											src="${pageContext.request.contextPath}/${house.hpicture3}" width="200" height="200">
										<P>房屋名称：${fangDong.uname }的别墅</P>
		
										<P>房屋类型：
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
										</P>
		
		
		
		
										<P>面积：${house.area }</P>
										<P>地址：${house.haddress }</P>
										<P>日租价：${house.hrentPrice }</P>
										<P>出租类型：<span>
											<c:choose>
														<c:when test="${house.hleaseTpe==1 }">整租 </c:when>
														<c:when test="${house.hleaseTpe==0 }">单间 </c:when>												
														<c:otherwise>床位</c:otherwise>
											</c:choose>
										</span></P>
		
		
										<P>可住人数：${house.hlive }</P>
										<P>床位数：${house.hbed }</P>
										<P>卧室数：${house.hbedRoom }</P>
		
										<P>床型：<span>
											<c:choose>												
												<c:when test="${house.htype==0 }">单人床</c:when>												
												<c:otherwise>双人床</c:otherwise>
											</c:choose>
										</span></P>
										
										<P>卫生间数：${house.htoilet }</P>
										<P>房屋描述：${house.hdescribe }</P>
									</DIV>
								</DIV>
								<DIV class=clear></DIV>
								<DIV class=introduction>
									<H2>
										<SPAN><STRONG>住房规则</STRONG></SPAN>
									</H2>
									<DIV class=content>
										<P>入住时间：<fmt:formatDate value="${house.hcheckIn }" pattern="HH:mm:ss"/></P>
										<P>退房时间：<fmt:formatDate value="${house.hcheckOut }" pattern="HH:mm:ss"/></P>
										<P>最少天数：${house.hminDay }</P>
										<P>最多天数：${house.hmaxDay }</P>
										<P>全额退款日：${house.hrefundDay }</P>
										<P>使用规则：${house.huseRule }</P>
										<P>设施服务：${house.hservice }</P>
										<P>付款规则：<span>
											<c:choose>												
												<c:when test="${house.hpayRule==0 }">严格</c:when>												
												<c:otherwise>不严格</c:otherwise>
											</c:choose>
										</span></P>
										<P>有无发票：<span>
											<c:choose>												
												<c:when test="${house.hinvoice==1 }">提供</c:when>												
												<c:otherwise>不提供</c:otherwise>
											</c:choose>
										</span></P>
									</DIV>
								</DIV>
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
	<DIV id=footer class=wrap>
		<DL>
			<DT>青鸟租房 © 2010 北大青鸟 京ICP证1000001号</DT>
			<DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
		</DL>
	</DIV>
	</div>
	</DIV>
	</DIV>
</BODY>
</HTML>