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

	//作为房屋缓存，以houseID作为Key,house作为对象
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
			
			this.listHouse(request,response);//对页面的参数进行查询
		}else if(type!=null&&type.equals("listHouseAdmin")){//对房屋的审核
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.listHouseAdmin(request,response);			
		}else if(type!=null&&type.equals("deleteHouseAdmin")){//审核不通过
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.deleteHouseAdmin(request,response);
		}else if(type!=null&&type.equals("fabuHouseAdmin")){//通过审核发布的订单
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.updateHouseAdmin(request,response);
		}else if(type!=null&&type.equals("readlyHouseAdmin")){//查看管理员已经审核的订单
			
			this.readlyHouseAdmin(request,response);
		}else if(type!=null&&type.equals("detailHouse")){//根据房屋的id去取，房屋的详细信息
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.detailHouse(request,response);			
		}else{
			if(request.getSession().getAttribute("readliHouseAdmin")!=null)request.getSession().removeAttribute("readliHouseAdmin");;
			
			this.listHouse(request,response);//进入首页进行无条件查询
		}
		
		
		
	}
	/**
	 * 下午3:08:05
	 * 更据房屋的Id找房屋
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
		//查房东ID
		Integer fangDongId=house.getHuid();
		UserDao userDao=new UserDaoImpl();
		User fangDong=userDao.findById(fangDongId);
		
		request.getSession().setAttribute("fangDong", fangDong);
		request.getSession().setAttribute("houseDetail", house);
		request.getRequestDispatcher("details.jsp").forward(request, response);
		/*response.sendRedirect("details.jsp");*/
		
		
	}
	/**
	 * 下午1:02:24
	 * 查看管理员已经审核的订单
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
		//查询房东的姓名
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
		request.getSession().setAttribute("adminReadly", "adminReadly");//对页面的修改通过与不通过进行控制
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",rm.getRecordCount());
		
		//控制页面的分页显示
		request.getSession().setAttribute("readliHouseAdmin", "readliHouseAdmin");
		System.out.println("-----listHouse:"+rm.getCurPage()+","+rm.getPageCount()+","+rm.getRecordCount()+","+rm.getHouseList().size()+",");
		request.getSession().removeAttribute("type1");
		response.sendRedirect("manage/adminHouse.jsp");
		
		
		
	}

	/**
	 * 上午10:13:27
	 * 管理员审核通过发布
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
		System.out.println("审核房子house不通过:"+house);
		
		houseDao.updateHouse(house);
		request.getSession().setAttribute("type1", "listHouseAdmin");
		
		
		//更新首页显示的房屋数量+1
		/*修改首页显示房屋数量加一*/
		HouseR houser=new HouseR();
		houser.indexComtomer(request);
		
		response.sendRedirect("houseR");
		
		
	}
	/**
	 * 上午10:13:43
	 * 用户发布管理员不通过
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
		System.out.println("审核房子house不通过:"+house);
		
		int count = houseDao.updateHouse(house);		
		request.getSession().setAttribute("type1", "listHouseAdmin");
		response.sendRedirect("houseR");
	}

	/**
	 * 上午9:24:17
	 * 管理员对房屋进行审核
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
		//查询房东的姓名
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
	 * 上午9:24:03
	 * 游客预览
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
		
		//游客对房屋内容进行了筛选
		String  houseName=request.getParameter("houseName");
		if(houseName!=null&&houseName.equals("")){
			houseName=null;
		}
		String price=request.getParameter("price");//房屋价格		
		String street=request.getParameter("street");//房屋位置
		if(street!=null&&street.equals("")){
			street=null;
		}
		String type_id=request.getParameter("type_id");//房屋类型:0是不限，酒店，别墅
				
		
		
		String area=request.getParameter("area");//用户面积
		//用于页面回显
		ResultModel backLook=new ResultModel();
		backLook.setQueryStr(houseName);
		backLook.setPrice(price);
		backLook.setHouseType(type_id);
		backLook.setStreet(street);
		backLook.setArea(area);
		System.out.println("页面回显：----"+backLook);
		
		
		
		
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
		//查询房东的姓名
		List<Integer> listFangDong=new ArrayList<Integer>();
		for(House h:rm.getHouseList()){
			listFangDong.add(h.getHuid());
		}
		UserDao userDao=new UserDaoImpl();
		List<User> findUser = userDao.findUser(listFangDong);
		System.out.println("0----fangDong"+findUser);
		
		/*优化缓存*/
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
		if(rm.getHouseList()==null||rm.getHouseList().size()==0){//如果查询的记录为空，则先把条件致为空，再次查询。
			backLook.setArea(null);backLook.setQueryStr(null);backLook.setPrice(null);backLook.setStreet(null);backLook.setHouseType(null);
			//页面回显
			request.getSession().setAttribute("lookRm",backLook );	
			response.sendRedirect("houseR");
			return;
		}*/
		//页面回显
		request.getSession().setAttribute("lookRm",backLook );
		return rm;
	}

	/**
	 * 上午9:23:50
	 * 游客对房屋的查询
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
