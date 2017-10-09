package com.jumper.weight.nutrition.his.vo;

/**
 * 宝安妇幼初检信息接口返回字段
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月30日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public class VoHisFirstRecord {
	
	/**孕妇姓名*/
	private String Item1 = "";
	/**保健号*/
	private String stuHealthNo = "";
	/**身份证号*/
	private String idCard = "";
	private String birthday;
	/**受孕方式*/
	private String Item2 = "";
	/**植入日期*/
	private String Item173;
	/**初检时间*/
	private String Item3;
	/**末次月经*/
	private String Item6;
	/**计算孕周*/
	private Integer Item162;
	/**计算孕天*/
	private Integer Item163;
	/**预产期*/
	private String Item164;
	/**修正孕周*/
	private Integer Item7;
	/**修正孕天*/
	private Integer Item8;
	/**修正预产期*/
	private String Item10;
	/**孕次*/
	private Integer Item11;
	/**产次*/
	private Integer Item12;
	/**初潮*/
	private Integer Item165;
	/**周期*/
	private String Item166;
	/**月经周期/天*/
	private String Item174;
	/**月经量----可能为空*/
	private String Item167 = "";
	/**痛经----可能为空*/
	private String Item228 = "";
	/**结婚年龄---可能传‘未婚’*/
	private String Item15 = "";
	/**过敏史*/
	private String Item172 = "";
	/**既往史*/
	private String Item170 = "";
	/**家族史*/
	private String Item171 = "";
	/**孕产史*/
	private String Item177 = "";
	/**本次妊娠特殊情况----可能为空*/
	private String Item222 = "";
	/**身高*/
	private Integer Item47;
	/**孕前体重*/
	private Double Item49;
	/**收缩压，血压*/
	private Integer Item45;
	/**舒张压，血压*/
	private Integer Item46;
	/**一般检查----可能为空*/
	private String Item44 = "";
	/**骨盆检查*/
	private String Item60 = "";
	/**妇科检查*/
	private String Item205 = "";
	/**外阴----可能为空*/
	private String Item67 = "";
	/**阴道----可能为空*/
	private String Item68 = "";
	/**宫颈----可能为空*/
	private String Item69 = "";
	/**子宫体----可能为空*/
	private String Item70 = "";
	/**子宫体孕天----可能为空*/
	private Integer Item71;
	/**子宫体附件----可能为空*/
	private String Item178 = "";
	/**手机号*/
	private String Item221;
	/**血型*/
	private String Item229 = "";
	/**乙肝两对半----可能为空*/
	private String Item227 = "";
	/**丙肝*/
	private String Item231 = "";
	/**梅毒，HIV*/
	private String Item235 = "";
	/**OGTT*/
	private String Item237 = "";
	/**GBS*/
	private String Item239 = "";
	public String getItem1() {
		return Item1;
	}
	public void setItem1(String item1) {
		Item1 = item1;
	}
	public String getStuHealthNo() {
		return stuHealthNo;
	}
	public void setStuHealthNo(String stuHealthNo) {
		this.stuHealthNo = stuHealthNo;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getItem2() {
		return Item2;
	}
	public void setItem2(String item2) {
		Item2 = item2;
	}
	public String getItem173() {
		return Item173;
	}
	public void setItem173(String item173) {
		Item173 = item173;
	}
	public String getItem3() {
		return Item3;
	}
	public void setItem3(String item3) {
		Item3 = item3;
	}
	public String getItem6() {
		return Item6;
	}
	public void setItem6(String item6) {
		Item6 = item6;
	}
	public Integer getItem162() {
		return Item162;
	}
	public void setItem162(Integer item162) {
		Item162 = item162;
	}
	public Integer getItem163() {
		return Item163;
	}
	public void setItem163(Integer item163) {
		Item163 = item163;
	}
	public String getItem164() {
		return Item164;
	}
	public void setItem164(String item164) {
		Item164 = item164;
	}
	public Integer getItem7() {
		return Item7;
	}
	public void setItem7(Integer item7) {
		Item7 = item7;
	}
	public Integer getItem8() {
		return Item8;
	}
	public void setItem8(Integer item8) {
		Item8 = item8;
	}
	public String getItem10() {
		return Item10;
	}
	public void setItem10(String item10) {
		Item10 = item10;
	}
	public Integer getItem11() {
		return Item11;
	}
	public void setItem11(Integer item11) {
		Item11 = item11;
	}
	public Integer getItem12() {
		return Item12;
	}
	public void setItem12(Integer item12) {
		Item12 = item12;
	}
	public Integer getItem165() {
		return Item165;
	}
	public void setItem165(Integer item165) {
		Item165 = item165;
	}
	public String getItem166() {
		return Item166;
	}
	public void setItem166(String item166) {
		Item166 = item166;
	}
	public String getItem174() {
		return Item174;
	}
	public void setItem174(String item174) {
		Item174 = item174;
	}
	public String getItem167() {
		return Item167;
	}
	public void setItem167(String item167) {
		Item167 = item167;
	}
	public String getItem228() {
		return Item228;
	}
	public void setItem228(String item228) {
		Item228 = item228;
	}
	public String getItem15() {
		return Item15;
	}
	public void setItem15(String item15) {
		Item15 = item15;
	}
	public String getItem172() {
		return Item172;
	}
	public void setItem172(String item172) {
		Item172 = item172;
	}
	public String getItem170() {
		return Item170;
	}
	public void setItem170(String item170) {
		Item170 = item170;
	}
	public String getItem171() {
		return Item171;
	}
	public void setItem171(String item171) {
		Item171 = item171;
	}
	public String getItem177() {
		return Item177;
	}
	public void setItem177(String item177) {
		Item177 = item177;
	}
	public String getItem222() {
		return Item222;
	}
	public void setItem222(String item222) {
		Item222 = item222;
	}
	public Integer getItem47() {
		return Item47;
	}
	public void setItem47(Integer item47) {
		Item47 = item47;
	}
	public Double getItem49() {
		return Item49;
	}
	public void setItem49(Double item49) {
		Item49 = item49;
	}
	public Integer getItem45() {
		return Item45;
	}
	public void setItem45(Integer item45) {
		Item45 = item45;
	}
	public Integer getItem46() {
		return Item46;
	}
	public void setItem46(Integer item46) {
		Item46 = item46;
	}
	public String getItem44() {
		return Item44;
	}
	public void setItem44(String item44) {
		Item44 = item44;
	}
	public String getItem60() {
		return Item60;
	}
	public void setItem60(String item60) {
		Item60 = item60;
	}
	public String getItem205() {
		return Item205;
	}
	public void setItem205(String item205) {
		Item205 = item205;
	}
	public String getItem67() {
		return Item67;
	}
	public void setItem67(String item67) {
		Item67 = item67;
	}
	public String getItem68() {
		return Item68;
	}
	public void setItem68(String item68) {
		Item68 = item68;
	}
	public String getItem69() {
		return Item69;
	}
	public void setItem69(String item69) {
		Item69 = item69;
	}
	public String getItem70() {
		return Item70;
	}
	public void setItem70(String item70) {
		Item70 = item70;
	}
	public Integer getItem71() {
		return Item71;
	}
	public void setItem71(Integer item71) {
		Item71 = item71;
	}
	public String getItem178() {
		return Item178;
	}
	public void setItem178(String item178) {
		Item178 = item178;
	}
	public String getItem221() {
		return Item221;
	}
	public void setItem221(String item221) {
		Item221 = item221;
	}
	public String getItem229() {
		return Item229;
	}
	public void setItem229(String item229) {
		Item229 = item229;
	}
	public String getItem227() {
		return Item227;
	}
	public void setItem227(String item227) {
		Item227 = item227;
	}
	public String getItem231() {
		return Item231;
	}
	public void setItem231(String item231) {
		Item231 = item231;
	}
	public String getItem235() {
		return Item235;
	}
	public void setItem235(String item235) {
		Item235 = item235;
	}
	public String getItem237() {
		return Item237;
	}
	public void setItem237(String item237) {
		Item237 = item237;
	}
	public String getItem239() {
		return Item239;
	}
	public void setItem239(String item239) {
		Item239 = item239;
	}
}
