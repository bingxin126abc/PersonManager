package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private String url="jdbc:mysql://localhost:3306/db_personmanage?useUnicode=true&characterEncoding=utf8";
	private String user="root";
	private String password="123456";
	private String driver="com.mysql.jdbc.Driver";
	
	public Connection getCon() throws Exception{
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,user,password);
		return con;
	}

	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			System.out.println("连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
				System.out.println("关闭连接");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
