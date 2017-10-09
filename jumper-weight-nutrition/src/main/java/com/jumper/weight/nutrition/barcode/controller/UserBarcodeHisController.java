package com.jumper.weight.nutrition.barcode.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jumper.dubbo.bean.bo.UserBasicInfo;
import com.jumper.dubbo.bean.po.UserVerifiedCode;
import com.jumper.dubbo.service.UserService;
import com.jumper.weight.common.util.Consts;
import com.jumper.weight.common.util.DesEncryptUtils;
import com.jumper.weight.common.util.HttpClient;
import com.jumper.weight.common.util.ReturnMsg;
import com.jumper.weight.common.util.SignatureUtils;
import com.jumper.weight.common.util.Util;
import com.jumper.weight.nutrition.his.service.HisInfoService;
import com.jumper.weight.nutrition.his.vo.VoHisInfo;
import com.jumper.weight.nutrition.hospital.entity.HospitalHisSetting;
import com.jumper.weight.nutrition.hospital.entity.HospitalInfo;
import com.jumper.weight.nutrition.hospital.service.HospitalHisSettingService;
import com.jumper.weight.nutrition.hospital.service.HospitalInfoService;
import com.jumper.weight.nutrition.user.entity.HospitalUserInfo;
import com.jumper.weight.nutrition.user.service.HospitalUserInfoService;
import com.jumper.weight.nutrition.user.service.UserInfoService;
import com.jumper.weight.nutrition.user.vo.VoUserInfo;

/**
 * 用户扫码入口
 * @author gyx
 * @time 2017年7月28日
 */
@Controller
@RequestMapping("his/barcode")
public class UserBarcodeHisController {
	private final static Logger logger = Logger.getLogger(UserBarcodeHisController.class);
	@Autowired
	private HospitalInfoService hospitalInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private HospitalUserInfoService hospitalUserInfoService; 
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private HospitalHisSettingService hospitalHisSettingService;
	@Autowired
	private HisInfoService hisInfoService;
	
	
	/**
	 * 获取登录验证码
	 * @param hospitalId 医院ID
	 * @param mobile 手机号
	 * @return
	 */
	@RequestMapping("getSmsCode")
	@ResponseBody
	public ReturnMsg getSmsCode(@RequestParam("hospitalId") Integer hospitalId, @RequestParam("mobile") String mobile){
		try {
			if(StringUtils.isEmpty(mobile)) {
				return new ReturnMsg(ReturnMsg.FAIL, "手机号码不能为空！");
			}
			if(!Util.isMobiPhoneNum(mobile)) {
				return new ReturnMsg(ReturnMsg.FAIL, "您输入的手机号码格式不正确，请重新输入！");
			}
			//生成6位数的随机验证码
			String code = RandomStringUtils.random(6, false, true);
//			String content = code+"是你申请注册天使医生(用户端)的验证码。(15分钟内有效，如非本人操作请忽略)";
			String content = "您本次操作的验证码为："+code+"，[任何人向您索取此验证码均为诈骗，请勿告知他人]";
			HospitalInfo hospitalInfo = hospitalInfoService.findById(hospitalId);
			//生成签名
			Map<String, String> params = SignatureUtils.getHttpSignatureParams(hospitalId, hospitalInfo.getName(), mobile, content);
			String sign = SignatureUtils.getSignature(params);
			//调用http请求
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospId", hospitalId.longValue());
			map.put("hospName", hospitalInfo.getName());
			map.put("content", content);
			map.put("mobile", mobile);
			map.put("req_sign", sign);
			map.put("appid", Consts.NUTRITIONV2_SMS_APPID);
			HttpClient client = new HttpClient(Consts.BASE_SERVICE_PATH+"/sms/send_smsbyhb", map, HttpClient.TYPE_JSON);
//			client.addHeader("Content-Type", "application/json;charset=UTF-8");
			String result = client.post();
			if(result != null){
				ReturnMsg returnMsg = JSON.parseObject(result, ReturnMsg.class);
				if(returnMsg.getMsg()==1){
					//保存验证码到数据库
					UserVerifiedCode uv = new UserVerifiedCode();
					uv.setMobile(mobile);
					uv.setCode(code);
					//新增验证码
					userService.saveUserVerifiedCode(uv);
					return new ReturnMsg(ReturnMsg.SUCCESS, "验证码发送成功！");
				}
			}
			return new ReturnMsg(ReturnMsg.FAIL, "验证码发送失败！");
		} catch (IOException e) {
			logger.error("getSmsCode()", e);
			return new ReturnMsg(ReturnMsg.FAIL, "验证码发送失败！");
		}
	}
	
