package com.example.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	public Map responseOK(Object object){
		Map<String,Object> map  = new HashMap();
		map.put("data", object);
		map.put("success", true);
		map.put("code", 1);
		return map;
	}
	public Map responseError(Object object){
		Map<String,Object> map  = new HashMap();
		map.put("data", object);
		map.put("success", false);
		map.put("code", 0);
		return map;
	}

}
