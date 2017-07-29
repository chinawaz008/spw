// 树配置
var settingForPosition = {
    check: {
        enable: true,
        chkboxType: { "Y": "", "N": "" }
    },
    view: {

        selectedMulti: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    async: {
        enable: true,
        url: ctx + "/department/login_data",
        autoParam: ["id", "type", "keyid", "departmentid", "branchcompanyid", "countyfranchiseesid", "storeid"],
        otherParam: { "otherParam": "zTreeAsyncTest" },
        dataFilter: filter
    },
    callback: {
        onCheck: zTreeOnCheck,
        onAsyncSuccess: zTreeOnAsyncSuccess
    }
};

function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
};
var zNodeArr;
// 加载树形结构
getPositionData();

function getPositionData() {
    $.ajax({
        type: "post",
        url: ctx + "/department/login_data",
        data: {
            id: '',
            type: '',
            keyid: ''
        },
        dataType: "json",
        success: function(msg) {
            //console.log(msg);
            if (msg.result.toString() === "1") {

                showPosition(msg.zNodes);
                $("#overlay").hide();
            } else {
                alert(msg.message);
            }
        }
    });
}
// 模糊查询
$('#search').on('keyup', function() {
    $("#treeSearch").show();
    var data = $.trim($('.search').val());
    $.ajax({
        type: 'POST',
        url: ctx + '/staff/login_like_staff_data',
        data: { "key": data },
        dataType: 'json',
        success: function(msg) {
            if (msg.result == 1) {
                $("#treeSearch").empty();
                for (var i = 0; i < msg.employees.length; i++) {
                    // var list = "<li class='list iconfont icon-renyuan' id=" + msg.employees[i].id + ">" + msg.employees[i].name + "</li>";
                    var list = $("<li><span class='iconfont icon-renyuan'></span></li>").attr({
                        'data-id': msg.employees[i].id,
                        'data-type': 0,
                        'data-name': msg.employees[i].name,
                        title: msg.employees[i].positionName
                    });
                    $("<span><b>" + msg.employees[i].name + "</b></br>" + msg.employees[i].positionName + "</span>").attr('class', "positionName").appendTo(list);
                    list.appendTo('#treeSearch');
                }
            } else {
                alert(msg.message)
            }
        }
    });
});
// 模糊查询选择人员
$('body').on('click', "#treeSearch li", function() {
    var list = $(this).clone();
    var obj = {
        idname: $(this).data('id'),
        type: 0,
        name: $(this).data('name'),
    };
    if (zNodeArr) {
        zNodeArr = zNodeArr
    } else {
        zNodeArr = [];
    }
    zNodeArr.push(obj);
    // 禁止再次点击
    $(this).addClass('default');
    $("#treeShow").append(list);
    // $(this).remove();
});
/**
 * 函数：显示岗位架构
 * 输入：
 * 输出：
 **/

