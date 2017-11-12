package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;

@RestController
//@RequestMapping
public class UserController {
    @Autowired
	private UserService userService;

	@RequestMapping("/demo/xxx")
	public User demo() {
		return userService.findUser();
	}
	@RequestMapping("/demo/list")
	public List<User> getList(){
		 PageHelper.startPage(1,1);  
//		 int i = userService.countItem();
//		 System.out.println("返回数据大小为"+i);
	      return userService.selectList(); 
      }
	
	@RequestMapping("/demo/index")
	public ModelAndView index(Map map){
//		List<User> listS = userService.selectList();
//		map.put("index", listS);
		return new ModelAndView("index");
      }
}