package com.jumper.weight.nutrition.his.enums;

/**
 * 妇科检查枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum GynecologyInspection {
	
	kong(-1, ""),
	weizuo(0, "未做"),
	yizuo(1, "已做");
	
	private int type;
	private String name;
	private GynecologyInspection(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		GynecologyInspection[] enums = GynecologyInspection.values();
		String name = kong.name;//默认为未做
		for (GynecologyInspection obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		GynecologyInspection[] enums = GynecologyInspection.values();
		int type = kong.type;//默认为未做
		for (GynecologyInspection obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
