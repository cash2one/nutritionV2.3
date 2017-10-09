package com.jumper.weight.nutrition.his.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jumper.weight.common.util.ArrayUtils;
import com.jumper.weight.common.util.Const;
import com.jumper.weight.common.util.Consts;
import com.jumper.weight.common.util.FunctionUtils;
import com.jumper.weight.common.util.HttpClient;
import com.jumper.weight.common.util.ReturnMsg;
import com.jumper.weight.common.util.TimeUtils;
import com.jumper.weight.nutrition.his.entity.HisDiseaseHistory;
import com.jumper.weight.nutrition.his.entity.HisInspectionRecord;
import com.jumper.weight.nutrition.his.entity.HisUserExtraInfo;
import com.jumper.weight.nutrition.his.entity.HisWeightRecord;
import com.jumper.weight.nutrition.his.enums.Cervix;
import com.jumper.weight.nutrition.his.enums.Dysmenorrhea;
import com.jumper.weight.nutrition.his.enums.GynecologyInspection;
import com.jumper.weight.nutrition.his.enums.ImpregnationWay;
import com.jumper.weight.nutrition.his.enums.PeriodAmount;
import com.jumper.weight.nutrition.his.enums.PregnantType;
import com.jumper.weight.nutrition.his.enums.UterineAccessory;
import com.jumper.weight.nutrition.his.enums.UterineBody;
import com.jumper.weight.nutrition.his.enums.Vagina;
import com.jumper.weight.nutrition.his.enums.Vulua;
import com.jumper.weight.nutrition.his.mapper.HisDiseaseHistoryMapper;
import com.jumper.weight.nutrition.his.mapper.HisInspectionRecordMapper;
import com.jumper.weight.nutrition.his.mapper.HisUserExtraInfoMapper;
import com.jumper.weight.nutrition.his.mapper.HisWeightRecordMapper;
import com.jumper.weight.nutrition.his.service.HisInfoService;
import com.jumper.weight.nutrition.his.vo.HisRetMsg;
import com.jumper.weight.nutrition.his.vo.VoHIsRepeatRecord;
import com.jumper.weight.nutrition.his.vo.VoHisFirstRecord;
import com.jumper.weight.nutrition.his.vo.VoHisInfo;
import com.jumper.weight.nutrition.hospital.entity.HospitalModuleHost;
import com.jumper.weight.nutrition.hospital.mapper.HospitalModuleHostMapper;
import com.jumper.weight.nutrition.user.entity.HospitalUserInfo;
import com.jumper.weight.nutrition.user.entity.UserInfo;
import com.jumper.weight.nutrition.user.mapper.UserInfoMapper;
import com.jumper.weight.nutrition.user.service.HospitalUserInfoService;

