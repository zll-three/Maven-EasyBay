package com.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.EasybuyCollect;
import com.entity.EasybuyOrder;
import com.entity.EasybuyProduct;
import com.entity.EasybuyProductCategory;
import com.entity.EasybuyUser;
import com.entity.EasybuyUserAddress;
import com.service.CartService;
import com.service.EasybuyOrderService;
import com.service.EasybuyUserAddressService;
import com.service.ProductCategoryService;
import com.service.ProductService;
import com.utils.Constants;
import com.utils.EmptyUtils;
import com.utils.ReturnResult;
import com.utils.ShoppingCart;
import com.utils.ShoppingCartItem;

@Controller
@RequestMapping("/cart")
public class CartController {
       @Resource
       private ProductService productService;
       @Resource
       private CartService cartService; 
       @Resource
       private  ProductCategoryService productCategoryService;
       @Resource
       private EasybuyUserAddressService easybuyUserAddressService; 
       @Resource
       private EasybuyOrderService easybuyOrderService;
       
         
	/**
	 * 添加到购物车！
	 *
	 * @return
	 */
    @RequestMapping("/add")
    @ResponseBody
	public ReturnResult add(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ReturnResult result = new ReturnResult();
		// 获取用户信息
		EasybuyUser user = getUserFromSession(request);
		if (user == null) {
			return result.returnFail("您未登录，请先登录！");
		}
		String id = request.getParameter("entityId");
		String quantityStr = request.getParameter("quantity");
		Integer quantity = 1;
		if (!EmptyUtils.isEmpty(quantityStr))
			quantity = Integer.parseInt(quantityStr);
		// 查询出商品
		EasybuyProduct product = productService.findById(Integer.parseInt(id));
		if (product.getStock() < quantity) {
			return result.returnFail("商品数量不足");
		}
		// 获取购物车
		ShoppingCart cart = getCartFromSession(request);
		// 往购物车放置商品
		result = cart.addItem(product, quantity);
		if (result.getStatus() == Constants.ReturnResult.SUCCESS) {
			cart.setSum(
					(EmptyUtils.isEmpty(cart.getSum()) ? 0.0 : cart.getSum()) + (product.getPrice() * quantity * 1.0));
		}
		return result;
	}

	/**
	 * 添加到收藏！
	 *
	 * @return
	 */
    @RequestMapping("/addCollect")
    @ResponseBody
	public ReturnResult addCollect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// HttpSession session = request.getSession();
		ReturnResult result = new ReturnResult();

		// 获取用户信息
		EasybuyUser user = getUserFromSession(request);

		if (user == null) {
			return result.returnFail("您未登录，请先登录！");
		}
		String id = request.getParameter("id");
		// 查询出商品
		EasybuyProduct product = productService.findById(Integer.parseInt(id));
    
