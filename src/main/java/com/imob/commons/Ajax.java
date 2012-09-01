package com.imob.commons;

import java.util.HashMap;
import java.util.Map;

public class Ajax {
	public static final String ATTR_SUCCESS = "success";
	public static final String ATTR_MESSAGE = "message";
	public static final String ATTR_VALUE = "value";
	
	
	public static final String MESSAGE_SUCCESS = "ok";
	
	public static Map<String,Object> buildBasicResult(boolean success, String message){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put(ATTR_SUCCESS,success);
		resultMap.put(ATTR_MESSAGE,message);
		return resultMap;
	}
	
	public static Map<String,Object> buildSuccessResult(){
		return buildBasicResult(true,MESSAGE_SUCCESS);
	}
	public static Map<String,Object> buildSuccessResult(Object value){
		Map<String,Object> resultMap = buildSuccessResult();
		resultMap.put(ATTR_VALUE, value);
		return resultMap;
	}
	public static  Map<String,Object> buildErrorResult(String errorMessage){
		return buildBasicResult(true,errorMessage);
	}
}
