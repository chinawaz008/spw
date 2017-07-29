flag = $("#flag").val(); 
//provinceNo = $("#provinceNo").val();

$(function(){
	setProviceName(); 
	
	$("#btn_submit").click(function(){
		if(checkRequired() && checkFormat() && townNoYZ() && confirm("确定提交吗？")){
	    	return true;
	   	}
	   	return false;
    });
});
   
function townNoYZ(){
	var tf = true;
	var id = $("#townId").val();
	var tNo=$.trim($("#townNo").val());
	if(tNo!=""){
		$.ajax({
			url:ctx+"/area/townsNo/"+id+"/"+tNo,
			type:"post",
			dataType:"json",
			async: false,
			success:function(msg){
				if(msg.isexist){//已存在
					alert("该街道编码已经存在");
					tf = false;
				}
			}
		});
	}
	return tf;
}

function setProviceName(){ 
		$.ajax({
		url:ctx+"/webService/province",
		type:"post",
		dataType:"json",
		success:function(msg){ 
			$("#provinceId").empty(); 
			
			$("<option value='" + 0 + "'>" + "请选择省份" + "</option>").appendTo($("#provinceId"));
             $.each(eval(msg), function(i, item) { 
                 $("<option  value='" + item.provinceNo + "'>" + item.provinceName + "</option>").appendTo($("#provinceId"));
             });
             if(flag =="edit" && flag !=null && flag !=""){
                var id = $("#provinceNo").val();
                $("#provinceId").find("option[value='"+id+"']").attr("selected",true)
             }
             setRegionName();
		}
	});
}

function setRegionName(){
    var id = $("#provinceId").find("option:selected").val();
    $("#provinceNo").val(id);
	$.ajax({
		url:ctx+"/webService/region",
		data:{"id":id},
		type:"post",
		dataType:"json",
		success:function(msg){
			$("#regionId").empty();  
			$("<option  value='" + 0 + "'>" + "请选择城市" + "</option>").appendTo($("#regionId"));
            $.each(eval(msg), function(i, item) { 
                $("<option value='" + item.regionNo + "'>" + item.regionName + "</option>").appendTo($("#regionId"));
            });		
            if(flag =="edit" && flag !=null && flag !=""){
               var id = $("#regionNo").val();
               $("#regionId").find("option[value='"+id+"']").attr("selected",true)
            }
            setCountyName();
		}
		});
}
function setCountyName(){
    var id = $("#regionId").find("option:selected").val();
    $("#regionNo").val(id);
	$.ajax({
		url:ctx+"/webService/county",
		data:{"id":id},
		type:"post",
		dataType:"json",
		success:function(msg){
			$("#countyId").empty();  
			$("<option value='" + 0 + "'>" + "请选择区县" + "</option>").appendTo($("#countyId"));
            $.each(eval(msg), function(i, item) { 
                $("<option  value='" + item.countyNo + "'>" + item.countyName + "</option>").appendTo($("#countyId"));
            });	
            if(flag =="edit" && flag !=null && flag !=""){
               var id = $("#countyNo").val();
               $("#countyId").find("option[value='"+id+"']").attr("selected",true)
            } 
		}
		});
}

function setTownsName(){
    var id = $("#countyId").find("option:selected").val();
    $("#countyNo").val(id);
}

function operate(){
	var id = $("#countyId").find("option:selected").val();
	if(id<=0){
		$("#xianqu").show();
		return false;
	}else{
		$("#xianqu").hide();
	}
}