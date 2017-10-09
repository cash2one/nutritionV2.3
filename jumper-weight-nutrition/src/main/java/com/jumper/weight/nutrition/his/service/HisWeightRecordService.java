package com.jumper.weight.nutrition.his.service;

import com.jumper.weight.common.base.BaseService;
import com.jumper.weight.nutrition.his.entity.HisWeightRecord;
import com.jumper.weight.nutrition.his.vo.VoWeightChart;

public interface HisWeightRecordService extends BaseService<HisWeightRecord> {
	
	/**
	 * 获取用户体重曲线图数据
	 * @createTime 2017年9月1日,下午3:36:08
	 * @createAuthor fangxilin
	 * @param userId
	 * @param type 获取类型（0：近7天，1：所有）
	 * @param hospitalId
	 * @return
	 */
	VoWeightChart getWeightChartData(int userId, int type, int hospitalId);
}
