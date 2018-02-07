package com.it.userservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.adminservice.HouseR;
import com.it.dao.HouseDao;
import com.it.dao.HouseDaoImpl;
import com.it.dao.OrderDao;
import com.it.dao.OrderDaoImpl;
import com.it.dao.OrderDetailDao;
import com.it.dao.OrderDetailImpl;
import com.it.dao.UserDao;
import com.it.dao.UserDaoImpl;
import com.it.entity.House;
import com.it.entity.Order;
import com.it.entity.OrderDetail;
import com.it.entity.User;
/**
 * 
 * @author Administrator 2017年8月17日 下午1:57:26
 *	分页按钮的处理
 */
public class OrderUser extends HttpServlet {

	static Map<Integer,Order> maps1=new HashMap<>();
	static Map<Integer,House> maps2=new HashMap<>();
	static Map<Integer,OrderDetail> maps3=new HashMap<>();
	static Map<Integer,User> maps4=new HashMap<>();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type4");
		if(type==null){
			String str=String.valueOf(request.getSession().getAttribute("type4"));			
			if(str!=null){
				System.out.println(str);
				type=str;
			}
			
		}	
		
		
		
		if(type!=null&&type.equals("creatingOrder")){//创建订单
			this.createOrder(request,response);
		}else if(type!=null&&type.equals("quxiao")){//房客点击了取消该订单，状态值为-1
			this.back(request,response);
			
		}else if(type!=null&&type.equals("waitOrder")){//房客查看待处理的订单
			
			this.waitOrder(request,response);			
		}else if(type!=null&&type.equals("chuli")){//房东受理订单
			
			this.dealOrder(request,response);
		}else if(type!=null&&type.equals("alreadyDeal")){//房客查看已经房东已经处理了的订单,也就是订单状态(order_type)为1的订单
			
			this.alreadyDeal(request,response);
		}else if(type!=null&&type.equals("alreadyBack")){//房客查看已经自己取消了的订单,也就是订单状态(order_type)为-1的订单
			
			this.alreadyBack(request,response);
		}
		
	}
	
	
	/**房客查看已经自己取消了的订单,也就是订单状态(order_type)为-1的订单			
	 * 2017年8月18日下午4:06:45 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void alreadyBack(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String struid=request.getParameter("userId");
		Integer uid=null;
		if(struid==null){
			uid=(Integer)request.getSession().getAttribute("userId");
			request.getSession().removeAttribute("userId");
			if(request.getSession().getAttribute("type4")!=null){
				request.getSession().removeAttribute("type4");	
			}		
		}else{
			uid=Integer.parseInt(struid);
		}
		
		
		String strPage =request.getParameter("pageCurrent");
		OrderDao orderDao=new OrderDaoImpl();
		int totalRow=orderDao.totalCount(uid, 0);//获取记录数
		int totalPage=orderDao.totalPage(uid,0,5);//用户Id,状态，分页数量
		int pac=0;
		if(strPage==null){
			pac=1;
		}else{
			pac=Integer.parseInt(strPage);
			if(pac>totalPage){
				pac=1;
			}else if(pac<1){
				pac=totalPage;
			}
		}
		
		
		
		System.out.println("-------uid:"+uid);
		//先查Tb_order表,显示出自己所有的代订的订单
				
		List<Order> listOrder = orderDao.findByUid(uid,-1);//取消订单的状态为-1
		List<Integer> listoid=new ArrayList<>();//批量查询当前用户的所有待处理订单的oid
		for(Order o:listOrder){
			maps1.put(o.getOid(), o);
			listoid.add(o.getOid());
		}
		
		//批量查询OrderDetail
		OrderDetailDao orderDetailDao=new OrderDetailImpl();
		List<OrderDetail> litsDetail = orderDetailDao.litsDetail(listoid);//查询所有的OrderDetail结果
		//取房东的房子Id
		List<Integer> listhid=new ArrayList<>();
		for(OrderDetail o:litsDetail){
			if(o!=null){
				maps3.put(o.getOdid(), o);
				listhid.add(o.getOdhid());
			}
		}
		
		/*更据Id批量查询房子*/
		HouseDao houseDao=new HouseDaoImpl();
		List<House> findByListHouse = houseDao.findByListHouse(listhid);//查询所有的房子
		//查询所有的Uid
		List<Integer> listuid=new ArrayList<>();
		for(House h:findByListHouse){
			if(h!=null){
				maps2.put(h.getHid(), h);
				listuid.add(h.getHuid());
			}
		}		
		
		/*更据id批量查询用户*/
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listuid);
		for(User u:findUser){
			maps4.put(u.getUid(), u);			
		}
		
		
		//输出四张表
		System.out.println("----------listOrder----:"+listOrder);
		System.out.println("----------litsDetail----:"+litsDetail);
		System.out.println("----------findByListHouse----:"+findByListHouse);
		System.out.println("----------findUser----:"+findUser);
		request.getSession().setAttribute("listOrder",listOrder );
		request.getSession().setAttribute("litsDetail", litsDetail);
		request.getSession().setAttribute("findByListHouse", findByListHouse);
		request.getSession().setAttribute("findUser", findUser);
		
		//防止分页信息
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",totalRow);
		
		//用于页面控制是用户待处理订单，还是，房东要处理的订单。
		request.getSession().setAttribute("orderStatus", 3);//这种状态房客可以取消按钮，当为1是，说明用户进的是房东界面，显示受理按钮
		
		
		//分页按钮的处理
		
		
		response.sendRedirect("manage/order.jsp");
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	/**房客查看已经房东已经处理了的订单,也就是订单状态(order_type)为1的订单			
	 * 2017年8月18日下午4:07:16 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void alreadyDeal(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String struid=request.getParameter("userId");
		Integer uid=null;
		if(struid==null){
			uid=(Integer)request.getSession().getAttribute("userId");
			request.getSession().removeAttribute("userId");
			if(request.getSession().getAttribute("type4")!=null){
				request.getSession().removeAttribute("type4");	
			}		
		}else{
			uid=Integer.parseInt(struid);
		}
		
		
		String strPage =request.getParameter("pageCurrent");
		OrderDao orderDao=new OrderDaoImpl();
		int totalRow=orderDao.totalCount(uid, 0);//获取记录数
		int totalPage=orderDao.totalPage(uid,0,5);//用户Id,状态，分页数量
		int pac=0;
		if(strPage==null){
			pac=1;
		}else{
			pac=Integer.parseInt(strPage);
			if(pac>totalPage){
				pac=1;
			}else if(pac<1){
				pac=totalPage;
			}
		}
		
		
		
		System.out.println("-------uid"+uid);
		//先查Tb_order表,显示出自己所有的代订的订单
				
		List<Order> listOrder = orderDao.findByUid(uid,1);//已处理订单的状态为1
		List<Integer> listoid=new ArrayList<>();//批量查询当前用户的所有待处理订单的oid
		for(Order o:listOrder){
			maps1.put(o.getOid(), o);
			listoid.add(o.getOid());
		}
		
		//批量查询OrderDetail
		OrderDetailDao orderDetailDao=new OrderDetailImpl();
		List<OrderDetail> litsDetail = orderDetailDao.litsDetail(listoid);//查询所有的OrderDetail结果
		//取房东的房子Id
		List<Integer> listhid=new ArrayList<>();
		for(OrderDetail o:litsDetail){
			if(o!=null){
				maps3.put(o.getOdid(), o);
				listhid.add(o.getOdhid());
			}
		}
		
		/*更据Id批量查询房子*/
		HouseDao houseDao=new HouseDaoImpl();
		List<House> findByListHouse = houseDao.findByListHouse(listhid);//查询所有的房子
		//查询所有的Uid
		List<Integer> listuid=new ArrayList<>();
		for(House h:findByListHouse){
			if(h!=null){
				maps2.put(h.getHid(), h);
				listuid.add(h.getHuid());
			}
		}		
		
		/*更据id批量查询用户*/
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listuid);
		for(User u:findUser){
			maps4.put(u.getUid(), u);			
		}
		
		
		//输出四张表
		System.out.println("----------listOrder----:"+listOrder);
		System.out.println("----------litsDetail----:"+litsDetail);
		System.out.println("----------findByListHouse----:"+findByListHouse);
		System.out.println("----------findUser----:"+findUser);
		request.getSession().setAttribute("listOrder",listOrder );
		request.getSession().setAttribute("litsDetail", litsDetail);
		request.getSession().setAttribute("findByListHouse", findByListHouse);
		request.getSession().setAttribute("findUser", findUser);
		
		//防止分页信息
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",totalRow);
		
		//用于页面控制是用户待处理订单，还是，房东要处理的订单。				//为3就是两个按钮都不显示
		request.getSession().setAttribute("orderStatus", 3);//这种状态房客可以取消按钮，当为1是，说明用户进的是房东界面，显示受理按钮
		
		
		
		
		//下面分页按钮的处理
		
		
		
		
		response.sendRedirect("manage/order.jsp");
		
		
		
		
		
		
		
		
		
	}
	/**
	 * 上午9:40:33
	 * 房东受理了订单。值为1
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 *
	 */	
	private void dealOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str=request.getParameter("orderUserId");
		String[] arr=str.split("\\.");
		Integer orderId=Integer.parseInt(arr[0]);
		Integer userId=Integer.parseInt(arr[1]);
		OrderDao orderDao=new OrderDaoImpl();
		Order order=new Order();
		
		Set<Integer> set = maps1.keySet();
		if(set.contains(orderId)){
			order=maps1.get(orderId);
		}else{
			order=orderDao.findById(orderId);
		}
		order.setOid(orderId);
		order.setOstatus(1);//order_status:修改改为1
		orderDao.update(order);
		System.out.println("------------orderBack:"+order);
		request.getSession().setAttribute("userId", userId);
		request.getSession().setAttribute("type4", "waitOrder");
		response.sendRedirect("orderUser");
		
	}
	/**
	 * 上午9:40:03
	 * 房客取消了订单,状态值为-1
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 *
	 */
	private void back(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str=request.getParameter("orderUserId");
		String[] arr=str.split("\\.");
		Integer orderId=Integer.parseInt(arr[0]);
		Integer userId=Integer.parseInt(arr[1]);
		OrderDao orderDao=new OrderDaoImpl();
		Order order=new Order();
		order.setOid(orderId);
		Set<Integer> set = maps1.keySet();
		if(set.contains(orderId)){
			order=maps1.get(orderId);
		}else{
			order=orderDao.findById(orderId);
		}	
		
		order.setOstatus(-1);
		System.out.println("updateOrder:"+order);
		orderDao.update(order);
		
		OrderDetailDao orderDetailDao=new OrderDetailImpl();
		OrderDetail od = orderDetailDao.findByOid(orderId);
		//修改房屋状态为4，表示已订。现在要改为2
		HouseDao houseDao=new HouseDaoImpl();
		House house = houseDao.findById(od.getOdhid());
		house.setHstatus(2);
		house.setHid(od.getOdhid());
		//System.out.println("house:---->>>>>"+house);
		houseDao.updateHouse(house);
		
		System.out.println("------------orderBack:"+order);
		request.getSession().setAttribute("userId", userId);
		request.getSession().setAttribute("type4", "waitOrder");
		
		
		
		/*修改首页显示房屋数量加一*/
		HouseR houser=new HouseR();
		houser.indexComtomer(request);			
		
		
		
		
		
		
		response.sendRedirect("orderUser");
		/*request.getRequestDispatcher("orderUser").forward(request, response);*/
	}
	/**
	 * 下午6:48:13
	 * 房客查看自己等待房东受理的订单
	 * @param request
	 * @param response
	 *待房东受理的订单页面上显示的内容，在tb_house和Tb_user和tb_order,Tb_OrderDetail上面
	 * @throws IOException 
	 */
	private void waitOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String struid=request.getParameter("userId");
		Integer uid=null;
		if(struid==null){
			uid=(Integer)request.getSession().getAttribute("userId");
			request.getSession().removeAttribute("userId");
			if(request.getSession().getAttribute("type4")!=null){
				request.getSession().removeAttribute("type4");	
			}		
		}else{
			uid=Integer.parseInt(struid);
		}
		
		
		String strPage =request.getParameter("pageCurrent");
		OrderDao orderDao=new OrderDaoImpl();
		int totalRow=orderDao.totalCount(uid, 0);//获取记录数
		int totalPage=orderDao.totalPage(uid,0,5);//用户Id,状态，分页数量
		int pac=0;
		if(strPage==null){
			pac=1;
		}else{
			pac=Integer.parseInt(strPage);
			if(pac>totalPage){
				pac=1;
			}else if(pac<1){
				pac=totalPage;
			}
		}
		
		
		
		
		//先查Tb_order表,显示出自己所有的代订的订单
				
		List<Order> listOrder = orderDao.findByUid(uid,0);//待处理订单的状态为0
		List<Integer> listoid=new ArrayList<>();//批量查询当前用户的所有待处理订单的oid
		for(Order o:listOrder){
			maps1.put(o.getOid(), o);
			listoid.add(o.getOid());
		}
		
		//批量查询OrderDetail
		OrderDetailDao orderDetailDao=new OrderDetailImpl();
		List<OrderDetail> litsDetail = orderDetailDao.litsDetail(listoid);//查询所有的OrderDetail结果
		//取房东的房子Id
		List<Integer> listhid=new ArrayList<>();
		for(OrderDetail o:litsDetail){
			if(o!=null){
				maps3.put(o.getOdid(), o);
				listhid.add(o.getOdhid());
			}
		}
		
		/*更据Id批量查询房子*/
		HouseDao houseDao=new HouseDaoImpl();
		List<House> findByListHouse = houseDao.findByListHouse(listhid);//查询所有的房子
		//查询所有的Uid
		List<Integer> listuid=new ArrayList<>();
		for(House h:findByListHouse){
			if(h!=null){
				maps2.put(h.getHid(), h);
				listuid.add(h.getHuid());
			}
		}		
		
		/*更据id批量查询用户*/
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listuid);
		for(User u:findUser){
			maps4.put(u.getUid(), u);			
		}
		
		
		//输出四张表
		System.out.println("----------listOrder----:"+listOrder);
		System.out.println("----------litsDetail----:"+litsDetail);
		System.out.println("----------findByListHouse----:"+findByListHouse);
		System.out.println("----------findUser----:"+findUser);
		request.getSession().setAttribute("listOrder",listOrder );
		request.getSession().setAttribute("litsDetail", litsDetail);
		request.getSession().setAttribute("findByListHouse", findByListHouse);
		request.getSession().setAttribute("findUser", findUser);
		
		//防止分页信息
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",totalRow);
		
		//用于页面控制是用户待处理订单，还是，房东要处理的订单。
		request.getSession().setAttribute("orderStatus", 0);//这种状态房客可以取消按钮，当为1是，说明用户进的是房东界面，显示受理按钮
		
		
		response.sendRedirect("manage/order.jsp");
		
		
		
		
		
		
	}

	/**
	 * 下午2:03:02
	 * 创建订单，但未付款
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void createOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		double payCost =Double.parseDouble(request.getParameter("ocost")) ;//总金额
		int ouid= Integer.parseInt(request.getParameter("ouid"));//客户ID
		String ouname=request.getParameter("ouname");//客户名字
		
		Integer opayType=Integer.parseInt(request.getParameter("payType"));//支付类型
		Date date=new Date();
		Order order=new Order();
		order.setOpayType(opayType);
		order.setOcreateTime(date);
		order.setOstatus(0);
		order.setOuid(ouid);
		order.setOuname(ouname);
		order.setOcost(payCost);
		
		OrderDao orderDao=new OrderDaoImpl();
		int count = orderDao.add(order);
		System.out.println("----以下订单："+order+",<---->,"+count);	
		
		
		OrderDetail detail=new OrderDetail();
		int hid=Integer.parseInt(request.getParameter("odhid"));
		
		//修改房屋状态为4，表示已订。
		HouseDao houseDao=new HouseDaoImpl();
		House house = houseDao.findById(hid);
		house.setHstatus(4);
		house.setHid(hid);
		//System.out.println("house:---->>>>>"+house);
		houseDao.updateHouse(house);
		
		
		double priceDay=Double.parseDouble(request.getParameter("price"));
		int day=Integer.parseInt(request.getParameter("odday"));
		if(count>0){
			order=orderDao.findCondition(ouid, date);//查询新增加的OrderID
			detail.setOdoid(order.getOid());detail.setOdcost(payCost);
			detail.setOdday(day);detail.setOdhid(hid);
			detail.setOdprice(priceDay);
			
			/*修改首页显示房屋数量减一*/
			HouseR houser=new HouseR();
			houser.indexComtomer(request);			
			
			OrderDetailDao orderDetailDao=new OrderDetailImpl();
			int add = orderDetailDao.add(detail);
			response.sendRedirect("manage/cms.jsp");
		}
		
		
		
		
	}

}
