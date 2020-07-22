package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyCollect;
import com.utils.ShoppingCart;
/**
 * 购物车业务�?�辑层！�?
 * @author Administrator
 *
 */
public interface CartService {
	/**
	 * 获得购物车商品所有信息！
	 * @param productId
	 * @param quantityStr
	 * @param cart
	 * @return
	 * @throws Exception
	 */
    public ShoppingCart modifyShoppingCart(String productId,String quantityStr,ShoppingCart cart) throws Exception;
    /**
     * 核算购物车的金额�?
     * @param cart
     * @return
     * @throws Exception
     */
    public ShoppingCart calculate(ShoppingCart cart)throws Exception;

	/**
	 * 查询收藏表！
	 * 
	 * @return
	 */
    List<EasybuyCollect> select();
    /**
     * 添加收藏记录�?
     * @param userId
     * @param productId
     * @param productNum
     * @param type
     * @return
     */
	/* public int addCollect(int userId, int productId,int productNum,int type); */
    public int addCollect(@Param("userId")Integer userId,
		       @Param("productId")Integer productId,
		       @Param("productNum")Integer productNum,
		       @Param("type")Integer type);
    /**
     * 根据ID查询购物车！
     * @param userId
     * @param productId
     * @return
     */
    public EasybuyCollect selectId(Integer userId,Integer productId);
    /**
     * 查询用户的收藏夹�?
     * @param userId
     * @return
     */
    public List<EasybuyCollect> selectByUserId(Integer userId);
	/**
	 * 删除收藏
	 * @param userId
	 * @param productId
	 * @return
	 */
	int delCollect(EasybuyCollect easybuyCollect);
    
}