		String typeId = request.getParameter("type");
		int type = Integer.parseInt(typeId);
		if (type == 0) {
			// 访问三层！
			EasybuyCollect easybuyCollect =cartService.selectId(user.getId(), product.getId());
			if (easybuyCollect == null) {
				int count = cartService.addCollect(user.getId(), product.getId(), 0, type);
				if (count > 0) {
					result.returnSuccess("收藏成功");
				} else {
					result.returnFail("收藏失败");
				}
			} else {
				int count=cartService.delCollect(easybuyCollect);
				if(count>0) {
					result.returnSuccess("成功取消收藏");
				}else {
					result.returnFail("取消失败");
				}
				
			}
		}
		return result;
	}

	/**
	 * 刷新购物车！
	 *
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/refreshCart")
	public String refreshCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = cartService.calculate(cart);
		session.setAttribute("cart", cart);// 全部的商品
		return "/common/pre/searchBar";
	}

	/**
	 * 从session中获取购物车
	 *
	 * @param request
	 * @return
	 */
    @RequestMapping("/getCartFromSession")
	private ShoppingCart getCartFromSession(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		// 获取对象！
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}

	/**
	 * 跳到结算页面！
	 *
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/toSettlement")
	public String toSettlement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 访问三层！
		List<EasybuyProductCategory> list = productCategoryService.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = productCategoryService.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = productCategoryService.getProductCategoryList(3);
		// 放置对象！
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		return "/pre/settlement/toSettlement";

	}

	/**
	 * 跳转到购物车页面！
	 *
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/settlement1")
	public String settlement1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = cartService.calculate(cart);
		// 放置对象！
		request.getSession().setAttribute("cart", cart);
		return "/pre/settlement/settlement1";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/settlement2")
	public String settlement2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EasybuyUser user = getUserFromSession(request);
		// 访问三层！
		List<EasybuyUserAddress> userAddressList = easybuyUserAddressService
				.getEasybuyUserAddressAll(user.getId());// 获取用户收货地址
		// 放置对象！
		request.setAttribute("userAddressList", userAddressList);
		return "/pre/settlement/settlement2";
	}

	/**
	 * 生成订单！
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping("/settlement3")
    @ResponseBody
	public Object settlement3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = cartService.calculate(cart);
		EasybuyUser user = getUserFromSession(request);
		ReturnResult result = checkCart(request);
		if (result.getStatus() == Constants.ReturnResult.FAIL) {
			return result;
		}
		// 新增地址
		String addressId = request.getParameter("addressId");
		String newAddress = request.getParameter("newAddress");
		String newRemark = request.getParameter("newRemark");
		// 创建对象！
		EasybuyUserAddress userAddress = new EasybuyUserAddress();
		if (addressId.equals("-1")) {
			HttpSession session = request.getSession();
			EasybuyUser user1 = (EasybuyUser) session.getAttribute("easybuyUserLogin");
			userAddress.setRemark(newRemark);
			userAddress.setAddress(newAddress);
			userAddress.setUserId(user1.getId());
			userAddress.setId(easybuyUserAddressService.addUserAddress(userAddress));
		} else {
			userAddress = easybuyUserAddressService.getUserAddressById(Integer.parseInt(addressId));
		}

		// 生成订单
		EasybuyOrder order = easybuyOrderService.payShoppingCart(cart, user, userAddress.getAddress());
		clearCart(request, response);
		request.setAttribute("currentOrder", order);
		return "/pre/settlement/settlement3";
	}

	/**
	 * 修改购物车信息
	 *
	 * @param request
	 * @return
	 */
    @RequestMapping("/modCart")
    @ResponseBody
	public ReturnResult modCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 创建对象！
		HttpSession session = request.getSession();
		// 获取ID！
		String id = request.getParameter("entityId");
		String quantityStr = request.getParameter("quantity");
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		EasybuyProduct product = productService.findById(Integer.parseInt(id));
		if (EmptyUtils.isNotEmpty(quantityStr)) {
			if (Integer.parseInt(quantityStr) > product.getStock()) {
				return result.returnFail("商品数量不足");
			}
		}
		// 访问三层！
		cart = cartService.modifyShoppingCart(id, quantityStr, cart);
		session.setAttribute("cart", cart);// 全部的商品
		return result.returnSuccess();
	}

	/**
	 * @param request
	 * @return
	 */
    @RequestMapping("/getUserFromSession")
	private EasybuyUser getUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取对象！
		EasybuyUser user = (EasybuyUser) session.getAttribute("easybuyUserLogin");
		return user;
	}

	/**
	 * 判断购物车信息！
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/checkCart")
    @ResponseBody
	private ReturnResult checkCart(HttpServletRequest request) throws Exception {
		ReturnResult result = new ReturnResult();
		HttpSession session = request.getSession();
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = cartService.calculate(cart);
		// 循环！
		for (ShoppingCartItem item : cart.getItems()) {
			// 访问三层！
			EasybuyProduct product =productService.findById(item.getProduct().getId());
			if (product.getStock() < item.getQuantity()) {
				return result.returnFail(product.getName() + "商品数量不足");
			}
		}
		return result.returnSuccess();
	}

	/**
	 * 清空购物车！
	 *
	 * @param request
	 * @param response
	 */
    @RequestMapping("/clearCart")
    @ResponseBody
	public ReturnResult clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 结账后清空购物车、
		request.getSession().removeAttribute("cart");
		result.returnSuccess(null);
		return result;
	}
}
