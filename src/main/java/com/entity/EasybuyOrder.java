package com.entity;

import java.util.Date;
/**
 * 订单表信息！
 * @author Administrator
 *
 */
public class EasybuyOrder {
	// 编号�?
	private int id;
	// 用户ID�?
	private String userId;
	// 登录用户名！
	private String loginName;
	// 用户地址�?
	private String userAddress;
	// 创建时间�?
	private Date createTime;
	// 金额�?
	private float cost;
	// 状�?�！
	private int status;
	// 类型�?
	private int type;
	// 订单号！
	private String serialNumber;
	
	private EasybuyOrderDetail  orderDetail;  
	public EasybuyOrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(EasybuyOrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	/**
	 * 带参数构造！
	 * @param id
	 * @param userId
	 * @param loginName
	 * @param userAddress
	 * @param createTime
	 * @param cost
	 * @param status
	 * @param type
	 * @param serialNumber
	 */
	public EasybuyOrder(int id, String userId, String loginName, String userAddress, Date createTime, float cost,
			int status, int type, String serialNumber) {
		this.id = id;
		this.userId = userId;
		this.loginName = loginName;
		this.userAddress = userAddress;
		this.createTime = createTime;
		this.cost = cost;
		this.status = status;
		this.type = type;
		this.serialNumber = serialNumber;
	}

	/**
	 * 无参数构造方法！
	 */
	public EasybuyOrder() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	
}
