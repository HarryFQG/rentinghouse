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
 * @author Administrator 2017��8��17�� ����1:57:26
 *	��ҳ��ť�Ĵ���
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
		
		
		
		if(type!=null&&type.equals("creatingOrder")){//��������
			this.createOrder(request,response);
		}else if(type!=null&&type.equals("quxiao")){//���͵����ȡ���ö�����״ֵ̬Ϊ-1
			this.back(request,response);
			
		}else if(type!=null&&type.equals("waitOrder")){//���Ͳ鿴������Ķ���
			
			this.waitOrder(request,response);			
		}else if(type!=null&&type.equals("chuli")){//����������
			
			this.dealOrder(request,response);
		}else if(type!=null&&type.equals("alreadyDeal")){//���Ͳ鿴�Ѿ������Ѿ������˵Ķ���,Ҳ���Ƕ���״̬(order_type)Ϊ1�Ķ���
			
			this.alreadyDeal(request,response);
		}else if(type!=null&&type.equals("alreadyBack")){//���Ͳ鿴�Ѿ��Լ�ȡ���˵Ķ���,Ҳ���Ƕ���״̬(order_type)Ϊ-1�Ķ���
			
			this.alreadyBack(request,response);
		}
		
	}
	
	
	/**���Ͳ鿴�Ѿ��Լ�ȡ���˵Ķ���,Ҳ���Ƕ���״̬(order_type)Ϊ-1�Ķ���			
	 * 2017��8��18������4:06:45 
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
		int totalRow=orderDao.totalCount(uid, 0);//��ȡ��¼��
		int totalPage=orderDao.totalPage(uid,0,5);//�û�Id,״̬����ҳ����
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
		//�Ȳ�Tb_order��,��ʾ���Լ����еĴ����Ķ���
				
		List<Order> listOrder = orderDao.findByUid(uid,-1);//ȡ��������״̬Ϊ-1
		List<Integer> listoid=new ArrayList<>();//������ѯ��ǰ�û������д���������oid
		for(Order o:listOrder){
			maps1.put(o.getOid(), o);
			listoid.add(o.getOid());
		}
		
		//������ѯOrderDetail
		OrderDetailDao orderDetailDao=new OrderDetailImpl();
		List<OrderDetail> litsDetail = orderDetailDao.litsDetail(listoid);//��ѯ���е�OrderDetail���
		//ȡ�����ķ���Id
		List<Integer> listhid=new ArrayList<>();
		for(OrderDetail o:litsDetail){
			if(o!=null){
				maps3.put(o.getOdid(), o);
				listhid.add(o.getOdhid());
			}
		}
		
		/*����Id������ѯ����*/
		HouseDao houseDao=new HouseDaoImpl();
		List<House> findByListHouse = houseDao.findByListHouse(listhid);//��ѯ���еķ���
		//��ѯ���е�Uid
		List<Integer> listuid=new ArrayList<>();
		for(House h:findByListHouse){
			if(h!=null){
				maps2.put(h.getHid(), h);
				listuid.add(h.getHuid());
			}
		}		
		
		/*����id������ѯ�û�*/
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listuid);
		for(User u:findUser){
			maps4.put(u.getUid(), u);			
		}
		
		
		//������ű�
		System.out.println("----------listOrder----:"+listOrder);
		System.out.println("----------litsDetail----:"+litsDetail);
		System.out.println("----------findByListHouse----:"+findByListHouse);
		System.out.println("----------findUser----:"+findUser);
		request.getSession().setAttribute("listOrder",listOrder );
		request.getSession().setAttribute("litsDetail", litsDetail);
		request.getSession().setAttribute("findByListHouse", findByListHouse);
		request.getSession().setAttribute("findUser", findUser);
		
		//��ֹ��ҳ��Ϣ
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",totalRow);
		
		//����ҳ��������û��������������ǣ�����Ҫ����Ķ�����
		request.getSession().setAttribute("orderStatus", 3);//����״̬���Ϳ���ȡ����ť����Ϊ1�ǣ�˵���û������Ƿ������棬��ʾ����ť
		
		
		//��ҳ��ť�Ĵ���
		
		
		response.sendRedirect("manage/order.jsp");
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	/**���Ͳ鿴�Ѿ������Ѿ������˵Ķ���,Ҳ���Ƕ���״̬(order_type)Ϊ1�Ķ���			
	 * 2017��8��18������4:07:16 
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
		int totalRow=orderDao.totalCount(uid, 0);//��ȡ��¼��
		int totalPage=orderDao.totalPage(uid,0,5);//�û�Id,״̬����ҳ����
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
		//�Ȳ�Tb_order��,��ʾ���Լ����еĴ����Ķ���
				
		List<Order> listOrder = orderDao.findByUid(uid,1);//�Ѵ�������״̬Ϊ1
		List<Integer> listoid=new ArrayList<>();//������ѯ��ǰ�û������д���������oid
		for(Order o:listOrder){
			maps1.put(o.getOid(), o);
			listoid.add(o.getOid());
		}
		
		//������ѯOrderDetail
		OrderDetailDao orderDetailDao=new OrderDetailImpl();
		List<OrderDetail> litsDetail = orderDetailDao.litsDetail(listoid);//��ѯ���е�OrderDetail���
		//ȡ�����ķ���Id
		List<Integer> listhid=new ArrayList<>();
		for(OrderDetail o:litsDetail){
			if(o!=null){
				maps3.put(o.getOdid(), o);
				listhid.add(o.getOdhid());
			}
		}
		
		/*����Id������ѯ����*/
		HouseDao houseDao=new HouseDaoImpl();
		List<House> findByListHouse = houseDao.findByListHouse(listhid);//��ѯ���еķ���
		//��ѯ���е�Uid
		List<Integer> listuid=new ArrayList<>();
		for(House h:findByListHouse){
			if(h!=null){
				maps2.put(h.getHid(), h);
				listuid.add(h.getHuid());
			}
		}		
		
		/*����id������ѯ�û�*/
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listuid);
		for(User u:findUser){
			maps4.put(u.getUid(), u);			
		}
		
		
		//������ű�
		System.out.println("----------listOrder----:"+listOrder);
		System.out.println("----------litsDetail----:"+litsDetail);
		System.out.println("----------findByListHouse----:"+findByListHouse);
		System.out.println("----------findUser----:"+findUser);
		request.getSession().setAttribute("listOrder",listOrder );
		request.getSession().setAttribute("litsDetail", litsDetail);
		request.getSession().setAttribute("findByListHouse", findByListHouse);
		request.getSession().setAttribute("findUser", findUser);
		
		//��ֹ��ҳ��Ϣ
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",totalRow);
		
		//����ҳ��������û��������������ǣ�����Ҫ����Ķ�����				//Ϊ3����������ť������ʾ
		request.getSession().setAttribute("orderStatus", 3);//����״̬���Ϳ���ȡ����ť����Ϊ1�ǣ�˵���û������Ƿ������棬��ʾ����ť
		
		
		
		
		//�����ҳ��ť�Ĵ���
		
		
		
		
		response.sendRedirect("manage/order.jsp");
		
		
		
		
		
		
		
		
		
	}
	/**
	 * ����9:40:33
	 * ���������˶�����ֵΪ1
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
		order.setOstatus(1);//order_status:�޸ĸ�Ϊ1
		orderDao.update(order);
		System.out.println("------------orderBack:"+order);
		request.getSession().setAttribute("userId", userId);
		request.getSession().setAttribute("type4", "waitOrder");
		response.sendRedirect("orderUser");
		
	}
	/**
	 * ����9:40:03
	 * ����ȡ���˶���,״ֵ̬Ϊ-1
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
		//�޸ķ���״̬Ϊ4����ʾ�Ѷ�������Ҫ��Ϊ2
		HouseDao houseDao=new HouseDaoImpl();
		House house = houseDao.findById(od.getOdhid());
		house.setHstatus(2);
		house.setHid(od.getOdhid());
		//System.out.println("house:---->>>>>"+house);
		houseDao.updateHouse(house);
		
		System.out.println("------------orderBack:"+order);
		request.getSession().setAttribute("userId", userId);
		request.getSession().setAttribute("type4", "waitOrder");
		
		
		
		/*�޸���ҳ��ʾ����������һ*/
		HouseR houser=new HouseR();
		houser.indexComtomer(request);			
		
		
		
		
		
		
		response.sendRedirect("orderUser");
		/*request.getRequestDispatcher("orderUser").forward(request, response);*/
	}
	/**
	 * ����6:48:13
	 * ���Ͳ鿴�Լ��ȴ���������Ķ���
	 * @param request
	 * @param response
	 *����������Ķ���ҳ������ʾ�����ݣ���tb_house��Tb_user��tb_order,Tb_OrderDetail����
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
		int totalRow=orderDao.totalCount(uid, 0);//��ȡ��¼��
		int totalPage=orderDao.totalPage(uid,0,5);//�û�Id,״̬����ҳ����
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
		
		
		
		
		//�Ȳ�Tb_order��,��ʾ���Լ����еĴ����Ķ���
				
		List<Order> listOrder = orderDao.findByUid(uid,0);//����������״̬Ϊ0
		List<Integer> listoid=new ArrayList<>();//������ѯ��ǰ�û������д���������oid
		for(Order o:listOrder){
			maps1.put(o.getOid(), o);
			listoid.add(o.getOid());
		}
		
		//������ѯOrderDetail
		OrderDetailDao orderDetailDao=new OrderDetailImpl();
		List<OrderDetail> litsDetail = orderDetailDao.litsDetail(listoid);//��ѯ���е�OrderDetail���
		//ȡ�����ķ���Id
		List<Integer> listhid=new ArrayList<>();
		for(OrderDetail o:litsDetail){
			if(o!=null){
				maps3.put(o.getOdid(), o);
				listhid.add(o.getOdhid());
			}
		}
		
		/*����Id������ѯ����*/
		HouseDao houseDao=new HouseDaoImpl();
		List<House> findByListHouse = houseDao.findByListHouse(listhid);//��ѯ���еķ���
		//��ѯ���е�Uid
		List<Integer> listuid=new ArrayList<>();
		for(House h:findByListHouse){
			if(h!=null){
				maps2.put(h.getHid(), h);
				listuid.add(h.getHuid());
			}
		}		
		
		/*����id������ѯ�û�*/
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listuid);
		for(User u:findUser){
			maps4.put(u.getUid(), u);			
		}
		
		
		//������ű�
		System.out.println("----------listOrder----:"+listOrder);
		System.out.println("----------litsDetail----:"+litsDetail);
		System.out.println("----------findByListHouse----:"+findByListHouse);
		System.out.println("----------findUser----:"+findUser);
		request.getSession().setAttribute("listOrder",listOrder );
		request.getSession().setAttribute("litsDetail", litsDetail);
		request.getSession().setAttribute("findByListHouse", findByListHouse);
		request.getSession().setAttribute("findUser", findUser);
		
		//��ֹ��ҳ��Ϣ
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",totalRow);
		
		//����ҳ��������û��������������ǣ�����Ҫ����Ķ�����
		request.getSession().setAttribute("orderStatus", 0);//����״̬���Ϳ���ȡ����ť����Ϊ1�ǣ�˵���û������Ƿ������棬��ʾ����ť
		
		
		response.sendRedirect("manage/order.jsp");
		
		
		
		
		
		
	}

	/**
	 * ����2:03:02
	 * ������������δ����
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void createOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		double payCost =Double.parseDouble(request.getParameter("ocost")) ;//�ܽ��
		int ouid= Integer.parseInt(request.getParameter("ouid"));//�ͻ�ID
		String ouname=request.getParameter("ouname");//�ͻ�����
		
		Integer opayType=Integer.parseInt(request.getParameter("payType"));//֧������
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
		System.out.println("----���¶�����"+order+",<---->,"+count);	
		
		
		OrderDetail detail=new OrderDetail();
		int hid=Integer.parseInt(request.getParameter("odhid"));
		
		//�޸ķ���״̬Ϊ4����ʾ�Ѷ���
		HouseDao houseDao=new HouseDaoImpl();
		House house = houseDao.findById(hid);
		house.setHstatus(4);
		house.setHid(hid);
		//System.out.println("house:---->>>>>"+house);
		houseDao.updateHouse(house);
		
		
		double priceDay=Double.parseDouble(request.getParameter("price"));
		int day=Integer.parseInt(request.getParameter("odday"));
		if(count>0){
			order=orderDao.findCondition(ouid, date);//��ѯ�����ӵ�OrderID
			detail.setOdoid(order.getOid());detail.setOdcost(payCost);
			detail.setOdday(day);detail.setOdhid(hid);
			detail.setOdprice(priceDay);
			
			/*�޸���ҳ��ʾ����������һ*/
			HouseR houser=new HouseR();
			houser.indexComtomer(request);			
			
			OrderDetailDao orderDetailDao=new OrderDetailImpl();
			int add = orderDetailDao.add(detail);
			response.sendRedirect("manage/cms.jsp");
		}
		
		
		
		
	}

}
