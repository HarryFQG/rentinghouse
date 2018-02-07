<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
</script>
  <div id="header" class="wrap">
	<div id="logo"><img src="${pageContext.request.contextPath }/images/logo.gif" width="120" height="40"/></div>
	<div class="help"><a href="${pageContext.request.contextPath}/Index">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
			<li><a href="house.jsp">房东</a></li>
			
			<li><a href="order.jsp">房客</a></li>
		 	<li><a href="letterList.jsp">站内信</a></li>
		</ul>
	</div>
</div>

<div id="childNav">
	<div class="welcome wrap" > 
		
		<label id="lable1"></label>
		
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="cms.jsp">租房网</a> &gt; 用户后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>我是房东</dt>
				<dd><a href="fabu.jsp">创建房屋</a></dd>
				<dd><a href="userHouse?type2=orderList&userId=${sessionScope.user.uid }">受理订单</a></dd>
				<dd><a href="userHouse?type2=userhouselist">我的房屋</a></dd>
			    <dt>我是房客<br></dt>
				<dd><a href="${pageContext.request.contextPath }/orderUser?type4=waitOrder&userId=${sessionScope.user.uid }">待受理订单</a></dd>
				<dd><a href="${pageContext.request.contextPath }/orderUser?type4=alreadyDeal&userId=${sessionScope.user.uid }">已受理订单</a></dd>
				<dd><a href="${pageContext.request.contextPath }/orderUser?type4=alreadyBack&userId=${sessionScope.user.uid }">已取消订单</a></dd>
				<dt>个人信息</dt>
				<dd><a href="user-modify.jsp">修改信息</a></dd>
				<dd><a href="password-modify.jsp">修改密码</a></dd>	
				<dt>站内信</dt>
				<dd><a href="${pageContext.request.contextPath }/letterList?type5=letterList&userId=${sessionScope.user.uid }">站内信</a></dd>	 
			</dl>
		</div>
	</div>
