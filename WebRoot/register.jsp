<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短租网 </title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="scripts/ajax.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>


<script type="text/javascript">
	function valiName(obj){
		valiname();
	}
	
	function valiname() {
		var name=document.getElementById("username").value;
		var data="username="+name+"&type=valiName";
		window.getElement.Post("addUser",data, function(obj){
			alert(""+obj);
			
		}, "JSON1");
	
	}
	
	
	function onsub(){
		
		
	}

</script>


</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<img src="images/logo.gif" />
		</div>
	</div>
	
	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1 align="center">欢迎注册短租网</h1>
				
				<ul class="steps clearfix">
					<li class="current"><em></em>填写注册信息</li>
					<li class="last"><em>${requset.Scope.Msg }</em>注册成功</li>
				</ul>
				<div id="msgInfo"></div>
				<form id="regForm" method="post" action="addUser" onsubmit="return onsub();">
				
					<table width="509" height="300">
						<tr>
							<td class="field">用户名(*)：</td>
							<td><input class="text" type="text" id="username" name="userName" onblur="valiName(this);"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">登录密码(*)：</td>
							<td><input class="text" type="password" id="password"
								name="password" /><span></span></td>
						</tr>
						<tr>
							<td class="field">确认密码(*)：</td>
							<td><input class="text" type="password"
								name="confirmPassword" /><span></span></td>
						</tr>
						<tr>
							<td class="field">电子邮件：</td>
							<td><input class="text" type="text" name="email" /><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">手机(*)：</td>
							<td><input class="text" type="text" name="mobile" /><span></span>
							</td>
						</tr>
		
						<tr>
							<td></td>
							<td><label class="ui-green"><input type="submit"
									name="submit" value="提交注册"/> </label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2016中南财大 All Rights Reserved.
		鄂ICP证*******号</div>
</body>
</html>
