package com.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.entity.EasybuyProduct;

public class ShoppingCart implements Serializable{
	public List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
	private Double sum;

	//获取购物车中�??有商�??
	public List<ShoppingCartItem> getItems() {
		return items;
	}	
	//添加�??�??
	public ReturnResult addItem(EasybuyProduct product, Integer quantity) {
		ReturnResult result=new ReturnResult();
		int flag=0;
		for(int i=0;i<items.size();i++){
			//判断购物车中是否已有此商品，如果有则累计数量
			if((items.get(i).getProduct().getId())==(product.getId())){
				if(items.get(i).getQuantity()+quantity>product.getStock()){
					return result.returnFail("商品数量不足");
				}else{
					items.get(i).setQuantity(items.get(i).getQuantity()+quantity);
					flag=1;
				}
			}
		}
		if(quantity>product.getStock()){
			return result.returnFail("商品数量不足");
		}
		if(flag==0){
			items.add(new ShoppingCartItem(product, quantity));
		}
		return result.returnSuccess();
	}

	//移除�??�??
	public void removeItem(int index) {
		items.remove(index);
	}

	//修改数量
	public void modifyQuantity(int index, Integer quantity) {
		items.get(index).setQuantity(quantity);
	}

	//计算总价�??
	public float getTotalCost() {
		float sum = 0;
		for (ShoppingCartItem item : items) {
			sum = sum + item.getCost();
		}
		return sum;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		BigDecimal b=new BigDecimal(sum);
		this.sum = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
