package com.jumper.weight.nutrition.his.mapper;

import com.jumper.weight.common.base.BaseMapper;
import com.jumper.weight.nutrition.his.entity.HisUserExtraInfo;

public interface HisUserExtraInfoMapper extends BaseMapper<HisUserExtraInfo> {
	
	/**
	 * 通过hospUserId查询
	 * @createTime 2017年8月28日,下午5:54:21
	 * @createAuthor fangxilin
	 * @return
	 */
	HisUserExtraInfo findByHospUId(int hospUserId);
}