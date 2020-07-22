package com.service;
import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.entity.EasybuyCollect;
import com.mapper.EasybuyCollectDao;
import com.utils.DataBaseUtil;
import com.utils.EmptyUtils;
import com.utils.MyBatisUtil;
import com.utils.ShoppingCart;
import com.utils.ShoppingCartItem;
/**
 * 购物车业务�?�辑层实现类！！
 * @author Administrator
 *
 */
@Service
public class CartServiceImpl implements CartService {
	   @Resource
	   private  EasybuyCollectDao easybuyCollectDao;
    @Override
    /**
	 * 获得购物车商品所有信息！
	 * @param productId
	 * @param quantityStr
	 * @param cart
	 * @return
	 * @throws Exception
	 */
    public ShoppingCart modifyShoppingCart(String productId, String quantityStr, ShoppingCart cart) throws Exception {
    	Integer quantity = 0;
    	if (!EmptyUtils.isEmpty(quantityStr))
            quantity = Integer.parseInt(quantityStr);
        //便利购物车寻找该商品 修改其数�?
        for (ShoppingCartItem item : cart.getItems()) {
            if (item.getProduct().getId()==(Integer.parseInt(productId))) {
                if (quantity == 0 || quantity < 0) {
                    cart.getItems().remove(item);
                    break;
                } else {
                    item.setQuantity(quantity);
                }
            }
        }
        //重新计算金额
        calculate(cart);
        return cart;
    }

    /**
     * 核算购物车的金额�?
     *
     * @param cart
     * @return
     * @throws Exception
     */
    @Override
    public ShoppingCart calculate(ShoppingCart cart) throws Exception {
        double sum = 0.0;
        for (ShoppingCartItem item : cart.getItems()) {
            sum = sum + item.getQuantity() * item.getProduct().getPrice();
            item.setCost(item.getQuantity() * item.getProduct().getPrice());
        }
        cart.setSum(sum);
        return cart;
    }

    /**
     * 添加收藏�?
     */
	@Override
	public int addCollect(Integer userId, Integer productId, Integer productNum, Integer type) {
		return easybuyCollectDao.addCollect(userId, productId, productNum, type);
	}

	@Override
	 /**
     * 查询收藏表！
     * @return
     */
	public List<EasybuyCollect> select() {
		return easybuyCollectDao.select();
	}

	@Override
	/**
     * 根据ID查询购物车！
     * @param userId
     * @param productId
     * @return
     */
	public EasybuyCollect selectId(Integer userId, Integer productId) {
	
				return easybuyCollectDao.selectId(userId, productId);
			}

	@Override
	/**
     * 查询用户的收藏夹�?
     * @param userId
     * @return
     */
	public List<EasybuyCollect> selectByUserId(Integer userId) {
		
		return easybuyCollectDao.selectByUserId(userId);
	}

	@Override
	public int delCollect(EasybuyCollect easybuyCollect) {
		
		return easybuyCollectDao.delCollect(easybuyCollect);
	}
	
	
	
}