	/**
	 * 验证登录
	 */
	@RequestMapping("verifedLogin")
	@ResponseBody
	public ReturnMsg verifedLogin(@RequestParam("code") String code, @RequestParam("mobile") String mobile, @RequestParam("hospitalId") int hospitalId){
		try {
			if(StringUtils.isEmpty(mobile)) {
				return new ReturnMsg(ReturnMsg.FAIL, "手机号码不能为空！");
			}
			if(StringUtils.isEmpty(code)) {
				return new ReturnMsg(ReturnMsg.FAIL, "请先输入验证码！");
			}
			//通过手机号码查询验证码
			UserVerifiedCode userVerifiedCode = userService.findVerifiedCodeByMobileSQL(mobile);
			if(userVerifiedCode == null) {
				return new ReturnMsg(ReturnMsg.FAIL, "验证码已过期，请重新获取！");
			}
			if(!userVerifiedCode.getCode().equals(code)) {
				return new ReturnMsg(ReturnMsg.FAIL, "验证码错误，请重新输入！");
			}
			//查询用户信息
			VoUserInfo data = userInfoService.findVoUserByMobile(mobile, hospitalId);
			if(data != null){
				return new ReturnMsg(ReturnMsg.SUCCESS, "成功", data);
			}else{
				return new ReturnMsg(ReturnMsg.SUCCESS, "成功");
			}
		} catch (Exception e) {
			logger.error("verifedLogin()", e);
			return new ReturnMsg(ReturnMsg.FAIL, "失败");
		}
	}
	
	/**
	 * 微信公众号、支付宝生活号体重营养菜单请求入口
	 */
	@RequestMapping("wxaliWeightNutritionV2")
	public String wxaliWeightNutritionV2(HttpServletRequest request, HttpServletResponse response){
		//医院Id
		String hospitalId = request.getParameter("hospitalId");
		//渠道
		String channel = request.getParameter("channel");
		//openId
		String openId = request.getParameter("openid");
		Integer hospitalid = Integer.parseInt(DesEncryptUtils.decrypt(hospitalId));
		//查询用户业务表记录，有的话判断是否绑定登记号，没有记录跳转到验证手机号码页面（注意保存openID）
		HospitalUserInfo hospitalUserInfo = hospitalUserInfoService.findHospitalUserInfoByOpenId(hospitalid, channel, openId);
		String redirectUrl = "";
		//openID绑定到业务用户表
		if(hospitalUserInfo != null){
			String mobile = hospitalUserInfo.getMobile();
			Integer userId = hospitalUserInfo.getUserId();
			if(StringUtils.isNotEmpty(hospitalUserInfo.getRegisterNum())){
				//绑定过登记号了，直接跳转用户首页
				redirectUrl += "/mobile/bafy/homePage.html?hospitalId="+hospitalid+"&registerNum="+hospitalUserInfo.getRegisterNum()+"&userId="+userId;
			}else{
				//没有绑定过登记号，跳转到用户绑定登记号页面
				redirectUrl += "/mobile/bafy/bindRegisterNum.html?skipTo=1&hospitalId="+hospitalid+"&mobile="+mobile;
			}
			//redirectUrl += "/mobile/checkPage.html?hospitalId="+hospitalid+"&userId="+userId+"&mobile="+mobile;
		}else{
			//openID没有绑定到业务用户表，跳转登录页面
			redirectUrl += "/mobile/bafy/login.html?hospitalId="+hospitalid+"&channel="+channel+"&openId="+openId;
		}
		logger.info("redirectUrl:"+redirectUrl);
//			response.sendRedirect(redirectUrl);
		return "redirect:"+redirectUrl;
	}
	
