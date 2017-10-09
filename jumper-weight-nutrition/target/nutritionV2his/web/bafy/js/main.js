
//获取门诊id
var outpatientId = $.cookie('outpatientId');

/** 菜单栏公共跳转js */
$(function() {
	//门诊
	$("#outpatient").click(function() {
		//宝安妇幼HIS体重营养入口参数
		//var hospitalId = $.cookie("hospitalId");
		var registerNum = $.cookie("registerNum");
		var hisType = $.cookie("hisType");
		if (hisType == 0) {//初诊
			window.location.href = "firstOutpatient.html?hospitalId=" + hospitalId + "&registerNum=" + registerNum + "&outpatientId=" + outpatientId;
		} else if (hisType == 1) {//复诊
			window.location.href = "repeatOutpatient.html?hospitalId=" + hospitalId + "&registerNum=" + registerNum + "&outpatientId=" + outpatientId;
		} else {//完成诊断
			window.location.href = "warn.html?hospitalId=" + hospitalId + "&registerNum=" + registerNum + "&outpatientId=" + outpatientId;
		}
	});
	//配置食谱
	$("#standardRecipes").click(function() {
		window.location.href = "standardRecipes.html";
	});
	//门诊统计
	$("#statistics, .statistics").click(function() {
		window.location.href = "statistics.html";
	});
	//体重异常统计
	$(".statisticsWeight").click(function() {
		window.location.href = "statisticsWeight.html";
	});
	//设置
	$("#settings").click(function() {
		window.location.href = "settings.html";
	});
	//返回按钮
    $(".go-back").on("click",function(){
    	history.back(); //返回上一页
    });
    
    //用户信息页面--体重、饮食记录
    $("#user-dietRecord").click(function() {
    	var registerNum = $.cookie("registerNum");
    	window.location.href = "repeatOutpatient.html?hospitalId=" + hospitalId + "&registerNum=" + registerNum + "&outpatientId=" + outpatientId;
	});
    //用户信息页面--运动记录 
    $("#user-sportRecord").click(function() {
		window.location.href = "userSportRecord.html?userId=" + userId + "&outpatientId=" + outpatientId + "&menuType=" + menuType;
	});
    //用户信息页面--目前方案
    $("#user-currentPlan").click(function() {
		window.location.href = "currentPlan.html?userId=" + userId + "&outpatientId=" + outpatientId + "&menuType=" + menuType;
	});
    //用户信息页面--诊疗历史
    $("#user-treatHistory").click(function() {
		window.location.href = "treatHistory.html?userId=" + userId + "&outpatientId=" + outpatientId + "&menuType=" + menuType;
	});
    //复诊完成诊断
    $(document).on("click","#completeDiagnosis",function() {
    	var doctorAdvice = $("#doctorAdvice").val();
    	$.ajax({
     		url: basePath+"/outpatient/updateOutpatient",
     		type: "POST",
     		dataType: "json",
     		data: {outpatientId:outpatientId,isFinish:1,doctorAdvice:doctorAdvice},
     		async:false,
     		success:function(json){
     			if(json.msg == 1){
     				//返回门诊页面
     				var registerNum = $.cookie("registerNum");
     				window.location.href = "warn.html?hospitalId=" + hospitalId + "&registerNum=" + registerNum + "&outpatientId=" + outpatientId;
     				//将该门诊记录设置为已完成诊断，并调取his接口，通知该用户已完成诊断
     				//$.cookie("hisType", 2);
     				
     				
     				
     				
     				
     				
     				
     			}else{
     				layer.msg(json.msgbox,{time:1000});
     			}
     		}
     	});
    });
    
    //回车键触发弹框确定取消事件
    $(document).keydown(function(e) {
    	e = e||event
    	var key=e.keyCode; 
    	switch(key) {
	  	case 13: // 回车按钮
	  		if($(".layui-layer").hasClass("layui-layer")){
	  			$(".layui-layer-btn0").click();
	  		}
	  		break;
		case 27: // Esc按钮，去掉弹框
			if($(".layui-layer").hasClass("layui-layer")) {
	  			$(".layui-layer-close").click();
	  		}
    	} 
	});
    /*if(document.documentElement.clientWidth<1600){
   	 $(".container-right").width(window.screen.width);
   }*/
    
});

function isShowAdvice() {
	//复诊显示医生建议和完成诊断
//	if(menuType == CONST.menuType[0]){
    	$("#adviceDiv").css("display","block");
    	$("#finishDiv").css("display","block");
    	$("#modifyPlanDiv").css("display","block");//修改方案按钮
    	$("#mealsInfo").css("margin-bottom","20px");//门诊时距离底部20px
    	//切换门诊菜单和孕妇管理菜单
    	$("#outpatient").addClass("active");
    	$("#userManage").removeClass("active");
//    }
}
function tableHover(target,color1,color2,color3) {
    var  atr=target;
    var  arr= [color1,color2];
    for (var i=0;i<atr.length;i++){
        atr[i].index=i;
        atr[i].style.background=arr[i%arr.length];
        atr[i].onmouseover=function(){this.style.background=color3;};
        atr[i].onmouseout=function(){this.style.background=arr[this.index%arr.length]; };
    }
}

/**
 * 文本域字数统计
 */
function countDietAdvice(textareaName,spanName) {
    var textNam = document.getElementById(textareaName);
    var spanNam=  document.getElementById(spanName);
    if(textNam.value.length >= 300){
        textNam.value=textNam.value.substring(0, 300);
    }
    spanNam.innerHTML =textNam.value.length;
}