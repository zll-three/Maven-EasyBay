package com.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.entity.EasybuyProductCategory;
import com.service.ProductCategoryService;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.utils.ReturnResult;

@Controller
@RequestMapping("/pro")
public class ProductCategoryController {
      @Resource
      private ProductCategoryService productCategoryService;
      
      //分类管理页面
      @RequestMapping("/category")
      public String category(Model model,@RequestParam(value = "currentPage", required = false) String currentPageStr,
  			@RequestParam(value = "pageSize", required = false) String pageSize){
  		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
  		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
  		// 获取总页数！
  		int count = productCategoryService.getTotalCount();
  		Pager pager = new Pager(count, rowPerPage, currentPage);
  		if (pager.getPageCount() < pager.getCurrentPage()) {
  			pager.setCurrentPage(currentPage - 1);

  		}
  		List<EasybuyProductCategory> listCategoryP = productCategoryService
  				.getEasybuyProductCategoryAll1();
  		pager.setUrl("/pro/category");
  		System.out.println("路径"+pager.getUrl());
  		model.addAttribute("pager", pager);
  		model.addAttribute("menu", 4);
  		System.out.println(count+"行"+pageSize+"业"+currentPage);
  		List<EasybuyProductCategory> listCategory = productCategoryService
  				.getEasybuyProductCategoryAll(currentPage,rowPerPage);
  		model.addAttribute("listCategoryP", listCategoryP);
  		model.addAttribute("listCategory", listCategory);
  		return "backend/user/Member_Money";
      }
      //添加分类根据一级分类获取二级分类
      @RequestMapping("/getProductCategoryTwo")
      public String getProductCategoryTwo(Model model,@RequestParam(value = "parentId", required = false) String parentId) {
  		List<EasybuyProductCategory> productCategoryList2 =productCategoryService
  				.getProductCategoryListByparentId(2, Integer.parseInt(parentId));
  		// 放置对象
  		model.addAttribute("listTwo", productCategoryList2);
  		return "backend/product/toAddProduct";
    	  
      }
      //添加分类根据二级获取三级分类
      @RequestMapping("/getProductCategoryThree")
      public String getProductCategoryThree(Model model,@RequestParam(value = "parentId", required = false) String parentId) {
  		List<EasybuyProductCategory> productCategoryList3 = productCategoryService
  				.getProductCategoryListByparentId(3, Integer.parseInt(parentId));
  		model.addAttribute("listThree", productCategoryList3);
  		return "backend/product/toAddProduct";
    	  
      }
      
      //添加分类跳转
      @RequestMapping("/AddProductCategory")
      public String AddProductCategory(Model model) {
    	  List<EasybuyProductCategory> productCategoryList1 =productCategoryService
  				.getProductCategoryListOne();
    	  model.addAttribute("productCategoryList1", productCategoryList1);
  		return "backend/category/toAddProductCategory";
    	  
      }
      //根据id修改商品
      @RequestMapping("/upProductCategory")
      public String upProductCategory(Model model,@RequestParam(value = "id", required = false) String id) {
  		EasybuyProductCategory parentProductCategory = null;
  		// 根据id查询商品分类信息
  		EasybuyProductCategory productCategory =productCategoryService
  				.getProductCategoryById(Integer.parseInt(id));
  		if (productCategory.getType() == 3) {// 如果是三级分类查询父分类
  			parentProductCategory = productCategoryService
  					.getProductCategoryById(productCategory.getParentId());
  		}
  		// 一级分类列表
  		List<EasybuyProductCategory> productCategoryList1 = productCategoryService
  				.getProductCategoryListOne();
  		// 二级分类列表
  		List<EasybuyProductCategory> productCategoryList2 = productCategoryService
  				.getProductCategoryListTwo();
  		// 三级分类列表
  		List<EasybuyProductCategory> productCategoryList3 = productCategoryService
  				.getProductCategoryListThree();
  		model.addAttribute("productCategory", productCategory);
  		model.addAttribute("parentProductCategory", parentProductCategory);
  		model.addAttribute("productCategoryList1", productCategoryList1);
  		model.addAttribute("productCategoryList2", productCategoryList2);
  		model.addAttribute("productCategoryList3", productCategoryList3);
  		return "backend/category/toAddProductCategory";
    	  
    	  
      }
      //二级分类
      @RequestMapping("/addCategoryLevel2")
      public String addCategoryLevel2(Model model,@RequestParam(value = "parentId", required = false) String parentId) {
  		List<EasybuyProductCategory> productCategoryList2 = productCategoryService
  				.getProductCategoryListByparentId(2, Integer.parseInt(parentId));
  		// 放置对象
  		model.addAttribute("productCategoryList2", productCategoryList2);
  		return "backend/category/toAddProductCategory";
    	  
      }
      //新增或修改
      @RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
      @ResponseBody
      public Object insertCategory(@RequestParam(value = "id", required = false) String sid,
    		                @RequestParam(value = "name", required = false) String name,
    		                @RequestParam(value = "parentId", required = false) String parentId,
    		                @RequestParam(value = "type", required = false) String type) {
    	 
  		// 分页
  		int id = EmptyUtils.isEmpty(sid) ? 0 : Integer.parseInt(sid);
  		// System.out.println("id:"+id+"name"+name+"parentId"+parentId+"type"+type);
  		EasybuyProductCategory easybuyProductCategory = new EasybuyProductCategory(id, name, Integer.parseInt(parentId),
  				Integer.parseInt(type));
  		// 访问三层�?
  		int ins = productCategoryService.insertEasybuyProductCategory(easybuyProductCategory);
  		// 实例化向页面输出对象
  		HashMap<String, String> resultMap = new HashMap<String, String>();
  		// 判断是否操作成功
  		if (ins > 0) {
  			resultMap.put("message", "操作成功");
  			resultMap.put("status", "true");
  		} else {
  			resultMap.put("message", "操作失败");
  		}
  		return JSONArray.toJSONString(resultMap);
    	  
    	  
    	  
      }
      //删除操作分类
      @RequestMapping(value ="/delCategory", method = RequestMethod.POST)
      @ResponseBody
      public Object delCategory(@RequestParam(value = "id", required = false) String id,
    		                    @RequestParam(value = "type", required = false) String type) {
  		
  		
  		// 执行删除的时候先进去判断�?
  		int find = productCategoryService.getdParentId(Integer.parseInt(id));
  		int count = productCategoryService.getProductById(type, Integer.parseInt(id));
  		int del = -1;
  		// 实例化向页面输出对象�?
  		HashMap<String, String> resultMap = new HashMap<String, String>();
  		// 判断外键是否有数据！
  		if(find == 0&&count==0) {
  			// 调用三层
  			del = productCategoryService.deleteEasybuyProductCategoryById(Integer.parseInt(id));
  			// 判断是否删除成功
  			
  			if (del > 0) {
  				resultMap.put("message", "删除成功");
  	  			resultMap.put("status", "true");
  			}else {
  				// 删除失败！！
  				resultMap.put("message", "该分类下有商品，无法删除");
  			}
  		}else {
  		        // 删除失败！！
				resultMap.put("message", "该分类下有商品，无法删除");
  		}
  		return JSONArray.toJSONString(resultMap);
  	}

}
