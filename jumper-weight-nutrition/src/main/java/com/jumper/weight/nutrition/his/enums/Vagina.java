package com.jumper.weight.nutrition.his.enums;

/**
 * 阴道枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum Vagina {
	
	kong(-1, ""),
	chang(0, "畅"),
	nianmocc(1, "粘膜粗糙"),
	chongxue(2, "充血"),
	zhongwu(3, "肿物"),
	jianruisy(4, "尖锐湿疣"),
	pengchu(5, "膨出"),
	qita(6, "其他");
	
	private int type;
	private String name;
	
	private Vagina(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		Vagina[] enums = Vagina.values();
		String name = kong.name;//默认为畅
		for (Vagina obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		Vagina[] enums = Vagina.values();
		int type = kong.type;//默认为畅
		for (Vagina obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
