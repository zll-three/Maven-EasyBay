package com.controller;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.entity.EasybuyCollect;
import com.entity.EasybuyProduct;
import com.entity.EasybuyProductCategory;
import com.entity.EasybuyUser;
import com.service.CartService;
import com.service.ProductCategoryService;
import com.service.ProductService;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.utils.ReturnResult;
import com.utils.StringUtils;
import com.web.AbstractServlet;

@Controller
@RequestMapping("/pro")
@WebServlet
public class ProductController extends AbstractServlet{
      @Resource
      private ProductService productService;
      @Resource
      private ProductCategoryService productCategoryService;
      @Resource
      private CartService cartService;
	
	private static final long serialVersionUID = 2249562998931818342L;
	private static final String TMP_DIR_PATH = "D:\\tmp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "/files";
	private File destinationDir;
	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);
		if (!tmpDir.exists()) {//濡傛灉鐩綍涓嶅瓨鍦紝鍒欐柊寤虹洰锟�?
			tmpDir.mkdirs();
		}
		String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		destinationDir.mkdirs();
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ " is not a directory");
		}
		
	}
	
	

	//鍟嗗搧鍒楄〃
	@RequestMapping("/index")
	public String index(Model model,@RequestParam(value = "currentPage", required = false) String currentPageStr,
			@RequestParam(value = "pageSize", required = false) String pageSize) {
		
				//椤甸潰鏉℃暟
				int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 5 : Integer.parseInt(pageSize);
				//褰撳墠锟�?
				int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
				// 鑾峰彇鎬昏褰曟暟锟�?
				int total = productService.getTotalCount();
				int pageCount=(total % rowPerPage == 0)?(total / rowPerPage):(total / rowPerPage)+1;
				Pager pager = new Pager(total, rowPerPage, currentPage);
				if(pager.getPageCount()<pager.getCurrentPage()) {
					pager.setCurrentPage(currentPage-1);
				}
				pager.setUrl("/pro/index");
				// 璁块棶涓夊眰锟�?
				List<EasybuyProduct> productList = productService.getEasybuyProductAll(currentPage,rowPerPage);
				// 鏀剧疆瀵硅薄锟�?
				model.addAttribute("productList", productList);
				model.addAttribute("pager", pager);
				model.addAttribute("menu", 5);
				
				return "/backend/product/productList";
		
		
	}
	//鐐瑰嚮鍥剧墖鏌ョ湅璇︽儏
	@RequestMapping("/queryProductDeatil/{id}")
	public String queryProductDeatil(Model model,@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response){
		
		EasybuyProduct product =productService.findById(Integer.parseInt(id));
		List<EasybuyProductCategory> list = productCategoryService.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = productCategoryService.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = productCategoryService.getProductCategoryList(3);
		List<EasybuyCollect> listCollect=getUserFromSession(request);
		if(listCollect!=null) {
			for (EasybuyCollect easybuyCollect : listCollect) {
				if(product.getId()==easybuyCollect.getProductId()) {
					request.setAttribute("easybuyCollect", easybuyCollect);
				}
				
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("product", product);
		return "/pre/product/productDeatil";
	}
	/**
	 * @param request
	 * @return
	 */
	private List<EasybuyCollect> getUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 鑾峰彇瀵硅薄锟�?
		EasybuyUser user = (EasybuyUser) session.getAttribute("easybuyUserLogin");
		List<EasybuyCollect> list=null;
		if(user!=null) {
			list=cartService.selectByUserId(user.getId());
		}
		
		return list;
	}
	//淇敼鍥炴樉鏁版嵁
	@RequestMapping("/getProduct")
	public String getProduct(Model model,@RequestParam("id") String id,
			@RequestParam("currentPage") String currentPage,HttpServletRequest request){
		// 鑾峰彇ID锟�?
				
				// 璋冪敤涓夊眰锟�?
				EasybuyProduct easybuyProduct = productService.getEasybuyProductById(Integer.parseInt(id));
				// 璋冪敤涓夊眰锟�?
				List<EasybuyProductCategory> listOne = productCategoryService.getProductCategoryListOne();
		  		List<EasybuyProductCategory> listTwo = productCategoryService.getProductCategoryListTwo();
		 		List<EasybuyProductCategory> listThree = productCategoryService.getProductCategoryListThree();
				// 鏀惧埌鍐呯疆瀵硅薄锟�?
		 		model.addAttribute("listOne", listOne);
		 		model.addAttribute("listTwo", listTwo);
		 		model.addAttribute("listThree", listThree);
		 		model.addAttribute("currentPage", currentPage);
				// 瀛樻斁鍦ㄥ唴缃璞′腑锟�?
				request.setAttribute("easybuyProduct", easybuyProduct);
				return "/backend/product/toAddProduct";
		
	
		
	}
	/**
	 * 鍟嗗搧涓夌骇鍒嗙被锛侊紒
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public void getCategoryList(HttpServletRequest request, HttpServletResponse response){
		// 璋冪敤涓夊眰锟�?
		List<EasybuyProductCategory> listOne = productCategoryService.getProductCategoryListOne();
		List<EasybuyProductCategory> listTwo = productCategoryService.getProductCategoryListTwo();
		List<EasybuyProductCategory> listThree =productCategoryService.getProductCategoryListThree();
		// 鏀惧埌鍐呯疆瀵硅薄锟�?
		request.setAttribute("listOne", listOne);
		request.setAttribute("listTwo", listTwo);
		request.setAttribute("listThree", listThree);
	}

	//璺冲埌娣诲姞鍟嗗搧椤甸潰
	@RequestMapping("/toAddUpdate")
	public String toAddUpdate(HttpServletRequest request, HttpServletResponse response) {
		getCategoryList(request, response);
		EasybuyProduct easybuyProduct = new EasybuyProduct();
		// 鏀句竴涓┖鐨勫晢鍝佷俊鎭璞★紒
		request.setAttribute("easybuyProduct", easybuyProduct);
		request.setAttribute("menu", 6);
		return "/backend/product/toAddProduct";
		
	}
	//涓婃灦鍜屼慨鏀瑰晢鍝�
	@RequestMapping(value ="/getImgs", method = RequestMethod.POST)
    @ResponseBody
    public Object getImgs(HttpServletRequest request, HttpServletResponse respons) throws Exception{
		// ************
				ReturnResult result = new ReturnResult();
				// ************
				EasybuyProduct product = new EasybuyProduct();
				String fieldName = ""; // 表单字段元素的name属性值
				// 请求信息中的内容是否是multipart类型
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);		
				if (isMultipart) {// 是多文件类型
					// 创建对象
					DiskFileItemFactory factory = new DiskFileItemFactory();
					// 设置缓冲区大小4kb
					factory.setSizeThreshold(4096);			
					factory.setRepository(tmpDir);
					ServletFileUpload upload = new ServletFileUpload(factory);
					// 设置单个文件的最大限制
					upload.setFileSizeMax(1024 * 30*1024);
					System.out.println("request"+request);
					System.out.println("内容"+upload);
					// 解析表单中所有信息
					try {
						// 获得request中的FileItem集合
						List<FileItem> items = upload.parseRequest(request);
						System.out.println("内容是否为空"+upload.parseRequest(request));
						System.out.println("内容是否为空"+items);
						Iterator<FileItem> iter = items.iterator();
						while (iter.hasNext()) {
							FileItem item = iter.next();
							if (!item.isFormField()) { // 文件表单字段
								String fileName  = item.getName().substring(
								 item.getName().lastIndexOf("."));
								File file = new File(destinationDir, fileName);
								 fileName = StringUtils.randomUUID()
								 + item.getName().substring(
								 item.getName().lastIndexOf("."));
								 
								 System.out.println("uuid"+StringUtils.randomUUID());
								 System.out.println("item.getName()"+item.getName());
								 System.out.println("item.getName()"+item.getName());
								 
								 System.out.println("item.getName().lastIndexOf"+item.getName().lastIndexOf("."));
								 
								 System.out.println(item.getName().substring( item.getName().lastIndexOf(".")));
								 
								 
								 file = new File(destinationDir, fileName);//图片名与商品ID一致
									
								// 通过Arrays类的asList()方法创建固定长度的集合
								List<String> filType = Arrays.asList("gif", "bmp", "jpg");
								String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
								if (!filType.contains(ext)) // 判断文件类型是否在允许范围内
									return result.returnFail("上传失败，文件类型只能是gif、bmp、jpg");
								else {
									if (fileName != null && !fileName.equals("")) {		
										// ************
										product.setFileName(file.getName());
										// ************
										item.write(file);//保存商品图片
										
									}
								}
							} else {// 普通表单信息
									// ************
								fieldName = item.getFieldName();
								if (fieldName != null && !fieldName.equals("")) {
									if (fieldName.equals("categoryLevel1Id")) {
										product.setCategoryLevel1(Integer.parseInt(item.getString("UTF-8")));
									} else if (fieldName.equals("categoryLevel2Id")) {
										product.setCategoryLevel2(Integer.parseInt(item.getString("UTF-8")));
									} else if (fieldName.equals("categoryLevel3Id")) {
										product.setCategoryLevel3(Integer.parseInt(item.getString("UTF-8")));
									} else if (fieldName.equals("name")) {
										product.setName(item.getString("UTF-8"));
									} else if (fieldName.equals("price")) {
										product.setPrice(Float.parseFloat(item.getString("UTF-8")));
									} else if (fieldName.equals("stock")) {
										product.setStock(Integer.parseInt(item.getString("UTF-8")));
									} else if (fieldName.equals("description")) {
										product.setDescription(item.getString("UTF-8"));
									} else if (fieldName.equals("id")) {
										System.out.println(item.getString("UTF-8"));
										product.setId(Integer.parseInt(item.getString("UTF-8")));
									}
									
								}
								// ************
							}
						}
						// 调用三层，添加数据到数据库中
										
					} catch (FileUploadBase.SizeLimitExceededException ex) {
						return result.returnFail("上传失败，文件太大，单个文件的最大限制是：" + upload.getSizeMax() + "bytes!");
					} catch (Exception e) {			
						e.printStackTrace();
					}

				}
				// 访问三层！
				int count = productService.addEasybuyProduct(product);
				// 判断是否上传成功！
				if(count>0) {
					return result.returnSuccess("操作成功！");	
				}else {
					return result.returnFail("操作失败！请联系管理员。。");
				}
	}



	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return null;
	}
                  
	
	
}
