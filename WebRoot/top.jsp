<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function disptime() {
		var today = new Date(); //获得当前时间
		var hh = today.getHours(); //获得小时、分钟、秒
		var mm = today.getMinutes();
		var ss = today.getSeconds();
		/*设置div的内容为当前时间*/
		document.getElementById("myclock").innerHTML = "<font>你好,"+"${sessionScope.user.uname}"+" 现在是：" + hh + ":" + mm
				+ ": " + ss + "</font>";
	}
	/*使用setInterval()每间隔指定毫秒后调用disptime()*/
	var myTime = setInterval("disptime()", 1000);
</script>
<div id="header" class="wrap">
		<div id="logo">
			<img src="images/logo.gif" />
		</div>
		<div class="help" align="right">
		
			
			
			<c:if test="${empty sessionScope.user }">
				<a href="login.jsp"><font size="3" face="幼圆">登录</font></a>&nbsp;&nbsp;
				<a href="register.jsp"><font size="3" face="幼圆">注册</font></a>&nbsp;&nbsp;
			</c:if>
			<c:if test="${not empty sessionScope.user }">
				<span id="myclock" align="right"></span>
				<a href="logout-result.jsp"><font size="3" face="幼圆">注销</font></a>&nbsp;&nbsp;
				
			</c:if>
			<c:if test="${sessionScope.user.status==1 }">				
				<a href="manage/cms.jsp"><font size="3" face="幼圆">后台管理</font></a>&nbsp;&nbsp;
				<a href="manage/letterList.jsp" target="_blank"><img
				src="images/letter.gif" width="20" height="20"></a>&nbsp;&nbsp;
			</c:if>
			<c:if test="${sessionScope.user.status==2 }">
				<a href="manage/adminCms.jsp"><font size="3" face="幼圆">管理员后台管理</font></a>&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath }/letterList?type5=letterList" target="_blank"><img
				src="images/letter.gif" width="20" height="20"></a>&nbsp;&nbsp;
			</c:if>
		
			
		</div>

		

	</div>
