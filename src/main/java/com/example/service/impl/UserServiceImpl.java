package com.example.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.annotation.LogAnnotation;
import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;


@Service
public class UserServiceImpl implements UserService {
    

	@Autowired
	private UserMapper userMapper;
	
	@Override
	@LogAnnotation(description="获取用户接口")
	public User findUser() {
		User user = new User();
		user.setName("dedem,o");
		return user;
		/*userMapper.insert(user);
		Integer id = user.getId();
		return "ddddd";*/
	}

	@Override
	@LogAnnotation(description="获取用户接口")
	public List<User> selectList(User user) {
	        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
	    List<User> allItems = userMapper.getAll(user);    
	    return allItems;	
	}

	@Override
	public int countItem() {
		// TODO Auto-generated method stub
		return userMapper.countItem();
	}

}
