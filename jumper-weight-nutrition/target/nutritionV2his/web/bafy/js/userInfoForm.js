/**
 * his用户信息
 */

//查看全部弹框1：无弹框
var jumpType = $("input[name='jumpType']").val();
//门诊id
var outpatientId = (GetQueryString("outpatientId") != null) ? GetQueryString("outpatientId") : undefined;;
var userId = (GetQueryString("userId") != null) ? GetQueryString("userId") : undefined;

//页面所需后台的数据
$(function() {
	$.ajaxSetup({ //设置ajax为同步提交
        async: false
    });
	
	$("#userInfoHtml").load("common/userInfoForm.html");
	if (jumpType == 1) {//建档页面，无弹框
		$(".userInfoTab").show();
	} else {//用户信息页面，有弹框
		$("#headUserInfo").load("common/headUserInfo.html");
	}
	
	//宝安妇幼HIS体重营养入口参数
	var registerNum = $.cookie("registerNum");
	var data = {hospitalId : hospitalId, registerNum : registerNum, outpatientId : outpatientId};
	$.post(basePath + "/hisInfo/synchorOutpHisInfo", data, function(ret) {
		if (ret.msg != 1) {
			layer.msg(ret.msgbox, {time : 1000});
			return;
		}
		var obj = ret.data;
		if (outpatientId == null) {
			outpatientId = obj.outpatientId;
			//设置cookie
			$.cookie("outpatientId", outpatientId);
		}
		if (userId == null) {
			userId = obj.userId;
		}
		//赋值
		$(".realName").text(obj.realName);
		$(".registerNum").text(obj.registerNum);
		$(".healthNum").text(obj.healthNum);
		$(".idCard").text(obj.idCard);
		$(".mobile").text(obj.mobile);
		$(".age").text(obj.age);
		$(".height").text(obj.height);
		$(".weight").text(obj.weight);
		$(".bmi").text(obj.bmi);
		$(".currentBmi").text(obj.currentBmi);
		$(".currentWeight").text(obj.currentWeight);
		$(".bloodPresure").text(obj.bloodPresure);
		$(".pregnantType").text(obj.pregnantType);
		$(".initialSurveyDate").text(obj.initialSurveyDate);
		$(".impregnationWay").text(obj.impregnationWay);
		$(".implantationDate").text(obj.implantationDate);
		$(".birthTimes").text(obj.birthTimes);
		$(".pregnantTimes").text(obj.pregnantTimes);
		$(".lastPeriod").text(obj.lastPeriod);
		var pweek = (obj.pregnantWeek != null) ? parseInt(obj.pregnantWeek / 7) + "周" + (obj.pregnantWeek % 7) + "天" : "";
		$(".pregnantWeek").text(pweek);
		var cpweek = (obj.correctPregnantWeek != null) ? parseInt(obj.correctPregnantWeek / 7) + "周" + (obj.correctPregnantWeek % 7) + "天" : "";
		$(".correctPregnantWeek").text(cpweek);
		$(".expectedDate").text(obj.expectedDate);
		$(".correctExpectedDate").text(obj.correctExpectedDate);
		$(".menarcheAge").text(obj.menarcheAge);
		$(".periodCycle").text(obj.periodCycle);
		$(".periodAmount").text(obj.periodAmount);
		$(".dysmenorrhea").text(obj.dysmenorrhea);
		$(".marryAge").text(obj.marryAge);
		$(".generalInspection").text(obj.generalInspection);
		$(".pelvisInspection").text(obj.pelvisInspection);
		$(".gynecologyInspection").text(obj.pregnantType);
		$(".vulua").text(obj.vulua);
		$(".vagina").text(obj.vagina);
		$(".cervix").text(obj.cervix);
		$(".uterineBody").text(obj.uterineBody);
		$(".uterineDay").text(obj.uterineDay);
		$(".uterineAccessory").text(obj.uterineAccessory);
		$(".bloodType").text(obj.bloodType);
		$(".hbvm").text(obj.hbvm);
		$(".hcv").text(obj.hcv);
		$(".syphilis").text(obj.syphilis);
		$(".hiv").text(obj.hiv);
		$(".gbs").text(obj.gbs);
		$(".ogtt").text(obj.ogtt);
		$(".allergyHistory").text(obj.allergyHistory);
		$(".pastHistory").text(obj.pastHistory);
		$(".familyHistory").text(obj.familyHistory);
		$(".birthHistory").text(obj.birthHistory);
		$(".pregnancySpecialCase").text(obj.pregnancySpecialCase);
		$(".outpatientReason").text(obj.outpatientReason);
		//headerUserInfo
		$(".realNameSpan").text(obj.realName);
		$(".outpNumSpan").text(obj.outpatientNum);
		$(".ageSpan").text(obj.age);
		$(".pweekSpan").text(pweek);
		$(".pregTypeSpan").text(obj.pregnantType);
		$(".currWeightSpan").text(obj.currentWeight);
	});
});

/*--------------------  layui 弹 框     ---------------------- --*/	
function ShowDiv() {
	layui.use(['layer'], function() {
		var layer = layui.layer;
		layer.open({
			title:'孕妇信息',
  			type: 1,
  			area: ['1120px'], //宽高
  			content: $('.userInfoTab'),
  			btn: [],
			yes: function(index, layero) {
				
			}
		});
	});
}