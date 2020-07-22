package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyOrder;
import com.utils.Pager;

/**
 * 订单信息数据访问层！
 * @author Administrator
 *
 */
public interface EasybuyOrderDao {
	
	
	
	/**
	 * 根据用户信息查询对应订单信息！
	 * @param id
	 * @return
	 */
	List<EasybuyOrder> findEasybuyOrderList(@Param("userId")Integer userId,@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 查询全部订单
	 */
	List<EasybuyOrder> findEasybuyOrderList1(@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 获得总记录数！
	 * @return
	 */
	public int getTotalCount();
	
	//*********
	/**
	 * 根据ID查询订单信息！
	 * @param id
	 * @return
	 */
	int findUserByIdOrder(Integer id);
	//*********
}
