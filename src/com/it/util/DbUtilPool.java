package com.it.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;



/**
 *	2017年8月12日 上午9:17:00
 * 
 */
public class DbUtilPool {
private static DataSource datasource=null;
	
	static {
		
		InputStream rs = DbUtilPool.class.getClassLoader().getResourceAsStream("Db.properties");
		Properties p=new Properties();
		try {
			p.load(rs);
			BasicDataSourceFactory factory=new  BasicDataSourceFactory();
			datasource=factory.createDataSource(p);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	public static DataSource getDataSource(){
		System.out.println(datasource);		
		if(datasource==null){
			throw new RuntimeException("数据源不存在...");
		}
		
		return datasource;	
	}
	
	
	
}

