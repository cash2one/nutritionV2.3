package com.jumper.weight.nutrition.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.weight.common.base.BaseMapper;
import com.jumper.weight.common.base.BaseServiceImpl;
import com.jumper.weight.nutrition.hospital.entity.HospitalHisSetting;
import com.jumper.weight.nutrition.hospital.mapper.HospitalHisSettingMapper;
import com.jumper.weight.nutrition.hospital.service.HospitalHisSettingService;

@Service
public class HospitalHisSettingServiceImpl extends BaseServiceImpl<HospitalHisSetting> implements HospitalHisSettingService{
	@Autowired
	private HospitalHisSettingMapper hospitalHisSettingMapper;

	@Override
	protected BaseMapper<HospitalHisSetting> getDao() {
		return hospitalHisSettingMapper;
	}

	@Override
	public HospitalHisSetting findHospitalHisSettingByHospId(int hospitalId) {
		HospitalHisSetting setting = hospitalHisSettingMapper.findHospitalHisSettingByHospId(hospitalId);
		if(setting != null){
			return setting;
		}
		return null;
	}
	
	
}
