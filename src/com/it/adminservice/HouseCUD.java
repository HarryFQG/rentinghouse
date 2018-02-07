package com.it.adminservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.HouseDao;
import com.it.dao.HouseDaoImpl;
import com.it.entity.House;
import com.it.util.UtilUpLoad;
/**
 * 增删改
 *
 */
public class HouseCUD extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type");
		if(type!=null&&type.equals("deleteHouse")){
			this.deleteHouse(request, response);
		}else if(type!=null&&type.equals("updateHouse")){
			this.updateHouse(request, response);		
		}else{//添加房屋
			this.addHouse(request, response);
		}
	}

	private void deleteHouse(HttpServletRequest request,
			HttpServletResponse response) {
		
		
	}

	private void updateHouse(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addHouse(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] arr={"huid","huname","hinvoice","hleaseTpe","htype","area","hlive","hbed","hbedRoom","hbedForm","htoilet","hcheckIn","hcheckOut","hminDay","hmaxDay","hrefundDay","hrentPrice","hdescribe","huseRule","hservice","haddress","hpayRule","hpicture1","hpicture2","hpicture3"};
		House house =(House) UtilUpLoad.upLoad(request, response, House.class,arr );
		HouseDao houseDao=new HouseDaoImpl();
		houseDao.addHouse(house);
		response.sendRedirect("manage/cms.jsp");
		
	}

}
