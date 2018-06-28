package com.java1234.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.java1234.dao.EmployeeDao;
import com.java1234.model.Employee;
import com.java1234.model.PageBean;
import com.java1234.model.User;
import com.java1234.util.DateUtil;
import com.java1234.util.DbUtil;
import com.java1234.util.JsonUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class EmployeeAction extends ActionSupport{

	private Employee employee;
	private String s_employeeNo;
	private String s_name;
	private String s_sex;
	private String s_bbirthday;
	private String s_ebirthday;
	private String s_nationality;
	private String s_education;
	private String s_departmentId;
	private String s_productLine;
	private String s_supplier;
	private String s_currentState;
	private String s_hiredate;
	private String s_leaveDate;
	private String s_technicalDirection;
	private String s_technicalLevel;
	private String s_payDepartment;
	private String s_position;
	private String page;
	private String rows;
	private String delIds;
	private String employeeId;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getS_employeeNo() {
		return s_employeeNo;
	}
	public void setS_employeeNo(String s_employeeNo) {
		this.s_employeeNo = s_employeeNo;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_sex() {
		return s_sex;
	}
	public void setS_sex(String s_sex) {
		this.s_sex = s_sex;
	}
	public String getS_bbirthday() {
		return s_bbirthday;
	}
	public void setS_bbirthday(String s_bbirthday) {
		this.s_bbirthday = s_bbirthday;
	}
	public String getS_ebirthday() {
		return s_ebirthday;
	}
	public void setS_ebirthday(String s_ebirthday) {
		this.s_ebirthday = s_ebirthday;
	}
	public String getS_nationality() {
		return s_nationality;
	}
	public void setS_nationality(String s_nationality) {
		this.s_nationality = s_nationality;
	}
	public String getS_education() {
		return s_education;
	}
	public void setS_education(String s_education) {
		this.s_education = s_education;
	}
	public String getS_departmentId() {
		return s_departmentId;
	}
	public void setS_departmentId(String s_departmentId) {
		this.s_departmentId = s_departmentId;
	}
	public String getS_productLine() {
		return s_productLine;
	}
	public void setS_productLine(String s_productLine) {
		this.s_productLine = s_productLine;
	}
	public String getS_supplier() {
		return s_supplier;
	}
	public void setS_supplier(String s_supplier) {
		this.s_supplier = s_supplier;
	}
	public String getS_currentState() {
		return s_currentState;
	}
	public void setS_currentState(String s_currentState) {
		this.s_currentState = s_currentState;
	}
	public String getS_hiredate() {
		return s_hiredate;
	}
	public void setS_hiredate(String s_hiredate) {
		this.s_hiredate = s_hiredate;
	}
	public String getS_leaveDate() {
		return s_leaveDate;
	}
	public void setS_leaveDate(String s_leaveDate) {
		this.s_leaveDate = s_leaveDate;
	}
	public String getS_technicalDirection() {
		return s_technicalDirection;
	}
	public void setS_technicalDirection(String s_technicalDirection) {
		this.s_technicalDirection = s_technicalDirection;
	}
	public String getS_technicalLevel() {
		return s_technicalLevel;
	}
	public void setS_technicalLevel(String s_technicalLevel) {
		this.s_technicalLevel = s_technicalLevel;
	}
	public String getS_payDepartment() {
		return s_payDepartment;
	}
	public void setS_payDepartment(String s_payDepartment) {
		this.s_payDepartment = s_payDepartment;
	}
	public String getS_position() {
		return s_position;
	}
	public void setS_position(String s_position) {
		this.s_position = s_position;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getDelIds() {
		return delIds;
	}
	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public ResultSet search() throws Exception {
		Connection con=null;
		con=dbUtil.getCon();
		if(page==null){
			page = "1";
			rows = "10";
		}else{
			page = page;
			rows = rows;
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		employee=new Employee();
		if(s_employeeNo!=null){
			employee.setEmployeeNo(s_employeeNo);
			employee.setName(s_name);
			employee.setSex(s_sex);
			employee.setEducation(s_education);
			employee.setPosition(s_position);
			employee.setProductLine(s_productLine);
			employee.setSupplier(s_supplier);
			employee.setCurrentState(s_currentState);
			employee.setCurrentState(s_currentState);
			employee.setTechnicalDirection(s_technicalDirection);
			employee.setTechnicalLevel(s_technicalLevel);
			employee.setPayDepartment(s_payDepartment);
			employee.setNationality(s_nationality);
			if(StringUtil.isNotEmpty(s_departmentId)){
				employee.setDepartmentId(Integer.parseInt(s_departmentId));
			}
		}
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user = (User)session.getAttribute("currentUser");
		ResultSet rs = employeeDao.employeeList(con,user,pageBean,employee,s_bbirthday,s_ebirthday,s_hiredate,s_leaveDate);
		return rs;
	}
	
	DbUtil dbUtil=new DbUtil();
	EmployeeDao employeeDao=new EmployeeDao();
	@Override
	public String execute() throws Exception {
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(search());
			int total=employeeDao.employeeCount(con,employee,s_bbirthday,s_ebirthday,s_hiredate,s_leaveDate);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String delete()throws Exception{
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int delNums=employeeDao.employeeDelect(con, delIds);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败");
			}
			result.put("delNum", delNums);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String save()throws Exception{
		if(StringUtil.isNotEmpty(employeeId)){
			employee.setEmployeeId(Integer.parseInt(employeeId));
		}
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(employeeId)){
				saveNums=employeeDao.employeeModify(con, employee);
			}else{
				saveNums=employeeDao.employeeAdd(con, employee);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "保存失败");
			}
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}	
	
	public void ExportEmployee() throws Exception{	
		Connection con = null;
		con=dbUtil.getCon();		
		List<Employee> list = employeeDao.getData(con,search());
		HSSFWorkbook workbook = new HSSFWorkbook();	
		try {
			HSSFSheet sheet = workbook.createSheet("employee");	
			HSSFRow row = sheet.createRow(0);
			String[] cellTitle = {"ID", "编号", "姓名", "性别","出生日期","学历", "专业","部门","项目组","身份证","联系电话","入职时间","离职时间","供应商","当前状态","技术方向","技术级别","付费部门","职务"};	
			for (int i = 0; i < cellTitle.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(cellTitle[i]);
			}
			
			for(int rowIndex=0;rowIndex<list.size();rowIndex++){
				row = sheet.createRow(rowIndex+1);	
				HSSFCell cell = row.createCell(rowIndex);
				cell.setCellValue(cellTitle[rowIndex]);
				Employee employee = list.get(rowIndex);
				for(int cellnum = 0; cellnum < 20; cellnum++){
					cell = row.createCell(cellnum);
					switch(cellnum){
					case 0:
						cell.setCellValue(employee.getEmployeeId());
						break;
					case 1:
						cell.setCellValue(employee.getEmployeeNo());
						break;
					case 2:
						cell.setCellValue(employee.getName());
						break;
					case 3:
						cell.setCellValue(employee.getSex());
						break;
					case 4:
						cell.setCellValue(DateUtil.formatDate(employee.getBirthday(), "yyyy-MM-dd"));
						break;
					case 5:
						cell.setCellValue(employee.getEducation());
						break;
					case 6:
						cell.setCellValue(employee.getProfession());
						break;
					case 7:
						cell.setCellValue(employee.getDepartmentNameSrc());
						break;
					case 8:
						cell.setCellValue(employee.getProductLine());
						break;
					case 9:
						cell.setCellValue(employee.getIdcardno());
						break;
					case 10:
						cell.setCellValue(employee.getPhone());
						break;
					case 11:
						cell.setCellValue(DateUtil.formatDate(employee.getHiredate(), "yyyy-MM-dd"));
						break;
					case 12:
						cell.setCellValue(DateUtil.formatDate(employee.getLeaveDate(), "yyyy-MM-dd"));
						break;
					case 13:
						cell.setCellValue(employee.getSupplier());
						break;
					case 14:
						cell.setCellValue(employee.getCurrentState());
						break;
					case 15:
						cell.setCellValue(employee.getTechnicalDirection());
						break;
					case 16:
						cell.setCellValue(employee.getTechnicalLevel());
						break;
					case 17:
						cell.setCellValue(employee.getPayDepartment());
						break;
					case 18:
						cell.setCellValue(employee.getPosition());
						break;				
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		String exportFileName = "employee.xls";
		
		ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String((exportFileName).getBytes(), "ISO8859-1"));//设定输出文件头
		ServletActionContext.getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型
		
		
		OutputStream out = ServletActionContext.getResponse().getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}
}
