//web端第一个页面赋初始cookie值
var hospitalId = GetQueryString("hospitalId");
var registerNum = GetQueryString("registerNum");
$.cookie("hospitalId", hospitalId);
$.cookie("registerNum", registerNum);
$.cookie("hisType", 2);//2：完成诊断

var outpatientId = GetQueryString("outpatientId");
//页面所需后台的数据
$(function() {
	
	//弹出提示框
	layer.alert('您已完成诊断！', {
        icon: 0,
        title: '提示',
        btn: ['确认'],
        btnAlign: 'c',
        yes: function (index, layero) {
            layer.close(index);
        }
    });
});