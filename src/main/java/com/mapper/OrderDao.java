package com.mapper;

import java.util.List;

import com.entity.DetailProduct;
import com.entity.EasybuyOrder;
import com.entity.EasybuyOrderDetail;
/**
 * 订单信息数据访问层！
 * @author Administrator
 *
 */
public interface OrderDao {
	/**
	 * 
	 * @param order
	 */
	public int saveOrder(EasybuyOrder order);
	/**
	 * 获取订单信息！
	 * @return
	 */
	public List<DetailProduct> getEasybuyOrderDetail();
	/**
	 * 根据订单号获取订单详情信息！
	 * @param orderId
	 * @return
	 */
	public List<EasybuyOrderDetail> getEasybuyOrderDetail1(Integer orderId);
}
