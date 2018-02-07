<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0032)http://localhost:8080/HouseRent/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>短租网 - 用户登录</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="css/style.css">
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<div id="register" class="wrap" >
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1 align="center">用户登录</h1>
			<!--<font color=red></font>  -->
			<div id="msgInfo"><marquee scrollamount="10" bgcolor="aquamarine">${requestScope.Msg }${sessionScope.Msg }</marquee>	 </div>
			<form id="loginForm" method="post" action="loginUser">
			
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td>
                            <input class="text" type="text" id="userName" name="userName" />
							<span></span>
						</td>
						<td></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td>
                            <input class="text" type="password" id="password" name="password" />
							<span></span>
						</td>
						<td></td>
					</tr>
					<tr>
					<td class="field">验证码：</td>
						<td>
                            <img src="valiCode" id="safeCode"/><a id="changeCode" href="#">看不清，换一张</a><br>
	                        <input type="text" name="code"><div class="mess"></div>
							<span></span>
						</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="立即登录"/></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="register.jsp"><font size=3>注册新用户</font></a></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 中南财大 All Rights Reserved. 鄂ICP证*******号
</div>
</DIV>
</DIV>
</BODY></HTML>
