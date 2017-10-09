package com.jumper.weight.nutrition.his.enums;

/**
 * 怀孕类型枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum PregnantType {
	
	dantai(0, "单胎"),
	duotai(1, "多胎");
	
	private int type;
	private String name;
	private PregnantType(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public static String getNameByType(int type) {
		PregnantType[] enums = PregnantType.values();
		String name = dantai.name;
		for (PregnantType obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		PregnantType[] enums = PregnantType.values();
		int type = dantai.type;
		for (PregnantType obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
