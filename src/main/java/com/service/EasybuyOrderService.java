package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.DetailProduct;
import com.entity.EasybuyOrder;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyUser;
import com.utils.Pager;
import com.utils.ShoppingCart;

/**
 * 订单信息业务逻辑层！
 * 
 * @author Administrator
 *
 */
public interface EasybuyOrderService {

	/**
	 * 根据用户ID得到对应信息业务！
	 * 
	 * @param userId
	 * @return
	 */
	List<EasybuyOrder> getEasybuyOrderAll(@Param("userId")Integer userId,@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 查询全部订单
	 */
	List<EasybuyOrder> findEasybuyOrderList1(@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 获得总计数！
	 * 
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 购物！
	 * 
	 * @param cart
	 * @param user
	 * @param adress
	 * @return
	 */
	EasybuyOrder payShoppingCart(ShoppingCart cart, EasybuyUser user, String adress);

	/**
	 * 获得购物信息！
	 * 
	 * @return
	 */
	public List<DetailProduct> getEasybuyOrderDetail();

	/**
	 * 获得下单购物订单信息！
	 * 
	 * @param orderId
	 * @return
	 */
	public List<EasybuyOrderDetail> getEasybuyOrderDetail(int orderId);

	// *********
	/**
	 * 根据ID查询订单信息！
	 * 
	 * @param id
	 * @return
	 */
	int getUserByIdOrder(Integer id);
	// *********
}
