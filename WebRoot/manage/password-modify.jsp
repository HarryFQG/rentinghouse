<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<body>
	



<%@ include file="cmsTop.jsp" %>


	<div class="main">
		<h2>修改用户</h2>
		<div class="manage">
			<form action="../addUser" method="post" onsubmit="return submit1();">
				<!-- form表单隐藏域，用于修改用户时 根据id修改，其中id不能修改 -->
				<input type="hidden" name="type" value="updatePwd" />
				<table class="form">
					<tr>
						<td class="field">请输入原密码:：</td><input type="hidden" id="pwd" name="userId" value="${sessionScope.user.upwd }" />
						<td><input class="text" type="password" id="password1"
							name="oldPassword" onblur="func1();"/> <span id="span1"></span></td>
					</tr>
					<tr>
						<td class="field">请输入新的密码:：</td>
						<td><input class="text" type="password" id="password2"
							name="password"   onblur="func2();"/><span id="span2"></span></td>
					</tr>
					<tr>
						<td class="field">请再次输入新的密码:：</td>
						<td><input class="text" type="password" id="password3"
							name="confirmPassword"  onblur="func3();" /><span id="span3"></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit"
								name="submit" value="修改" />
						</label>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.&nbsp;鄂ICP证*******号</div>
</body>
<script type="text/javascript">
	function disptime() {
		var today = new Date(); //获得当前时间
		var hh = today.getHours(); //获得小时、分钟、秒
		var mm = today.getMinutes();
		var ss = today.getSeconds();
		/*设置div的内容为当前时间*/
		document.getElementById("lable1").innerHTML = "<font>尊敬的用户,"+"${sessionScope.user.uname}"+" 您好，今天是：" + hh + ":" + mm
				+ ": " + ss +"，欢迎回到管理后台。"+ "</font>";
	}
	/*使用setInterval()每间隔指定毫秒后调用disptime()*/
	var myTime = setInterval("disptime()", 1000);
	
	function func1(){
	
		valiPwd1();
	}	
	function valiPwd1(){
		var pwd1=document.getElementById("pwd");
		var pwd2=document.getElementById("password1");
		
		if(pwd1.value==""||pwd2.value!=pwd1.value){
			document.getElementById("span1").style.color="red";
			document.getElementById("span1").textContent="×：与原始密码不一致，";
			return false;
		}else{
			document.getElementById("span1").style.color="greenyellow";
			document.getElementById("span1").textContent="√";	
			return true;
		}	
	}
	
	function func2(){
		valiPwd2();
	}
	function valiPwd2(){
		var pwd1=document.getElementById("password2");
		//var pwd2=document.getElementById("password2");
		
		var patter=/^\w{3,9}$/;
		
		if(pwd1.value==""||!patter.test(pwd1.value)){
			document.getElementById("span2").style.color="red";
			document.getElementById("span2").textContent="×：密码在3-9位，";
			return false;
		}else{
			document.getElementById("span2").style.color="greenyellow";
			document.getElementById("span2").textContent="√";				
			return true;
		}	
	}
	
	function func3(){
		valiPwd3();
	}
	function valiPwd3(){
		var pwd1=document.getElementById("password3");
		var pwd2=document.getElementById("password2");
		var patter=/^(\w){3,9}$/;
		if(pwd1.value==""||!patter.test(pwd1.value)||!pwd2.value==pwd1.value){
			document.getElementById("span3").style.color="red";
			document.getElementById("span3").textContent="×：前后两次密码不一致，";
				
			return false;
		}else{
			document.getElementById("span3").style.color="greenyellow";
			document.getElementById("span3").textContent="√";
				
			return true;
		}	
	}
	function submit1(){
		var flag1=valiPwd1();
		var flag2=valiPwd2();
		var flag3=valiPwd3();
		if(flag1&&flag2&&flag3){
			return true;
		}else return false;
	}
	
</script>
</html>
