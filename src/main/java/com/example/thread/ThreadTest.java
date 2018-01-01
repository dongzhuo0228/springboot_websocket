package com.example.thread;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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
	
	
	        
	  
    public static void main(String[] args) {  
        System.out.println("start");  
        ThreadTestCallable runnable = new ThreadTestCallable();  
        Thread thread1 = new Thread(runnable);  
        Thread thread2 = new Thread(runnable);  
        thread1.start();  
        thread2.start();  
	 }  
}
