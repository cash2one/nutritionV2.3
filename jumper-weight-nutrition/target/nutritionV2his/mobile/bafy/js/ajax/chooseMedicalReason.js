/**
 * 选择就诊原因js
 */
//手机端h5或web端第一个页面赋初始session值
var hospitalId = GetQueryString("hospitalId");
var userId = GetQueryString("userId");
var registerNum = GetQueryString("registerNum");
$.cookie('hospitalId', hospitalId);
$.cookie('userId', userId);

var outpatientId = "";
//页面所需后台的数据
$(function() {
	$.ajaxSetup({ //设置ajax为同步提交
        async: false
    });
	if(userId == null){
		$(".checkInfo").attr("outpType", 0);
		$(".liucheng").show();
	}else{
		var param = {hospitalId : hospitalId, userId : userId};
		//判断是否是初诊，并返回basePath
		$.post(basePath + "/outpatient/isFirstOutpatient", param, function(ret) {
			if (ret.msg != 1) {
				mui.toast(ret.msgbox);
				return;
			}
			$(".checkInfo").attr("outpType", ret.data.type);
		}, "json");
	}
	
	document.activeElement.blur();//收回软键盘
	var data = {hospitalId : hospitalId, registerNum : registerNum};
	//加载用户信息
	$.post(basePath + "/hisInfo/synchorHisInfo", data, function(ret) {
		if (ret.msg == 1) {
			//根据登记号查询用户信息并展示
			$(".realName").text(ret.data.realName);
			$(".mobile").text(ret.data.mobile);
			$(".registerNum").text(ret.data.registerNum);
			$(".hospitalName").text(ret.data.hospitalName);
			if(ret.data.userId != null){
				userId = ret.data.userId;
				$.cookie('userId', userId);
			}
		} else {
			mui.toast(ret.msgbox);
		}
	}, "json");
	
	//下一步，数据验证并且保存跳转
	$(".checkInfo").click(function() {
		if($("#reason").attr("value") == ""){
			mui.toast("请选择就诊原因");
			return;
		}
		var outpType = $(".checkInfo").attr("outpType");
		
		var isSkipDiet = ($("#reason").attr("isSkipDiet") == "true");
		var isSkipSport = ($("#reason").attr("isSkipSport") == "true");
		var reasonId = $("#reason").attr("value");
		if (outpType == 0 && !isSkipDiet) {
			window.location.href = "dietSurvey.html";
		} else if (outpType == 0 && !isSkipSport) {
			window.location.href = "sportSurvey.html";
		} else {
			//否则都跳过的话就保存一条门诊记录
			var outpatientId = "";
			var opData = {hospitalId : hospitalId, userId : userId, outpatientReason : reasonId};
			//保存一条门诊记录
			$.post(basePath + "/outpatient/addOutpatient", opData, function(ret) {
				if (ret.msg != 1) {
					mui.toast(ret.msgbox);
					return;
				}
				outpatientId = ret.data;
				if(outpType == 0){
					//初诊，跳转初诊提示页面
					window.location.href = "firstCheck.html?outpatientId=" + outpatientId;
				}else if(outpType == 2){
					//复诊，跳转复诊提示页面
					window.location.href = "repeatCheck.html?outpatientId=" + outpatientId;
				}
			}, "json");
		}
		//将就诊原因暂时先放到cookie中
		$.cookie('outpReason', reasonId);
	});
});