function showPosition(zNodesPosition) {
    $.fn.zTree.init($("#treePosition"), settingForPosition, zNodesPosition);
}
// 删除选项
$('body').on('click', "#treeShow li", function() {
    var listid = $(this).data('id');
    for (var i = 0; i < zNodeArr.length; i++) {
        if (listid == zNodeArr[i].idname) {
            zNodeArr.splice(i, 1);
        }
    }
    // 解除禁止
    $("#treeSearch li").each(function() {
        if ($(this).data('id') == listid) {
            $(this).removeClass('default')
        }
    })
    $(this).remove();
});
// 异步加载完成回调
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
    var orgindata = $.parseJSON(msg);

    var zTree_Menu = $.fn.zTree.getZTreeObj("treePosition");
    var nodes = zTree_Menu.expandNode();
    var nodes = zTree_Menu.addNodes();
    var nodes = zTree_Menu.transformToArray();
    // var nodes = zTree_Menu.getNodes();
    console.log(zTree_Menu);
    console.log(nodes)
    for (var j = 0; j < orgindata.length; j++) {
        for (var i = 0; i < zNodeArr.length; i++) {
            if (orgindata[j].type == zNodeArr[i].type && orgindata[j].id == zNodeArr[i].idname) {
                orgindata[j].checked = true;

                // console.log(zNodeArr[i].idname);
            }
            // if (orgindata[j].id == zNodeArr[i].idname) {
            //     
            // }
        }
    }

    var afterdata = JSON.stringify(orgindata)



};
// 选择部门/人员
function zTreeOnCheck(event, treeId, treeNode) {
    // console.log(treeNode);
    var className;
    var obj;
    if (zNodeArr) {
        zNodeArr = zNodeArr
    } else {
        zNodeArr = [];
    }
    if (treeNode.type == 0) {
        // 人员
        className = " iconfont icon-renyuan";
        obj = {
            idname: treeNode.id,
            id: treeNode.keyid,
            type: 0,
            name: treeNode.name
        }
    } else if (treeNode.type == 2) {
        // 岗位
        className = "iconfont icon-team";
        obj = {
            idname: treeNode.id,
            positions: treeNode.keyid,
            name: treeNode.name,
            branchcompanyid: treeNode.branchcompanyid,
            countyfranchiseesid: treeNode.countyfranchiseesid,
            storeid: treeNode.storeid,
            type: treeNode.type
        }
    } else if (treeNode.type == 1) {
        // bumen
        className = " iconfont icon-zuzhi";
        obj = {
            // id: treeNode.keyid,
            idname: treeNode.id,
            id: treeNode.id,
            name: treeNode.name,
            departmentIds: treeNode.keyid,
            branchcompanyid: treeNode.branchcompanyid,
            countyfranchiseesid: treeNode.countyfranchiseesid,
            storeid: treeNode.storeid,
            type: treeNode.type
        }
    } else {
        //其他
        className = " iconfont icon-zuzhi";
        obj = {
            idname: treeNode.id,
            id: treeNode.id,
            name: treeNode.name,
            departmentIds: treeNode.departmentid,
            branchcompanyid: treeNode.branchcompanyid,
            countyfranchiseesid: treeNode.countyfranchiseesid,
            storeid: treeNode.storeid,
            type: treeNode.type
        }
    }
    // 如果是选中
    if (treeNode.checked) {

        treeNode.isParent = false;
        treeNode.getParentNode().chkDisabled = true;
        var list = $('<li></li>').attr({
            class: 'list',
            'data-id': obj.idname,
            'data-pId': obj.pId,
            'data-type': obj.type - 0
        })
        $('<span></span>').attr({
            class: className
        }).appendTo(list);
        $('<span><b>' + obj.name + '</b></span>').attr({}).appendTo(list);
        list.appendTo($("#treeShow"));
        zNodeArr.push(obj);
    } else {
        // console.log(zNode);
        treeNode.isParent = true;
        treeNode.getParentNode().chkDisabled = false;
        var length = $("#treeShow li").length;
        $("#treeShow li").each(function() {
            if ($(this).data('id') == obj.idname) {
                $(this).remove();
            }
        })
        for (i = 0; i < zNodeArr.length; i++) {
            var oid = obj.id;
            if (zNodeArr[i].idname == obj.idname) {
                zNodeArr.splice(i, 1);
            }
        }
        console.log(zNodeArr);
    }
    // console.log(zNode);
};
var personArry,
    positionArry,
    partmentArry,
    otherArry,
    staffids,
    positions,
    partments;
// 派发数组
function dispachArry(Arry) {
    personArry = [];
    positionArry = [];
    partmentArry = [];
    otherArry = [];
    staffids = '';
    positions = '';
    partments = '';
    for (var i = 0; i < Arry.length; i++) {
        if (Arry[i].type == 0) {
            personArry.push(Arry[i]);
            var staffid = Arry[i].idname;
            if (staffids === '') {
                staffids = staffid;
            } else {
                staffids += "," + staffid;
            };
        } else if (Arry[i].type == 2) {
            positionArry.push(Arry[i]);
            var positionstr = Arry[i].positions + "@" + Arry[i].branchcompanyid + "@" + Arry[i].countyfranchiseesid + "@" + Arry[i].storeid;
            if (positions === '') {
                positions = positionstr;
            } else {
                positions += "," + positionstr;
            };
        } else {
            var partmentstr = Arry[i].departmentIds + "@" + Arry[i].branchcompanyid + "@" + Arry[i].countyfranchiseesid + "@" + Arry[i].storeid;
            if (partments === '') {
                partments = partmentstr;
            } else {
                partments += "," + partmentstr;
            };
            if (Arry[i].type == 1) {
                partmentArry.push(Arry[i]);
            } else {
                otherArry.push(Arry[i]);
            };
        }
    }
}

function freshListLay(obj) {
    zNodeArr = obj;
    $("#treeShow").empty();
    for (var i = 0; i < obj.length; i++) {
        // console.log(obj[i])
        // var list = "<li class='list iconfont icon-renyuan' id=" + msg.employees[i].id + ">" + msg.employees[i].name + "</li>";
        var list = $("<li><span class='iconfont icon-renyuan'></span></li>").attr({
            'data-id': obj[i].id,
            'data-type': 0,
            'data-name': obj[i].name,
        });
        $("<span><b>" + obj[i].name + "</b></span>").attr('class', "positionName").appendTo(list);
        list.appendTo('#treeShow');
    }
};

$(document).ready(function() {

    $('.search').on('focus', function() {
        $('#treePosition').hide()
    });
    $('.search').on('blur', function() {
        if ($('.search').val() == '') {
            $('#treePosition').show();
            $('#treeSearch').hide();
        } else {
            $('#treePosition').hide();
            $('#treeSearch').show();
        }
    });
    $('.sure').on('click', function() {
        dispachArry(zNodeArr);
        // 前台页面数据
        // console.log(personArry, positionArry, partmentArry, otherArry);
        // 发送到后台数据
        // console.log(staffids, positions, partments);
        parent.closeBox();
        parent.$("#list_iframe")[0].contentWindow.freshLiLay(zNodeArr);
    })
    $('.cancel').on('click', function() {
        parent.closeBox()
    })


});