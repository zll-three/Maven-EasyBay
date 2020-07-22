package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyCollect;

/**
 * 收藏列表数据访问层！！
 * 
 * @author Administrator
 *
 */
public interface EasybuyCollectDao {
	/**
	 * 收藏商品！
	 * @param userId
	 * @param productId
	 * @param productNum
	 * @param type
	 * @return
	 */
	int addCollect(@Param("userId")Integer userId,
			       @Param("productId")Integer productId,
			       @Param("productNum")Integer productNum,
			       @Param("type")Integer type);
	/**
	 * 获得收藏列表信息！
	 * @return
	 */
	List<EasybuyCollect> select();
	/**
	 * 查询商品信息！
	 * @param userId
	 * @param productId
	 * @return
	 */
	EasybuyCollect selectId(@Param("userId")Integer userId,@Param("productId")Integer productId);
	/**
	 * 根据用户查询商品信息！
	 * @param userId
	 * @return
	 */
	List<EasybuyCollect> selectByUserId(@Param("userId")Integer userId);
	/**
	 * 删除收藏
	 * @param userId
	 * @param productId
	 * @return
	 */
	int delCollect(EasybuyCollect easybuyCollect);
	
}
