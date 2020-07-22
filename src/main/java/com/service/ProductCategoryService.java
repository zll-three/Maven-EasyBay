package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyProductCategory;
import com.utils.Pager;
/**
 * 商品信息数据访问层！
 * @author Administrator
 *
 */
public interface ProductCategoryService {
	/**
	 * 查询商品�?
	 * @param typeId
	 * @return
	 */
	public List<EasybuyProductCategory> getProductCategoryList(Integer typeId);
	
	/**
	 * 获得�?有商品信息！
	 * @return
	 */
	List<EasybuyProductCategory> getEasybuyProductCategoryAll(@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	List<EasybuyProductCategory> getEasybuyProductCategoryAll1();
	/**
	 * 获取总记录数�?
	 * @return
	 */
	int getTotalCount();
	
	/**
	 * 根据商品ID删除指定商品信息业务�?
	 * @param id
	 * @return
	 */
	int deleteEasybuyProductCategoryById(Integer id);
	
	/**
	 *  根据id查询商品信息
	 * @param id
	 * @return
	 */
	public EasybuyProductCategory getProductCategoryById(Integer id);
	
	/**
	 * �?级分类！
	 * @return
	 */
	List<EasybuyProductCategory> getProductCategoryListOne();
	
	/**
	 * 二级分类�?
	 * @return
	 */
	List<EasybuyProductCategory> getProductCategoryListTwo();
	
	/**
	 * 三级分类�?
	 * @return
	 */
	List<EasybuyProductCategory> getProductCategoryListThree();	
	/**
	 * 根据父分类查询商品分类信�?
	 * @param typeId
	 * @return
	 */
	List<EasybuyProductCategory> getProductCategoryListByparentId(Integer typeId,Integer parentId);
	
	/**
	 * 新增商品分类
	 * @param easybuyProductCategory
	 * @return
	 */
	int insertEasybuyProductCategory(EasybuyProductCategory easybuyProductCategory);
	
	/**
	 * 根据删除的商品分类父ID编号，去查询�?遍外键表中是否有数据业务！！
	 * @param parentId
	 * @return
	 */
	int getdParentId(Integer parentId);
	/**
	 * 查询分类是否存在商品商品
	 */
	int getProductById(@Param("typeid")String typeid,@Param("id")Integer id);
	
}
