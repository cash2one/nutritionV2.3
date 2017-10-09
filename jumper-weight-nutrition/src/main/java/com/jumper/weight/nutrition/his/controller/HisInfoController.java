package com.jumper.weight.nutrition.his.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.weight.common.base.BaseController;
import com.jumper.weight.common.util.ReturnMsg;
import com.jumper.weight.nutrition.his.service.HisInfoService;
import com.jumper.weight.nutrition.his.service.HisWeightRecordService;
import com.jumper.weight.nutrition.his.vo.VoHisInfo;
import com.jumper.weight.nutrition.his.vo.VoWeightChart;
import com.jumper.weight.nutrition.hospital.entity.HospitalInfo;
import com.jumper.weight.nutrition.hospital.service.HospitalInfoService;
import com.jumper.weight.nutrition.user.entity.HospitalOutpatient;
import com.jumper.weight.nutrition.user.entity.HospitalUserInfo;
import com.jumper.weight.nutrition.user.service.HospitalOutpatientService;
import com.jumper.weight.nutrition.user.service.HospitalUserInfoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/hisInfo")
@Api(value = "/hisInfo", description = "医院his信息")
public class HisInfoController extends BaseController {
	
	//宝安妇幼医院id
	private static final int BAFY_HOSPITAL_ID = 42;
	
	@Autowired
	private HisInfoService hisInfoService;
	@Autowired
	private HospitalOutpatientService hospitalOutpatientService;
	@Autowired
	private HospitalInfoService hospitalInfoService;
	@Autowired
	private HospitalUserInfoService hospitalUserInfoService;
	@Autowired
	private HisWeightRecordService hisWeightRecordService;
	
