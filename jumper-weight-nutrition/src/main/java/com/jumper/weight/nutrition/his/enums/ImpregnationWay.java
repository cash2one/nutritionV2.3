package com.jumper.weight.nutrition.his.enums;

/**
 * 受孕方式枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum ImpregnationWay {
	
	kong(-1, ""),
	ziran(0, "自然受孕"),
	rengong(1, "人工受孕"),
	IVF_ET(2, "IVF-ET");
	
	private int type;
	private String name;
	
	private ImpregnationWay(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		ImpregnationWay[] enums = ImpregnationWay.values();
		String name = kong.name;//默认自然受孕
		for (ImpregnationWay obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		ImpregnationWay[] enums = ImpregnationWay.values();
		int type = kong.type;//默认自然受孕
		for (ImpregnationWay obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
