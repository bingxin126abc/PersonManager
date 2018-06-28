package com.java1234.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.WebTool;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePwdAction extends ActionSupport{
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String changePwd(){
		//判断旧密码是否正确
//		从库中取出旧密码与当前输入密码进行比较，一致则可以修改密码
		//更新当前记录的密码值并md5加密
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");	
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			  User loginUser =(User) session.getAttribute("currentUser");
		        if(WebTool.getMD5Encoding(oldPassword).equals(loginUser.getPassword())||oldPassword.equalsIgnoreCase(loginUser.getPassword())){
		        	loginUser.setPassword(WebTool.getMD5Encoding(newPassword.trim()));
		    	  saveNums= userDao.update(con, loginUser);
			        if(saveNums>0){
						result.put("success", "true");
					}else{
						result.put("success", "true");
						result.put("errorMsg", "修改失败");
					}
		        }else{
		        	result.put("success", "true");
					result.put("errorMsg", "旧密码输入有误！");
		        }
				ResponseUtil.write(ServletActionContext.getResponse(), result);	
		}catch (Exception e) {
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
	
}
