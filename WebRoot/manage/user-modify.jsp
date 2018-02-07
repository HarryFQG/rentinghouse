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
		<h2>修改信息</h2>
		<div class="manage">
			<form action="../addUser" method="post" onsubmit="return submit1();">
				<!-- form表单隐藏域，用于修改用户时 根据id修改，其中id不能修改 -->
				<input type="hidden" name="userId" value="2" />
				<input type="hidden" name="type" value="updateUser" />
				<table class="form">
					<tr>
						<td class="field">用户名(*)：</td>
						<td><input type="text" class="text" name="userName"
							value="${sessionScope.user.uname }" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td class="field">手机(*)：</td>
						<td><input type="text" class="text" name="mobile"
							value="${sessionScope.user.mobile }" />
						</td>
					</tr>
					<tr>
						<td class="field">邮箱(*)：</td>
						<td><input type="text" class="text" name="email"
							value="${sessionScope.user.email }" />
						</td>
					</tr>
					
					<tr>
						<td class="field">请输入密码:：</td>
						<td><input class="text" type="password" id="password" name="password" onblur="valiPwd();"/> <span id="span1"></span>
							<input type="hidden" id="pwd" name="" value="${sessionScope.user.upwd }"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit"
								name="submit" value="更新" />
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
	
	function submit1(){
		var flag=valipwd();
		if(flag){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	function valiPwd(){
		valipwd();
	} 
	function valipwd(){
		var pwd1=document.getElementById("pwd").value;//密码框面
		var pwd2=document.getElementById("password").value;
		//alert("pwd1"+pwd1+"pwd2"+pwd2)
		if(pwd1==pwd2){
			document.getElementById("span1").style.color="greenyellow";
			document.getElementById("span1").textContent="√";	
			
			return true;
		}else{
			
			document.getElementById("span1").style.color="red";
			document.getElementById("span1").textContent="×：与原始密码不一致，";
			return false;
		}
	}
	
	
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
	
	
	
	
</script>

</html>
