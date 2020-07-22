package com.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.EasybuyUserAddress;
import com.service.EasybuyUserAddressService;

@Controller
@RequestMapping("/dz")
public class EasybuyUserAddressController {
        @Resource 
        private EasybuyUserAddressService easybuyUserAddressService;
	/**
	 * 获取地址信息|！
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
      @RequestMapping("/address")
	public String address(HttpServletRequest request, HttpServletResponse response) {
		// 获取登陆用户信息ID！
		// 调用三层信息！
		List<EasybuyUserAddress> easybuyUserAddress =easybuyUserAddressService.getEasybuyUserAddressAll(10);
		// 把该对象存储在内置对象中！
		request.setAttribute("easybuyUserAddress", easybuyUserAddress);
		return "backend/user/Member_Address";
	}
}
