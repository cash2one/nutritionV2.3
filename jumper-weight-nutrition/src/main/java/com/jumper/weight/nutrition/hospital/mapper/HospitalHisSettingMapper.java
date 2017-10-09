package com.jumper.weight.nutrition.hospital.mapper;

import com.jumper.weight.common.base.BaseMapper;
import com.jumper.weight.nutrition.hospital.entity.HospitalHisSetting;

public interface HospitalHisSettingMapper extends BaseMapper<HospitalHisSetting>{

	/**
	 * 通过医院ID查询医院his版本配置
	 * @param hospitalId 医院ID
	 * @return
	 */
	HospitalHisSetting findHospitalHisSettingByHospId(int hospitalId);
	
}
