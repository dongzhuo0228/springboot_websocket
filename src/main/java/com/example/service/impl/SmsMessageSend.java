package com.example.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.example.vo.SmsMessageVo;

@Service
public class SmsMessageSend implements SmsMessageSendImpl {
	@Resource(name = "taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	UserMapper userMapper;
	@Override
	public void sendTo(SmsMessageVo sms) {
		taskExecutor.submit(new Runnable() {
			@Override
			public void run() {
				User user =new User();
				user.setName(sms.getContent());
				userMapper.insert(user);
			}
		});	
		
	}

}
