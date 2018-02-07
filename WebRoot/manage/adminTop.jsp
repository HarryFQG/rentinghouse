<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<div id="logo"><img src="${pageContext.request.contextPath }/images/logo.gif" width="120" height="40"/></div>
	<div class="help"><a href="${pageContext.request.contextPath }/index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<!-- <li class="current"><a href="#">首页</a></li> -->
			<li><a href="adminCms.jsp">首页</a></li>
			<li><a href="adminUser.jsp">用户</a></li>
			<li><a href="adminHouse.jsp">房屋管理</a></li>
			<li><a href="adminOrder.jsp">订单</a></li>
			<li><a href="adminLetterList.jsp">站内信</a></li>
		 
		</ul>
	</div>
</div>

<div id="childNav">
	<div class="welcome wrap">
		<span id="myclock" align="right"></span>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="adminCms.jsp">租房网</a> &gt; 管理员后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="${pageContext.request.contextPath }/addUser?type=listUser">用户管理</a></dd>
			    <dt>房屋管理</dt>
				<dd><a href="${pageContext.request.contextPath }/houseR?type1=readlyHouseAdmin">已审核房屋</a></dd>
				<dd><a href="${pageContext.request.contextPath }/houseR?type1=listHouseAdmin">待审核房屋</a></dd>			
				<dt>订单管理</dt>
				<dd><a href="adminOrder.jsp">订单管理</a></dd>
				<dt>站内信</dt>
				<dd><a href="adminLetterList.jsp">站内信</a></dd>
			 
			</dl>
		</div>
	</div>
