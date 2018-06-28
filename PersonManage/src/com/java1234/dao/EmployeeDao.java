package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import com.java1234.model.Department;
import com.java1234.model.Employee;
import com.java1234.model.PageBean;
import com.java1234.model.User;
import com.java1234.util.DateUtil;
import com.java1234.util.StringUtil;

public class EmployeeDao {

	public ResultSet employeeList(Connection con,User user,PageBean pageBean,Employee employee,String bbirthday,String ebirthday,String hiredate,String leaveDate) throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_employee s,t_department g where s.departmentId=g.departmentId");
		if(user!=null&&user.getRole().equalsIgnoreCase("2")){//2是部门管理员
			sb.append(" and s.departmentId ='"+user.getDepartmentId()+"'");		
		}else{
			if(employee!=null&&employee.getDepartmentId()!=-1){
				sb.append(" and s.departmentId ='"+employee.getDepartmentId()+"'");	
			}
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getEmployeeNo())){
			sb.append(" and s.employeeNo like '%"+employee.getEmployeeNo()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getName())){
			sb.append(" and s.name like '%"+employee.getName()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getSex())){
			sb.append(" and s.sex = '"+employee.getSex()+"'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getNationality())){
			sb.append(" and s.nationality like '%"+employee.getNationality()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getEducation())){
			sb.append(" and s.education like '%"+employee.getEducation()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getPosition())){
			sb.append(" and s.position like '%"+employee.getPosition()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getProductLine())){
			sb.append(" and s.productLine like '%"+employee.getProductLine()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getSupplier())){
			sb.append(" and s.supplier like '%"+employee.getSupplier()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getCurrentState())){
			sb.append(" and s.currentState = '"+employee.getCurrentState()+"'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getTechnicalDirection())){
			sb.append(" and s.technicalDirection like '%"+employee.getTechnicalDirection()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getTechnicalLevel())){
			sb.append(" and s.technicalLevel like '%"+employee.getTechnicalLevel()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getPayDepartment())){
			sb.append(" and s.payDepartment like '%"+employee.getPayDepartment()+"%'");
		}
		if(StringUtil.isNotEmpty(bbirthday)){
			sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('"+bbirthday+"')");			
		}
		if(StringUtil.isNotEmpty(ebirthday)){
			sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+ebirthday+"')");			
		}
		if(StringUtil.isNotEmpty(hiredate)){
			sb.append(" and TO_DAYS(s.hiredate)=TO_DAYS('"+hiredate+"')");			
		}
		if(StringUtil.isNotEmpty(leaveDate)){
			sb.append(" and TO_DAYS(s.leaveDate)=TO_DAYS('"+leaveDate+"')");			
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();		
	}
	
	public int employeeCount(Connection con,Employee employee,String bbirthday,String ebirthday,String hiredate, String leaveDate)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_employee s,t_department g where s.departmentId=g.departmentId");
		if(employee!=null&&!StringUtil.isEmpty(employee.getEmployeeNo())){
			sb.append(" and s.employeeNo like '%"+employee.getEmployeeNo()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getName())){
			sb.append(" and s.name like '%"+employee.getName()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getSex())){
			sb.append(" and s.sex = '"+employee.getSex()+"'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getNationality())){
			sb.append(" and s.nationality like '%"+employee.getNationality()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getEducation())){
			sb.append(" and s.education like '%"+employee.getEducation()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getPosition())){
			sb.append(" and s.position like '%"+employee.getPosition()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getPayDepartment())){
			sb.append(" and s.payDepartment like '%"+employee.getPayDepartment()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getProductLine())){
			sb.append(" and s.productLine like '%"+employee.getProductLine()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getSupplier())){
			sb.append(" and s.supplier like '%"+employee.getSupplier()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getCurrentState())){
			sb.append(" and s.currentState = '"+employee.getCurrentState()+"'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getTechnicalDirection())){
			sb.append(" and s.technicalDirection like '%"+employee.getTechnicalDirection()+"%'");
		}
		if(employee!=null&&!StringUtil.isEmpty(employee.getTechnicalLevel())){
			sb.append(" and s.technicalLevel like '%"+employee.getTechnicalLevel()+"%'");
		}
		if(employee.getDepartmentId()!=-1){
			sb.append(" and s.departmentId ='"+employee.getDepartmentId()+"'");			
		}
		if(StringUtil.isNotEmpty(bbirthday)){
			sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('"+bbirthday+"')");			
		}
		if(StringUtil.isNotEmpty(ebirthday)){
			sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+ebirthday+"')");			
		}
		if(StringUtil.isNotEmpty(hiredate)){
			sb.append(" and TO_DAYS(s.hiredate)=TO_DAYS('"+hiredate+"')");			
		}
		if(StringUtil.isNotEmpty(leaveDate)){
			sb.append(" and TO_DAYS(s.leaveDate)=TO_DAYS('"+leaveDate+"')");			
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public int employeeDelect(Connection con,String delIds)throws Exception{
		String sql="delete from t_employee where employeeId in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	public int employeeAdd(Connection con,Employee employee)throws Exception{
		String sql="insert into t_employee values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, employee.getEmployeeNo());
		pstmt.setString(2, employee.getName());
		pstmt.setString(3, employee.getSex());
		pstmt.setString(4, DateUtil.formatDate(employee.getBirthday(), "yyyy-MM-dd"));
		pstmt.setString(5, employee.getNationality());
		pstmt.setString(6, employee.getEducation());
		pstmt.setString(7, employee.getProfession());
		pstmt.setInt(8, employee.getDepartmentId());
		pstmt.setString(9, employee.getPosition());
		pstmt.setFloat(10, employee.getBaseMoney());
		pstmt.setFloat(11, employee.getOvertime());
		pstmt.setFloat(12, employee.getAge());
		pstmt.setFloat(13, employee.getCheck1());
		pstmt.setFloat(14, employee.getAbsent());
		pstmt.setFloat(15, employee.getSafety());
		pstmt.setString(16, employee.getIdcardno());
		pstmt.setString(17, employee.getPhone());
		pstmt.setString(18, DateUtil.formatDate(employee.getHiredate(), "yyyy-MM-dd"));
		pstmt.setString(19, DateUtil.formatDate(employee.getLeaveDate(), "yyyy-MM-dd"));
		pstmt.setString(20, employee.getSupplier());
		pstmt.setString(21, employee.getProductLine());
		pstmt.setString(22, employee.getCurrentState());
		pstmt.setString(23, employee.getTechnicalDirection());
		pstmt.setString(24, employee.getTechnicalLevel());
		pstmt.setString(25, employee.getPayDepartment());
		return pstmt.executeUpdate();
	}
	
	public int employeeModify(Connection con,Employee employee)throws Exception{
		String sql="update t_employee set employeeNo=?,name=?,sex=?,birthday=?,nationality=?,education=?,profession=?,departmentId=?,position=?,baseMoney=?,overtime=?,age=?,check1=?,absent=?,safety=?,idcardno=?,phone=?,hiredate=?,leaveDate=?,supplier=?,productLine=?,currentState=?,technicalDirection=?,technicalLevel=?, payDepartment=? where employeeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, employee.getEmployeeNo());
		pstmt.setString(2, employee.getName());
		pstmt.setString(3, employee.getSex());
		pstmt.setString(4, DateUtil.formatDate(employee.getBirthday(), "yyyy-MM-dd"));
		pstmt.setString(5, employee.getNationality());
		pstmt.setString(6, employee.getEducation());
		pstmt.setString(7, employee.getProfession());
		pstmt.setInt(8, employee.getDepartmentId());
		pstmt.setString(9, employee.getPosition());
		pstmt.setFloat(10, employee.getBaseMoney());
		pstmt.setFloat(11, employee.getOvertime());
		pstmt.setFloat(12, employee.getAge());
		pstmt.setFloat(13, employee.getCheck1());
		pstmt.setFloat(14, employee.getAbsent());
		pstmt.setFloat(15, employee.getSafety());		
		pstmt.setString(16, employee.getIdcardno());
		pstmt.setString(17, employee.getPhone());
		pstmt.setString(18, DateUtil.formatDate(employee.getHiredate(), "yyyy-MM-dd"));
		pstmt.setString(19, DateUtil.formatDate(employee.getLeaveDate(), "yyyy-MM-dd"));
		pstmt.setString(20, employee.getSupplier());
		pstmt.setString(21, employee.getProductLine());
		pstmt.setString(22, employee.getCurrentState());
		pstmt.setString(23, employee.getTechnicalDirection());
		pstmt.setString(24, employee.getTechnicalLevel());
		pstmt.setString(25, employee.getPayDepartment());
		pstmt.setInt(26, employee.getEmployeeId());
		return pstmt.executeUpdate();
	}
	
	public boolean getEmployeeByDepartmentId(Connection con,String departmentId)throws Exception{
		String sql="select * from t_employee where departmentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, departmentId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	
	public static List<Employee> getData(Connection con,ResultSet rs) throws Exception{
		List<Employee> list = new ArrayList<Employee>();
		try {
			con.setAutoCommit(false);
			while(rs.next()){
				int employeeId = rs.getInt("employeeId");
				String employeeNo = rs.getString("employeeNo");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				Date birthday = rs.getDate("birthday");
				String nationality = rs.getString("nationality");
				String education = rs.getString("education");
				String profession = rs.getString("profession");
				String departmentNameSrc = rs.getString("departmentName");
				String position = rs.getString("position");
				String productLine = rs.getString("productLine");
				String currentState = rs.getString("currentState");
				Date hiredate = rs.getDate("hiredate");
				Date leaveDate = rs.getDate("leaveDate");
				String idcardno = rs.getString("idcardno");
				String phone = rs.getString("phone");
				String supplier = rs.getString("supplier");
				String technicalDirection = rs.getString("technicalDirection");
				String technicalLevel = rs.getString("technicalLevel");
				String payDepartment = rs.getString("payDepartment");
				float baseMoney = rs.getFloat("baseMoney");
				float overtime = rs.getFloat("overtime");
				float age = rs.getFloat("age");
				float check1 = rs.getFloat("check1");
				float absent = rs.getFloat("absent");
				float safety = rs.getFloat("safety");
				
				Employee employee = new Employee();
				employee.setEmployeeId(employeeId);
				employee.setEmployeeNo(employeeNo);
				employee.setName(name);
				employee.setSex(sex);
				employee.setBirthday(birthday);
				employee.setNationality(nationality);
				employee.setEducation(education);
				employee.setProfession(profession);
				employee.setDepartmentNameSrc(departmentNameSrc);
				employee.setPosition(position);
				employee.setProductLine(productLine);
				employee.setCurrentState(currentState);
				employee.setHiredate(hiredate);
				employee.setLeaveDate(leaveDate);
				employee.setIdcardno(idcardno);
				employee.setPhone(phone);
				employee.setSupplier(supplier);
				employee.setTechnicalDirection(technicalDirection);
				employee.setTechnicalLevel(technicalLevel);
				employee.setPayDepartment(payDepartment);
				employee.setBaseMoney(baseMoney);
				employee.setOvertime(overtime);
				employee.setAge(age);
				employee.setCheck1(check1);
				employee.setAbsent(absent);
				employee.setSafety(safety);
				
				list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
