package com.example.mapper;

import java.util.List;

import com.example.domain.User;







public interface UserMapper {
//	@Insert("INSERT INTO users(name,createTime) VALUES(#{name}, #{createTime})")
	void insert(User user);
	List<User> selectList();
	List<User> getAll();
	int countItem();
}
