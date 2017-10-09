package com.jumper.weight.nutrition.his.vo;

/**
 * his接口返回格式
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月30日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public class HisRetMsg {
	
	private String code;
	private String message;
	private Object data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
