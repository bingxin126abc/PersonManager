package com.java1234.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UserLogoutAction extends ActionSupport {

	@Override
	public String execute()   throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return SUCCESS;
	}
}
