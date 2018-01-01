package com.example.thread;

import java.util.Random;


public class ThreadTestCallable implements Runnable {
	//给每个线程 拷贝一份属于自己的全局变量 Integer 并不会被不同线程改变他的值
	private ThreadLocal<Integer> threadlocal = new ThreadLocal<Integer>(); 
	 @Override  
     public void run() {  
         threadlocal.set(new Random().nextInt(10));  
         try {  
             Thread.sleep(2000);  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }  
         System.out.println(Thread.currentThread() + " : " + threadlocal.get());  
     } 

}
