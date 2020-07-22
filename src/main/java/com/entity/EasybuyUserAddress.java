package com.entity;
/**
 * 用户地址类（实体类）
 * @author Administrator
 *
 */
public class EasybuyUserAddress {

	//主键id（主�?)
	 private int id; 
	 //地址
	 private String address;  
	 //创建时间
	 private String createTime;
	 //用户主键
	 private int userId; 
	  //是否默认地址
	 private int  isDefault;
	 //备注
	 private String  remark;
	 
	//无参构�?�函�?
	 public EasybuyUserAddress() {}

	//有参构�?�函�?
	public EasybuyUserAddress(int id, String address, String createTime, int userId, int isDefault, String remark) {
		this.id = id;
		this.address = address;
		this.createTime = createTime;
		this.userId = userId;
		this.isDefault = isDefault;
		this.remark = remark;
	}


	/**
	 * 为属性进行封�?
	 * @return
	 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getIsDefault() {
		return isDefault;
	}


	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
