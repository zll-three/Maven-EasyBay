package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyCollect;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyProduct;
import com.utils.Pager;
/**
 * 商品信息业务逻辑层！
 * @author Administrator
 *
 */
public interface ProductService {
	/**
	 * 获取�?有商品信息！
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductList();
	/**
	 * 获取�?级分类�?�记录数�?
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount(Integer categoryId);
	
	/**
	 * 获取模糊查询继续�?
	 * @param categoryName
	 * @return
	 */
	public int getProductRowCountByName(String categoryName);
	/**
	 * 获取二级分类总记录数�?
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount2(Integer categoryId);
	/**
	 * 获取三级分类总记录数�?
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount3(Integer categoryId);
	/**
	 * 获得商品�?级分类！
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId(@Param("categoryId")Integer categoryId, @Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 获得商品二级分类�?
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId2(@Param("categoryId")Integer categoryId, @Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 获取商品三级分类�?
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId3(@Param("categoryId")Integer categoryId, @Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 根据ID获取信息�?
	 * @param tid
	 * @return
	 */
	public EasybuyProduct findById(Integer tid);
	/**
	 * 查询用户收藏夹！
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByUser(Integer userId,Integer currentPageNo,Integer pageSize);
	/**
	 * 订单表！
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByOrder1111(EasybuyProduct easybuyProduct);
	
	/**
	 * 商品管理�?
	 * @param pager
	 * @return
	 */
	List<EasybuyProduct> getEasybuyProductAll(@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
	/**
	 * 获取总记录数�?
	 * @return
	 */
	public int getTotalCount();
	
	/**
	 * 根据ID删除指定商品信息�?
	 * @param id
	 * @return
	 */
	int delEasybuyProductById(Integer id);
	
	/**
	 * 商品上架/修改商品业务�?
	 * @param easybuyProduct
	 * @return
	 */
	public int addEasybuyProduct(EasybuyProduct easybuyProduct);
	
	/**
	 * 根据Id查询对应的商品信息！
	 * @param id
	 * @return
	 */
	EasybuyProduct getEasybuyProductById(Integer id);
	/**
	 * 模糊查询
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryName(@Param("categoryName")String categoryName, @Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
}
