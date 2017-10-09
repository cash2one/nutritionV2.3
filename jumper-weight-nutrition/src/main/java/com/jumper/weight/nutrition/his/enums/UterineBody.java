package com.jumper.weight.nutrition.his.enums;

/**
 * 子宫体枚举
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public enum UterineBody {
	
	kong(-1, ""),
	zigongjl(0, "子宫肌瘤"),
	shuangzgsyd(1, "双子宫双阴道"),
	shuangzgdyd(2, "双子宫单阴道"),
	shuangjzg(3, "双角子宫"),
	anzhuangzg(4, "鞍状子宫"),
	buquanzgzg(5, "不全中隔子宫"),
	wanquanzgzg(6, "完全中隔子宫"),
	danjzg(7, "单角子宫"),
	canjzg(8, "残角子宫"),
	qita(9, "其他");
	
	private int type;
	private String name;
	
	private UterineBody(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static String getNameByType(int type) {
		UterineBody[] enums = UterineBody.values();
		String name = kong.name;//默认为子宫肌瘤
		for (UterineBody obj : enums) {
			if (obj.type == type) {
				name = obj.name;
				break;
			}
		}
		return name;
	}
	
	public static int getTypeByName(String name) {
		UterineBody[] enums = UterineBody.values();
		int type = kong.type;//默认为无子宫肌瘤
		for (UterineBody obj : enums) {
			if (obj.name.equals(name)) {
				type = obj.type;
				break;
			}
		}
		return type;
	}
}
