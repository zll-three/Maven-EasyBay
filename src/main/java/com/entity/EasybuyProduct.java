package com.entity;

import java.util.List;

/**
 * 商品类（实体类）
 * @author Administrator
 *
 */
public class EasybuyProduct {

	// 商品编号（主键）
	private int id;
	// 商品名字
	private String name;
	//商品描述
	 private String  description; 
	//商品价格
	 private float price;
	//商品库存
	 private int  stock; 
	// �?属分类ID
	 private int categoryLevel1;
	//�?属二级分类ID
	 private int categoryLevel2;
	// �?属三级分类ID
	 private int categoryLevel3;
	// 上传的文件名
	 private String fileName;
	//是否删除�?1删除,0未删除）'
	 private int  isDelete;
	 
	 public EasybuyCollect getEasybuyCollect() {
		return easybuyCollect;
	}


	public void setEasybuyCollect(EasybuyCollect easybuyCollect) {
		this.easybuyCollect = easybuyCollect;
	}
	private EasybuyCollect easybuyCollect;
	 public List<EasybuyCollect> getCollectlist() {
		return Collectlist;
	}


	public void setCollectlist(List<EasybuyCollect> collectlist) {
		Collectlist = collectlist;
	}
	private List<EasybuyCollect> Collectlist;
	 /**
	  * 无参构�?�函�? 
	  */
	 public EasybuyProduct() {}
	 
	 
	/**
	 * 有参构�?�函�? 
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 * @param stock
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @param fileName
	 * @param isDelete
	 */
	 public EasybuyProduct(int id, String name, String description, float price, int stock, int categoryLevel1,
			int categoryLevel2, int categoryLevel3, String fileName, int isDelete) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.categoryLevel1 = categoryLevel1;
		this.categoryLevel2 = categoryLevel2;
		this.categoryLevel3 = categoryLevel3;
		this.fileName = fileName;
		this.isDelete = isDelete;
	}
	//为属性进行封�?
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCategoryLevel1() {
		return categoryLevel1;
	}
	public void setCategoryLevel1(int categoryLevel1) {
		this.categoryLevel1 = categoryLevel1;
	}
	public int getCategoryLevel2() {
		return categoryLevel2;
	}
	public void setCategoryLevel2(int categoryLevel2) {
		this.categoryLevel2 = categoryLevel2;
	}
	public int getCategoryLevel3() {
		return categoryLevel3;
	}
	public void setCategoryLevel3(int categoryLevel3) {
		this.categoryLevel3 = categoryLevel3;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	 
	 
	 
}
