package com.jumper.weight.nutrition.his.enums;

/**
 * 痛经枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum Dysmenorrhea {
	
	kong(-1, ""),
	wu(0, "无"),
	you(1, "有");
	
	private int type;
	private String name;
	private Dysmenorrhea(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		Dysmenorrhea[] enums = Dysmenorrhea.values();
		String name = kong.name;//默认为无
		for (Dysmenorrhea obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		Dysmenorrhea[] enums = Dysmenorrhea.values();
		int type = kong.type;//默认为无
		for (Dysmenorrhea obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
