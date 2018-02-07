
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<LINK rel=stylesheet type=text/css href="../css/stylefa.css">

<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>

<META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>
	<DIV id=header class=wrap>
		<DIV id=logo>
			<IMG src="${pageContext.request.contextPath }/images/logo.gif">
		</DIV>
	</DIV>
	<DIV id=regLogin class=wrap>
		<DIV class=dialog>
			<DL class=clearfix>
				<DT>新房屋信息发布</DT>
				
						
					
				<DD class=past>填写房屋信息</DD>
			</DL>
			<DIV class=box>
				<c:forEach items="${sessionScope.userhouseList }" var="house">
					<c:if test="${house.hid==param.houseId1 }">
				<FORM id="form11" name="addhouse"
					enctype="multipart/form-data" action="${pageContext.request.contextPath }/manage/userHouse" onsubmit="return submit1();"	method="post">
					<DIV class=infos>
						<TABLE class=field>
							<TBODY>
								<TR>
									
									<TD class=field>房屋名称：<INPUT type="hidden" name="huid" value="${sessionScope.user.uid }"/></TD>
									<TD><INPUT type="hidden" name="hid" value="${house.hid }"/>
									<INPUT id="houseName" class="text" type="text"
										name="huname" onblur="valiHouse();" value="${house.huname }"/><span id="span1"></span>
									</TD>
									<TD class=field>全额退款日：</TD>
									<TD><INPUT id="backMoneyDay" class="text" type="text"
										name="hrefundDay" onblur="valiBackMoney();" value="${house.hrefundDay}"/><span id="span2"></span></TD>
								</TR>
								<TR>
									<TD class=field>发票：</TD>
									<td><input class="radio" type="radio" name="hinvoice" value="1"<c:if test="${house.hinvoice==1 }"> checked="checked"</c:if> />提供 <input class="radio"
										type="radio" name="hinvoice" value="0" <c:if test="${house.hinvoice==0 }"> checked="checked"</c:if> />不提供 <span></span></td>
									<TD class=field>日租价：</TD>
									<TD><INPUT id="dayprice" class="text" type="text"
										name="hrentPrice" onblur="dayPrice()" value="${house.hrentPrice }"><span id="span3"></span>元/天</TD>
								</TR>
								<TR>

									<TD class=field>出租类型：</TD>
									<TD><input class="radio" type="radio" name="hleaseTpe"
										value="0" <c:if test="${house.hleaseTpe==0 }"> checked="checked" </c:if> />整租 <input class="radio"
										type="radio" name="hleaseTpe" value="1" <c:if test="${house.hleaseTpe==1 }"> checked="checked" </c:if> />单间 <input
										class="radio" type="radio" name="hleaseTpe" value="2" <c:if test="${house.hleaseTpe==2 }"> checked="checked" </c:if> />床位 <span></span>
									</TD>

									<TD class=field>房屋类型：</TD>
									<TD><select name="htype">
											<option value="1"<c:if test="${house.htype==1 }"> selected="selected" </c:if>>酒店</option>
											<option value="2"<c:if test="${house.htype==2 }"> selected="selected" </c:if> >别墅</option>
											<option value="3" <c:if test="${house.htype==3 }"> selected="selected" </c:if> >普通单元</option>
											<option value="4" <c:if test="${house.htype==4 }"> selected="selected" </c:if> >大洋房</option>
											<option value="5" <c:if test="${house.htype==5 }"> selected="selected" </c:if> >小洋房</option>
											<option value="6" <c:if test="${house.htype==6 }"> selected="selected" </c:if> >旅馆</option>
									</select></TD>




								</TR>

								<tr>
									<TD class=field>可住人数：</TD>
									<TD><select name="hlive">
											<option value="1" <c:if test="${house.hlive==1 }"> selected="selected" </c:if> >1</option>
											<option value="2" <c:if test="${house.hlive==2 }"> selected="selected" </c:if>>2</option>
											<option value="4" <c:if test="${house.hlive==4 }"> selected="selected" </c:if> >4</option>
											<option value="6" <c:if test="${house.hlive==6 }"> selected="selected" </c:if> >6</option>
											<option value="100" <c:if test="${house.hlive==100 }"> selected="selected" </c:if> >不限</option>
									</select></TD>
									<TD class=field>床位数：</TD>
									<TD><select name="hbed">
											<option value="1" <c:if test="${house.hbed==1 }"> selected="selected" </c:if> >1</option>
											<option value="2" <c:if test="${house.hbed==2 }"> selected="selected" </c:if> >2</option>
											<option value="3" <c:if test="${house.hbed==3 }"> selected="selected" </c:if> >3</option>
											<option value="4" <c:if test="${house.hbed==4 }"> selected="selected" </c:if> >4</option>
											<option value="5" <c:if test="${house.hbed==5 }"> selected="selected" </c:if> >5</option>
											<option value="6" <c:if test="${house.hbed==6 }"> selected="selected" </c:if> >6及以上</option>
									</select></TD>
								</tr>
							
								<TR>

									<TD class=field>卧室数：</TD>
									<TD><select name="hbedRoom">
											<option value="1" <c:if test="${house.hbedRoom==1 }"> selected="selected" </c:if> >1</option>
											<option value="2" <c:if test="${house.hbedRoom==2 }"> selected="selected" </c:if> >2</option>
											<option value="3" <c:if test="${house.hbedRoom==3 }"> selected="selected" </c:if> >3</option>
											<option value="4" <c:if test="${house.hbedRoom==4 }"> selected="selected" </c:if> >4</option>
											<option value="5" <c:if test="${house.hbedRoom==5 }"> selected="selected" </c:if> >5</option>
											<option value="6" <c:if test="${house.hbedRoom==6 }"> selected="selected" </c:if> >6</option>
									</select></TD>
									<TD class=field>床型：</TD>
									<TD><select name="hbedForm">
											<option value="0" <c:if test="${house.hbedForm==0 }"> selected="selected" </c:if> >单人床</option>
											<option value="1" <c:if test="${house.hbedForm==1 }"> selected="selected" </c:if> >双人床</option>

									</select></TD>
								</TR>
								<TR>
									<TD class=field>房屋描述：</TD>
									<TD><TEXTAREA name="hdescribe" id="desc" onblur="valiDesc()" >${house.hdescribe }</TEXTAREA><span id="span4"></span></TD>

									<TD class=field>使用规则：</TD>
									<TD><TEXTAREA name="huseRule" id="userrule" onblur="userRule()" >${house.huseRule }</TEXTAREA><span id="span5"></span></TD>
								</TR>
								<TR>
								<TR>
									<TD class="field">设施服务：</TD>
									<TD><TEXTAREA name="hservice" id="service" onblur="serviceFunc()">${house.hservice }</TEXTAREA><span id="span6"></span></TD>
									<TD class="field">地址：</TD>
									<TD><TEXTAREA name="haddress" id="address" onblur="addrFunc()">${house.haddress }</TEXTAREA><span id="span7"></span>
									</TD>

								</TR>
								<TR>
									<TD class=field>卫生间数：</TD>
									<TD><select name="htoilet">
											<option value="1" <c:if test="${house.htoilet==1 }"> selected="selected" </c:if> >1</option>
											<option value="2" <c:if test="${house.htoilet==2 }"> selected="selected" </c:if> >2</option>
											<option value="3" <c:if test="${house.htoilet==3 }"> selected="selected" </c:if> >3</option>
											<option value="4" <c:if test="${house.htoilet==4 }"> selected="selected" </c:if> >4个及以上</option>
									</select>
									</TD>
									<TD class=field>入住时间：</TD>
									<TD><select name="hcheckIn">
											<option value="14:00:00" <c:if test="${house.hcheckIn=='14:00:00' }"> selected="selected" </c:if> >14：00</option>
											<option value="12:00:00" <c:if test="${house.hcheckIn=='12:00:00' }"> selected="selected" </c:if> >12：00</option>
											<option value="00:00:00" <c:if test="${house.hcheckIn=='00:00:00' }"> selected="selected" </c:if> >不限</option>
									</select></TD>
								</TR>
								<TR>
									<TD class=field>付款规则：</TD>
									<TD><select name="hpayRule">
											<option value="0" <c:if test="${house.hpayRule==0 }"> selected="selected" </c:if> >严格</option>
											<option value="1" <c:if test="${house.hpayRule==1 }"> selected="selected" </c:if> >不严格</option>

									</select></TD>
									<TD class="field">退房时间：</TD>
									<TD><select name="hcheckOut">
											<option value="14:00:00" <c:if test="${house.hcheckOut=='14:00:00' }"> selected="selected" </c:if>>14：00</option>
											<option value="12:00:00"  <c:if test="${house.hcheckOut=='12:00:00' }"> selected="selected" </c:if> >12：00</option>
											<option value="00:00:00" <c:if test="${house.hcheckOut=='00:00:00' }"> selected="selected" </c:if> >不限</option>
									</select></TD>
								</TR>
								<TR>
									<TD class="field">最小天数：</TD>
									<TD><INPUT id="leastDays" class="text" type="text"
										name="hminDay" onblur="leastDaysFunc()" value="${house.hminDay }" /><span id="span8"></span>
									</TD>
									<TD class=field>最大天数：</TD>
									<TD><INPUT id="mostDays" class="text" type="text"
										name="hmaxDay" onblur="mostDaysFunc()" value="${house.hmaxDay }" /> <span id="span9"></span></TD>
								</TR>
								<TR>
									<TD class=field>房屋图片：<br>
									</TD>
									<td><input type="file" class="text" name="hpicture1"  id="image1" onblur="image1Func()" value="${house.hpicture1 }" /><span id="span10"></span>
									</td>
									<TD class=field>面积：</TD>
									<TD><INPUT class="text" type="text" name="area" id="area" onblur="areaFunc();" value="${house.area }" /><span id="span11"></span>平方米</TD>
								</TR>

								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td><input type="file" class="text" name="hpicture2" id="image2" onblur="image2Func()" value="${house.hpicture2 }" /><span id="span12"></span>
									</td>
								</tr>

								<tr>

									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td><input type="file" class="text" name="hpicture3" id="image3" onblur="image3Func()" value="${house.hpicture3 }" /><span id="span13"></span>
									</td>
								</tr>

							</TBODY>
						</TABLE>

						<DIV class=buttons>
							<INPUT value="立即修改" type="submit"/><INPUT value="返回" type="button" onclick="goBack();"/>
						</DIV>
					</DIV>
				</FORM>
				</c:if>
					
				</c:forEach>
			</DIV>
		</DIV>
	</DIV>
	<DIV id=footer class=wrap>Copyright &copy; 2016 中南财大 All Rights
		Reserved. 鄂ICP证*******号</DIV>
