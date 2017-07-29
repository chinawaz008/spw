/**
 * 文件：新增增员代扣页面脚本
 * 作者：小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2017-05-08
**/
$(function(){
	//点击提交
    $("#add_submit_btn").click(function(){
        if (checkRequired() && checkFormat() && confirm("您确认提交？")) {
        	//移除禁用是为 表单提交取值
        	$("#line_type").removeAttr("disabled");
            $("#branch_company_id").removeAttr("disabled");
            $("#county_franchisees_id").removeAttr("disabled");
            $("#position").removeAttr("disabled");
            $("#debitName").removeAttr("disabled");
            $("#debitNo").removeAttr("disabled");
            $("#organization_form").submit();
	   	}
    });
	
	
	 //由条线获取省分公司
    $("#line_type, #branch_company_id, #county_franchisees_id").change(function(){
        var id = $(this).find("option:selected").val();
        var lineType = $("#line_type").val();
        var elId = $(this).attr("id");
        var $target, urlTail, no, name, sin;
        switch (elId){
            case "line_type":
                $target = $("#branch_company_id");
                urlTail = "company";
                no = "id";
                name = "compName";
                sin = "省级机构";
                break;
            case "branch_company_id" :
                $target = $("#county_franchisees_id");
                urlTail = "dxq";
                no = "id";
                name = "name";
                sin = "督训区";
                break;
            case "county_franchisees_id" :
                $target = $("#fenbu");
                urlTail = "fenbu";
                no = "id";
                name = "name";
                sin = "分部";
                getPositions(1);
                break;
            default :
                break;

        }

        $.ajax({
            type: "post",
            url: ctx + "/organization/" + urlTail,
            data : {id: id},
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                var optionArr = [];
                for (var i = 0, len = msg.length; i < len; i++) {
                    optionArr[i] = '<option value="' + msg[i][no] + '">' + msg[i][name] + '</option>';
                }
                var htmlStr = '<option value="">' + '请选择' + sin + '</option>' + optionArr.join("");
                $target.html(htmlStr);
            }
        });
    });
    
    
  //BEGIN:添加业务人员
    //点击搜索业务人员
    $("#search_salesman_btn").click(function(){
        $("#search_salesman_tip").empty();
        var text = $.trim($("#search_salesman_input").val());
        if (text === "") {
            $("#search_salesman_tip").html("请输入查询条件，人员姓名或身份号！");
        } else {
            $.ajax({
                type: "post",
                url: "../staff/getFrontPerson",
                data: {data:text},
                dataType: "json",
                success: function(msg) {
                    if (msg.result === "1") {
                        var len = msg.members.length;
                        var liStr = [];
                        var htmlStr = [];
                        var i;
                        liStr[0] = '<li class="member" data-name="';
                        liStr[1] = '--';
                        liStr[2] = '" data-work="';
                        liStr[3] = '--';
                        liStr[4] = '" data-linetype="';
                        liStr[5] = '--';
                        liStr[6] = '" data-localname="';
                        liStr[7] = '--';
                        liStr[8] = '" data-branchcompanyId="';
                        liStr[9] = '--';
                        liStr[10] = '" data-companyname="';
                        liStr[11] = '--';
                        liStr[12] = '" data-countyfranchiseesid="';
                        liStr[13] = '--';
                        liStr[14] = '" data-countyfranchiseesname="';
                        liStr[15] = '--';
                        liStr[16] = '" data-position="';
                        liStr[17] = '--';
                        liStr[18] = '" data-positionname="';
                        liStr[19] = '--';
                        liStr[20] = '" data-idcard="';
                        liStr[21] = '--';
                        liStr[22] = '" title="手机号：';
                        liStr[23] = '--';
                        liStr[24] = ',岗位号：';
                        liStr[25] = '--';
                        liStr[26] = '">';
                        liStr[27] = '--';
                        liStr[28] = '(';
                        liStr[29] = '--';
                        liStr[30] = ')<br/>';
                        liStr[31] = '--';
                        liStr[32] = '</li>';
                        for (i = 0; i < len; i++) {
                            liStr[1] = msg.members[i].name;
                            liStr[3] = msg.members[i].workNum;
                            liStr[5] = msg.members[i].lineType;
                            liStr[7] = msg.members[i].localName;
                            liStr[9] = msg.members[i].branchCompanyId;
                            liStr[11] = msg.members[i].companyName;
                            liStr[13] = msg.members[i].countyFranchiseesId;
                            liStr[15] = msg.members[i].countyFranchiseesName;
                            liStr[17] = msg.members[i].position;
                            liStr[19] = msg.members[i].positionName;
                            liStr[21] = msg.members[i].idCard;
                            liStr[23] = msg.members[i].phoneNum;
                            liStr[25] = msg.members[i].workNum;
                            liStr[27] = msg.members[i].name;
                            liStr[29] = msg.members[i].countyFranchiseesName;
                            liStr[31] = msg.members[i].idCard;
                            htmlStr.push(liStr.join(""));
                        }
                        var html = htmlStr.join("");
                        $("#search_salesman_ul").html(html);
                    } else {
                        $("#search_salesman_tip").html(msg.message);
                    }
                }
            });
        }
    });

    //点击业务人员
    $("#search_salesman_ul").on("click","li",function() {
        var name = $(this).data("name");
        var workNum = $(this).data("work");
        var lineType = $(this).data("linetype");
        var localName = $(this).data("localname");
        var branchCompanyId = $(this).data("branchcompanyid");
        var companyName = $(this).data("companyname");
        var countyFranchiseesId = $(this).data("countyfranchiseesid");
        var countyFranchiseesName = $(this).data("countyfranchiseesname");
        var position = $(this).data("position");
        var positionName = $(this).data("positionname");
        var idCard = $(this).data("idcard");
        
        $("#line_type").empty();
        $("#branch_company_id").empty();
        $("#county_franchisees_id").empty();
        $("#position").empty();
        
        $("#line_type").append("<option value='"+lineType+"'>"+localName+"</option>");
        $("#branch_company_id").append("<option value='"+branchCompanyId+"'>"+companyName+"</option>");
        $("#county_franchisees_id").append("<option value='"+countyFranchiseesId+"'>"+countyFranchiseesName+"</option>");
        $("#position").append("<option value='"+position+"'>"+positionName+"</option>");
        
        $("#debitName").val(name);
        $("#debitNo").val(idCard);
        
        $("#line_type").attr("disabled","disabled");
        $("#branch_company_id").attr("disabled","disabled");
        $("#county_franchisees_id").attr("disabled","disabled");
        $("#position").attr("disabled","disabled");
        $("#debitName").attr("disabled","disabled");
        $("#debitNo").attr("disabled","disabled");
        
        $("#search_salesman_ul").empty();
    });
    
    $("#reset").click(function(){
    	window.location.reload();
    	$("#organization_form")[0].reset();
    });

//END:添加业务人员
    
    /**
     * 函数：获取职级下拉框里的列表
     * 输入：type();
     * 输出：
    **/
    function getPositions(type) {
        $.ajax({
            type: "post",
            url: ctx + "/staff/getJobs",
            data: {type:type},
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                var optionArr = [];
                for (var i = 0, len = msg.length; i < len; i++) {
                    optionArr[i] = '<option value="' + msg[i].id + '">' + msg[i].name + '</option>';
                }
                var htmlStr = '<option value="">请选择职级</option>' + optionArr.join("");
                $("#position").html(htmlStr);

            }
        });
    }   
});
