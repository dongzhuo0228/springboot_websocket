package com.example;

import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();  
	public static void main(String[] args) {
		new Thread("Thread1"){
			@Override
			public void run() {
				map.put(33, 33);
			}
		};
		new Thread("Thread2"){  
            @Override  
            public void run() {  
                map.put(4, 44);  
            }  
        };  
          
        new Thread("Thread3"){  
            @Override  
            public void run() {  
                map.put(7, 77);  
            }  
        };  
//        System.out.println(map);  
        
        
        
        System.out.println(3/4 );
    	System.out.println(Integer.parseInt("0011111", 2)& 15);
    	System.out.println(Integer.parseInt("0111111", 2) & 15);
    	System.out.println(Integer.parseInt("1111111", 2) & 15);
	}
	
	
	 

}
