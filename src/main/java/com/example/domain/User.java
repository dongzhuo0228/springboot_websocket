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
	private String username;
	private String password;
	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date createTime;
    
	
	private Role roles;
	
	
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

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
