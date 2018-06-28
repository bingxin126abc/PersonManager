package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.Department;
import com.java1234.model.PageBean;
import com.java1234.util.StringUtil;

public class DepartmentDao {

	public ResultSet departmentList(Connection con,PageBean pageBean,Department department) throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_department");
		if(department!=null&&!StringUtil.isEmpty(department.getDepartmentName())){
			sb.append(" and departmentName like '%"+department.getDepartmentName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();		
	}
	
	public int departmentCount(Connection con)throws Exception{
		String sql="select count(*) as total from t_department";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int departmentAdd(Connection con,Department department) throws Exception{
		String sql="insert into t_department values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setString(2, department.getDepartmentDesc());
		return pstmt.executeUpdate();		
	}
	
	public int departmentDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_department where departmentId in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	public int departmentModify(Connection con,Department department)throws Exception{
		String sql="update t_department set departmentName=?,departmentDesc=? where departmentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setString(2, department.getDepartmentDesc());
		pstmt.setInt(3, department.getDepartmentId());
		return pstmt.executeUpdate();
	}
}
