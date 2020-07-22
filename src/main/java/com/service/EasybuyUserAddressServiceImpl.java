package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;


import com.entity.EasybuyUserAddress;
import com.mapper.EasybuyUserAddressDao;
import com.utils.MyBatisUtil;

/**
 * 用户地址信息业务逻辑层实现类！
 * 
 * @author Administrator
 *
 */
@Service 
public class EasybuyUserAddressServiceImpl implements EasybuyUserAddressService {
     @Resource
     private EasybuyUserAddressDao easybuyUserAddressDao;
	@Override
	/**
	 * 根据用户ID查找对应的地址信息！
	 * 
	 * @param userId
	 * @return
	 */
	public List<EasybuyUserAddress> getEasybuyUserAddressAll(Integer userId) {
		return easybuyUserAddressDao.findEasybuyUserAddressAll(userId);
	}

	@Override
	/**
	 * 新增地址！
	 * 
	 * @param easybuyUserAddress
	 * @return
	 */
	public int addUserAddress(EasybuyUserAddress easybuyUserAddress) {
		return easybuyUserAddressDao.updateEasybuyUserAddressById(easybuyUserAddress);
	}

	@Override
	/**
	 * 根据用户ID获得相依地址信息！
	 * 
	 * @param id
	 * @return
	 */
	public EasybuyUserAddress getUserAddressById(Integer  id) {

		return easybuyUserAddressDao.getUserAddressById(id);
	}

	// ********
	@Override
	/**
	 * 根据用户Id判断该编号是否存在地址信息！
	 * 
	 * @param userId
	 * @return
	 */
	public int getUserByIdAddress(Integer userId) {

		return easybuyUserAddressDao.findUserByIdAddress(userId);
	}
	// ********
}
