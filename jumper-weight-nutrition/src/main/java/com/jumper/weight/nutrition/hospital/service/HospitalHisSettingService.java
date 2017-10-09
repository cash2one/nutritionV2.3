package com.jumper.weight.nutrition.hospital.service;

import com.jumper.weight.common.base.BaseService;
import com.jumper.weight.nutrition.hospital.entity.HospitalHisSetting;

public interface HospitalHisSettingService extends BaseService<HospitalHisSetting>{

	/**
	 * 通过医院ID查询医院his版本配置
	 * @param hospitalId 医院ID
	 * @return
	 */
	HospitalHisSetting findHospitalHisSettingByHospId(int hospitalId);
	
}
