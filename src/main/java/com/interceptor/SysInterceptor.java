package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.EasybuyUser;

public class SysInterceptor {
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
		
		EasybuyUser user = (EasybuyUser)session.getAttribute("easybuyUserLogin");
		  
		  if(null == user){ 
			  response.sendRedirect(request.getContextPath()+"/500.jsp");
		  return false; 
		  }
		 
		response.sendRedirect(request.getContextPath()+"/404.jsp");
		return true;
	}
}
