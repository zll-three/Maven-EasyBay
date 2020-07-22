package com.service;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.entity.EasybuyNews;
import com.entity.EasybuyUser;
import com.mapper.NewsDao;
import com.utils.DataBaseUtil;
import com.utils.MyBatisUtil;
import com.utils.Pager;
/**
 * 资讯列表业务逻辑层实现类�?
 * @author Administrator
 *
 */
@Service
public class NewsServiceImpl implements NewsService {
     @Resource
     private NewsDao newsDao;
	
	
	@Override
	/**
	 * 获取资讯列表业务�?
	 * @param pager
	 * @return
	 */
	public List<EasybuyNews> queryNewsList(Integer currentPageNo,Integer pageSize) {
		
			currentPageNo = (currentPageNo-1)*pageSize;
			
		
		return newsDao.queryNewsList(currentPageNo, pageSize);
	}
	 /**
     * 查询全部咨询
     */
	@Override
	public List<EasybuyNews> queryNewsList1() {
		
		return newsDao.queryNewsList1();
	}
	@Override
	/**
	 * 获取资讯列表总记录数业务�?
	 * @return
	 */
	public int getTotalCount() {
		
		return newsDao.getTotalCount();
	}
	
	/**
	 * 根据ID获取资讯列表详情业务�?
	 * @param id
	 * @return
	 */
	public EasybuyNews findNewsById(Integer id) {
	
		return newsDao.getNewsById(id);
	}
   

}
