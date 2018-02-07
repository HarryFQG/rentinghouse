package com.it.adminservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.HouseDao;
import com.it.dao.HouseDaoImpl;
import com.it.dao.UserDao;
import com.it.dao.UserDaoImpl;
import com.it.entity.House;
import com.it.entity.ResultModel;
import com.it.entity.User;


public class HouseR extends HttpServlet{

	//��Ϊ���ݻ��棬��houseID��ΪKey,house��Ϊ����
	static Map<Integer ,House> maps=new HashMap<>();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type=request.getParameter("type1");
		if(type==null){			
			String str=String.valueOf(request.getSession().getAttribute("type1"));			
			if(str!=null){
				System.out.println(str);
				type=str;
			}
		}
		
		if(type!=null&&type.equals("listHouse")){
			
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.listHouse(request,response);//��ҳ��Ĳ������в�ѯ
		}else if(type!=null&&type.equals("listHouseAdmin")){//�Է��ݵ����
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.listHouseAdmin(request,response);			
		}else if(type!=null&&type.equals("deleteHouseAdmin")){//��˲�ͨ��
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.deleteHouseAdmin(request,response);
		}else if(type!=null&&type.equals("fabuHouseAdmin")){//ͨ����˷����Ķ���
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.updateHouseAdmin(request,response);
		}else if(type!=null&&type.equals("readlyHouseAdmin")){//�鿴����Ա�Ѿ���˵Ķ���
			
			this.readlyHouseAdmin(request,response);
		}else if(type!=null&&type.equals("detailHouse")){//���ݷ��ݵ�idȥȡ�����ݵ���ϸ��Ϣ
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.detailHouse(request,response);			
		}else{
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.listHouse(request,response);//������ҳ������������ѯ
		}
		
		
		
	}
	/**
	 * ����3:08:05
	 * ���ݷ��ݵ�Id�ҷ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 *
	 */
	private void detailHouse(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		Integer hid=Integer.parseInt(request.getParameter("houseid"));
		Set<Integer> set = maps.keySet();
		House house=new House();
		
		if(maps.containsKey(hid)){
			
			house=maps.get(hid);
		}else{
			HouseDao houseDao=new HouseDaoImpl();
			house= houseDao.findById(hid);house.setHid(hid);
		}
		System.out.println("-----house:"+house);
		//�鷿��ID
		Integer fangDongId=house.getHuid();
		UserDao userDao=new UserDaoImpl();
		User fangDong=userDao.findById(fangDongId);
		
		request.getSession().setAttribute("fangDong", fangDong);
		request.getSession().setAttribute("houseDetail", house);
		request.getRequestDispatcher("details.jsp").forward(request, response);
		/*response.sendRedirect("details.jsp");*/
		
		
	}
	/**
	 * ����1:02:24
	 * �鿴����Ա�Ѿ���˵Ķ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void readlyHouseAdmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String strPage = request.getParameter("pageCurrent");		
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
		ResultModel rm = houseDao.pageListHouse(null,null,null,null,null,"house_status:[2 TO 3]",pac,5);
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
		request.getSession().setAttribute("houseList1", rm.getHouseList());
		request.getSession().setAttribute("listFangDong",findUser);
		request.getSession().setAttribute("adminReadly", "adminReadly");//��ҳ����޸�ͨ���벻ͨ�����п���
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",rm.getRecordCount());
		
		//����ҳ��ķ�ҳ��ʾ
		request.getSession().setAttribute("readliHouseAdmin", "readliHouseAdmin");
		System.out.println("-----listHouse:"+rm.getCurPage()+","+rm.getPageCount()+","+rm.getRecordCount()+","+rm.getHouseList().size()+",");
		request.getSession().removeAttribute("type1");
		response.sendRedirect("manage/adminHouse.jsp");
		
		
		
	}

	/**
	 * ����10:13:27
	 * ����Ա���ͨ������
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateHouseAdmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*Integer hid=Integer.parseInt(request.getParameter("id"));
		List<House> listH=(List<House>) request.getSession().getAttribute("houseList");
		House house=null;
		for(House h:listH){
			if(h.getHid()==hid){
				house=h;
			}
		}
		if(house!=null){
			house.setHstatus(2);
		}		
		HouseDao houseDao=new HouseDaoImpl();*/
		Integer hid=Integer.parseInt(request.getParameter("id"));
		List<House> listH=(List<House>) request.getSession().getAttribute("houseList");
		HouseDao houseDao=new HouseDaoImpl();
		House house=null;
		if(listH!=null&&listH.contains(hid)){
			for(House h:listH){
				if(h.getHid()==hid){
					house=h;
				}
			}
		}else{
			if(maps.containsKey(hid)){
				house=maps.get(hid);
			}else{
				house=houseDao.findById(hid);
				if(!maps.containsKey(house.getHid())){
					maps.put(house.getHid(), house);
				}
			}
		}		
		if(house!=null){
			house.setHid(hid);
			house.setHstatus(2);			
		}	
		System.out.println("��˷���house��ͨ��:"+house);
		
		houseDao.updateHouse(house);
		request.getSession().setAttribute("type1", "listHouseAdmin");
		
		
		//������ҳ��ʾ�ķ�������+1
		/*�޸���ҳ��ʾ����������һ*/
		HouseR houser=new HouseR();
		houser.indexComtomer(request);
		
		response.sendRedirect("houseR");
		
		
	}
	/**
	 * ����10:13:43
	 * �û���������Ա��ͨ��
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 *
	 */
	private void deleteHouseAdmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		Integer hid=Integer.parseInt(request.getParameter("id"));
		List<House> listH=(List<House>) request.getSession().getAttribute("houseList");
		HouseDao houseDao=new HouseDaoImpl();
		House house=null;
		if(listH!=null&&listH.contains(hid)){
			for(House h:listH){
				if(h.getHid()==hid){
					house=h;
				}
			}
		}else{
			if(maps.containsKey(hid)){
				house=maps.get(hid);
			}else{
				house=houseDao.findById(hid);
				if(!maps.containsKey(house.getHid())){
					maps.put(house.getHid(), house);
				}
			}
		}		
		if(house!=null){
			house.setHid(hid);
			house.setHstatus(3);			
		}	
		System.out.println("��˷���house��ͨ��:"+house);
		
		int count = houseDao.updateHouse(house);		
		request.getSession().setAttribute("type1", "listHouseAdmin");
		response.sendRedirect("houseR");
	}

	/**
	 * ����9:24:17
	 * ����Ա�Է��ݽ������
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void listHouseAdmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String strPage = request.getParameter("pageCurrent1");		
		HouseDao houseDao=new HouseDaoImpl();
		int totalPage=houseDao.totalPage(5);
		System.out.println("strPage:"+strPage+","+totalPage);
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
		ResultModel rm = houseDao.pageListHouse(null,null,null,null,null,"1",pac,5);
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
		request.getSession().setAttribute("houseList1", rm.getHouseList());
		request.getSession().setAttribute("listFangDong",findUser);
				
		request.getSession().setAttribute("pageCurrent1",pac);
		request.getSession().setAttribute("totalPage1",totalPage);
		request.getSession().setAttribute("totalRow1",rm.getRecordCount());
		System.out.println("11-----listHouse:"+rm.getCurPage()+","+rm.getPageCount()+","+rm.getRecordCount()+","+rm.getHouseList().size()+",");
		request.getSession().removeAttribute("type1");
		response.sendRedirect("manage/adminHouse.jsp");
		
		
		
	}

	/**
	 * ����9:24:03
	 * �ο�Ԥ��
	 * @param request
	 * @param response
	 * @throws IOException
	 *
	 */
	private void listHouse(HttpServletRequest request,
			HttpServletResponse response) throws IOException {		
		ResultModel rm = indexComtomer(request);
				
		System.out.println("-----listHouse:"+rm.getHouseList());
		response.sendRedirect("index.jsp");
	}
	
	public ResultModel indexComtomer(HttpServletRequest request) {
		
		String strPage = request.getParameter("pageCurrent");
		
		//�οͶԷ������ݽ�����ɸѡ
		String  houseName=request.getParameter("houseName");
		if(houseName!=null&&houseName.equals("")){
			houseName=null;
		}
		String price=request.getParameter("price");//���ݼ۸�		
		String street=request.getParameter("street");//����λ��
		if(street!=null&&street.equals("")){
			street=null;
		}
		String type_id=request.getParameter("type_id");//��������:0�ǲ��ޣ��Ƶ꣬����
				
		
		
		String area=request.getParameter("area");//�û����
		//����ҳ�����
		ResultModel backLook=new ResultModel();
		backLook.setQueryStr(houseName);
		backLook.setPrice(price);
		backLook.setHouseType(type_id);
		backLook.setStreet(street);
		backLook.setArea(area);
		System.out.println("ҳ����ԣ�----"+backLook);
		
		
		
		
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
		System.out.println(houseName+"---"+price+"---"+area+"---"+"----"+type_id+"---"+area+"---"+"---"+"2"+"--"+pac+"--"+5);
		ResultModel rm = houseDao.pageListHouse(houseName,price,area,type_id,area,"2",pac,5);		
		System.err.println("-----rm:--->"+rm.getHouseList());
		//��ѯ����������
		List<Integer> listFangDong=new ArrayList<Integer>();
		for(House h:rm.getHouseList()){
			listFangDong.add(h.getHuid());
		}
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listFangDong);
		System.out.println("0----fangDong"+findUser);
		
		/*�Ż�����*/
		for(House h:rm.getHouseList()){
			maps.put(h.getHid(),h);
		}
		
		
		request.getSession().setAttribute("Msg", request.getAttribute("Msg"));		
		request.getSession().setAttribute("houseList", rm.getHouseList());
		request.getSession().setAttribute("listFangDong",findUser);
		
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",rm.getRecordCount());
	/*	
		if(rm.getHouseList()==null||rm.getHouseList().size()==0){//�����ѯ�ļ�¼Ϊ�գ����Ȱ�������Ϊ�գ��ٴβ�ѯ��
			backLook.setArea(null);backLook.setQueryStr(null);backLook.setPrice(null);backLook.setStreet(null);backLook.setHouseType(null);
			//ҳ�����
			request.getSession().setAttribute("lookRm",backLook );	
			response.sendRedirect("houseR");
			return;
		}*/
		//ҳ�����
		request.getSession().setAttribute("lookRm",backLook );
		return rm;
	}

	/**
	 * ����9:23:50
	 * �οͶԷ��ݵĲ�ѯ
	 * @param request
	 * @param response
	 *
	 */
	private void listHouseQ(HttpServletRequest request,
			HttpServletResponse response) {
		
		/*houseDao.pageListHouse(houseName, price, area, type_id, null, null, currentPage, pageSize)
		*/
	}
	
	
	
	
	
	
	
	
	
}