@Service
public class HisInfoServiceImpl implements HisInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(HisInfoServiceImpl.class);
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private HospitalUserInfoService hospitalUserInfoService;
	@Autowired
	private HisUserExtraInfoMapper hisUserExtraInfoMapper;
	@Autowired
	private HisInspectionRecordMapper hisInspectionRecordMapper;
	@Autowired
	private HisDiseaseHistoryMapper hisDiseaseHistoryMapper;
	@Autowired
	private HospitalModuleHostMapper hospitalModuleHostMapper;
	@Autowired
	private HisWeightRecordMapper hisWeightRecordMapper;
	/*@Autowired
	private UserWeightRecordService userWeightRecordService;*/
	
	@Override
	public VoHisInfo synchorHisInfo(int hospitalId, String registerNum) {
		//http查询HIS用户信息
		VoHisInfo hisInfo = findHttpHisInfo(hospitalId, registerNum);
		if (hisInfo == null) {
			return null;
		}
		//插入一个天使用户
		Integer userId = registerTsUserByHttp(hisInfo, hospitalId);
		if (userId == null) {
			return null;
		}
		
		//插入或更新业务用户
		HospitalUserInfo hospUser = new HospitalUserInfo();
		hospUser.setRegisterNum(registerNum);//设置登记号
		hospUser.setHospitalId(hospitalId);
		hospUser.setUserId(userId);
		//hospUser.setOutpatientNum();
		hospUser.setPregnantType(PregnantType.getTypeByName(hisInfo.getPregnantType()));
		hospUser.setAddTime(new Date());
		hospUser.setMobile(hisInfo.getMobile());
		hospUser.setRealName(hisInfo.getRealName());
		hospUser.setHeight(hisInfo.getHeight());
		hospUser.setWeight(hisInfo.getWeight());
		Date expDate = TimeUtils.stringFormatToDate(hisInfo.getExpectedDate(), Const.YYYYMMDD);
		hospUser.setExpectedDate(expDate);
		Date birth = TimeUtils.stringFormatToDate(hisInfo.getBirthday(), Const.YYYYMMDD);
		hospUser.setBirthday(birth);
		hospitalUserInfoService.addOrUpdateHospUser(hospUser);
		int hospUserId = hospUser.getId();
		//插入或更新his信息
		try {
			//保存HIS医院用户信息扩展表
			HisUserExtraInfo hisUserExt = getHisUserExtraInfo(hisInfo, hospUserId);
			HisUserExtraInfo huei = hisUserExtraInfoMapper.findByHospUId(hospUserId);
			if (huei != null) {
				hisUserExt.setId(huei.getId());
				hisUserExtraInfoMapper.update(hisUserExt);
			} else {
				hisUserExtraInfoMapper.insert(hisUserExt);
			}
			//保存HIS检查信息表
			HisInspectionRecord hisInsRec = getHisInspectionRecord(hisInfo, hospUserId);
			HisInspectionRecord hir = hisInspectionRecordMapper.findByHospUId(hospUserId);
			if (hir != null) {
				hisInsRec.setId(hir.getId());
				hisInspectionRecordMapper.update(hisInsRec);
			} else {
				hisInspectionRecordMapper.insert(hisInsRec);
			}
			//保存HIS病史信息表
			HisDiseaseHistory hisDisHis = getHisDiseaseHistory(hisInfo, hospUserId);
			HisDiseaseHistory hdh = hisDiseaseHistoryMapper.findByHospUId(hospUserId);
			if (hdh != null) {
				hisDisHis.setId(hdh.getId());
				hisDiseaseHistoryMapper.update(hisDisHis);
			} else {
				hisDiseaseHistoryMapper.insert(hisDisHis);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new RuntimeException(e2);
		}
		
		//同步复检体重信息
		Double currWeight = synchorHisWeightRec(hospitalId, registerNum, hospUserId, userId);
		
		//封装需要返回的信息
		Double currBmi = FunctionUtils.getBMI(hisInfo.getHeight(), currWeight);
		hisInfo.setRegisterNum(registerNum);
		hisInfo.setUserId(userId);
		hisInfo.setCurrentWeight(currWeight);
		hisInfo.setCurrentBmi(currBmi);
		return hisInfo;
	}
	
	/**
	 * 插入天使用户
	 * @createTime 2017年8月30日,上午10:07:36
	 * @createAuthor fangxilin
	 * @param hisInfo
	 * @param hospitalId
	 * @return
	 */
	private Integer registerTsUserByHttp(VoHisInfo hisInfo, int hospitalId) {
		Integer userId = null;
		//查询天使用户表
		UserInfo userInfo = userInfoMapper.findByMobile(hisInfo.getMobile());
		if (userInfo == null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mobile", hisInfo.getMobile());
			params.put("password", "123456");
			params.put("expect_date", hisInfo.getExpectedDate());
			params.put("real_name", hisInfo.getRealName());
			params.put("current_identity", 0);
			params.put("hospital_id", hospitalId);
			params.put("mom_birthday", hisInfo.getBirthday());
			HttpClient client = new HttpClient(Consts.USER_PORTAL_URL + "/registerUser", params, HttpClient.TYPE_JSON);
			String result = "";
			try {
				result = client.post();
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
			if (StringUtils.isEmpty(result)) {
				return null;//添加失败
			}
			ReturnMsg ret = JSON.parseObject(result, ReturnMsg.class);
			if(ret.getMsg() != 1) {
				logger.info("---------" + ret.getMsgbox());
				return null;//添加失败
			}
			Map<String, Object> map = (Map<String, Object>) ret.getData();
			userId = (Integer) map.get("userId");
		} else {
			userId = userInfo.getId();
		}
		return userId;
	}
	
	private VoHisInfo getVoHisInfo(VoHisFirstRecord voRet) {
		VoHisInfo data = new VoHisInfo();
		data.setRealName(voRet.getItem1());
		data.setHealthNum(voRet.getStuHealthNo());
		data.setIdCard(voRet.getIdCard());
		data.setMobile(voRet.getItem221());
		data.setBirthday(voRet.getBirthday());
		data.setHeight(voRet.getItem47());
		data.setWeight(voRet.getItem49());
		String bloodPresure = (voRet.getItem45() != null && voRet.getItem46() != null) ? voRet.getItem45() + "/" + voRet.getItem46() : "";
		data.setBloodPresure(bloodPresure);
		data.setPregnantType(PregnantType.dantai.getName());//默认单胎
		data.setInitialSurveyDate(voRet.getItem3());
		data.setImpregnationWay(voRet.getItem2());
		data.setImplantationDate(voRet.getItem173());
		data.setPregnantTimes(voRet.getItem11());
		data.setBirthTimes(voRet.getItem12());
		data.setLastPeriod(voRet.getItem6());
		Integer pweek = (voRet.getItem162() != null && voRet.getItem163() != null) ? voRet.getItem162() * 7 + voRet.getItem163() : null;
		data.setPregnantWeek(pweek);
		Integer corrPweek = (voRet.getItem7() != null && voRet.getItem8() != null) ? voRet.getItem7() * 7 + voRet.getItem8() : null;
		data.setCorrectPregnantWeek(corrPweek);
		data.setExpectedDate(voRet.getItem164());
		data.setCorrectExpectedDate(voRet.getItem10());
		data.setMenarcheAge(voRet.getItem165());
		String periodCycle = (StringUtils.isNotEmpty(voRet.getItem166()) && StringUtils.isNotEmpty(voRet.getItem174())) ? voRet.getItem166() + "/" + voRet.getItem174() : "";
		data.setPeriodCycle(periodCycle);//周期
		data.setPeriodAmount(voRet.getItem167());
		data.setDysmenorrhea(voRet.getItem228());
		data.setMarryAge(voRet.getItem15());
		data.setGeneralInspection(voRet.getItem44());
		data.setPelvisInspection(voRet.getItem60());
		data.setGynecologyInspection(voRet.getItem205());
		data.setVulua(voRet.getItem67());
		data.setVagina(voRet.getItem68());
		data.setCervix(voRet.getItem69());
		data.setUterineBody(voRet.getItem70());
		data.setUterineDay(voRet.getItem71());
		data.setUterineAccessory(voRet.getItem178());
		data.setBloodType(voRet.getItem229());
		data.setHbvm(voRet.getItem227());
		data.setHcv(voRet.getItem231());
		String syphilis = "", hiv = "";//梅毒和HIV
		if (StringUtils.isNotEmpty(voRet.getItem235())) {
			String[] sypHiv = voRet.getItem235().split(",");
			syphilis = sypHiv[0];
			hiv = (sypHiv.length > 1) ? sypHiv[1] : "";
		}
		data.setSyphilis(syphilis);
		data.setHiv(hiv);
		data.setGbs(voRet.getItem239());
		data.setOgtt(voRet.getItem237());
		data.setAllergyHistory(voRet.getItem172());
		data.setPastHistory(voRet.getItem170());
		data.setFamilyHistory(voRet.getItem171());
		data.setBirthHistory(voRet.getItem177());
		data.setPregnancySpecialCase(voRet.getItem222());
		//需要计算的值
		Date birth = TimeUtils.stringFormatToDate(voRet.getBirthday(), Const.YYYYMMDD);
		Integer age = TimeUtils.getTowDateMinusYear(new Date(), birth);
		Double bmi = FunctionUtils.getBMI(voRet.getItem47(), voRet.getItem49());
		data.setAge(age);
		data.setBmi(bmi);
		return data;
	}
	
	/**
	 * 获取HisUserExtraInfo
	 * @createTime 2017年8月28日,下午3:51:55
	 * @createAuthor fangxilin
	 * @param hisInfo
	 * @return
	 */
	private HisUserExtraInfo getHisUserExtraInfo(VoHisInfo hisInfo, int hospUserId) {
		HisUserExtraInfo data = new HisUserExtraInfo();
		data.setHospUserId(hospUserId);
		data.setHealthNum(hisInfo.getHealthNum());
		data.setIdCard(hisInfo.getIdCard());
		data.setInitialSurveyDate(TimeUtils.convertToDate(hisInfo.getInitialSurveyDate()));
		data.setImpregnationWay(ImpregnationWay.getTypeByName(hisInfo.getImpregnationWay()));
		data.setImplantationDate(TimeUtils.convertToDate(hisInfo.getImplantationDate()));
		data.setPregnantTimes(hisInfo.getPregnantTimes());
		data.setBirthTimes(hisInfo.getBirthTimes());
		data.setPregnantWeek(hisInfo.getPregnantWeek());
		data.setCorrectPregnantWeek(hisInfo.getCorrectPregnantWeek());
		data.setCorrectExpectedDate(TimeUtils.convertToDate(hisInfo.getCorrectExpectedDate()));
		data.setMenarcheAge(hisInfo.getMenarcheAge());
		data.setPeriodCycle(hisInfo.getPeriodCycle());
		data.setPeriodAmount(PeriodAmount.getTypeByName(hisInfo.getPeriodAmount()));
		data.setDysmenorrhea(Dysmenorrhea.getTypeByName(hisInfo.getDysmenorrhea()));
		data.setMarryAge(hisInfo.getMarryAge());
		return data;
	}
	
	/**
	 * 获取HisInspectionRecord
	 * @createTime 2017年8月28日,下午5:48:03
	 * @createAuthor fangxilin
	 * @param hisInfo
	 * @param hospUserId
	 * @return
	 */
	private HisInspectionRecord getHisInspectionRecord(VoHisInfo hisInfo, int hospUserId) {
		HisInspectionRecord data = new HisInspectionRecord();
		data.setHospUserId(hospUserId);
		data.setGeneralInspection(hisInfo.getGeneralInspection());
		data.setPelvisInspection(hisInfo.getPelvisInspection());
		data.setGynecologyInspection(GynecologyInspection.getTypeByName(hisInfo.getGynecologyInspection()));
		data.setVulua(Vulua.getTypeByName(hisInfo.getVulua()));
		data.setVagina(Vagina.getTypeByName(hisInfo.getVagina()));
		data.setCervix(Cervix.getTypeByName(hisInfo.getCervix()));
		data.setUterineBody(UterineBody.getTypeByName(hisInfo.getUterineBody()));
		data.setUterineDay(hisInfo.getUterineDay());
		data.setUterineAccessory(UterineAccessory.getTypeByName(hisInfo.getUterineAccessory()));
		data.setBloodType(hisInfo.getBloodType());
		data.setHbvm(hisInfo.getHbvm());
		data.setHcv(hisInfo.getHcv());
		data.setSyphilis(hisInfo.getSyphilis());
		data.setHiv(hisInfo.getHiv());
		data.setGbs(hisInfo.getGbs());
		data.setOgtt(hisInfo.getOgtt());
		return data;
	}
	
	/**
	 * 获取HisDiseaseHistory
	 * @createTime 2017年8月28日,下午5:51:34
	 * @createAuthor fangxilin
	 * @param hisInfo
	 * @param hospUserId
	 * @return
	 */
	private HisDiseaseHistory getHisDiseaseHistory(VoHisInfo hisInfo, int hospUserId) {
		HisDiseaseHistory data = new HisDiseaseHistory();
		data.setHospUserId(hospUserId);
		data.setAllergyHistory(hisInfo.getAllergyHistory());
		data.setPastHistory(hisInfo.getPastHistory());
		data.setFamilyHistory(hisInfo.getFamilyHistory());
		data.setBirthHistory(hisInfo.getBirthHistory());
		data.setPregnancySpecialCase(hisInfo.getPregnancySpecialCase());
		return data;
	}
	
	/**
	 * 同步HIS复检体重记录
	 * @createTime 2017年8月30日,上午10:12:49
	 * @createAuthor fangxilin
	 * @param hospitalId
	 * @param registerNum
	 * @param hospUserId
	 * @param userId
	 * @return 返回最近的一条体重记录
	 */
	private Double synchorHisWeightRec(int hospitalId, String registerNum, int hospUserId, int userId) {
		//宝安妇幼HIS/电子产检信息接口，12表示医院his用户信息
		HospitalModuleHost module = hospitalModuleHostMapper.findHostByHospType(hospitalId, 12);
		if (module == null) {
			return null;
		}
		String url = module.getHost() + "/service/recordItems";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("interfaceUser", Consts.BAFY_INTERFACE_USER);//接口用户
		params.put("interfaceUserPwd", Consts.BAFY_INTERFACE_PWD);//接口密码
		params.put("registerNo", registerNum);
		HttpClient client = new HttpClient(url, params);
		String result = "";
		//调取宝安妇幼复检信息记录接口
		try {
			result = client.post();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(result)) {
			logger.info("---------result为空！");
			return null;
		}
		HisRetMsg ret = JSON.parseObject(result, HisRetMsg.class);
		if (!"1".equals(ret.getCode())) {
			logger.info("---------result msg结果不为1！");
			return null;
		}
		List<VoHIsRepeatRecord> voRet = JSON.parseArray(JSON.toJSONString(ret.getData()), VoHIsRepeatRecord.class);
		if (ArrayUtils.isEmpty(voRet)) {
			logger.info("---------result data结果为空！");
			return null;
		}
		
		//同步到本地表
		List<HisWeightRecord> hisWeiList = hisWeightRecordMapper.listHisWeightByHUId(hospUserId);
		List<HisWeightRecord> addList = new ArrayList<HisWeightRecord>(), updList = new ArrayList<HisWeightRecord>();
		for (VoHIsRepeatRecord obj : voRet) {
			if (obj.getItem5() == null) {
				continue;//体重值为空的记录跳过
			}
			Date dt = TimeUtils.convertToDate(obj.getItem2());
			HisWeightRecord sameRec = null;
			for (HisWeightRecord hisWei : hisWeiList) {
				if (TimeUtils.isSameDay(dt, hisWei.getAddDate())) {
					sameRec = hisWei;
					break;
				}
			}
			if (sameRec != null && obj.getItem5().equals(sameRec.getWeight())) {//体重值相等，不用更新
				continue;
			}
			//需要保存的记录
			HisWeightRecord saveBean = new HisWeightRecord();
			saveBean.setWeight(obj.getItem5());
			if (sameRec != null) {
				saveBean.setId(sameRec.getId());
				updList.add(saveBean);
			} else {
				saveBean.setHospUserId(hospUserId);
				saveBean.setAddDate(dt);
				addList.add(saveBean);
			}
		}
		//批量更新添加
		try {
			if (ArrayUtils.isNotEmpty(addList)) {
				hisWeightRecordMapper.insertBatch(addList);
			}
			if (ArrayUtils.isNotEmpty(updList)) {
				hisWeightRecordMapper.updateBatch(updList);
			}
		} catch (Exception e) {
			logger.error("---------同步HIS复检体重记录异常");
			e.printStackTrace();
		}
		
		/*VoUserWeightRecord last = userWeightRecordService.findUserLastWeight(userId, hospitalId);
		if (last == null) {//为空时添加一条昨日的体重记录
			//将最近体重同步到天使，添加或更新体重监测数据
			Date date = TimeUtils.getDateByDaysLate(-1, new Date());
			userWeightRecordService.saveWeightByDate(currWeight, date, userId, hospitalId);
		}*/
		
		//查询最近一条不为空的体重记录值
		Double currWeight = voRet.get(voRet.size() - 1).getItem5();//当前最新的体重值
		for (int i = voRet.size() - 1; i >=0; i--) {
			VoHIsRepeatRecord obj = voRet.get(i);
			if (obj.getItem5() != null) {
				currWeight = obj.getItem5();
				break;
			}
		}
		return currWeight;
	}

	@Override
	public VoHisInfo findHttpHisInfo(int hospitalId, String registerNum) {
		//宝安妇幼HIS/电子产检信息接口，12表示医院his用户信息
		HospitalModuleHost module = hospitalModuleHostMapper.findHostByHospType(hospitalId, 12);
		if (module == null) {
			return null;
		}
		String url1 = module.getHost() + "/service/recordFirst";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("interfaceUser", Consts.BAFY_INTERFACE_USER);//接口用户
		params.put("interfaceUserPwd", Consts.BAFY_INTERFACE_PWD);//接口密码
		params.put("registerNo", registerNum);
		HttpClient client = new HttpClient(url1, params);
		String result = "";
		//调取宝安妇幼HIS/电子产检信息接口
		try {
			result = client.post();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(result)) {
			logger.info("---------result为空！");
			return null;
		}
		HisRetMsg ret = JSON.parseObject(result, HisRetMsg.class);
		if (!"1".equals(ret.getCode())) {
			logger.info("---------result msg结果不为1！");
			return null;
		}
		VoHisFirstRecord voRet = JSON.parseObject(JSON.toJSONString(ret.getData()), VoHisFirstRecord.class);
		if (voRet == null) {
			logger.info("---------result data结果为空！");
			return null;
		}
		VoHisInfo hisInfo = getVoHisInfo(voRet);//转换成VoHisInfo
		return hisInfo;
	}

}
