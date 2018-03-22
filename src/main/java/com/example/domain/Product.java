package com.example.domain;

import org.apache.solr.client.solrj.beans.Field;

public class Product {  
    //Field中的value要遵循核心中schema中<fields>的规则  
    @Field(value="id")  
    private String id;  
      
    @Field(value="name")  
    private String name;  
      
    @Field(value="description")  
    private String desc;  
      
    @Field(value="price")  
    private float price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}  
    
    
}  