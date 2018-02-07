package com.it.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.it.entity.House;
import com.it.entity.ResultModel;
import com.it.util.DbUtilPool;
import com.it.util.SolrUtil;

public class HouseDaoImpl implements HouseDao {
	
	QueryRunner query=null;
	public HouseDaoImpl(){
		try {
			query=new QueryRunner(DbUtilPool.getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建HouseDaoImpl失败。");
		}
	}
	
	
	@Override
	public House findById(int id) {
		String sql="select house_uid as huid ,house_name as huname,house_invoice as hinvoice,house_lease_type as hleaseTpe,house_house_type as htype,house_area as area,house_live as hlive,house_bed as hbed,house_bedroom as hbedRoom,house_bed_form as hbedForm,"
				+"house_toilet as htoilet,house_check_in as hcheckIn,house_check_out as hcheckOut,house_minday as hminDay,house_maxday as hmaxDay,house_refund_day as hrefunDay ,house_rent_price as hrentPrice,house_describe as hdescribe,house_use_rule as huseRule,house_service as hservice,house_address as haddress,house_pay_rule as hpayRule,house_picture1 as hpicture1,house_picture2 as hpicture2,house_picture3 as hpicture3,house_status as hstatus from tb_house where house_id=?";
		try {
			return query.query(sql, new BeanHandler<House>(House.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更据Id差房子失败！");
		}
		
		
	}
	
	
	@Override
	public int updateHouse(House house) {
		String sql="update tb_house set house_uid=?, house_name=?,house_invoice=?,house_lease_type=?,house_house_type=?,house_area=?,house_live=?,house_bed=?,house_bedroom=?,house_bed_form=?,"
				+"house_toilet=?,house_check_in=?,house_check_out=?,house_minday=?,house_maxday=?,house_refund_day=?,house_rent_price=?,house_describe=?,house_use_rule=?,house_service=?,house_address=?,house_pay_rule=?,house_picture1=?,house_picture2=?,house_picture3=?,house_status=? where house_id=?";
		
		try {
			//手动solr与数据同步
			HttpSolrServer server=SolrUtil.getServer();
			SolrInputDocument doc=new SolrInputDocument();
			doc.addField("id", house.getHid());
			doc.addField("house_uid",house.getHuid() );doc.addField("house_name",house.getHuname() );doc.addField("house_invoice",house.getHinvoice() );doc.addField("house_lease_type",house.getHleaseTpe() );
			doc.addField("house_house_type",house.getHtype() );doc.addField("house_area",house.getArea() );doc.addField("house_live",house.getHlive() );doc.addField("house_bed",house.getHbed());
			doc.addField("house_bedroom",house.getHbedRoom() );doc.addField("house_bed_form",house.getHbedForm() );doc.addField("house_toilet",house.getHtoilet() );doc.addField("house_check_in",house.getHcheckIn() );
			doc.addField("house_check_out", house.getHcheckOut());doc.addField("house_minday",house.getHminDay() );doc.addField("house_maxday",house.getHmaxDay() );doc.addField("house_refund_day",house.getHrefundDay() );
			doc.addField("house_rent_price", house.getHrentPrice());doc.addField("house_describe",house.getHdescribe() );doc.addField("house_use_rule",house.getHuseRule() );doc.addField("house_service",house.getHservice() );
			doc.addField("house_address",house.getHaddress() );doc.addField("house_pay_rule",house.getHpayRule() );doc.addField("house_picture1",house.getHpicture1() );doc.addField("house_picture2",house.getHpicture2() );
			doc.addField("house_picture3",house.getHpicture3() );doc.addField("house_status",house.getHstatus() );			
			server.add(doc);
			server.commit();
			
			return	query.update(sql,house.getHuid(), house.getHuname(),house.getHinvoice(),house.getHleaseTpe(),house.getHtype(),house.getArea(),house.getHlive(),house.getHbed(),house.getHbedRoom(),house.getHbedForm(),
					house.getHtoilet(),house.getHcheckIn(),house.getHcheckOut(),house.getHminDay(),house.getHmaxDay(),house.getHrefundDay(),house.getHrentPrice(),house.getHdescribe(),house.getHuseRule(),house.getHservice(),house.getHaddress(),house.getHpayRule(),house.getHpicture1(),house.getHpicture2(),house.getHpicture3(),house.getHstatus(),house.getHid());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("admin的审核状态修改失败！");
		}
	}

	@Override
	public int deleteHouse(int id) {
		String sql="delete tb_house where house_id=?";
		
		
		try {
			HttpSolrServer server=SolrUtil.getServer();
			server.deleteById(id+"");
			server.commit();
			
			return query.update(sql, id);
		} catch (Exception e) {
			return 404;
		}
	}
	
	@Override
	public int addHouse(House house) {
		String sql="insert into tb_house (house_uid,house_name,house_invoice,house_lease_type,house_house_type,house_area,house_live,house_bed,house_bedroom,house_bed_form,"
				+"house_toilet,house_check_in,house_check_out,house_minday,house_maxday,house_refund_day,house_rent_price,house_describe,house_use_rule,house_service,house_address,house_pay_rule,house_picture1,house_picture2,house_picture3,house_status"+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			
			HttpSolrServer server=SolrUtil.getServer();
			SolrInputDocument doc=new SolrInputDocument();
			doc.addField("id", this.findMaxHouseId()+1);
			doc.addField("house_uid",house.getHuid() );doc.addField("house_name",house.getHuname() );doc.addField("house_invoice",house.getHinvoice() );doc.addField("house_lease_type",house.getHleaseTpe() );
			doc.addField("house_house_type",house.getHtype() );doc.addField("house_area",house.getArea() );doc.addField("house_live",house.getHlive() );doc.addField("house_bed",house.getHbed());
			doc.addField("house_bedroom",house.getHbedRoom() );doc.addField("house_bed_form",house.getHbedForm() );doc.addField("house_toilet",house.getHtoilet() );doc.addField("house_check_in",house.getHcheckIn() );
			doc.addField("house_check_out", house.getHcheckOut());doc.addField("house_minday",house.getHminDay() );doc.addField("house_maxday",house.getHmaxDay() );doc.addField("house_refund_day",house.getHrefundDay() );
			doc.addField("house_rent_price", house.getHrentPrice());doc.addField("house_describe",house.getHdescribe() );doc.addField("house_use_rule",house.getHuseRule() );doc.addField("house_service",house.getHservice() );
			doc.addField("house_address",house.getHaddress() );doc.addField("house_pay_rule",house.getHpayRule() );doc.addField("house_picture1",house.getHpicture1() );doc.addField("house_picture2",house.getHpicture2() );
			doc.addField("house_picture3",house.getHpicture3() );doc.addField("house_status",house.getHstatus() );			
			server.add(doc);
			server.commit();
			/*System.out.println("-------:findMaxHouseId:"+(this.findMaxHouseId()+1)+house);*/
			return 	query.update(sql,house.getHuid(),house.getHuname(),house.getHinvoice(),house.getHleaseTpe(),house.getHtype(),house.getArea(),house.getHlive(),house.getHbed(),house.getHbedRoom(),house.getHbedForm(),
					house.getHtoilet(),house.getHcheckIn(),house.getHcheckOut(),house.getHminDay(),house.getHmaxDay(),house.getHrefundDay(),house.getHrentPrice(),house.getHdescribe(),house.getHuseRule(),house.getHservice(),house.getHaddress(),house.getHpayRule(),house.getHpicture1(),house.getHpicture2(),house.getHpicture3(),house.getHstatus()
					
					);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("房屋添加失败！");
		}
		
	}

	@Override
	public int findAllHouse() {
		
		return 0;
	}

	@Override
	public ResultModel pageListHouse(String querystr,String price,String area,String houseType,String position,String status,int currentPage,int pageSize) {

		ResultModel rm=new ResultModel();
		List<House> list=new ArrayList<House>();
		House house=null;
		HttpSolrServer server=SolrUtil.getServer();
		//创建SolrQuery对象
		SolrQuery query=new SolrQuery();		
		query.setStart((currentPage-1)*pageSize);
		query.setRows(pageSize);
		//设置查询语句
		if(StringUtils.isNotEmpty(querystr)){
			query.setQuery("house_keywords:"+querystr);
		}else{
			query.setQuery("*:*");
		}

		if(StringUtils.isNotEmpty(status)){
			if(status.length()==1){
				System.out.println("status的长度等于1");
				query.setFilterQueries("house_status:"+status);
			}else{
				System.out.println("status的长度大于1");
				query.setFilterQueries(status);
			}
		}else{
			query.setFilterQueries("house_status:2");
		}
		/*if(StringUtils.isNotEmpty(status)&&status.equals("1")){
			query.setFilterQueries("house_status:1");			
		}else if(StringUtils.isNotEmpty(status)&&status.equals("2")){
			query.setFilterQueries("house_status:2");
		}*/
		
		//对价格进行过滤
		if(StringUtils.isNotEmpty(price)){
			String[] ss = price.split("-");
			if(ss.length==2)
			query.addFilterQuery("house_rent_price:["+ss[0]+" TO "+ss[1]+"]");
		}
		//对住房面积进行过滤
		if(StringUtils.isNotEmpty(area)){
			String[] ss = area.split("-");
			if(ss.length==2)
			query.addFilterQuery("house_area:["+ss[0]+" TO "+ss[1]+"]");
		}
		//住房类型houseType
		if(StringUtils.isNotEmpty(houseType)&&!houseType.equals("0")){			
			query.addFilterQuery("house_house_type:"+houseType);
		}
		//对房屋位置进行过滤
		/*if(StringUtils.isNotEmpty(position)){			
			query.addFilterQuery("house_address:"+position);
		}
		*/
		
		
		try {
			//设置高亮度
			query.setHighlight(true);
			query.addHighlightField("house_name");
			query.setHighlightSimplePre("<font style=\"color:red\">");
			query.setHighlightSimplePost("</font>");
			
			//执行查询：
			QueryResponse response=server.query(query);
			
			//得到结果集
			SolrDocumentList rs = response.getResults();
			//获得记录总数
			long lcount = rs.getNumFound();
			
			String s=lcount+"";
			double d=pageSize*1.0;
			rm.setRecordCount(lcount);//设置总条数
			rm.setPageCount((int)(Math.ceil(Integer.parseInt(s)/d)));//设置总页数
					
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			
			for(SolrDocument doc:rs){
				
				house=new House();
				house.setHid(Integer.parseInt(String.valueOf(doc.get("id"))));
				house.setHuid(Integer.parseInt(String.valueOf(doc.get("house_uid"))));
				
				List<String> list2 = highlighting.get(doc.get("id")).get("house_name");
				if(list2!=null){
					house.setHuname(list2.get(0));
				}else{
					house.setHuname(String.valueOf(doc.get("house_name")));									
				}
				
				
				
				house.setHinvoice(Integer.parseInt(String.valueOf(doc.get("house_invoice"))));
				house.setHleaseTpe(Integer.parseInt(String.valueOf(doc.get("house_lease_type"))));
				house.setHtype(Integer.parseInt(String.valueOf(doc.get("house_house_type"))));
				house.setArea(Double.parseDouble(String.valueOf( doc.get("house_area"))));
				house.setHlive(Integer.parseInt(String.valueOf(doc.get("house_live"))));
				house.setHbed(Integer.parseInt(String.valueOf(doc.get("house_bed"))));
				house.setHbedRoom(Integer.parseInt(String.valueOf(doc.get("house_bedroom"))));
				house.setHbedForm(Integer.parseInt(String.valueOf(doc.get("house_bed_form"))));
				house.setHtoilet(Integer.parseInt(String.valueOf(doc.get("house_toilet"))));
				house.setHcheckIn((Date)doc.get("house_check_in"));
				house.setHcheckOut((Date)doc.get("house_check_out"));
				house.setHminDay(Integer.parseInt(String.valueOf(doc.get("house_minday"))));
				house.setHmaxDay(Integer.parseInt(String.valueOf(doc.get("house_maxday"))));
				house.setHrefundDay(Integer.parseInt(String.valueOf(doc.get("house_refund_day"))));
				house.setHrentPrice(Integer.parseInt(String.valueOf(doc.get("house_rent_price"))));
				house.setHdescribe(String.valueOf(doc.get("house_describe")));
				house.setHuseRule(String.valueOf(doc.get("house_use_rule")));
				house.setHservice(String.valueOf(doc.get("house_service")));
				house.setHaddress(String.valueOf(doc.get("house_address")));
				house.setHpayRule(String.valueOf(doc.get("house_pay_rule")));
				house.setHpicture1(String.valueOf(doc.get("house_picture1")));
				house.setHpicture2(String.valueOf(doc.get("house_picture2")));
				house.setHpicture3(String.valueOf(doc.get("house_picture3")));
				house.setHstatus(Integer.parseInt(String.valueOf(doc.get("house_status"))));
				
				list.add(house);
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rm.setCurPage(currentPage);
		rm.setHouseList(list);		
		return rm;
	}

	@Override
	public int totalPage(int pageSize) {
		
		return (int)Math.ceil(this.totalCount()/(pageSize*1.0));
	}

	@Override
	public int totalCount() {
		/*HttpSolrServer server=SolrUtil.getServer();
		SolrQuery query=new SolrQuery();
		query.setFilterQueries("house_status:1");
		long lcount=0L;
		try {
			QueryResponse res=server.query(query);
			SolrDocumentList rd = res.getResults();
			lcount=rd.getNumFound();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count=Integer.parseInt(lcount+"");
		System.out.println("totalCount:"+count);*/
		//return count;
		String sql="select count(*) from tb_house";
		
		try {
			return Integer.parseInt( query.query(sql, new ScalarHandler<Long>()).toString());
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("查询用户总数量失败");
		}
		
	}

	@Override
	public int[] DeleteAll(List<Integer> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<House> pageListHouse(int currentPage, int pageSize, int id) {
		
		return null;
	}

	@Override
	public int findMaxHouseId() {
		String sql="SELECT MAX(house_id) FROM tb_house";
		try {			
			return query.query(sql, new ScalarHandler<Integer>());
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("查询House的Id的最大值失败");
		}
		
		
	}


	@Override
	public List<House> findByListHouse(List<Integer> listhid) {
		String sql="select house_id as hid ,house_uid as huid ,house_name as huname,house_invoice as hinvoice,house_lease_type as hleaseTpe,house_house_type as htype,house_area as area,house_live as hlive,house_bed as hbed,house_bedroom as hbedRoom,house_bed_form as hbedForm,"
				+"house_toilet as htoilet,house_check_in as hcheckIn,house_check_out as hcheckOut,house_minday as hminDay,house_maxday as hmaxDay,house_refund_day as hrefunDay ,house_rent_price as hrentPrice,house_describe as hdescribe,house_use_rule as huseRule,house_service as hservice,house_address as haddress,house_pay_rule as hpayRule,house_picture1 as hpicture1,house_picture2 as hpicture2,house_picture3 as hpicture3,house_status as hstatus from tb_house where house_id=?";
		List<House> list1=new ArrayList<House>();
		for(int i=0;i<listhid.size();i++){
			try {
				House house = query.query(sql, new BeanHandler<House>(House.class),listhid.get(i));				
				if(!list1.contains(house)){
					list1.add(house);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("查询失败！");
			}			
		}		
		return list1;
		
		
		
	}

	@Override
	public List<House> findByListHouse1(List<Integer> listhid) {
		String sql="select house_id as hid ,house_uid as huid ,house_name as huname,house_invoice as hinvoice,house_lease_type as hleaseTpe,house_house_type as htype,house_area as area,house_live as hlive,house_bed as hbed,house_bedroom as hbedRoom,house_bed_form as hbedForm,"
				+"house_toilet as htoilet,house_check_in as hcheckIn,house_check_out as hcheckOut,house_minday as hminDay,house_maxday as hmaxDay,house_refund_day as hrefunDay ,house_rent_price as hrentPrice,house_describe as hdescribe,house_use_rule as huseRule,house_service as hservice,house_address as haddress,house_pay_rule as hpayRule,house_picture1 as hpicture1,house_picture2 as hpicture2,house_picture3 as hpicture3,house_status as hstatus from tb_house where house_uid=?";
		List<House> list1=new ArrayList<House>();
		for(int i=0;i<listhid.size();i++){
			try {
				List<House> houseList = query.query(sql, new BeanListHandler<House>(House.class),listhid.get(i));				
				list1=houseList;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("查询失败！");
			}			
		}		
		return list1;
		
		
		
	}

}
