package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.EasybuyUser;
import com.service.EasybuyOrderService;
import com.service.EasybuyUserAddressService;
import com.service.EasybuyUserService;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.utils.ReturnResult;
import com.utils.SecurityUtils;

@Controller
@RequestMapping("/pre")
public class EasybuyUserController {
	 @Resource
	 private EasybuyUserService easybuyUserService;
	 @Resource
	 private EasybuyOrderService easybuyOrderService;
	 @Resource
	 private EasybuyUserAddressService easybuyUserAddressService;
	      //跳转到注册页面
          @RequestMapping("/Regist")
          public String Regist() {
			return "/pre/Regist";
        	  
          }
          /**
      	 * 加载用户列表
      	 * 
      	 * @param request
      	 * @param response
      	 * @return
      	 * @throws Exception
      	 */
        @RequestMapping("/userList")
      	public String user(Model model,@RequestParam(value = "currentPage", required = false) String currentPageStr,
      			@RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
      		List<EasybuyUser> list=new ArrayList<EasybuyUser>();
      		// 获取当前页数
      		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
      		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
      		// 获取总页数！
      		int count =easybuyUserService.getTotalCount();
      		Pager pager = new Pager(count, rowPerPage, currentPage);
      		System.out.println("路径"+pager.getUrl());
      		if (pager.getPageCount() < pager.getCurrentPage()) {
      			pager.setCurrentPage(currentPage - 1);

      		}      		
      		pager.setUrl("/pre/userList");

      		model.addAttribute("pager", pager);
      		model.addAttribute("menu", 8);
      		System.out.println(count+"行"+pageSize+"业"+currentPage);
      		list =easybuyUserService.getEasybuyUserAll(currentPage,rowPerPage);
      		model.addAttribute("list", list);   		  		
      		return "/backend/user/Member_Packet";
      		
      	}
        
