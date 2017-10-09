package com.jumper.weight.nutrition.his.entity;

import java.util.Date;

import com.jumper.weight.common.base.BaseEntity;

public class HisUserExtraInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

    private Integer hospUserId;

    private String healthNum;

    private String idCard;

    private Date initialSurveyDate;

    private Integer impregnationWay;

    private Date implantationDate;

    private Integer pregnantTimes;

    private Integer birthTimes;

    private Integer pregnantWeek;

    private Integer correctPregnantWeek;

    private Date correctExpectedDate;

    private Integer menarcheAge;

    private String periodCycle;

    private Integer periodAmount;

    private Integer dysmenorrhea;

    private String marryAge;

    public Integer getHospUserId() {
        return hospUserId;
    }

    public void setHospUserId(Integer hospUserId) {
        this.hospUserId = hospUserId;
    }

    public String getHealthNum() {
        return healthNum;
    }

    public void setHealthNum(String healthNum) {
        this.healthNum = healthNum == null ? null : healthNum.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Date getInitialSurveyDate() {
        return initialSurveyDate;
    }

    public void setInitialSurveyDate(Date initialSurveyDate) {
        this.initialSurveyDate = initialSurveyDate;
    }

    public Integer getImpregnationWay() {
        return impregnationWay;
    }

    public void setImpregnationWay(Integer impregnationWay) {
        this.impregnationWay = impregnationWay;
    }

    public Date getImplantationDate() {
        return implantationDate;
    }

    public void setImplantationDate(Date implantationDate) {
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

    public Date getCorrectExpectedDate() {
        return correctExpectedDate;
    }

    public void setCorrectExpectedDate(Date correctExpectedDate) {
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
        this.periodCycle = periodCycle == null ? null : periodCycle.trim();
    }

    public Integer getPeriodAmount() {
        return periodAmount;
    }

    public void setPeriodAmount(Integer periodAmount) {
        this.periodAmount = periodAmount;
    }

    public Integer getDysmenorrhea() {
        return dysmenorrhea;
    }

    public void setDysmenorrhea(Integer dysmenorrhea) {
        this.dysmenorrhea = dysmenorrhea;
    }

	public String getMarryAge() {
		return marryAge;
	}

	public void setMarryAge(String marryAge) {
		this.marryAge = marryAge;
	}

}