</BODY>
<script type="text/javascript">
	
	function goBack(){
		history.go(-1);
	}
	
	function valiHouse(){
		valihouse();		
	}
	function valihouse(){
		var name= document.getElementById("houseName");
		
		
		if(name.value.length<2||name.value.length>30){
			
			document.getElementById("span1").style.color='red';
			document.getElementById("span1").textContent="×：在2-25个字之间";
			
			return false;
		}else{
			
			document.getElementById("span1").style.color='#7FFF00';
			document.getElementById("span1").textContent="√";
			return true;		
		}		
	}
	
	/*function valiHouse(){
		valihouse();		
	}
	function valihouse(){
		var name= document.getElementById("add_action_title");
		var patter=/^\w{2,26}$/;
		if(name.value==""||!patter.test(name.value)){
			document.getElementById("span1").style.color='red';
			document.getElementById("span1").textContent="×：在2-25个字之间";
			return false;
		}else{
			document.getElementById("span1").style.color='#7FFF00';
			document.getElementById("span1").textContent="√";
			return true;		
		}		
	}
	*/
	function valiBackMoney(){
		valibackmoney();
	}
	function valibackmoney(){
		var backMoneyDay= document.getElementById("backMoneyDay");
		var day=parseInt(backMoneyDay.value);		
		
		if(!(day>0&&day<100)){
			document.getElementById("span2").style.color='red';
			document.getElementById("span2").textContent="×：大小100以内";
			return false;			
		}else{			
			document.getElementById("span2").style.color='#7FFF00';
			document.getElementById("span2").textContent="√";
			return true;			
		}
	}
	
	
	
	function dayPrice(){
		
		valiprice();
	}
	function valiprice(){
		var p=document.getElementById("dayprice");
		var price=parseInt(p.value);
		//alert(price)
		if(!(0<price)){
			document.getElementById("span3").style.color='red';
			document.getElementById("span3").textContent="×：单价应大于0";
			return false;
		}else{
			document.getElementById("span3").style.color='#7FFF00';
			document.getElementById("span3").textContent="√";
			return true;
			
		}		
	}
	
	
	
	function valiDesc(){
		validesc();		
	}
	function validesc(){
		var desc=document.getElementById("desc");
		if(!(desc.value.length<380&&desc.value.length>0)){
			document.getElementById("span4").style.color='red';
			document.getElementById("span4").textContent="×：不超过380字，以及不为0";
			return false;
		}else{
			document.getElementById("span4").style.color='#7FFF00';
			document.getElementById("span4").textContent="√";
			return true;		
		}		
	}
	
	
	
	
	function userRule(){
		userrule();		
	}
	function userrule(){
		var rule=document.getElementById("userrule");
		if(!(rule.value.length<380&&rule.value.length>0)){
			document.getElementById("span5").style.color='red';
			document.getElementById("span5").textContent="×：不超过380字，以及不为0";
			return false;
		}else{
			document.getElementById("span5").style.color='#7FFF00';
			document.getElementById("span5").textContent="√";
			return true;		
		}
	}
	
	
	function serviceFunc(){		
		servicefunc();		
	}
	function servicefunc(){
		var rule=document.getElementById("service");
		if(!(rule.value.length<380&&rule.value.length>0)){
			document.getElementById("span6").style.color='red';
			document.getElementById("span6").textContent="×：不超过380字，以及不为0";
			return false;
		}else{
			document.getElementById("span6").style.color='#7FFF00';
			document.getElementById("span6").textContent="√";
			return true;		
		}
	}
	
	function addrFunc(){
		addrfunc();		
	}	
	function addrfunc(){
		var rule=document.getElementById("address");
		if(!(rule.value.length<380&&rule.value.length>0)){
			document.getElementById("span7").style.color='red';
			document.getElementById("span7").textContent="×：不超过380字，以及不为0";
			return false;
		}else{
			document.getElementById("span7").style.color='#7FFF00';
			document.getElementById("span7").textContent="√";
			return true;		
		}
		
	}
	
	
	function leastDaysFunc(){
		leasDaysfunc();		
	}
	function  leasDaysfunc(){
		var backMoneyDay= document.getElementById("leastDays");
		var day=parseInt(backMoneyDay.value);		
		
		if(!(day>0&&day<100)){
			document.getElementById("span8").style.color='red';
			document.getElementById("span8").textContent="×：大小100以内";
			return false;			
		}else{			
			document.getElementById("span8").style.color='#7FFF00';
			document.getElementById("span8").textContent="√";
			return true;			
		}
	}
	
	function mostDaysFunc(){
		mostDaysfunc();
	}
	function mostDaysfunc(){
		var backMoneyDay= document.getElementById("mostDays");
		var day=parseInt(backMoneyDay.value);		
		
		if(!(day>0&&day<100)){
			document.getElementById("span9").style.color='red';
			document.getElementById("span9").textContent="×：大小100以内";
			return false;			
		}else{			
			document.getElementById("span9").style.color='#7FFF00';
			document.getElementById("span9").textContent="√";
			return true;			
		}
	}
	
	
	
	function areaFunc(){
		areafunc();
	}
	function areafunc(){
		var area1= document.getElementById("area");
		var area2=parseFloat(area1.value);			
		if(!(area2>0&&area2<200)){
			document.getElementById("span11").style.color='red';
			document.getElementById("span11").textContent="×：在200以内";
			return false;			
		}else{			
			document.getElementById("span11").style.color='#7FFF00';
			document.getElementById("span11").textContent="√";
			return true;			
		}
	}
	
	
	
	function image1Func(){
		image1func();
	}
	function image1func(){
		var image1= document.getElementById("image1");
		if((image1.value=="")){
			document.getElementById("span10").style.color='red';
			document.getElementById("span10").textContent="×：你还未选择图片";
			return false;			
		}else{			
			document.getElementById("span10").style.color='#7FFF00';
			document.getElementById("span10").textContent="√";
			return true;			
		}
		
	}
	
	function image2Func(){
		image2func();
	}
	function image2func(){
		var image1= document.getElementById("image2");
		if((image1.value=="")){
			document.getElementById("span12").style.color='red';
			document.getElementById("span12").textContent="×：你还未选择图片";
			return false;			
		}else{			
			document.getElementById("span12").style.color='#7FFF00';
			document.getElementById("span12").textContent="√";
			return true;			
		}
		
	}
	
	function image3Func(){
		image3func();
	}
	function image3func(){
		var image1= document.getElementById("image3");
		if((image1.value=="")){
			document.getElementById("span13").style.color='red';
			document.getElementById("span13").textContent="×：你还未选择图片";
			return false;			
		}else{
			document.getElementById("span13").style.color='#7FFF00';
			document.getElementById("span13").textContent="√";
			return true;			
		}
		
	}
	function submit1(){
		var flag1=valihouse();
		var flag2=valibackmoney();
		var flag3=validesc();
		var flag4=valiprice();
		var flag5=userrule();
		var flag6=servicefunc();
		var flag7=addrfunc();
		var flag8=image1func();
		var flag9=image2func();
		var flag10=image3func();
		var flag11=areafunc();
		var flag12=mostDaysfunc();
		var flag13=leasDaysfunc();
		if(flag1&&flag2&&flag3&&flag4&&flag5&&flag6&&flag7&&flag8&&flag9&&flag10&&flag11&&flag12&&flag13){
			alert("ture");
			return true;
		}else{
			alert("false");
			return false;			
		}
		
	}
	
</script>



</HTML>
