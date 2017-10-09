/**
 * 绑定登记号js
 */
var skipTo = GetQueryString("skipTo");
var hospitalId = GetQueryString("hospitalId");
var mobile = GetQueryString("mobile");
var channel = $.cookie("channel");
var openId = $.cookie("openId");

$(function(){
	$.ajaxSetup({ //设置ajax为同步提交
        async: false
    });
	//点击绑定
	$(document).on("click","#bindRegisterNum",function(){
		var realName = $("#realName").val();
		var registerNum = $("#registerNum").val();
		if(realName == null || realName == ""){
			mui.toast("请输入姓名");
			return;
		}
		if(registerNum == null || registerNum == ""){
			mui.toast("请输入登记号");
			return;
		}
		var flag = false;
		$.ajax({
			url:basePath+"/hisInfo/findUserByRegNum",
			type:"POST",
			dataType:"json",
			async:false,
			data:{registerNum:registerNum,hospitalId:hospitalId},
			success:function(json){
				if(json.msg == 1){
					mui.toast("登记号已经绑定其它手机号，请更换手机号登录。");
					flag = true;
				}
			}
		});
		if(!flag){
			$.ajax({
				url:basePath+"/hisInfo/findHttpHisInfo",
				type:"POST",
				dataType:"json",
				async:false,
				data:{registerNum:registerNum,hospitalId:hospitalId},
				success:function(json){
					if(json.msg == 1){
						if(json.data.realName != realName){
							mui.toast("绑定的姓名错误，请重新输入姓名。");
							return;
						}else if(json.data.mobile != mobile){
							mui.toast("绑定的手机号错误，请重新登录手机号。");
							return;
						}else{
							//调用接口查询his信息，注册天使用户，绑定业务用户，保存信息
							$.ajax({
								url:basePath+"/hisInfo/synchorHisInfo",
								type:"POST",
								dataType:"json",
								async:false,
								data:{registerNum:registerNum,hospitalId:hospitalId},
								success:function(json){
									if(json.msg == 1){
										var userId = json.data.userId;
										//如果openID不为空，绑定openID
										if(channel != null && channel != "null" && openId != null && openId != "null"){
											//微信公众号流程，绑定openID
											//ajax
											var param = {
												userId:userId,
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
										}
										//跳转
										if(skipTo == 0){
											//跳转到选择就诊原因页面
											window.location.href = "chooseMedicalReason.html?hospitalId="+hospitalId+"&userId="+userId+"&registerNum="+registerNum;
										}else if(skipTo == 1){
											//跳转到用户首页
											window.location.href = "homePage.html?hospitalId="+hospitalId+"&registerNum="+registerNum+"&userId="+userId;
										}
									}
								}
							});
						}
					}else if(json.msg == 0){
						mui.toast("输入的登记号错误，请重新输入登记号。");
						return;
					}
				}
			});
		}
		
	});
	
});
