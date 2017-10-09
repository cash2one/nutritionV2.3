package com.jumper.weight.nutrition.his.service;

import com.jumper.weight.nutrition.his.vo.VoHisInfo;

public interface HisInfoService {
	
	/**
	 * 同步HIS用户信息，绑定登记号
	 * @createTime 2017年8月28日,上午10:04:38
	 * @createAuthor fangxilin
	 * @param hospitalId
	 * @param registerNum
	 * @return 用户id
	 */
	VoHisInfo synchorHisInfo(int hospitalId, String registerNum);
	
	/**
	 * http查询HIS用户信息
	 * @createTime 2017年8月31日,下午3:06:57
	 * @createAuthor fangxilin
	 * @param hospitalId
	 * @param registerNum
	 * @return
	 */
	VoHisInfo findHttpHisInfo(int hospitalId, String registerNum);
	
}
