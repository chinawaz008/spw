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
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

    var positionID = '';
    var authStr = '';

    //获取测试数据
    getPositionData();

    //点击保存按钮
    $('#save_auth_btn').click(function(){
    	if(positionID == '1@4' || positionID == '1@5' || positionID == '1@6'){
    		alert("该职级暂不能登录后台管理系统，不能保存！");
    		return false;
    	}
        var treeObj = $.fn.zTree.getZTreeObj("treeAuth");
        var nodes = treeObj.getCheckedNodes(true);
        var authStr = '';
        $(nodes).each(function(){
            authStr += this.id + ',';
        });
        authStr = authStr.substring(0, authStr.length - 1);
        if(authStr==''){
        	alert("请选择一个菜单！");
    		return false;
        }
        $.ajax({
            type: "post",
            url: ctx + "/authority/save",
            data: {position: positionID, auths: authStr},
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
        var theId = treeNode.id;
        if (theId == '1@4' || theId == '1@5' || theId == '1@6') {
            alert("该职级暂不能登录后台管理系统，不能分配权限！");
            treeNode.checked = false;
        } else if (treeNode.checked) {
            positionID = theId;
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
            url: ctx + "/authority/framework",
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
            url: ctx + "/authority/menu",
            data: {id:positionID},
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
