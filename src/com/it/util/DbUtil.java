package com.it.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	
	private  static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static String driverClassName;
	private static String url;
	private static String userName;
	private static String pwd;
	static{
		
		InputStream ras = DbUtil.class.getClassLoader().getResourceAsStream("Db.properties");
		Properties p=new Properties();
		try {
			p.load(ras);
			driverClassName =p.getProperty("driverClassName");
			url=p.getProperty("url");
			userName=p.getProperty("username");
			pwd=p.getProperty("password");
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	//1.获取链接
	public static Connection getConnection(){
		Connection conn=null;
		if(conn==null){
			try {
				conn=DriverManager.getConnection(url, userName, pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	//2.关闭链接
	public static void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		try {
		// 存在问题
			if(rs!=null)rs.close();;
			if(pstmt!=null) pstmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	
	
}
