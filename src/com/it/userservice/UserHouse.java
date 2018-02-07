package com.it.userservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.it.entity.ResultModel;
import com.it.entity.User;
import com.it.util.UtilUpLoad;
/**
 * 
 * @author Administrator 2017��8��17�� ����12:00:42
 *
 */
public class UserHouse extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type2");
		if(type==null){
			String str=String.valueOf(request.getSession().getAttribute("type2"));			
			if(str!=null){
				System.out.println(str);
				type=str;
			}
			
		}		
		if(type!=null&&type.equals("userhouselist")){//�û��鿴�Լ����еķ��ݣ�
			this.userHouseList(request,response);
		}else if(type!=null&&type.equals("userhouseorder")){//�����鿴�Լ�����Ķ���
			
		}else if(type!=null&&type.equals("status1")){//�����ύ���룬�ȴ�����Ա���
			this.status1(request,response);			
		}else if(type!=null&&type.equals("shanchuHouse")){//�û�ɾ������
			this.deleteHouse(request,response);
		}else if(type!=null&&type.equals("chexiaoHouse")){	//�����ϼܣ��¼�		
			this.repeal(request,response);
		}else if(type!=null&&type.equals("orderList")){
			this.adminUserList(request,response);			
		}else{//�޸ķ���
			this.updateHouseUser(request,response);	
		}
		
		
	}
	/**
	 * 2017��8��17�� ����00:00:22
	 *	�����鿴���ʹ�ס���Ķ���
	 *	�߼���house��----->order_detail��------>order��
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void adminUserList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Integer uid=Integer.parseInt(request.getParameter("userId"));
		String strPage =request.getParameter("pageCurrent");
		
		System.out.println("-----�����鿴���ʹ�ס���Ķ���uid��"+uid);
		
		
		
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
		
		/*���ݷ���Id������ѯ����*/
		HouseDao houseDao=new HouseDaoImpl();
		List<Integer> listuid=new ArrayList<Integer>();
		listuid.add(uid);
		List<House> findByListHouse = houseDao.findByListHouse1(listuid);//��ѯ���еķ���		
		//��ѯ���е�Hid
		List<Integer> listhid=new ArrayList<>();
		for(House h:findByListHouse){
			listhid.add(h.getHid());
		}
		System.out.println("--------findByListHouse:"+findByListHouse.size());
		
		
		//������ѯOrderDetail
		OrderDetailDao orderDetailDao=new OrderDetailImpl();
		List<OrderDetail> litsDetail = orderDetailDao.litsDetail2(listhid);//��ѯ���е�OrderDetail���
		//����ȡ����Id
		List<Integer> listoid=new ArrayList<>();
		System.out.println("-----litsDetail:"+litsDetail+"-----"+litsDetail.size());		
		if(litsDetail!=null){			
			for(int o=0;o<litsDetail.size();o++){
				if(litsDetail.get(o)!=null){
					System.out.println(litsDetail.get(o).getOdoid());
					listoid.add(litsDetail.get(o).getOdoid());					
				}
			}
		}
		//��Tb_order��,��ʾ���Լ����еĴ����Ķ���
		System.out.println("---------listoid:"+listoid);
		List<Order> listOrder = orderDao.findByUid1(listoid, 0);//����������״̬Ϊ0
		for(int i=0;i<listOrder.size();i++){
			if(listOrder.get(i)==null)listOrder.remove(i);
		}
		
		
		
				
		
		//��ӵ�ǰ�ķ�����������
		UserDao userDao=new UserDaoImpl();
		List<User> findUser =new ArrayList<User>() ;
		findUser.add(userDao.findById(uid));
		
		
		//������ű�
		System.out.println("----------listOrder11----:"+listOrder);
		System.out.println("----------litsDetail11----:"+litsDetail);
		System.out.println("----------findByListHouse11----:"+findByListHouse);
		System.out.println("----------findUser11----:"+findUser);
		request.getSession().setAttribute("listOrder",listOrder );
		request.getSession().setAttribute("litsDetail", litsDetail);
		request.getSession().setAttribute("findByListHouse", findByListHouse);
		request.getSession().setAttribute("findUser", findUser);
		
		//��ֹ��ҳ��Ϣ
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",totalRow);
		
		//����ҳ��������û��������������ǣ�����Ҫ����Ķ�����
		request.getSession().setAttribute("orderStatus", 1);//����״̬���Ϳ���ȡ����ť����Ϊ1�ǣ�˵���û������Ƿ������棬��ʾ����ť
		
		
		response.sendRedirect("order.jsp");
		
		
		
	}

	/**
	 * ����7:46:27
	 * �������ݣ��¼ܡ�����Ѿ����û��������ʹ�á�
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void repeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer hid=Integer.parseInt(request.getParameter("houseId").toString());
		List<House> list=(List<House>) request.getSession().getAttribute("userhouseList");
		House house=new House();
		for(House h:list){
			if(h.getHid()==hid){
				house=h;
			}
		}
		house.setHstatus(-1);
		HouseDao houseDao=new HouseDaoImpl();
		int updateHouse = houseDao.updateHouse(house);
		request.getSession().setAttribute("type2", "userhouselist");
		response.sendRedirect("userHouse");
		
		
	}

	/**
	 * ����7:43:06
	 * ɾ������
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void deleteHouse(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer hid=Integer.parseInt(request.getParameter("houseId").toString());
		HouseDao houseDao=new HouseDaoImpl();
		int deleteHouse = houseDao.deleteHouse(hid);
		System.out.println("deleteHouse"+deleteHouse);
		if(deleteHouse==404){
			request.getSession().setAttribute("Msg", "ɾ���û�ʧ�ܣ�������Ƿ��пͻ���ס��....");
		}else if(deleteHouse==1){
			request.getSession().setAttribute("Msg", "ɾ���û��ɹ���");
		}
		
		request.getSession().setAttribute("type2", "userhouselist");
		response.sendRedirect("userHouse");
		
	}

	private void updateHouseUser(HttpServletRequest request,
			HttpServletResponse response)throws IOException {
		String[] arr={"hid","huid","huname","hinvoice","hleaseTpe","htype","area","hlive","hbed","hbedRoom","hbedForm","htoilet","hcheckIn","hcheckOut","hminDay","hmaxDay","hrefundDay","hrentPrice","hdescribe","huseRule","hservice","haddress","hpayRule","hpicture1","hpicture2","hpicture3"};
		House house =(House) UtilUpLoad.upLoad(request, response, House.class,arr );
		HouseDao houseDao=new HouseDaoImpl();
		int updateHouse = houseDao.updateHouse(house);		
		request.getSession().setAttribute("type2", "userhouselist");
		response.sendRedirect("userHouse");
		
		
	}

	/**
	 * ����5:48:54
	 * �ύ�������Ա����
	 * @param request
	 * @param response
	 * @throws IOException
	 *
	 */
	private void status1(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("---status1---");
		List<House> houselist= (List<House>) request.getSession().getAttribute("userhouseList");
		Integer houseid=Integer.parseInt(request.getParameter("houseId"));
		House house=new House();
		for(House h:houselist){
			if(h.getHid()==houseid){
				house=h;				
			}			
		}
		house.setHstatus(1);
		HouseDao houseDao=new HouseDaoImpl();
		houseDao.updateHouse(house);
		request.getSession().setAttribute("type2", "userhouselist");
		response.sendRedirect("userHouse");
		
	}

	/**
	 * ����7:23:29
	 * �û��鿴�Լ����б�
	 * @param request
	 * @param response
	 * @throws IOException
	 *
	 */
	private void userHouseList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {		
		String strPage = request.getParameter("pageCurrent");
		User user=(User) request.getSession().getAttribute("user");
		HouseDao houseDao=new HouseDaoImpl();
		int totalPage=houseDao.totalPage(5);
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
		ResultModel rm = houseDao.pageListHouse(null,null,null,null,null,"house_uid:"+user.getUid(),pac,5);
		//��ѯ����������
		List<Integer> listFangDong=new ArrayList<Integer>();
		for(House h:rm.getHouseList()){
			listFangDong.add(h.getHuid());
		}
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listFangDong);
		/*System.out.println("0----fangDong"+findUser);*/
		//
				
				
		request.getSession().setAttribute("Msg", request.getAttribute("Msg"));		
		request.getSession().setAttribute("userhouseList", rm.getHouseList());
				
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",rm.getRecordCount());
		System.out.println("-----listHouse:"+rm.getHouseList());
		request.getSession().removeAttribute("type2");
		response.sendRedirect("house.jsp");
		
		
	}
	
}
