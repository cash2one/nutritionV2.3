package com.jumper.weight.nutrition.his.enums;

/**
 * 外阴枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum Vulua {
	
	kong(-1, ""),
	wuyichang(0, "无异常"),
	chaohong(1, "潮红"),
	cucao(2, "粗糙"),
	sesujt(3, "色素减退"),
	zhongwu(4, "肿物"),
	yanzheng(5, "炎症"),
	baiban(6, "白斑"),
	nangzhong(7, "囊肿"),
	jianruisy(8, "尖锐湿疣"),
	bashixnz(9, "巴氏腺囊肿"),
	qita(10, "其他");
	
	private int type;
	private String name;
	
	private Vulua(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		Vulua[] enums = Vulua.values();
		String name = kong.name;//默认为无异常
		for (Vulua obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		Vulua[] enums = Vulua.values();
		int type = kong.type;//默认为无异常
		for (Vulua obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
