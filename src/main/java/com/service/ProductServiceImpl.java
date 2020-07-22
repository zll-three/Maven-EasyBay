package com.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.entity.EasybuyCollect;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyProduct;
import com.mapper.ProductDao;
import com.utils.DataBaseUtil;
import com.utils.MyBatisUtil;
import com.utils.Pager;

/**
 * 商品信息业务逻辑层实现类�?
 * 
 * @author Administrator
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
     @Resource
     private ProductDao productDao;
	

	@Override
	/**
	 * 获取�?有商品信息！
	 * 
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductList() {
		return productDao.getEasybuyProductList();
	}

	@Override
	/**
	 * 获取�?级分类�?�记录数�?
	 * 
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount(Integer categoryId) {
		return productDao.getProductRowCount(categoryId);
	}

	/**
	 * 获取二级分类总记录数�?
	 * 
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount2(Integer categoryId) {
		return productDao.getProductRowCount2(categoryId);
	}

	/**
	 * 获取三级分类总记录数�?
	 * 
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount3(Integer categoryId) {
		return productDao.getProductRowCount3(categoryId);
	}

	@Override
	/**
	 * 获得商品�?级分类！
	 * 
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId(Integer categoryId,Integer currentPageNo,Integer pageSize) {
	
			currentPageNo = (currentPageNo-1)*pageSize;
		
		
		return productDao.findEasybuyProductAll(currentPageNo, pageSize);
	}

	/**
	 * 获得商品二级分类�?
	 * 
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId2(Integer categoryId,Integer currentPageNo,Integer pageSize) {
		
			currentPageNo = (currentPageNo-1)*pageSize;
	
		return productDao.getEasybuyProductListByCategoryId2(categoryId, currentPageNo, pageSize);
	}

	/**
	 * 获得商品三级分类�?
	 * 
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId3(Integer categoryId,Integer currentPageNo,Integer pageSize) {
		
			currentPageNo = (currentPageNo-1)*pageSize;
	
		
		return productDao.getEasybuyProductListByCategoryId3(categoryId, currentPageNo, pageSize);
	}

	@Override
	/**
	 * 根据ID获取信息�?
	 * 
	 * @param tid
	 * @return
	 */
	public EasybuyProduct findById(Integer tid) {

		return productDao.getById(tid);
	}

	@Override
	/**
	 * 查询用户收藏夹！
	 * 
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByUser(Integer userId,Integer currentPageNo,Integer pageSize) {
		
			currentPageNo = (currentPageNo-1)*pageSize;

		return productDao.getEasybuyProductListByUser(userId,currentPageNo,pageSize);
	}

	@Override
	/**
	 * 订单表！
	 * 
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByOrder1111(EasybuyProduct easybuyProduct) {

		
		return productDao.getEasybuyProductListByOrder11(easybuyProduct);
	}

	// ***************************
	@Override
	/**
	 * 商品管理�?
	 * 
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductAll(Integer currentPageNo,Integer pageSize ) {

			currentPageNo = (currentPageNo-1)*pageSize;
			
		
		return productDao.findEasybuyProductAll(currentPageNo, pageSize);
	}

	@Override
	/**
	 * 获取总记录数�?
	 */
	public int getTotalCount() {

		return productDao.findTotalCount();
	}

	@Override
	/**
	 * 根据ID删除指定商品信息�?
	 */
	public int delEasybuyProductById(Integer id) {

		return productDao.deleteEasybuyProductById(id);
	}

	@Override
	/**
	 * 商品上架/修改商品业务�?
	 * 
	 * @param easybuyProduct
	 * @return
	 */
	public int addEasybuyProduct(EasybuyProduct easybuyProduct) {
	
		return productDao.insertEasybuyProduct(easybuyProduct);
	}

	@Override
	/**
	 * 根据Id查询对应的商品信息！
	 * 
	 * @param id
	 * @return
	 */
	public EasybuyProduct getEasybuyProductById(Integer id) {
		
		return productDao.findEasybuyProductById(id);
	}

	//获取模糊查询继续�?
	public int getProductRowCountByName(String categoryName) {
	
		return productDao.getProductRowCountByName(categoryName);
	}

	//模糊查询
	public List<EasybuyProduct> getEasybuyProductListByCategoryName(String categoryName,Integer currentPageNo,Integer pageSize) {

			currentPageNo = (currentPageNo-1)*pageSize;
			
		return productDao.getEasybuyProductListByCategoryName(categoryName, currentPageNo, pageSize);
	}

}
