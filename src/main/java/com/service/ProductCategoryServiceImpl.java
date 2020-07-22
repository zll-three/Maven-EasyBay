package com.service;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.entity.EasybuyProduct;
import com.entity.EasybuyProductCategory;
import com.mapper.ProductCategoryDao;
import com.utils.DataBaseUtil;
import com.utils.MyBatisUtil;
import com.utils.Pager;
/**
 * 商品信息数据访问层实现类�?
 * @author Administrator
 *
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
   private ProductCategoryDao productCategoryDao;

	
	@Override
	/**
	 * 查询商品�?
	 * @param typeId
	 * @return
	 */
	public List<EasybuyProductCategory> getProductCategoryList(Integer typeId) {
		return productCategoryDao.getProductCategoryList(typeId);
	}

	@Override
	/**
	 * 获得�?有商品信息！
	 * @return
	 */
	public List<EasybuyProductCategory> getEasybuyProductCategoryAll(Integer currentPageNo,Integer pageSize) {
		
			currentPageNo = (currentPageNo-1)*pageSize;
		
		
		return productCategoryDao.findEasybuyProductCategoryAll(currentPageNo, pageSize);
	}
	@Override
	public List<EasybuyProductCategory> getEasybuyProductCategoryAll1() {

		
		return productCategoryDao.findEasybuyProductCategoryAll1();
	}
	
	@Override
	/**
	 * 获取总记录数�?
	 * @return
	 */
	public int getTotalCount() {
		return productCategoryDao.getTotalCount();
	}

	@Override
	/**
	 * 根据商品ID删除指定商品信息业务�?
	 * @param id
	 * @return
	 */
	public int deleteEasybuyProductCategoryById(Integer id) {

		return productCategoryDao.delEasybuyProductCategoryById(id);
	}

	@Override
	/**
	 * �?级分类！
	 * @return
	 */
	public List<EasybuyProductCategory> getProductCategoryListOne() {

		
		return productCategoryDao.findProductCategoryListOne();
	}

	@Override
	/**
	 * 二级分类�?
	 * @return
	 */
	public List<EasybuyProductCategory> getProductCategoryListTwo() {
	
		
		
		return productCategoryDao.findProductCategoryListTwo();
	}

	@Override
	/**
	 * 三级分类�?
	 * @return
	 */
	public List<EasybuyProductCategory> getProductCategoryListThree() {

		
		return productCategoryDao.findProductCategoryListThree();
	}


	@Override
	/**
	 * 根据父分类查询商品分类信�?
	 * @param typeId
	 * @return
	 */
	public List<EasybuyProductCategory> getProductCategoryListByparentId(Integer typeId,Integer parentId) {
		
		
		return productCategoryDao.getProductCategoryListByparentId(typeId, parentId);
	}

	@Override
	/**
	 * 新增商品分类
	 * @param easybuyProductCategory
	 * @return
	 */
	public int insertEasybuyProductCategory(EasybuyProductCategory easybuyProductCategory) {

		return productCategoryDao.insertEasybuyProductCategory(easybuyProductCategory);
	}

	@Override
	/**
	 *  根据id查询商品信息
	 * @param id
	 * @return
	 */
	public EasybuyProductCategory getProductCategoryById(Integer id) {

		return productCategoryDao.getProductCategoryById(id);
	}

	@Override
	/**
	 * 根据删除的商品分类父ID编号，去查询�?遍外键表中是否有数据业务！！
	 * @param parentId
	 * @return
	 */
	public int getdParentId(Integer parentId) {
		return productCategoryDao.findParentId(parentId);
	}

	/**
	 * 查询分类是否存在该商�?
	 */
	public int getProductById(String type,Integer id) {
	
		return productCategoryDao.getProductById(type, id);
	}

	
	
}
