/**
 *  版本模块js
 */

//页面上的操作
$(function(){	
    
    //点击提交按钮
    $('#btnAdd').click(function() {
    	var version = $('#version').val();
    	var apkFile = $('#apkFile').val();
		if (version == ""){
			alert("版本号不能为空");
			return false;
		}
		if (apkFile == ""){
			alert("apk文件不能为空");
			return false;
		}
		var tVersion = parseFloat($("#top_version").val());
		var vs = parseFloat(version);
		if(vs<=tVersion){
			alert("新版本号不能低于上一版本号");
			return false;
		}
		var postf=apkFile.substring(apkFile.lastIndexOf(".")+1,apkFile.length);
		if(postf!='apk'){
			alert("该文件不是apk文件，请重新上传！");
			return false;
		}
		$("#upform").submit();
    });
});