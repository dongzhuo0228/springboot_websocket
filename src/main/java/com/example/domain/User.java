package com.example.domain;

import javax.persistence.GeneratedValue;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
public class User {
	@Id
	@GeneratedValue
	@Field("id")
	private Integer id;
	@Field("name")
	private String name;
	
	/*private String username;
	private String password;
	private String token;
	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date createTime;
    
	
	private Role roles;*/
	
	
	/*public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}*/



	public User() {
		super();
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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

	/*public Role getRoles() {
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
	}*/
/*
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
*/
	public User(String name) {
		super();
		this.name = name;
	}
	
	
	
	
	
	
}
