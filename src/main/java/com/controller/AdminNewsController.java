package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.EasybuyNews;
import com.service.NewsService;
import com.utils.EmptyUtils;
import com.utils.Pager;

@Controller
public class AdminNewsController {
      @Resource
      private NewsService newsService;
	
	@RequestMapping("/queryNewsList")
	public String queryNewsList(Model model,@RequestParam(value = "currentPage", required = false) String currentPageStr,
			@RequestParam(value = "pageSize", required = false) String pageSize){
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int count =newsService.getTotalCount();
		Pager pager = new Pager(count, rowPerPage, currentPage);
		if(pager.getPageCount()<pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage-1);		
		}
		pager.setUrl("/queryNewsList");
		List<EasybuyNews> newsList =newsService.queryNewsList(currentPage, rowPerPage);
		// 放置内置对象
		model.addAttribute("newsList", newsList);
		model.addAttribute("pager", pager);
		model.addAttribute("menu", 7);
		
		
		return "/backend/news/newsList";
	}
	
	@RequestMapping("/newsDeatil")
	public String newsDeatil(Model model,@RequestParam(value = "id", required = false) String id) {
		
		EasybuyNews news=newsService.findNewsById(Integer.parseInt(id));
		model.addAttribute("news", news);
		model.addAttribute("menu", 7);
		return "/backend/news/newsDetail";
		
		
		
	}
	
	
	
}
