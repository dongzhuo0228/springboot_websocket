package com.example.service;

import java.util.List;

import com.example.domain.User;


public interface UserService {


	public User findUser();
	List<User> selectList();
	public int countItem();

}
