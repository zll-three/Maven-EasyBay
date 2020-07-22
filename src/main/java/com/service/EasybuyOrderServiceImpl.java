package com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;


import com.entity.DetailProduct;
import com.entity.EasybuyOrder;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyUser;
import com.mapper.EasybuyOrderDao;
import com.mapper.OrderDao;
import com.mapper.OrderDetailDao;
import com.mapper.ProductDao;
import com.utils.DataBaseUtil;
import com.utils.MyBatisUtil;
import com.utils.Pager;
import com.utils.ShoppingCart;
import com.utils.ShoppingCartItem;
import com.utils.StringUtils;

/**
 * 订单信息业务逻辑层实现类！
 * 
 * @author Administrator
 *
 */
@Service
public class EasybuyOrderServiceImpl implements EasybuyOrderService {
    @Resource
    private EasybuyOrderDao easybuyOrderDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	@Resource
	private ProductDao productDao;
	
	@Override
	/**
	 * 根据用户ID得到对应信息业务！
	 */
	public List<EasybuyOrder> getEasybuyOrderAll(Integer userId,Integer currentPageNo,Integer pageSize) {

			currentPageNo = (currentPageNo-1)*pageSize;
	
		
		return easybuyOrderDao.findEasybuyOrderList(userId, currentPageNo, pageSize);
	}
     /**
      * 查询全部订单
      */
	@Override
	public List<EasybuyOrder> findEasybuyOrderList1(Integer currentPageNo, Integer pageSize) {
		
			currentPageNo = (currentPageNo-1)*pageSize;
			
		
		return easybuyOrderDao.findEasybuyOrderList1(currentPageNo, pageSize);
	}
	/**
	 * 购物！
	 * 
	 * @param cart
	 * @param user
	 * @param adress
	 * @return
	 */
	@SuppressWarnings("finally")
	public EasybuyOrder payShoppingCart(ShoppingCart cart, EasybuyUser user, String adress) {
		SqlSession sqlSession =null;
		System.out.println(1);
		// 创建对象！
		EasybuyOrder order = new EasybuyOrder();
		try {
			sqlSession= MyBatisUtil.createSqlSession();
			// 获得连接！
	        String id =String.valueOf(user.getId());
			// 更新订单
			order.setUserId(id);
			order.setUserAddress(adress);
			order.setCreateTime(new Date());
			order.setCost(cart.getTotalCost());
			order.setSerialNumber(StringUtils.randomUUID());
			order.setLoginName(user.getLoginName());
			orderDao.saveOrder(order);
			for (ShoppingCartItem item : cart.getItems()) {	
				// 更新订单详情
				EasybuyOrderDetail detail = new EasybuyOrderDetail();
				detail.setOrderId(order.getId());
				detail.setCost(item.getCost());
				detail.setProductId(item.getProduct().getId());
				detail.setQuantity(item.getQuantity());
				System.out.println(detail.getOrderId());
				System.out.println(detail.getCost());
				System.out.println(detail.getProductId());
				System.out.println(detail.getQuantity());
				orderDetailDao.saveOrderDetail(detail);
				//new ProductDaoImpl(conn).updateStock(item.getProduct().getId(), item.getQuantity());
				System.out.println(item.getProduct().getId()+"    "+item.getQuantity());
				productDao.updateStock(item.getProduct().getId(), item.getQuantity());	
			}
			sqlSession.commit();
		} catch (SQLException e) {
			
			order = null;
			// 捕获异常！
		} finally {
			// 释放资源！
			MyBatisUtil.closeSqlSession(sqlSession);
			
			
			
		}
		return order;
	}

	@Override
	/**
	 * 获得总计数！
	 * 
	 * @return
	 */
	public int getTotalCount() {
		
		return easybuyOrderDao.getTotalCount();
	}

	@Override
	/**
	 * 获得购物信息！
	 * 
	 * @return
	 */
	public List<DetailProduct> getEasybuyOrderDetail() {

		
		return orderDao.getEasybuyOrderDetail();
	}
	

	/**
	 * 获得下单购物订单信息！
	 * 
	 * @param orderId
	 * @return
	 */
	public List<EasybuyOrderDetail> getEasybuyOrderDetail(int orderId) {
		return orderDao.getEasybuyOrderDetail1(orderId);
	}

	// *********
	@Override
	/**
	 * 根据ID查询订单信息！
	 * 
	 * @param id
	 * @return
	 */
	public int getUserByIdOrder(Integer id) {

		return easybuyOrderDao.findUserByIdOrder(id);
	}
	// *********

	
}
