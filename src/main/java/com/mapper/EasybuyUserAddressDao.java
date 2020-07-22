package com.mapper;

import java.util.List;

import com.entity.EasybuyUserAddress;

/**
 * 用户地址表信息数据访问层！
 * @author Administrator
 *
 */
public interface EasybuyUserAddressDao {
	
	/**
	 * 根据用户ID查询对应的收货地址！
	 * @return
	 */
	List<EasybuyUserAddress> findEasybuyUserAddressAll(Integer userId);
	
	/**
	 * 根据登陆用户ID新增数据！
	 * @param userId
	 * @return
	 */
	int updateEasybuyUserAddressById(EasybuyUserAddress easybuyUserAddress);
	/**
	 * 根据id查询收货地址
	 * @param id
	 * @return
	 */
	EasybuyUserAddress getUserAddressById(Integer id);
	
	//********
	/**
	 * 根据用户Id判断该编号是否存在地址信息！
	 * @param userId
	 * @return
	 */
	int findUserByIdAddress(Integer userId);
	//********
}
