package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.annotation.Authorization;
import com.example.annotation.CurrentUser;
import com.example.domain.User;
import com.example.jms.Producer;
import com.example.reidsdao.RedisCacheRepository;
import com.example.service.TokenManager;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;

@RestController
//@RequestMapping
public class UserController extends BaseController{
	@Autowired
	RedisCacheRepository redisCacheRepository;
    @Autowired
	private UserService userService;
    @Autowired
    private Producer producer;
    @Autowired
    TokenManager tokenManager;
	@RequestMapping("/demo/xxx")
	public User demo() {
		return userService.findUser();
	}
	@RequestMapping("/demo/list")
	public List<User> getList(){
		 PageHelper.startPage(1,10);  
//		 int i = userService.countItem();
//		 System.out.println("返回数据大小为"+i);
	      return userService.selectList(null); 
      }
	
	@RequestMapping("/demo/index")
	public ModelAndView index(Map map){
//		List<User> listS = userService.selectList();
//		map.put("index", listS);
		return new ModelAndView("index");
      }
	
	@RequestMapping(value="/demo/listDemo",consumes="application/json")
	public List<User> getListDemo(@RequestBody @CurrentUser User user){
		 PageHelper.startPage(1,10);  
//		 int i = userService.countItem();
//		 System.out.println("返回数据大小为"+i);
	      return userService.selectList(user); 
     }
	@Authorization
	@RequestMapping("/update")
	public void update(@CurrentUser User user){
		 Destination destination = new ActiveMQQueue("mytest.queue");
		 producer.sendMessage(destination, "ddddddddddddddddddd");
	}
	 @RequestMapping(value = "/getUser/{name}/{sex}") 
	 public User getUser(@PathVariable String name, @PathVariable char sex) {  
	        User user = new User();  
	        user.setId(12);  
	        user.setName(name);  
	        return user;  
	} 
	 
	@RequestMapping(value = "/login") 
	public Map login(@RequestBody User user){
		User u = new User();
		/*if("1".equals(user.getName()) && "1".equals(user.getPassword())){
			u.setId(12);
			u.setName(user.getName());
//			u.setPassword(user.getPassword());
			TokenModel model = tokenManager.createToken(u.getId());
			return responseOK(model);
		}*/
		return responseError("登录失败");
	}
}