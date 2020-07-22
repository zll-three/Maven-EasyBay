package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyNews;
import com.utils.Pager;

/**
 * 资讯列表数据访问层！
 * 
 * @author Administrator
 *
 */
public interface NewsDao {
	/**
	 * 查询所有资讯列表！
	 * 
	 * @param pager
	 * @return
	 */
	public List<EasybuyNews> queryNewsList(@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
     /**
      * 查询全部咨询
      * 
      */
	public List<EasybuyNews> queryNewsList1();
	/**
	 * 获取资讯列表总记录数！
	 * 
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 根据ID查询资讯列表详情！
	 * 
	 * @param id
	 * @return
	 */
	public EasybuyNews getNewsById(Integer id);
}
