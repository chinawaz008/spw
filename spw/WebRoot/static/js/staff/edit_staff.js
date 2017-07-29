/**
 * 文件：编辑人员页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-3-11
**/
$(function(){
    //所有select在页面加载后显示值
    $("select").each(function(){
        var value = $(this).data("v");
        $(this).find("option[value='" + value + "']").attr("selected",true);
    });
    $("#insurance_industry").change();
    $(".belong_to_same").change();
    var list =$("#lists").val();
    var lists = list.split(",");
    for (var i = 2; i < lists.length; i++) {
    	 $("#rela"+i).show();
	}
    
    //不同orgType
//    var orgType = $("#add_fenbu").data("o");
//    if (orgType !== '') {
//    $("#add_fenbu").show();
        $("#fenbu").show();
//        $("#delete_fenbu").show();
        $(".has_fenbu").hide();
//    }
    //分公司还是总公司
    var companyType = $("#company_type").data("v");
    if (companyType === 1) {
    	$("#company_type").find("option[value='" + 1 + "']").attr("selected",true);
    }else{
    	$("#company_type").find("option[value='" + 2 + "']").attr("selected",true);
    }
    
    var havaPart = $("#fenbu").val();
    if(havaPart!=null && havaPart!=""){
    	$("#ownFenbu").find("option[value='" + 1 + "']").attr("selected",true);
    	$("#qudao").show();
		$("#duxunqu").show();
		$("#zhiji").show();
		$("#fenbu").attr("name","havePartId");
		$("#position").attr("name","position");
    }else{
    	$("#ownFenbu").find("option[value='" + 0 + "']").attr("selected",true);
    }
    
    var haveCar = $("#haveCar").data("v");
    if(haveCar=="是") $("#haveCar").attr("checked",true);
    //提交保存
    $("#edit_submit_btn").click(function(){
        if (checkFormat() && confirm("您确定保存修改？")) {
            var datas = {staffId:$(this).data("i")};
            submitStaff("/staff/updateEntity",datas);
        }
    });
    
  //提交保存
    $("#edit_submit_btn2").click(function(){
        if (checkFormat() && confirm("您确定保存修改？")) {
            var datas = {staffId:$(this).data("i")};
            submitStaff("/staff/updateEntitySupport",datas);
        }
    });


});