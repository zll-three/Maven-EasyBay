package com.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.DetailProduct;
import com.entity.EasybuyOrder;
import com.service.EasybuyOrderService;
import com.utils.Pager;

@Controller
@RequestMapping("/order")
public class EasybuyOrderController {
            @Resource
            private EasybuyOrderService easybuyOrderService;
	
	/**
	 * 订单列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    @RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取ID！
		String userId = request.getParameter("userId");
		// 获取当前页数
				String currentNo  = request.getParameter("currentPage");
				// 获取页大小
				String size = request.getParameter("pageSize");
				int count= easybuyOrderService.getUserByIdOrder(Integer.parseInt(userId));
				//开始的行数
				int currentPageNo = 1;
				//每页有几行
				int pageSize = 2;
				//判断不为空
				if(null != currentNo && !currentNo.trim().equals("")){
					currentPageNo = Integer.parseInt(currentNo);
				}
				if(null != size && !size.trim().equals("")){
					pageSize = Integer.parseInt(size);
				}
				Pager pager = new Pager(count, pageSize,currentPageNo);

		List<EasybuyOrder> orderList = easybuyOrderService.getEasybuyOrderAll(Integer.parseInt(userId), currentPageNo, pageSize);
		
		pager.setUrl("/order/index?userId=" + Integer.parseInt(userId));
		// 获取当前登录用户ID！
		// 调用三层！
		List<DetailProduct> listOrderDetail = easybuyOrderService.getEasybuyOrderDetail();
		// 放置内置对象！
		// 放置对象！
		//System.out.println(orderList.size());
				if(orderList.size()==0){
					request.setAttribute("odList", orderList.size());
					
				}
	    request.setAttribute("orderList", orderList);
				
		request.setAttribute("listOrderDetail", listOrderDetail);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 1);
		return "backend/order/orderList";
	}

	/**
	 * 查询订单明细！
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/queryOrderDeatil")
	public String queryOrderDeatil(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取ID！
		String orderId = request.getParameter("id");
		// 访问三层！
		List<DetailProduct> listOrderDetail = easybuyOrderService.getEasybuyOrderDetail();
		// 放置内置对象！
		request.setAttribute("orderId", Integer.parseInt(orderId));
		request.setAttribute("listOrderDetail", listOrderDetail);
		return "/backend/order/orderDetailList";
	}

	/**
	 * 全部订单！
	 */
    @RequestMapping("/queryAllOrder")
	public String queryAllOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentNo  = request.getParameter("currentPage");
		// 获取页大小
		String size = request.getParameter("pageSize");
		int count= easybuyOrderService.getTotalCount();
		//开始的行数
		int currentPageNo = 1;
		//每页有几行
		int pageSize = 2;
		//判断不为空
		if(null != currentNo && !currentNo.trim().equals("")){
			currentPageNo = Integer.parseInt(currentNo);
		}
		if(null != size && !size.trim().equals("")){
			pageSize = Integer.parseInt(size);
		}
		Pager pager = new Pager(count, pageSize,currentPageNo);
		List<EasybuyOrder> orderList = easybuyOrderService.findEasybuyOrderList1(currentPageNo, pageSize);
	
		pager.setUrl("/order/queryAllOrder");
		// 访问三层！
		List<DetailProduct> listOrderDetail = easybuyOrderService.getEasybuyOrderDetail();
		// 放置对象！
			request.setAttribute("orderList", orderList);
		
		
		request.setAttribute("listOrderDetail", listOrderDetail);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 9);
		return "/backend/order/orderList";
	}

}
