/**
 * 天使医生登录
 */
//timer变量，控制时间
var InterValObj; 
//间隔函数，1秒执行 
var count = 60;  
//当前剩余秒数
var curCount;
var hospitalId = GetQueryString("hospitalId");
var channel = GetQueryString("channel");
var openId = GetQueryString("openId");
/*$.cookie("channel",channel);
$.cookie("openId",openId);*/
$(document).ready(function() {
	$.ajaxSetup({ //设置ajax为同步提交
        async: false
    });
	//获取验证码
	$(".yzbutton").on("click", function() {
		curCount = count;
		//手机号码
		var mobile = $("#mobile").val();
		if(mobile == "") {
			$("#mobileTis").html("请输入手机号码");
			return;
		} else {
			$("#mobileTis").html("");
		}
		var code = 0;
		var msgbox = "";
		//发送验证码
		$.ajax({
			url: basePath + "/his/barcode/getSmsCode",
			type: "post",
			data:{mobile:mobile,hospitalId:hospitalId},
			dataType: "json",//从服务器端返回的数据类型
			async: false,//同步请求
			success: function(json) {
				code = json.msg;
				msgbox = json.msgbox;
			}
		});
		if(code == 1) {
			//禁用按钮
			$(".yzbutton").attr("disabled", "true");
			$(".yzbutton").val(curCount + "S");
			//启动计时器，1秒执行一次
			InterValObj = window.setInterval(SetRemainTime, 1000);
		} else {
			//启用按钮
			$(".yzbutton").removeAttr("disabled");
			$("#mobileTis").html(msgbox);
		}
	});
	
	//登录
	mui(document.body).on("tap", "#login", function() {
		//收起虚拟键盘
		document.activeElement.blur();
	    //手机号码
		var mobile = $("#mobile").val();
		//验证码
		var verificationCode = $("#code").val();
		if(mobile == "") {
			$("#mobileTis").html("请输入手机号码");
			return;
		} else {
			$("#mobileTis").html("");
		}
		if(verificationCode == "") {
			$("#codeTis").html("请输入验证码");
			return;
		} else {
			$("#codeTis").html("");
		}
		//验证码校验
		$.ajax({
			url: basePath + "/his/barcode/verifedLogin",
			type: "post",
			async:false,
			dataType: "json",//从服务器端返回的数据类型
			data: {mobile:mobile, code:verificationCode, hospitalId:hospitalId},
			beforeSend:function() { //请求中
				$("#login").attr("disabled", true);
			},
			success: function(json) {
				//验证成功
				if(json.msg == 1) {
					//用户业务信息
					var data = json.data;
					if(data != null){
						var registerNum = data.registerNum;
						if(channel != null && channel != "null" && openId != null && openId != "null"){
							//微信公众号流程，绑定openID
							//ajax
							var param = {
								userId:data.userId,
								channel:channel,
								openId:openId
							};
							//保存openId记录
							$.post(basePath + "/user/saveUserOpenId", param, function(data) {
								if(data.msg != 1) {
									mui.toast(ret.msgbox);
									return;
								}
							});
							if(registerNum != null && registerNum != ""){
								//绑定过登记号，直接跳转用户首页
								window.location.href = "homePage.html?hospitalId="+hospitalId+"&registerNum="+registerNum+"&userId="+data.userId;
							}
						}else{
							//扫码流程
							if(registerNum != null && registerNum != ""){
								//判断用户是初诊还是复诊状态
								var param = {hospitalId : hospitalId, userId : data.userId};
								$.post(basePath + "/outpatient/isFirstOutpatient", param, function(ret) {
									if (ret.msg != 1) {
										mui.toast(ret.msgbox);
										return;
									}
									var outpType = ret.data.type;
									var outpatientId = ret.data.outpatientId;
									if (outpType == 0 || outpType == 2) {// 初诊或者复诊
										window.location.href = "chooseMedicalReason.html?hospitalId="+hospitalId+"&userId="+data.userId+"&registerNum="+registerNum;
									} else if (outpType == 1) {// 初诊未完成诊断
										window.location.href = "firstCheck.html?outpatientId=" + outpatientId;
									} else {// 复诊未完成诊断
										window.location.href = "repeatCheck.html?outpatientId=" + outpatientId;
									}
								}, "json");
							}
						}
						if(registerNum == null || registerNum == ""){
							//没有绑定过登记号，跳转到绑定登记号页面
							var skipTo = 0;
							if(channel != null && channel != "null" && openId != null && openId != "null"){
								skipTo = 1;//从绑定登记号页面跳转到用户首页
							}else{
								skipTo = 0;//从绑定登记号页面跳转到选择就诊原因页面
							}
							window.location.href = "bindRegisterNum.html?skipTo="+skipTo+"&hospitalId="+hospitalId+"&mobile="+mobile;
						}
						
					}else{
						//手机号码没有绑定业务用户，跳转绑定登记号
						var skipTo = 0;
						if(channel != null && channel != "null" && openId != null && openId != "null"){
							//设置cookie，到绑定登记号页面进行绑定openID
							$.cookie("channel",channel);
							$.cookie("openId",openId);
							skipTo = 1;//从绑定登记号页面跳转到用户首页
						}else{
							skipTo = 0;//从绑定登记号页面跳转到选择就诊原因页面
						}
						window.location.href = "bindRegisterNum.html?skipTo="+skipTo+"&hospitalId="+hospitalId+"&mobile="+mobile;
					}
					
				}else { //失败
					mui.toast(json.msgbox);
				}
			},
			complete: function() { //请求完成
				$("#login").attr("disabled", false);
			}
		});
	});
});

//timer处理函数  
function SetRemainTime() {
    if (curCount == 0) {
    	//停止计时器
    	window.clearInterval(InterValObj);
        //启用按钮 
        $(".yzbutton").removeAttr("disabled"); 
        $(".yzbutton").val("获取验证码");  
    } else  {  
        curCount--;  
        $(".yzbutton").val(curCount + "S");  
    }  
}

//手机号码 /^[1][35789][0-9]{9}$/或者这个格式判断的
function isMobile(str) {
	var patrn=/^[1][35789][0-9]{9}$/;
	return patrn.test(str);
};