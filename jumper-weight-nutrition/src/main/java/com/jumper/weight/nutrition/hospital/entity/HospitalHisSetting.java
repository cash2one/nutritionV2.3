package com.jumper.weight.nutrition.hospital.entity;
/**
 * 医院his版本配置（调用的扫码、公众号流程是his版还是非his版）
 * @author gyx
 * @time 2017年8月24日
 */
public class HospitalHisSetting {
    private Integer id;

    private Integer hospitalId;

    private Integer isHis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getIsHis() {
        return isHis;
    }

    public void setIsHis(Integer isHis) {
        this.isHis = isHis;
    }
}