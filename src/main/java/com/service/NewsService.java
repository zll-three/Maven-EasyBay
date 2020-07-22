package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyNews;
import com.utils.Pager;
/**
 * 资讯列表业务逻辑层！
 * @author Administrator
 *
 */
public interface NewsService {
	/**
	 * 获取资讯列表业务�?
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
	 * 获取资讯列表总记录数业务�?
	 * @return
	 */
	public int getTotalCount();
	/**
	 * 根据ID获取资讯列表详情业务�?
	 * @param id
	 * @return
	 */
	public EasybuyNews findNewsById(Integer id);
	
}
