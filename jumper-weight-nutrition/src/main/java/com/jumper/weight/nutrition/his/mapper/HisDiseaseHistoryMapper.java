package com.jumper.weight.nutrition.his.mapper;

import com.jumper.weight.common.base.BaseMapper;
import com.jumper.weight.nutrition.his.entity.HisDiseaseHistory;

public interface HisDiseaseHistoryMapper extends BaseMapper<HisDiseaseHistory> {
    
	/**
	 * 通过hospUserId查询
	 * @createTime 2017年8月28日,下午5:54:21
	 * @createAuthor fangxilin
	 * @return
	 */
	HisDiseaseHistory findByHospUId(int hospUserId);
}