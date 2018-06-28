package com.java1234.action;

import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;
import com.java1234.util.WebTool;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.accessibility.internal.resources.accessibility;

public class UserAction extends ActionSupport {
	private User user;
	private String error;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(user.getUserName())||StringUtil.isEmpty(user.getPassword())){
			error="用户名或者密码为空！";
			return ERROR;
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			user.setPassword(WebTool.getMD5Encoding(user.getPassword()));
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				error="用户名或者密码错误！";
				return ERROR;
			}else{
				session.setAttribute("currentUser", currentUser);
				session.setAttribute("role", currentUser.getRole());
				return SUCCESS;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}	
}
