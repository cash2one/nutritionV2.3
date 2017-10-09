package com.jumper.weight.nutrition.his.entity;

import java.util.Date;

import com.jumper.weight.common.base.BaseEntity;

public class HisWeightRecord extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

    private Integer hospUserId;

    private Date addDate;

    private Double weight;

    public Integer getHospUserId() {
        return hospUserId;
    }

    public void setHospUserId(Integer hospUserId) {
        this.hospUserId = hospUserId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}