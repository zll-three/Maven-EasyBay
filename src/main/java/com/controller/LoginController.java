package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.entity.EasybuyUser;
import com.service.EasybuyUserService;
import com.utils.SecurityUtils;
@Controller
public class LoginController {
	  @Autowired
      private EasybuyUserService easybuyUserService;
	
	//跳转首页面
	@RequestMapping("/Login.html")
	public String Login() {
		return "pre/Login";
		
		
	}
	//注销用户
	@RequestMapping("/remove")
	public String remove(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession() != null) {
			// 让会话失效！
			request.getSession().invalidate();
		}
		return "pre/Login";
	}
	 //登录请求
    @RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object LoginUser(@ModelAttribute()
			@RequestParam(value = "name", required = false) String name,@RequestParam(value = "pwd", required = false) String pwd,HttpSession session){
    	//Md5转换
    	String password = SecurityUtils.md5Hex(pwd);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("loginName", name);
		resultMap.put("password", password);
		EasybuyUser user=easybuyUserService.getEasybuyUserInfo(resultMap);
		//判断是否有该用户
				if ( user == null) {
					//判断该用户是否注入
					resultMap.put("message", "用户不存在");		
				} else {
					//判断密码是否输入正确
					if (name.equals(name) && password.equals(password)) {
						//登陆成功
						session.setAttribute("easybuyUserLogin", user);
						
						resultMap.put("loginResult", "true");
						resultMap.put("message", "登陆成功");
					} else {
						resultMap.put("loginResult", "false");
						resultMap.put("message", "输入的用户名或密码错误");
						
					}
				}
				  
				 
		  return JSONArray.toJSONString(resultMap);
		  }
}
