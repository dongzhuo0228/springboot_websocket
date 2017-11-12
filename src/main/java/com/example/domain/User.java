package com.example.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

import com.alibaba.fastjson.annotation.JSONField;
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User() {
		super();
	}

	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
