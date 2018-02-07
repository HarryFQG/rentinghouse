<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<LINK rel=stylesheet type=text/css href="css/styleor.css">

<script type="text/javascript"
	src="scripts/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514">


</HEAD>
<script type="text/javascript">
	
	
	var price= parseFloat(document.getElementById("price").value);
	//alert(price);
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
			//alert("差："+(checkOutDate-checkInDate));
			var cha=checkOutDate-checkInDate;			
			if(checkoutstr.value.length>2&&cha>0){
				var priceV=document.getElementById("price");
				var price=parseFloat(priceV.value);
				document.getElementById("money").value=cha/1000/60/60/24*price;
				document.getElementById("totalDay").value=cha/1000/60/60/24;			
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
			
			//防止session过期
			/* var hid=document.getElementById("odhid").value;
			alert("hid"+hid); */
			/* if(hid==""){
				goBack();
				return false;
			} */
			
			if(flag1&&flag2){
				
				return true;
			}else{
				
				return false;
			}
			
		}
	function goBack(){
		history.go(-1);
	}
	
</script>


<BODY>
	<DIV id=header class=wrap>
		<DIV id=logo>
			<IMG src="images/logo.gif">
		</DIV>
	</DIV>
	<DIV id=regLogin class=wrap>
		<DIV class=dialog>
			<DL class=clearfix>
				<DT>新房屋信息发布</DT>
				<DD class="past">填写房屋信息</DD>
				<dt><a href="${pageContext.request.contextPath }/manage/letter.jsp?fangDongId=${sessionScope.fangDong.uid }&fangDongName=${sessionScope.fangDong.uname}" style="margin-left:85%;"><img src="images/letter.gif" width="20" height="20">联系商家</a></dt>
			</DL>
			<DIV class="box">

				<FORM id=add_action name=add.action 	action="${pageContext.request.contextPath }/orderUser" method="post" onsubmit="return submit1();">
					<input type="hidden" value="2" name="userId"> <input type="hidden" value="creatingOrder" name="type4">						
						<input type="hidden" name="ouid" value="${sessionScope.user.uid}" />
						<input type="hidden" name="ouname" value="${sessionScope.user.uname}" />
						<input type="hidden" name="opayType" value="${sessionScope.houseDetail.hpayRule }" >
						
						<input type="hidden" name="odhid" value="${sessionScope.houseDetail.hid }" >						
						<input type="hidden" name="odday" id="totalDay" value=""/>
						<input type="hidden" name="price" id="price" value="${sessionScope.houseDetail.hrentPrice }"/>
					<DIV class=infos>
						<font color="red"></font>
						<TABLE class=field>
							<TBODY>
								<tr>
									<td><font>你好,${sessionScope.user.uname }!</font></td>
									<td>您的订单信息为：</td>
								</tr>
								<TR>
									<TD class=field>房屋名称：</TD>
									<!-- <TD><INPUT id=add_action_title class=text type=text name=houseName> </TD> -->
									<td>${sessionScope.fangDong.uname }的别墅</td>
								</TR>
								<TR>
									<TD class=field>房屋类型：</TD>
									<!-- <TD><INPUT id=add_action_title class=text type=text name=houseType> </TD> -->
									<td>
										<SPAN><font size="+1">									
											<c:choose>
													<c:when test="${house.htype==1 }">酒店</c:when>
													<c:when test="${house.htype==2 }">别墅</c:when>
													<c:when test="${house.htype==3 }">普通单元</c:when>
													<c:when test="${house.htype==4 }">大洋房</c:when>
													<c:when test="${house.htype==5 }">小洋房</c:when>
													<c:otherwise>旅馆</c:otherwise>
											</c:choose>											
										</font></SPAN>
										
										
									</td>
								</TR>

								<TR>

									<TD class=field>付款方式：</TD>
									<TD><input class="radio" type="radio" name="payType"
										value="0" checked="checked" />网上支付 <input class="radio"
										type="radio" name="payType" value="1" />线下支付 <span></span></TD>

								</TR>

								<tr>
									<TD class=field>入住时间：</TD>
									<TD><input value="" name="checkInDate" id="birthday"
										height="200" onblur="checkInDate1()" /><span id="span11"></span>
										<!-- <input id="birthday" name="checkInDate" class=text type=text value="2016-08-10" ></input> --></TD>


								</tr>
								<tr>
									<TD class=field>退房时间：</TD>
									<TD><input value="" name="checkOutDate" id="birthday1"
										height="200" onblur="checkOutDate1()" /><span id="span22"></span>
										<!-- <input id="birthday1" name="checkOutDate" class=text type=text value="2016-08-17" ></input> --></TD>

								</tr>
								<tr>
									<TD class=field>总价(元)：</TD>
									<!-- <TD><input name="total" class=text type=text value=""></input></TD> -->
									<td><input type="text" id="money" name="ocost" value="" readonly="readonly"/> </td>
								</tr>

							</TBODY>
						</TABLE>

						<DIV class=buttons>
							
							<INPUT value=提交订单 type=submit name="submit">
							<!-- onclick='document.location="list.htm"' -->
						</DIV>
					</DIV>
				</FORM>
			</DIV>
		</DIV>
	</DIV>
	<DIV id=footer class=wrap>Copyright &copy; 2016中南财大 All Rights
		Reserved.&nbsp;鄂ICP证*******号</DIV>
</BODY>
</HTML>