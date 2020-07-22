package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyCollect;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyProduct;
import com.utils.Pager;
/**
 * 商品信息数据访问层！
 * @author Administrator
 *
 */
public interface ProductDao {
	/**
	 * 获取所有商品信息！
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductList();
	/**
	 * 获取一级分类总记录数！
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount(Integer categoryId);
	/**
	 * 获取二级分类总记录数！
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount2(Integer categoryId);
	/**
	 * 获取三级分类总记录数！
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount3(Integer categoryId);
	/**
	 * 获取一级分类所有商品信息！
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId(@Param("categoryId")Integer categoryId, @Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 获取二级分类所有商品信息！
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId2(@Param("categoryLevel2")Integer categoryId, @Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 获取三级分类所有商品信息！
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId3(@Param("categoryLevel3")Integer categoryId, @Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 根据商品编号查询商品信息！
	 * @param id
	 * @return
	 */
	public EasybuyProduct getById(Integer id);
	/**
	 * 修改商品库存信息！
	 * @param id
	 * @param quantity
	 */
	public void updateStock(@Param("id")Integer id, @Param("quantity")Integer quantity);
	/**
	 * 查询用户收藏列表！
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByUser(@Param("userId")Integer userId,@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 查询订单表！
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByOrder11(EasybuyProduct easybuyProduct);
	
	/**
	 * 商品管理！
	 * @param pager
	 * @return
	 */
	List<EasybuyProduct> findEasybuyProductAll(@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
	/**
	 * 获取总记录数！
	 * @return
	 */
	public int findTotalCount();
	
	/**
	 * 根据ID删除指定商品信息！
	 * @param id
	 * @return
	 */
	int deleteEasybuyProductById(Integer id);
	
	/**
	 * 商品上架！
	 * @param easybuyProduct
	 * @return
	 */
	int insertEasybuyProduct(EasybuyProduct easybuyProduct);
	
	/**
	 * 根据Id查询对应的商品信息！
	 * @param id
	 * @return
	 */
	EasybuyProduct findEasybuyProductById(Integer id);
	
	/**
	 * 获取模糊查询继续数
	 * @param categoryName
	 * @return
	 */
	public int getProductRowCountByName(@Param("categoryName")String categoryName);
	/**
	 * 模糊查询
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryName(@Param("categoryName")String categoryName, @Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
}