	/**
	 * app体重营养菜单请求入口
	 */
	@RequestMapping("appWeightNutritionV2")
	public String appWeightNutritionV2(@RequestParam("hospitalid") int hospitalid, @RequestParam("userid") String userid){
		Integer userId = null;
		if(hospitalid == 42){
			userId = Integer.parseInt(DesEncryptUtils.decrypt(String.valueOf(userid)));
		}else{
			userId = Integer.parseInt(userid);
		}
		//查询用户业务表记录，有的话直接跳转checkPage页面，没有记录跳转到验证手机号码页面
		HospitalUserInfo hospitalUserInfo = hospitalUserInfoService.findHospitalUserInfo(hospitalid, userId);
		String redirectUrl = "";
		if(hospitalUserInfo != null && StringUtils.isNotEmpty(hospitalUserInfo.getRegisterNum())){
			//用户已经绑定到业务表，也绑定到登记号，跳转到用户首页
			redirectUrl += "/mobile/bafy/homePage.html?hospitalId="+hospitalid+"&registerNum="+hospitalUserInfo.getRegisterNum()+"&userId="+userId;
		}else{
			//没有绑定过业务表，跳转到绑定登记号页面
			UserBasicInfo basicInfo = userService.getBasicInfoByUserId(userId);
			String mobile = "";
			if(basicInfo != null){
				mobile = basicInfo.getMobile();
			}
			redirectUrl += "/mobile/bafy/bindRegisterNum.html?skipTo=1&hospitalId="+hospitalid+"&mobile="+mobile;
		}
//		redirectUrl += "/mobile/checkPage.html?hospitalId="+hospitalid+"&userId="+userId+"&mobile="+mobile;
		logger.info("redirectUrl:"+redirectUrl);
		return "redirect:"+redirectUrl;
	}
	
	/**
	 * 针对宝安妇幼his版公众号对接新开接口，由微信公众号入口直接跳转到用户首页
	 */
	/**
	 * 微信公众号、支付宝生活号体重营养菜单请求入口
	 */
	@RequestMapping("wxaliWeightNutritionV2his")
	public String wxaliWeightNutritionV2his(HttpServletRequest request, HttpServletResponse response){
		//医院Id
		String hospitalId = request.getParameter("hospitalId");
		//渠道
		String channel = request.getParameter("channel");
		//openId
		String openId = request.getParameter("openid");
		//登记号
		String registerNum = request.getParameter("regNo");
		//手机号
//		String mobile = request.getParameter("mobile");
		
		Integer hospitalid = Integer.parseInt(DesEncryptUtils.decrypt(hospitalId));
		int isHis = 0;
		String redirectUrl = "";
		Integer userId = 0;
		HospitalHisSetting setting = hospitalHisSettingService.findHospitalHisSettingByHospId(hospitalid);
		if(setting != null){
			isHis = setting.getIsHis();
		}
		if(isHis == 1){
			//医院是对接his版
			//查询用户业务表记录，有的话判断是否绑定登记号
			HospitalUserInfo hospitalUserInfo = hospitalUserInfoService.findHospitalUserInfoByOpenId(hospitalid, channel, openId);
			//openID绑定到业务用户表
			if(hospitalUserInfo != null){
				userId = hospitalUserInfo.getUserId();
				//绑定过登记号直接跳转
				if(StringUtils.isEmpty(hospitalUserInfo.getRegisterNum()) || !registerNum.equals(hospitalUserInfo.getRegisterNum())){
					//没有绑定过登记号，绑定登记号;或者绑定的登记号跟原本的登记号不一致，则修改为新的
					VoHisInfo data = hisInfoService.synchorHisInfo(hospitalid, registerNum);
					if(data != null){
						userId = data.getUserId();
					}else{
						logger.info("同步HIS用户信息，绑定登记号失败");
					}
				}
			}else{
				//openID没有绑定到业务用户表
				//同步HIS用户信息，绑定登记号
				VoHisInfo data = hisInfoService.synchorHisInfo(hospitalid, registerNum);
				if(data != null){
					logger.info("同步HIS用户信息，绑定登记号成功");
					userId = data.getUserId();
					//openID表
					boolean b = hospitalUserInfoService.saveUserOpenId(userId, Integer.parseInt(channel), openId);
					if(!b){
						logger.info("绑定用户openID失败");
					}
				}else{
					logger.info("同步HIS用户信息，绑定登记号失败");
				}
			}
		}else{
			logger.info("该医院没有对接his版");
		}
		redirectUrl += "/mobile/bafy/homePage.html?hospitalId="+hospitalid+"&registerNum="+registerNum+"&userId="+userId;
		return "redirect:"+redirectUrl;
	}
	
}
