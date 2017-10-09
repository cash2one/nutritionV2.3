package com.jumper.weight.nutrition.his.enums;

/**
 * 月经量枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum PeriodAmount {
	
	kong(-1, ""),
	duo(0, "多"),
	zhong(1, "中"),
	shao(2, "少");
	
	private int type;
	private String name;
	
	private PeriodAmount(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		PeriodAmount[] enums = PeriodAmount.values();
		String name = kong.name;//默认为中
		for (PeriodAmount obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		PeriodAmount[] enums = PeriodAmount.values();
		int type = kong.type;//默认为中
		for (PeriodAmount obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
	
}
