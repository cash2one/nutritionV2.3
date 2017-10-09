package com.jumper.weight.nutrition.his.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.weight.common.base.BaseMapper;
import com.jumper.weight.common.base.BaseServiceImpl;
import com.jumper.weight.common.util.Const;
import com.jumper.weight.common.util.FunctionUtils;
import com.jumper.weight.common.util.TimeUtils;
import com.jumper.weight.common.util.WeightFormula;
import com.jumper.weight.nutrition.his.entity.HisWeightRecord;
import com.jumper.weight.nutrition.his.mapper.HisWeightRecordMapper;
import com.jumper.weight.nutrition.his.service.HisWeightRecordService;
import com.jumper.weight.nutrition.his.vo.VoWeightChart;
import com.jumper.weight.nutrition.hospital.entity.WeightHospitalSetting;
import com.jumper.weight.nutrition.hospital.mapper.WeightHospitalSettingMapper;
import com.jumper.weight.nutrition.record.entity.UserWeightRecord;
import com.jumper.weight.nutrition.record.mapper.UserWeightRecordMapper;
import com.jumper.weight.nutrition.user.service.UserInfoService;
import com.jumper.weight.nutrition.user.vo.VoUserInfo;

@Service
public class HisWeightRecordServiceImpl extends BaseServiceImpl<HisWeightRecord> implements HisWeightRecordService {
	
	@Autowired
	private HisWeightRecordMapper hisWeightRecordMapper;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private WeightHospitalSettingMapper weightHospitalSettingMapper;
	@Autowired
	private UserWeightRecordMapper userWeightRecordMapper;
	
	@Override
	protected BaseMapper<HisWeightRecord> getDao() {
		return hisWeightRecordMapper;
	}

	@Override
	public VoWeightChart getWeightChartData(int userId, int type, int hospitalId) {
		VoWeightChart vo = new VoWeightChart();
		VoUserInfo voUser = userInfoService.findVoUserByUId(userId, hospitalId);
		vo.setExpectedDate(voUser.getExpectedDate());//设置预产期
		if (StringUtils.isEmpty(voUser.getExpectedDate())) {
			logger.info(String.format("-------userId : %s预产期为空！", userId));
			return vo;
		}
		//查询医院安全体重公式设置
		WeightHospitalSetting setting = weightHospitalSettingMapper.findSettingByHospId(hospitalId);
		int formula = (setting != null) ? setting.getSafeFormula() : 0; 
		List<Object[]> safeWeightList = new ArrayList<Object[]>();
		for (int i = 0; i <= 280; i++) {
			double[] safeWeight = WeightFormula.getSafeWeight(voUser.getBmi(), voUser.getWeight(), i, formula, voUser.getPregnantType());
			Object[] obj = {i, safeWeight[0], safeWeight[1]};
			safeWeightList.add(obj);
		}
		vo.setSafeWeightList(safeWeightList);//设置安全体重范围集合
		UserWeightRecord lastWeight = userWeightRecordMapper.findUserLastWeight(userId);
		double weightIncrease = (lastWeight != null) ? lastWeight.getAverageValue() - voUser.getWeight() : 0;
		double minSug = FunctionUtils.setDecimal((double)safeWeightList.get(280)[1] - voUser.getWeight(), 1);
		double maxSug = FunctionUtils.setDecimal((double)safeWeightList.get(280)[2] - voUser.getWeight(), 1);
		Double[] suggestIncrease = {minSug, maxSug};
		vo.setWeightIncrease(weightIncrease);//当前增重
		vo.setSuggestIncrease(suggestIncrease);
		
		Date expDate = TimeUtils.convertToDate(voUser.getExpectedDate());
		Date startDate = TimeUtils.getDateByDaysLate(-280, expDate);//0周0天的日期
		Date endDate = TimeUtils.getDateByDaysLate(21, expDate);//43周0天的日期
		Date now = new Date();
		if (type == 0) {//表示近30天
			//今天日期比43周0天的日期大时，endDate就为43周0天的日期，否则为今天
			endDate = (now.compareTo(endDate) == 1) ? endDate : now;
			startDate = TimeUtils.getDateByDaysLate(-29, endDate);
			int[] max = FunctionUtils.calPregnantWeek(endDate, expDate);//第30天对应的孕周
			int[] min = FunctionUtils.calPregnantWeek(startDate, expDate);//第1天对应的孕周
			Integer[] sevenPweek = {min[2], max[2]};
			vo.setSevenPweek(sevenPweek);
		}
		String startStr = TimeUtils.dateFormatToString(startDate, Const.YYYYMMDD);
		String endStr = TimeUtils.dateFormatToString(endDate, Const.YYYYMMDD);
		//查询天使体重记录
		List<UserWeightRecord> weightList = userWeightRecordMapper.listWeightByDuring(userId, startStr, endStr);
		List<Object[]> userWeightList = new ArrayList<Object[]>();
		for (UserWeightRecord record : weightList) {
			int[] pweek = FunctionUtils.calPregnantWeek(record.getAddTime(), expDate);
			Object[] obj = {pweek[2], record.getAverageValue()};
			userWeightList.add(obj);
		}
		vo.setUserWeightList(userWeightList);
		
		//查询HIS体重记录
		List<HisWeightRecord> weightHisList = hisWeightRecordMapper.listWeightByDuring(voUser.getHospUserId(), startStr, endStr);
		List<Object[]> hisWeightList = new ArrayList<Object[]>();
		for (HisWeightRecord his : weightHisList) {
			int[] pweek = FunctionUtils.calPregnantWeek(his.getAddDate(), expDate);
			Object[] obj = {pweek[2], his.getWeight()};
			hisWeightList.add(obj);
		}
		vo.setHisWeightList(hisWeightList);
		return vo;
	}


}
