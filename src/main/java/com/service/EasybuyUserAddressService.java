package com.service;

import java.util.List;
import com.entity.EasybuyUserAddress;

/**
 * 用户地址信息业务逻辑层！
 * 
 * @author Administrator
 *
 */
public interface EasybuyUserAddressService {

	/**
	 * 根据用户ID查找对应的地址信息！
	 * 
	 * @param userId
	 * @return
	 */
	List<EasybuyUserAddress> getEasybuyUserAddressAll(Integer userId);

	/**
	 * 新增地址！
	 * 
	 * @param easybuyUserAddress
	 * @return
	 */
	public int addUserAddress(EasybuyUserAddress easybuyUserAddress);

	/**
	 * 根据用户ID获得相依地址信息！
	 * 
	 * @param id
	 * @return
	 */
	public EasybuyUserAddress getUserAddressById(Integer id);

	// ********
	/**
	 * 根据用户Id判断该编号是否存在地址信息！
	 * 
	 * @param userId
	 * @return
	 */
	int getUserByIdAddress(Integer userId);
	// ********
}
