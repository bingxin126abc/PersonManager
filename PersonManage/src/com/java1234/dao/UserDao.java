package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.Employee;
import com.java1234.model.User;
import com.java1234.util.DateUtil;
import com.java1234.util.StringUtil;

public class UserDao {

	public User login(Connection con,User user) throws Exception{
		User currentUser=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		
		if(rs.next()){
			currentUser = new User();
			currentUser.setUserId(rs.getInt("userId"));
			currentUser.setUserName(rs.getString("userName"));
			currentUser.setPassword(rs.getString("password"));
			currentUser.setRole(rs.getString("role"));
			currentUser.setDepartmentId(rs.getInt("departmentId"));
		}
		return currentUser;
	}
	
	public int update(Connection con ,User user) throws Exception{
			String sql="update t_user set password=? where userId=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getUserId());
			return pstmt.executeUpdate();
		}
}
