package com.jumper.weight.nutrition.his.enums;

/**
 * 子宫体附件枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum UterineAccessory {
	
	kong(-1, ""),
	zhengchang(0, "正常"),
	fujianbk(1, "附件包块");
	
	private int type;
	private String name;
	private UterineAccessory(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		UterineAccessory[] enums = UterineAccessory.values();
		String name = kong.name;//默认为正常
		for (UterineAccessory obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		UterineAccessory[] enums = UterineAccessory.values();
		int type = kong.type;//默认为正常
		for (UterineAccessory obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
