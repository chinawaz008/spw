/**
 * 文件：新增人员页面脚本
 * 作者：无名小强
 * 版本：v0.2
 * 版权：民盛集团
 * 日期：2016-3-4
**/
$(function(){
    
    //树形架构配置参数
    var settingForPosition = {
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "all"
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: zTreeOnCheck
            }
        };

    var settingForAuth = {
            check: {
                enable: true,
                chkboxType: { "Y" : "ps", "N" : "s" } 
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

    var positionID = '';
    var personType = '';
    var authStr = '';
    var departMent = '';

    //获取测试数据
    getPositionData();

    //点击保存按钮
    $('#save_auth_btn').click(function(){
//        if (personType == '0' && (positionID == '4' || positionID == '5' || positionID == '6')) {
//    		alert("该职级暂不能登录后台管理系统，不能保存！");
//    		return false;
//    	}
        var treeObj = $.fn.zTree.getZTreeObj("treeAuth");
        var nodes = treeObj.getCheckedNodes(true);
        var authStr = '';
        $(nodes).each(function(){
            authStr += this.id + '@' + this.type + ',';
        });
        authStr = authStr.substring(0, authStr.length - 1);
        if(authStr==''){
        	alert("请选择一个菜单！");
    		return false;
        }
        $.ajax({
            type: "post",
            url: ctx + "/authority/redevelope/save",
            data: {departMentId:departMent,position: positionID, auths: authStr,personType:personType},
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                if (msg.result.toString() === "1") {
                    alert('保存成功');
                } else {
                    alert(msg.message);
                }
            }
        });
    });

    /**
     * 函数：点击左侧岗位树结构引发的事件
     * 输入：
     * 输出：
    **/
    function zTreeOnCheck(event, treeId, treeNode) {
        var theId = treeNode.keyid;
        var thePerson = treeNode.personType;
        var departmentid = treeNode.departmentid;
        console.log(treeNode);
        if (thePerson == '0' && (theId == '4' || theId == '5' || theId == '6')) {
            alert("该职级暂不能登录后台管理系统，不能分配权限！");
            treeNode.checked = false;
        } else if (treeNode.checked) {
            positionID = theId;
            personType = thePerson;
            departMent = departmentid;
            getAuthData(positionID);
        } else {
            destroyTree("treeAuth");
        }
    }

    /**
     * 函数：获取岗位信息
     * 输入：
     * 输出：
    **/
    function getPositionData() {
        $.ajax({
            type: "post",
            url: ctx + "/authority/redevelope/framework",
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                if (msg.result.toString() === "1") {
                    showPosition(msg.zNodesPosition);
                } else {
                    alert(msg.message);
                }
            }
        });
    }


    /**
     * 函数：获取对应的权限配置
     * 输入：positionID(岗位ID)
     * 输出：
    **/
    function getAuthData() {
        $.ajax({
            type: "post",
            url: ctx + "/authority/redevelope/menu",
            data: {departMentId:departMent,id:positionID,personType:personType},
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                if (msg.result.toString() === "1") {
                    showAuthority(msg.zNodesAuth);
                } else {
                    alert(msg.message);
                }
            }
        });
    }

    /**
     * 函数：显示权限架构
     * 输入：
     * 输出：
    **/
    function showAuthority(zNodesAuth) {
        destroyTree("treeAuth");
        $.fn.zTree.init($("#treeAuth"), settingForAuth, zNodesAuth); 
    }

    /**
     * 函数：显示岗位架构
     * 输入：
     * 输出：
    **/
    function showPosition(zNodesPosition) {
        destroyTree("treePosition");
        $.fn.zTree.init($("#treePosition"), settingForPosition, zNodesPosition); 
    }

    /**
     * 函数：销毁树形结构
     * 输入：
     * 输出：
    **/
    function destroyTree(nameStr) {
        var zTreeObj = $.fn.zTree.getZTreeObj(nameStr);
        if (zTreeObj) {
            zTreeObj.destroy();
        }
        if(nameStr === 'treeAuth') {
            $('#treeAuth').html('<li style="color: #999">请先在左侧选择岗位</li>')
        }
    }

});
