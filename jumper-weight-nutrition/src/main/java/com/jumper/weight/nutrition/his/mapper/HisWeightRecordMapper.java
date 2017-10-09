package com.jumper.weight.nutrition.his.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jumper.weight.common.base.BaseMapper;
import com.jumper.weight.nutrition.his.entity.HisWeightRecord;

public interface HisWeightRecordMapper extends BaseMapper<HisWeightRecord> {
	
	/**
	 * 通过hospUserId查询
	 * @createTime 2017年8月30日,下午4:50:08
	 * @createAuthor fangxilin
	 * @param hospUserId
	 * @return
	 */
	List<HisWeightRecord> listHisWeightByHUId(int hospUserId);
	
	/**
	 * 获取日期范围内的体重列表
	 * @createTime 2017-5-5,下午2:08:45
	 * @createAuthor fangxilin
	 * @param hospUserId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<HisWeightRecord> listWeightByDuring(@Param("hospUserId") int hospUserId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}