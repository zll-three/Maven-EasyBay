package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.EasybuyNews;
import com.entity.EasybuyProduct;
import com.entity.EasybuyProductCategory;
import com.service.NewsService;
import com.service.ProductCategoryService;
import com.service.ProductService;

@Controller
public class IndexController {
     @Autowired
     private ProductCategoryService productCategoryService;
     @Resource
     private NewsService newsService;
     @Resource
     private ProductService productService;
     
     
     

        //首页显示的数据
	@RequestMapping({"index","/"})
	public String index(Model model) {
		List<EasybuyProductCategory> list =productCategoryService.getProductCategoryList(1);
		List<EasybuyProductCategory> list2=productCategoryService.getProductCategoryList(2);
		List<EasybuyProductCategory> list3=productCategoryService.getProductCategoryList(3);
		List<EasybuyNews> newsList =newsService.queryNewsList1();
		List<EasybuyProduct> productList =productService.getEasybuyProductList();
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("newsList", newsList);
		model.addAttribute("productList", productList);
		
		return "pre/Index";
		
		
	}
}