	/**
	 * h5端同步HIS用户信息，绑定登记号（宝安妇幼）
	 * @createTime 2017年8月24日,下午6:07:52
	 * @createAuthor fangxilin
	 * @param hospitalId
	 * @param registerNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/synchorHisInfo")
	@ApiOperation(value = "同步HIS用户信息，绑定登记号", httpMethod = "POST", response = ReturnMsg.class, notes = "同步HIS用户信息，绑定登记号", position = 1)
	public ReturnMsg synchorHisInfo(@ApiParam(value = "医院id") @RequestParam int hospitalId,
			@ApiParam(value = "登记号") @RequestParam String registerNum) {
		try {
			if (StringUtils.isEmpty(registerNum)) {
				return new ReturnMsg(ReturnMsg.FAIL, "登记号不能为空");
			}
			VoHisInfo data = hisInfoService.synchorHisInfo(hospitalId, registerNum);
			if (data == null) {
				return new ReturnMsg(ReturnMsg.FAIL, "同步HIS用户信息，绑定登记号失败");
			}
			HospitalInfo hosp = hospitalInfoService.findById(hospitalId);
			String hospitalName = (hosp != null) ? hosp.getName() : ""; 
			data.setHospitalName(hospitalName);
			//返回用户信息
			return new ReturnMsg(ReturnMsg.SUCCESS, "同步HIS用户信息，绑定登记号成功", data);
		} catch (Exception e) {
			logger.error("同步HIS用户信息，绑定登记号异常");
			e.printStackTrace();
			return new ReturnMsg(ReturnMsg.FAIL, "同步HIS用户信息，绑定登记号异常");
		}
	}
	
	/**
	 * http查询HIS用户信息
	 * @createTime 2017年8月31日,下午3:07:43
	 * @createAuthor fangxilin
	 * @param hospitalId
	 * @param registerNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/findHttpHisInfo")
	@ApiOperation(value = "http查询HIS用户信息", httpMethod = "POST", response = ReturnMsg.class, notes = "http查询HIS用户信息", position = 2)
	public ReturnMsg findHttpHisInfo(@ApiParam(value = "医院id") @RequestParam int hospitalId,
			@ApiParam(value = "登记号") @RequestParam String registerNum) {
		try {
			if (StringUtils.isEmpty(registerNum)) {
				return new ReturnMsg(ReturnMsg.FAIL, "登记号不能为空");
			}
			VoHisInfo data = hisInfoService.findHttpHisInfo(hospitalId, registerNum);
			if (data == null) {
				return new ReturnMsg(ReturnMsg.FAIL, "http查询HIS用户信息失败");
			}
			//返回用户信息
			return new ReturnMsg(ReturnMsg.SUCCESS, "http查询HIS用户信息成功", data);
		} catch (Exception e) {
			logger.error("http查询HIS用户信息异常");
			e.printStackTrace();
			return new ReturnMsg(ReturnMsg.FAIL, "http查询HIS用户信息异常");
		}
	}
	
	/**
	 * 通过登记号查询业务用户表
	 * @createTime 2017年8月31日,下午3:07:43
	 * @createAuthor fangxilin
	 * @param hospitalId
	 * @param registerNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/findUserByRegNum")
	@ApiOperation(value = "通过登记号查询业务用户表", httpMethod = "POST", response = ReturnMsg.class, notes = "通过登记号查询业务用户表", position = 3)
	public ReturnMsg findUserByRegNum(@ApiParam(value = "医院id") @RequestParam int hospitalId,
			@ApiParam(value = "登记号") @RequestParam String registerNum) {
		try {
			if (StringUtils.isEmpty(registerNum)) {
				return new ReturnMsg(ReturnMsg.FAIL, "登记号不能为空");
			}
			HospitalUserInfo data = hospitalUserInfoService.findHUserByRegNum(hospitalId, registerNum);
			if (data == null) {
				return new ReturnMsg(ReturnMsg.DATA_NULL, "通过登记号查询业务用户表为空");
			}
			//返回用户信息
			return new ReturnMsg(ReturnMsg.SUCCESS, "通过登记号查询业务用户表成功", data);
		} catch (Exception e) {
			logger.error("通过登记号查询业务用户表异常");
			e.printStackTrace();
			return new ReturnMsg(ReturnMsg.FAIL, "通过登记号查询业务用户表异常");
		}
	}
	
	
	/**
	 * web端初复诊同步HIS用户信息
	 * @createTime 2017年8月31日,下午2:49:30
	 * @createAuthor fangxilin
	 * @param hospitalId
	 * @param registerNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/synchorOutpHisInfo")
	@ApiOperation(value = "web端初复诊同步HIS用户信息", httpMethod = "POST", response = ReturnMsg.class, notes = "web端初复诊同步HIS用户信息", position = 4)
	public ReturnMsg synchorOutpHisInfo(@ApiParam(value = "医院id") @RequestParam int hospitalId,
			@ApiParam(value = "登记号") @RequestParam String registerNum,
			@ApiParam(value = "门诊id，如果不为空，那么不添加门诊记录") @RequestParam(required = false) Integer outpatientId) {
		try {
			if (StringUtils.isEmpty(registerNum)) {
				return new ReturnMsg(ReturnMsg.FAIL, "登记号不能为空");
			}
			//先同步信息
			VoHisInfo data = hisInfoService.synchorHisInfo(BAFY_HOSPITAL_ID, registerNum);
			if (data == null) {
				return new ReturnMsg(ReturnMsg.FAIL, "web端初复诊同步HIS用户信息失败");
			}
			if (outpatientId == null) {//添加一条门诊记录
				outpatientId = hospitalOutpatientService.addOutpatient(data.getUserId(), BAFY_HOSPITAL_ID, null, 0);
			}
			
			String reason = hospitalOutpatientService.findOutpReasonById(outpatientId);
			//设置门诊id和门诊原因
			data.setOutpatientId(outpatientId);
			data.setOutpatientReason(reason);
			//查询就诊卡号
			HospitalUserInfo hospUser = hospitalUserInfoService.findHospitalUserInfo(hospitalId, data.getUserId());
			data.setOutpatientNum(hospUser.getOutpatientNum());
			
			//返回用户信息
			return new ReturnMsg(ReturnMsg.SUCCESS, "web端初复诊同步HIS用户信息成功", data);
		} catch (Exception e) {
			logger.error("web端初复诊同步HIS用户信息异常");
			e.printStackTrace();
			return new ReturnMsg(ReturnMsg.FAIL, "web端初复诊同步HIS用户信息异常");
		}
	}
	
	/**
	 * 获取用户体重曲线图数据
	 * @createTime 2017-5-5,下午2:58:09
	 * @createAuthor fangxilin
	 * @param userId
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getWeightChartData")
	@ApiOperation(value = "获取用户体重曲线图数据", httpMethod = "POST", response = ReturnMsg.class, notes = "获取用户体重曲线图数据", position = 5)
	public ReturnMsg getWeightChartData(@ApiParam(value = "用户id") @RequestParam int userId, 
			@ApiParam(value = "医院id") @RequestParam int hospitalId, 
			@ApiParam(value = "获取类型（0：近30天，1：所有）") @RequestParam int type) {
		try {
			VoWeightChart data = hisWeightRecordService.getWeightChartData(userId, type, hospitalId);
			return new ReturnMsg(ReturnMsg.SUCCESS, "获取用户体重曲线图数据成功", data);
		} catch (Exception e) {
			logger.error("获取用户体重曲线图数据异常");
			e.printStackTrace();
			return new ReturnMsg(ReturnMsg.FAIL, "获取用户体重曲线图数据异常");
		}
	}
	
	/**
	 * 宝安妇幼体重营养入口
	 * @createTime 2017年8月29日,下午3:51:58
	 * @createAuthor fangxilin
	 * @param registerNum
	 * @param isFirst
	 * @param isFinish
	 * @return
	 */
	@RequestMapping("/outpatient")
	public String outpatient(@ApiParam(value = "登记号") @RequestParam String registerNum) {
		//宝安妇幼前置机IP端口
		String bafyHost = getRequest().getHeader("L-Host-IP");
		bafyHost = (StringUtils.isNotEmpty(bafyHost)) ? "http://" + bafyHost + getRequest().getContextPath() : "";
		//门诊类型 0：初诊用户，1：复诊用户，2：完成诊断用户
		int hisType = 0;//默认初诊
		//判断是初诊还是复诊
		HospitalUserInfo hUser = hospitalUserInfoService.findHUserByRegNum(BAFY_HOSPITAL_ID, registerNum);
		if (hUser != null) {
			HospitalOutpatient outp = hospitalOutpatientService.findUserLastOutpatient(BAFY_HOSPITAL_ID, hUser.getUserId(), null);
			if (outp == null || (outp.getStatus() == 0 && outp.getIsFinish() == 0)) {
				hisType = 0;
			} else {
				hisType = 1;
			}
		}
		if (hisType == 0) {
			return "redirect:" + bafyHost + "/web/bafy/firstOutpatient.html?hospitalId=" + BAFY_HOSPITAL_ID + "&registerNum=" + registerNum;
		} else {
			return "redirect:" + bafyHost + "/web/bafy/repeatOutpatient.html?hospitalId=" + BAFY_HOSPITAL_ID + "&registerNum=" + registerNum;
		}
	}
}
