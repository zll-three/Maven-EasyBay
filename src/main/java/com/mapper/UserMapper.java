package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyUser;
import com.utils.Pager;
/**
 * 用户信息数据访问层！
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * 根据用户名和密码查询对应信息！
	 * @param easybuyUser
	 * @return
	 */
	//EasybuyUser findEasybuyUserInfo(String loginName,String password);
	EasybuyUser findEasybuyUserInfo(Map<String,String> login);
	/**
	 * 根据用户信息注册
	 * @return
	 */
	int addRegisterInfo(EasybuyUser easybuyUser);
	/**
	 * 查询所有用户信息！
	 * @return
	 */
	List<EasybuyUser> findEasybuyUserAll(@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
	/**
	 * 根据用户ID删除信息！
	 * @param id
	 * @return
	 */
	int delEasybuyUserById(Integer id);
	
	/**
	 * 查询用户信息总记录数！
	 * @return
	 */
	int getTotalCount();
	
	/**
	 * 根据用户Id查询对应信息！
	 * @param id
	 * @return
	 */
	EasybuyUser findEasybuyUserById(Integer id);
	
	/**
	 * 根据用户ID更新用户信息！
	 * @return
	 */
	int modifyEasybuyUserById(EasybuyUser easybuyUser);
	
	/**
	 * 查询是否存在相同的用户名！
	 * @param name
	 * @return
	 */
	int findLoginNameByName(String name);
	
	//************
	/**
	 * 修改密码操作验证！
	 * @param name
	 * @param emailMobile
	 * @param value
	 * @return
	 */
	int findUserPasswordBy(@Param("name")String name,@Param("email")String emailMobile,@Param("value")String value);
	
	/**
	 * 根据用户名修改该用户密码！
	 * @param name
	 * @param password
	 * @return
	 */
	int updateUserPasswordBy(@Param("name")String name,@Param("password")String password);
	//************
}
