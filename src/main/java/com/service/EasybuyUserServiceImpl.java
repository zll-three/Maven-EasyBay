package com.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.EasybuyUser;
import com.mapper.UserMapper;
import com.utils.MyBatisUtil;
import com.utils.Pager;


/**
 * 鐢ㄦ埛淇℃伅涓氬姟閫昏緫灞傛搷浣滐紒
 * 
 * @author Administrator
 *
 */
@Service
public class EasybuyUserServiceImpl implements EasybuyUserService {
	@Autowired
    private UserMapper userMapper;
	
	@Override
	/**
	 * 鏍规嵁鐢ㄦ埛鍚嶅拰瀵嗙爜鏌ヨ鐩稿簲淇℃伅涓氬姟锟�?
	 */
	public EasybuyUser getEasybuyUserInfo(Map<String, String> login) {
		return userMapper.findEasybuyUserInfo(login);
	}
	/*
	 * public EasybuyUser getEasybuyUserInfo(String loginName,String password) {
	 * Connection conn = DataBaseUtil.getConnection(); EasybuyUserDao eu=new
	 * EasybuyUserDaoImpl(conn); EasybuyUser easybuyUserLogin =
	 * eu.findEasybuyUserInfo(loginName, password); DataBaseUtil.closeAll(null,
	 * null, conn); return easybuyUserLogin; }
	 */

	/**
	 * 瀹炵幇鐢ㄦ埛娉ㄥ唽涓氬姟鎿嶄綔锟�?
	 */
	@Override
	public int addRegisterInfo(EasybuyUser easybuyUser) {

		return userMapper.addRegisterInfo(easybuyUser);
	}

	
	/**
	 * 鑾峰緱锟�?鏈夌敤鎴蜂俊鎭笟鍔★紒
	 * 
	 * @return
	 */
	@Override
	public List<EasybuyUser> getEasybuyUserAll(Integer currentPageNo, Integer pageSize) {
		
		
		return userMapper.findEasybuyUserAll(currentPageNo, pageSize);
	}
	

	@Override
	/**
	 * 鏍规嵁鐢ㄦ埛ID鍒犻櫎淇℃伅锟�?
	 * 
	 * @param id
	 * @return
	 */
	public int removeEasybuyUserById(Integer id) {
		
		return userMapper.delEasybuyUserById(id);
	}

	@Override
	/**
	 * 鑾峰彇鐢ㄦ埛淇℃伅鎬昏褰曟暟涓氬姟锟�?
	 */
	public int getTotalCount() {
		
		return userMapper.getTotalCount();
	}

	@Override
	/**
	 * 鏍规嵁鐢ㄦ埛Id鏌ヨ瀵瑰簲淇℃伅锟�?
	 * 
	 * @param id
	 * @return
	 */
	public EasybuyUser getEasybuyUserById(Integer id) {
		
		return userMapper.findEasybuyUserById(id);
	}

	@Override
	/**
	 * 鏍规嵁鐢ㄦ埛ID鏇存柊鐢ㄦ埛淇℃伅锟�?
	 * 
	 * @return
	 */
	public int updateEasybuyUserById(EasybuyUser easybuyUser) {
		System.out.println("是否进入");
		return userMapper.modifyEasybuyUserById(easybuyUser);
	}

	@Override
	/**
	 * 鏌ヨ鏄惁瀛樺湪鐩稿悓鐨勭敤鎴峰悕涓氬姟锟�?
	 * 
	 * @param name
	 * @return
	 */
	public int getLoginNameByName(String name) {
		
		return userMapper.findLoginNameByName(name);
	}

	// ************
	@Override
	/**
	 * 淇敼瀵嗙爜鎿嶄綔楠岃瘉涓氬姟锟�?
	 * 
	 * @param name
	 * @param emailMobile
	 * @param value
	 * @return
	 */
	public int getUserPasswordBy(String name, String emailMobile, String value) {
	
		return userMapper.findUserPasswordBy(name, emailMobile, value);
	}
	// ************

	@Override
	/**
	 * 鏍规嵁鐢ㄦ埛鍚嶄慨鏀硅鐢ㄦ埛瀵嗙爜锟�?
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public int modifyUserPasswordBy(String name, String password) {
	
		return userMapper.updateUserPasswordBy(name, password);
	}
	

	
}
