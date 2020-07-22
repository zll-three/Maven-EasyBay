package com.entity;

/**
 * 用户类（实体类）
 * 
 * @author Administrator
 *
 */
public class EasybuyUser {
	// 用户名（主键�?
	private int id;
	// 用户登入用户�?
	private String loginName;
	// 用户真实姓名
	private String userName;
	// 密码
	private String password;
	// 性别�?1为男,0为女�?
	private int sex;
	// 身份证号
	private String identityCode;
	// 电子邮箱
	private String email;
	// 手机号码
	private String mobile;
	// 用户类型�?1为后�?,0为前台）
	private int type;

	// 无参构�?�函�?
	public EasybuyUser() {
	}

	// 有参构�?�函�?
	public EasybuyUser(int id, String loginName, String userName, String password, int sex, String identityCode,
			String email, String mobile, int type) {
		this.id = id;
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.identityCode = identityCode;
		this.email = email;
		this.mobile = mobile;
		this.type = type;
	}

	/**
	 * 对属性进行封�?
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
