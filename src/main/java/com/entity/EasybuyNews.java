package com.entity;

import java.util.Date;

/**
 * 资讯类（实体类）
 * 
 * @author Administrator
 *
 */
public class EasybuyNews {

	// 编号（主键）
	private int id;
	// 标题
	private String title;
	// 内容
	private String content;
	// 录入日期
	private Date createTime;

	// 无参构�?�函�?
	public EasybuyNews() {
	}

	// 有参构�?�函�?
	public EasybuyNews(int id, String title, String content, Date createTime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}

	/**
	 * 为属性进行封�?
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