        /**
    	 * 传递参数到修改信息窗口
    	 *
    	 * @param request
    	 * @param response
    	 * @return
    	 * @throws Exception
    	 */
        @RequestMapping("/toUpdateUser")
    	public String toUpdateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
    		        //声明用户类
    		        EasybuyUser user =new EasybuyUser();
    		        //获取id
    				String ID = request.getParameter("id");
    				//转换成int类型
    				int id=Integer.parseInt(ID);
    				String currentPage = request.getParameter("currentPage");
    				//接受结果
    			    user= easybuyUserService.getEasybuyUserById(id);
    				request.setAttribute("user", user);
    				request.setAttribute("currentPage", currentPage);
    				return "/backend/user/toUpdateUser";
    	}
        /**
    	 * 修改用户信息/添加用户信息！
    	 * 
    	 * @param request
    	 * @param response
    	 * @return
    	 * @throws Exception
    	 */
        @RequestMapping(value ="/modify", method = RequestMethod.POST)
        @ResponseBody
    	public Object modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
    		        //实例化向页面输出对象
    				ReturnResult result = new ReturnResult();
    				//创建servic对象
    				EasybuyUser easybuyUser = new EasybuyUser();
    				//获取数据
    				String loginName=request.getParameter("name");
    				int id=Integer.parseInt(request.getParameter("id"));
    				String userName=request.getParameter("userName");
    				String password=SecurityUtils.md5Hex(request.getParameter("password"));
    				int sex=Integer.parseInt(request.getParameter("sex"));
    				String identityCode=request.getParameter("identityCode");
    				String email=request.getParameter("email");
    				String mobile=request.getParameter("mobile");
    				int user=Integer.parseInt(request.getParameter("user"));
    				//判断id是否为空,如果id空就添加,不等于就修改
    				System.out.println(id);
    				if (id == 0) {
    					int count=easybuyUserService.getLoginNameByName(loginName);
    					//判断用户是否存在
    					if(count==1) {
    						 result.returnFail("用户"+loginName+"已存在");
    					}else {
    						easybuyUser.setId(id);
    						easybuyUser.setLoginName(loginName);
    						easybuyUser.setUserName(userName);
    						easybuyUser.setPassword(password);
    						easybuyUser.setSex(sex);
    						easybuyUser.setIdentityCode(identityCode);
    						easybuyUser.setEmail(email);
    						easybuyUser.setMobile(mobile);
    						easybuyUser.setType(user);
    						//添加方法
    						int add = easybuyUserService.addRegisterInfo(easybuyUser);			
    						//判断是否添加成功
    						if (add > 0) {
    							result.returnSuccess("添加用户成功");
    						} else {
    							result.returnFail("操作失败!请联系管理员..");
    						}
    					}
    				}else {
    					//修改操作
    					easybuyUser.setId(id);
    					easybuyUser.setLoginName(loginName);
    					easybuyUser.setUserName(userName);    					
    					easybuyUser.setSex(sex);
    					easybuyUser.setIdentityCode(identityCode);
    					easybuyUser.setEmail(email);
    					easybuyUser.setMobile(mobile);
    					easybuyUser.setType(user);
    					//修改方法
    					int up =easybuyUserService.updateEasybuyUserById(easybuyUser);		
    					//判断是否修改成功
    					if (up > 0) {
    						result.returnSuccess("修改用户成功");
    					} else {
    						result.returnFail("操作失败!请联系管理员..");
    					}
    				}
    				return result;
    		
    		
    	}
        /**
    	 * 点击新增用户！
    	 * 
    	 * @param request
    	 * @param response
    	 * @return
    	 * @throws Exception
    	 */
        @RequestMapping("/add")
    	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    		       //封装成对象
    				EasybuyUser easybuyUser = new EasybuyUser();
    				request.setAttribute("user", easybuyUser);
    		        //跳转页面
    		      return "/backend/user/toUpdateUser";

    	}
        /**
    	 * 用户详情！
    	 * 
    	 * @param request
    	 * @param response
    	 * @return
    	 * @throws Exception
    	 */
        @RequestMapping("/user/index")
    	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
    		// 封装成对象！
    				EasybuyUser easybuyUser = new EasybuyUser();
    				request.setAttribute("user", easybuyUser);
    				request.setAttribute("menu", 2);
    				// 调用三层！
    				return "/backend/user/Member_User";
    	}
        /**
    	 * 删除
    	 * 
    	 * @param request
    	 * @param response
    	 * @return
    	 * @throws Exception
    	 */
        @RequestMapping("/del")
        @ResponseBody
    	public ReturnResult del(HttpServletRequest request, HttpServletResponse response) throws Exception {
    		ReturnResult result = new ReturnResult();
    		// 获取要删除的用户id
    		int id = Integer.parseInt(request.getParameter("id"));
    		//获得姓名
    		String name =request.getParameter("name");
    		//查询是否有订单
    		int order = easybuyOrderService.getUserByIdOrder(id);
    		//System.out.println(find);
    		//查询该用户账户下是否有地址
    		int address = easybuyUserAddressService.getUserByIdAddress(id);
    		// 声明变量接受返回结果
    		int del = -1;
    		// 判断是否有订单
    		if (order == 0) {
    			//查询该用户账户下是否有地址
    			if (address == 0) {
    				del = easybuyUserService.removeEasybuyUserById(id);
    				// 判断是否删除成功
    				if (del > 0) {
    					result.returnSuccess("用户"+name+"删除成功");
    				}
    			} else {
    				// 删除失败
    				result.returnFail("用户"+name+"还有地址信息，无法删除！");
    			}
    		} else {
    			// 删除失败！！
    			    result.returnFail("用户"+name+"还有订单信息，无法删除！");
    		}
    		return result;
    	}

          //注册用户
          @RequestMapping("/addUser")
          @ResponseBody
          public ReturnResult addUser(HttpServletRequest request, HttpServletResponse response){
        	  ReturnResult result = new ReturnResult();
      		// 实例化用户信息对象！
      		EasybuyUser easybuyUser = new EasybuyUser();
      		// 获取ajax中传递过来的数据！
      		easybuyUser.setLoginName(request.getParameter("name"));
      		easybuyUser.setPassword(SecurityUtils.md5Hex(request.getParameter("password")));
      		easybuyUser.setUserName(request.getParameter("numName"));
      		String sex = request.getParameter("sex");
      		easybuyUser.setSex(Integer.parseInt(sex));
      		easybuyUser.setIdentityCode(request.getParameter("mem"));
      		easybuyUser.setEmail(request.getParameter("email"));
      		easybuyUser.setMobile(request.getParameter("phone"));
      		// 调用三层方法！
      		int tempInser =easybuyUserService.addRegisterInfo(easybuyUser);
      		// 判断是否注册成功！
      		if (tempInser > 0) {
      			return result.returnSuccess("注册成功");
      		} else {
      			return result.returnFail("注册失败");

      		}
          }
          @RequestMapping("/loginNameCount")
          @ResponseBody
          public ReturnResult loginNameCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
      		ReturnResult result = new ReturnResult();
      		// 获取用户昵称！
      		String name = request.getParameter("name");
      		// 调用三层！
      		int count =easybuyUserService.getLoginNameByName(name);
      		// 判断是否注册成功！
      		if (count > 0) {
      			result.returnFail("该用户名已经存在");
      		} else {
      			result.returnSuccess("用户名可用");
      		}
      		return result;
      	}
          //忘记密码跳转页面
          @RequestMapping("/toUpdatePwd")  
         public String toUpdatePwd() {
        	  
			return "/backend/userPassWord/toUpdatePwd";
        	  
          } 
          @RequestMapping("/findLoginNamePassword")  
          public String findLoginNamePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		        // 获取用户昵称！
				String name = request.getParameter("name");
				// 放置内置对象！
				request.setAttribute("name", name);
				return "/backend/userPassWord/forgetPwdTwo";
	}
          /**
      	 * 查询邮箱或者手机输入是否输入正确！
      	 * 
      	 * @param request
      	 * @param response
      	 * @return
      	 * @throws Exception
      	 */
          @RequestMapping("/loginNameBy")
          @ResponseBody
      	public ReturnResult loginNameBy(HttpServletRequest request, HttpServletResponse response) throws Exception {
      		ReturnResult result = new ReturnResult();
      		// 获取用户昵称！
      		String name = request.getParameter("name");
      		// 获取用户输入的数据！
      		String moName = request.getParameter("moName");
      		String valu = request.getParameter("valu");
      		// 调用三层！
      		int count = easybuyUserService.getUserPasswordBy(name, moName, valu);
      		// 判断是否注册成功！
      		if (count > 0) {
      			return result.returnSuccess("操作成功！");
      		} else {
      			return result.returnFail("信息不正确！");
      		}
      	}
          /**
      	 * 跳转至设置新密码！
      	 * 
      	 * @param request
      	 * @param response
      	 * @return
      	 * @throws Exception
      	 */
          @RequestMapping("/modifyPwd")
      	public String modifyPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
      		// 获取用户昵称！
      		String name = request.getParameter("name");
      		// 放置内置对象！
      		request.setAttribute("name", name);
      		return "/backend/userPassWord/forgetPwdThree";
      	}
          /**
      	 * 修改密码！
      	 * 
      	 * @param request
      	 * @param response
      	 * @return
      	 * @throws Exception
      	 */
          @RequestMapping("/modifyPassWord")
          @ResponseBody
      	public ReturnResult modifyPassWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
      		ReturnResult result = new ReturnResult();
      		// 获取用户输入的数据！
      		String name = request.getParameter("name");
      		String password = SecurityUtils.md5Hex(request.getParameter("password"));
      		// 访问三层！
      		int modify = easybuyUserService.modifyUserPasswordBy(name, password);
      		// 判断是否注册成功！
      		if (modify > 0) {
      			return result.returnSuccess("操作成功！");
      		} else {
      			return result.returnFail("修改失败！");
      		}
      	}
          /**
      	 * 跳转至设置修改成功页面！！
      	 * 
      	 * @param request
      	 * @param response
      	 * @return
      	 * @throws Exception
      	 */
          @RequestMapping("/forgetPwd")
      	public String forgetPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
      		// 获取用户昵称！
      		String name = request.getParameter("name");
      		// 放置内置对象！
      		request.setAttribute("name", name);
      		return "/backend/userPassWord/forgetPwdLast";
      	}
}
