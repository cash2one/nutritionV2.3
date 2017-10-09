package com.jumper.weight.nutrition.his.vo;

/**
 * HIS/电子产检信息接口返回信息
 * @Description TODO
 * @author fangxilin
 * @date 2017年8月28日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public class VoHisInfo {
	
	private String realName;
	/**登记号*/
	private String registerNum;
	/**保健号*/
	private String healthNum;
	/**身份证号*/
	private String idCard;
	private String mobile;
	private String birthday;
	private Integer height;
	private Double weight;
	/**目前体重*/
	private Double currentWeight;
	/**血压*/
	private String bloodPresure;
	/**怀孕类型，单胎多胎*/
	private String pregnantType;
	/**初检时间*/
	private String initialSurveyDate;
	/**受孕方式*/
	private String impregnationWay;
	/**植入日期*/
	private String implantationDate;
	/**孕次*/
	private Integer pregnantTimes;
	/**产次*/
	private Integer birthTimes;
	private String lastPeriod;
	/**计算孕周（天数）*/
	private Integer pregnantWeek;
	/**修正孕周（天数）*/
	private Integer correctPregnantWeek;
	private String expectedDate;
	/**修正预产期*/
	private String correctExpectedDate;
	/**初潮年龄*/
	private Integer menarcheAge;
	/**月经周期*/
	private String periodCycle;
	/**月经量*/
	private String periodAmount;
	/**痛经*/
	private String dysmenorrhea;
	/**结婚年龄---可能传‘未婚’*/
	private String marryAge;
	/**一般检查*/
	private String generalInspection;
	/**骨盆检查*/
	private String pelvisInspection;
	/**妇科检查*/
	private String gynecologyInspection;
	/**外阴*/
	private String vulua;
	/**阴道*/
	private String vagina;
	/**宫颈*/
	private String cervix;
	/**子宫体*/
	private String uterineBody;
	/**子宫体孕多少天大小*/
	private Integer uterineDay;
	/**子宫体附件*/
	private String uterineAccessory;
	/**血型*/
	private String bloodType;
	/**乙肝两对半*/
	private String hbvm;
	/**丙肝*/
	private String hcv;
	/**梅毒*/
	private String syphilis;
	private String hiv;
	private String gbs;
	private String ogtt;
	/**过敏史*/
	private String allergyHistory;
	/**既往史*/
	private String pastHistory;
	/**家族史*/
	private String familyHistory;
	/**孕产史*/
	private String birthHistory;
	/**本次妊娠特殊情况*/
	private String pregnancySpecialCase;
	
	//新增字段（非HIS返回），用户封装展现的信息----start
	private Integer age;
	/**孕前bmi*/
	private Double bmi;
	/**目前bmi*/
	private Double currentBmi;
	private Integer userId;
	private String hospitalName;
	/**当前门诊id*/
	private Integer outpatientId;
	/**当前门诊原因*/
	private String outpatientReason;
	/**就诊卡号*/
	private String outpatientNum;
	//新增字段（非HIS返回），用户封装展现的信息----end
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRegisterNum() {
		return registerNum;
	}
	public void setRegisterNum(String registerNum) {
		this.registerNum = registerNum;
	}
	public String getHealthNum() {
		return healthNum;
	}
	public void setHealthNum(String healthNum) {
		this.healthNum = healthNum;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getCurrentWeight() {
		return currentWeight;
	}
	public void setCurrentWeight(Double currentWeight) {
		this.currentWeight = currentWeight;
	}
	public String getBloodPresure() {
		return bloodPresure;
	}
	public void setBloodPresure(String bloodPresure) {
		this.bloodPresure = bloodPresure;
	}
	public String getPregnantType() {
		return pregnantType;
	}
	public void setPregnantType(String pregnantType) {
		this.pregnantType = pregnantType;
	}
	public String getInitialSurveyDate() {
		return initialSurveyDate;
	}
	public void setInitialSurveyDate(String initialSurveyDate) {
		this.initialSurveyDate = initialSurveyDate;
	}
	public String getImpregnationWay() {
		return impregnationWay;
	}
	public void setImpregnationWay(String impregnationWay) {
		this.impregnationWay = impregnationWay;
	}
	public String getImplantationDate() {
		return implantationDate;
	}
	public void setImplantationDate(String implantationDate) {
		this.implantationDate = implantationDate;
	}
	public Integer getPregnantTimes() {
		return pregnantTimes;
	}
	public void setPregnantTimes(Integer pregnantTimes) {
		this.pregnantTimes = pregnantTimes;
	}
	public Integer getBirthTimes() {
		return birthTimes;
	}
	public void setBirthTimes(Integer birthTimes) {
		this.birthTimes = birthTimes;
	}
	public String getLastPeriod() {
		return lastPeriod;
	}
	public void setLastPeriod(String lastPeriod) {
		this.lastPeriod = lastPeriod;
	}
	public Integer getPregnantWeek() {
		return pregnantWeek;
	}
	public void setPregnantWeek(Integer pregnantWeek) {
		this.pregnantWeek = pregnantWeek;
	}
	public Integer getCorrectPregnantWeek() {
		return correctPregnantWeek;
	}
	public void setCorrectPregnantWeek(Integer correctPregnantWeek) {
		this.correctPregnantWeek = correctPregnantWeek;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public String getCorrectExpectedDate() {
		return correctExpectedDate;
	}
	public void setCorrectExpectedDate(String correctExpectedDate) {
		this.correctExpectedDate = correctExpectedDate;
	}
	public Integer getMenarcheAge() {
		return menarcheAge;
	}
	public void setMenarcheAge(Integer menarcheAge) {
		this.menarcheAge = menarcheAge;
	}
	public String getPeriodCycle() {
		return periodCycle;
	}
	public void setPeriodCycle(String periodCycle) {
		this.periodCycle = periodCycle;
	}
	public String getPeriodAmount() {
		return periodAmount;
	}
	public void setPeriodAmount(String periodAmount) {
		this.periodAmount = periodAmount;
	}
	public String getDysmenorrhea() {
		return dysmenorrhea;
	}
	public void setDysmenorrhea(String dysmenorrhea) {
		this.dysmenorrhea = dysmenorrhea;
	}
	public String getMarryAge() {
		return marryAge;
	}
	public void setMarryAge(String marryAge) {
		this.marryAge = marryAge;
	}
	public String getGeneralInspection() {
		return generalInspection;
	}
	public void setGeneralInspection(String generalInspection) {
		this.generalInspection = generalInspection;
	}
	public String getPelvisInspection() {
		return pelvisInspection;
	}
	public void setPelvisInspection(String pelvisInspection) {
		this.pelvisInspection = pelvisInspection;
	}
	public String getGynecologyInspection() {
		return gynecologyInspection;
	}
	public void setGynecologyInspection(String gynecologyInspection) {
		this.gynecologyInspection = gynecologyInspection;
	}
	public String getVulua() {
		return vulua;
	}
	public void setVulua(String vulua) {
		this.vulua = vulua;
	}
	public String getVagina() {
		return vagina;
	}
	public void setVagina(String vagina) {
		this.vagina = vagina;
	}
	public String getCervix() {
		return cervix;
	}
	public void setCervix(String cervix) {
		this.cervix = cervix;
	}
	public String getUterineBody() {
		return uterineBody;
	}
	public void setUterineBody(String uterineBody) {
		this.uterineBody = uterineBody;
	}
	public Integer getUterineDay() {
		return uterineDay;
	}
	public void setUterineDay(Integer uterineDay) {
		this.uterineDay = uterineDay;
	}
	public String getUterineAccessory() {
		return uterineAccessory;
	}
	public void setUterineAccessory(String uterineAccessory) {
		this.uterineAccessory = uterineAccessory;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getHbvm() {
		return hbvm;
	}
	public void setHbvm(String hbvm) {
		this.hbvm = hbvm;
	}
	public String getHcv() {
		return hcv;
	}
	public void setHcv(String hcv) {
		this.hcv = hcv;
	}
	public String getSyphilis() {
		return syphilis;
	}
	public void setSyphilis(String syphilis) {
		this.syphilis = syphilis;
	}
	public String getHiv() {
		return hiv;
	}
	public void setHiv(String hiv) {
		this.hiv = hiv;
	}
	public String getGbs() {
		return gbs;
	}
	public void setGbs(String gbs) {
		this.gbs = gbs;
	}
	public String getOgtt() {
		return ogtt;
	}
	public void setOgtt(String ogtt) {
		this.ogtt = ogtt;
	}
	public String getAllergyHistory() {
		return allergyHistory;
	}
	public void setAllergyHistory(String allergyHistory) {
		this.allergyHistory = allergyHistory;
	}
	public String getPastHistory() {
		return pastHistory;
	}
	public void setPastHistory(String pastHistory) {
		this.pastHistory = pastHistory;
	}
	public String getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}
	public String getBirthHistory() {
		return birthHistory;
	}
	public void setBirthHistory(String birthHistory) {
		this.birthHistory = birthHistory;
	}
	public String getPregnancySpecialCase() {
		return pregnancySpecialCase;
	}
	public void setPregnancySpecialCase(String pregnancySpecialCase) {
		this.pregnancySpecialCase = pregnancySpecialCase;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getBmi() {
		return bmi;
	}
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	public Double getCurrentBmi() {
		return currentBmi;
	}
	public void setCurrentBmi(Double currentBmi) {
		this.currentBmi = currentBmi;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Integer getOutpatientId() {
		return outpatientId;
	}
	public void setOutpatientId(Integer outpatientId) {
		this.outpatientId = outpatientId;
	}
	public String getOutpatientReason() {
		return outpatientReason;
	}
	public void setOutpatientReason(String outpatientReason) {
		this.outpatientReason = outpatientReason;
	}
	public String getOutpatientNum() {
		return outpatientNum;
	}
	public void setOutpatientNum(String outpatientNum) {
		this.outpatientNum = outpatientNum;
	}
	
}
