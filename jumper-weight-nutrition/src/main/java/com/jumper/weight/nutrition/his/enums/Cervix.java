package com.jumper.weight.nutrition.his.enums;

/**
 * 宫颈枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum Cervix {
	
	kong(-1, ""),
	guanghua(0, "光滑"),
	qingmi(1, "轻糜"),
	zmi(2, "中糜"),
	zhongmi(3, "重糜"),
	feida(4, "肥大"),
	chongxue(5, "充血"),
	yichuxue(6, "易出血"),
	zhuliuz(7, "潴留肿"),
	xirou(8, "息肉"),
	qita(9, "其他");
	
	private int type;
	private String name;
	
	private Cervix(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		Cervix[] enums = Cervix.values();
		String name = kong.name;//默认为光滑
		for (Cervix obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		Cervix[] enums = Cervix.values();
		int type = kong.type;//默认为无光滑
		for (Cervix obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
