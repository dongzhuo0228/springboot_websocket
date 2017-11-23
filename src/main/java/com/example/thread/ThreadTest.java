package com.example.thread;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
@Service
public class ThreadTest {
	
	/*@Autowired
	ExecutorService service;*/
	
	@Resource(name = "taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;
	@PostConstruct
	public void threadRun(){
		ThreadTestCallable callable = new ThreadTestCallable();
		taskExecutor.submit(callable);
	}
	
}
