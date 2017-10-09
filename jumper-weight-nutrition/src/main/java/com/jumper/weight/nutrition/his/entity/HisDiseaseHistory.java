package com.jumper.weight.nutrition.his.entity;

import com.jumper.weight.common.base.BaseEntity;

public class HisDiseaseHistory extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

    private Integer hospUserId;

    private String allergyHistory;

    private String pastHistory;

    private String familyHistory;

    private String birthHistory;

    private String pregnancySpecialCase;

    public Integer getHospUserId() {
        return hospUserId;
    }

    public void setHospUserId(Integer hospUserId) {
        this.hospUserId = hospUserId;
    }

    public String getAllergyHistory() {
        return allergyHistory;
    }

    public void setAllergyHistory(String allergyHistory) {
        this.allergyHistory = allergyHistory == null ? null : allergyHistory.trim();
    }

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory == null ? null : pastHistory.trim();
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory == null ? null : familyHistory.trim();
    }

    public String getBirthHistory() {
        return birthHistory;
    }

    public void setBirthHistory(String birthHistory) {
        this.birthHistory = birthHistory == null ? null : birthHistory.trim();
    }

    public String getPregnancySpecialCase() {
        return pregnancySpecialCase;
    }

    public void setPregnancySpecialCase(String pregnancySpecialCase) {
        this.pregnancySpecialCase = pregnancySpecialCase == null ? null : pregnancySpecialCase.trim();
    }
}