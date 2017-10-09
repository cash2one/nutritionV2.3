package com.jumper.weight.nutrition.his.entity;

import com.jumper.weight.common.base.BaseEntity;

public class HisInspectionRecord extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

    private Integer hospUserId;

    private String generalInspection;

    private String pelvisInspection;

    private Integer gynecologyInspection;

    private Integer vulua;

    private Integer vagina;

    private Integer cervix;

    private Integer uterineBody;

    private Integer uterineDay;

    private Integer uterineAccessory;

    private String bloodType;

    private String hbvm;

    private String hcv;

    private String syphilis;

    private String hiv;

    private String gbs;

    private String ogtt;

    public Integer getHospUserId() {
        return hospUserId;
    }

    public void setHospUserId(Integer hospUserId) {
        this.hospUserId = hospUserId;
    }

    public String getGeneralInspection() {
        return generalInspection;
    }

    public void setGeneralInspection(String generalInspection) {
        this.generalInspection = generalInspection == null ? null : generalInspection.trim();
    }

    public String getPelvisInspection() {
        return pelvisInspection;
    }

    public void setPelvisInspection(String pelvisInspection) {
        this.pelvisInspection = pelvisInspection == null ? null : pelvisInspection.trim();
    }

    public Integer getGynecologyInspection() {
		return gynecologyInspection;
	}

	public void setGynecologyInspection(Integer gynecologyInspection) {
		this.gynecologyInspection = gynecologyInspection;
	}

	public Integer getVulua() {
        return vulua;
    }

    public void setVulua(Integer vulua) {
        this.vulua = vulua;
    }

    public Integer getVagina() {
        return vagina;
    }

    public void setVagina(Integer vagina) {
        this.vagina = vagina;
    }

    public Integer getCervix() {
        return cervix;
    }

    public void setCervix(Integer cervix) {
        this.cervix = cervix;
    }

    public Integer getUterineBody() {
        return uterineBody;
    }

    public void setUterineBody(Integer uterineBody) {
        this.uterineBody = uterineBody;
    }

    public Integer getUterineDay() {
        return uterineDay;
    }

    public void setUterineDay(Integer uterineDay) {
        this.uterineDay = uterineDay;
    }

    public Integer getUterineAccessory() {
        return uterineAccessory;
    }

    public void setUterineAccessory(Integer uterineAccessory) {
        this.uterineAccessory = uterineAccessory;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType == null ? null : bloodType.trim();
    }

    public String getHbvm() {
        return hbvm;
    }

    public void setHbvm(String hbvm) {
        this.hbvm = hbvm == null ? null : hbvm.trim();
    }

    public String getHcv() {
        return hcv;
    }

    public void setHcv(String hcv) {
        this.hcv = hcv == null ? null : hcv.trim();
    }

    public String getSyphilis() {
        return syphilis;
    }

    public void setSyphilis(String syphilis) {
        this.syphilis = syphilis == null ? null : syphilis.trim();
    }

    public String getHiv() {
        return hiv;
    }

    public void setHiv(String hiv) {
        this.hiv = hiv == null ? null : hiv.trim();
    }

    public String getGbs() {
        return gbs;
    }

    public void setGbs(String gbs) {
        this.gbs = gbs == null ? null : gbs.trim();
    }

    public String getOgtt() {
        return ogtt;
    }

    public void setOgtt(String ogtt) {
        this.ogtt = ogtt == null ? null : ogtt.trim();
    }
}