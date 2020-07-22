package com.service;

import java.util.List;
import java.util.Map;

import com.entity.EasybuyUser;
import com.utils.Pager;

/**
 * 用户信息业务逻辑层！
 * 
 * @author Administrator
 *
 */
public interface EasybuyUserService {
	/**
	 * 根据用户名和密码查询相应信息业务�?
	 * 
	 * @param easybuyUser
	 * @return
	 */
	//EasybuyUser getEasybuyUserInfo(String loginName,String password);
	EasybuyUser getEasybuyUserInfo(Map<String,String> login);

	/**
	 * 根据用户注册添加信息业务�?
	 * 
	 * @param user
	 * @return
	 */
	int addRegisterInfo(EasybuyUser easybuyUser);

	/**
	 * 获得�?有用户信息业务！
	 * 
	 * @return
	 */
	List<EasybuyUser> getEasybuyUserAll(Integer currentPageNo,Integer pageSize);
	

	/**
	 * 根据用户ID删除信息业务�?
	 * 
	 * @param id
	 * @return
	 */
	int removeEasybuyUserById(Integer id);

	/**
	 * 查询用户信息总记录数业务�?
	 * 
	 * @return
	 */
	int getTotalCount();

	/**
	 * 根据用户Id查询对应信息业务�?
	 * 
	 * @param id
	 * @return
	 */
	EasybuyUser getEasybuyUserById(Integer id);

	/**
	 * 根据用户ID更新用户信息业务�?
	 * 
	 * @return
	 */
	int updateEasybuyUserById(EasybuyUser easybuyUser);

	/**
	 * 查询是否存在相同的用户名业务�?
	 * 
	 * @param name
	 * @return
	 */
	int getLoginNameByName(String name);

	// ************
	/**
	 * 修改密码操作验证业务�?
	 * 
	 * @param name
	 * @param emailMobile
	 * @param value
	 * @return
	 */
	int getUserPasswordBy(String name, String emailMobile, String value);
	
	/**
	 * 根据用户名修改该用户密码�?
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	int modifyUserPasswordBy(String name,String password);
	// ************
}