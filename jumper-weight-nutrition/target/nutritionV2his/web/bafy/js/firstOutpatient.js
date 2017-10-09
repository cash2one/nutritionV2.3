//web端第一个页面赋初始cookie值
var hospitalId = GetQueryString("hospitalId");
var registerNum = GetQueryString("registerNum");
$.cookie("hospitalId", hospitalId);
$.cookie("registerNum", registerNum);
$.cookie("hisType", 0);//初诊

//页面所需后台的数据
$(function() {
	$.ajaxSetup({ //设置ajax为同步提交
        async: false
    });
    
	//点击确认信息，跳转到膳食调查
	$("#submit-btn").click(function() {
		$("#submitForm").submit();
		window.location.href = "dietSurvey.html?userId=" + userId + "&outpatientId=" + outpatientId;
	});
});