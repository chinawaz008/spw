$(function(){
    

	$("ul.lines").each(function(){
        //判断是否是全选
        var un_selected_num = $(this).find('li div.childline:not(.checked)').length;
        if (un_selected_num === 0) {
        	 $('#all').find('.line').removeClass('un_checked')
             $('#all').find('.line').addClass('checked')
        } else {
        	 $('#all').find('.line').removeClass('checked')
             $('#all').find('.line').addClass('un_checked')
        }
	});
	
    //点击搜索人员按钮
    $('#search').click(function(){
    	$('.message_ti').hide();
        var val = $('#staff').val().trim();
        if (val.length > 1) {
        	showOverlay("img");
            $.ajax({
                type: 'post',
                url: ctx + '/staff/get_staff_blur',
                data: {data: val},
                dataType: 'json',
                success: function(msg) {
                    if (msg.result === 1) {
                    	if (msg.list.length ==50) {
                    		$('.message_ti').show();
                    	}
                        showStaffTable(msg.list);
                    } else {    
                        alert(msg.message)
                    }
                    hideOverlay("img");
                }
            })
        }
        else {
        	alert("至少输入2个数字或者汉字");
        }
    })

    //监听搜索姓名输入框回车键触发搜索按钮
    $('#staff').keyup(function(e){
        if (e.which === 13) {
            $('#search').click();
        }
    })

    //点击人员前面的选择框
    $('#staff_tbody').on('click', '.un_selected', function(){
        $('#staff_tbody .selected').removeClass('selected').addClass('un_selected');
        $(this).addClass('selected');
    })

    //点击各个事业部
    $('#lines li:not(#all)').click(function(){
        var $line = $(this).find('.line');
        if ($line.hasClass('checked')) {
            $line.removeClass('checked').addClass('un_checked');
        } else {
            $line.removeClass('un_checked').addClass('checked');
        }
        //判断是否是全选
        var un_selected_num = $(this).parent().find('li div.childline:not(.checked)').length;
        if (un_selected_num === 0) {
        	 $('#all').find('.line').removeClass('un_checked')
             $('#all').find('.line').addClass('checked')
        } else {
        	 $('#all').find('.line').removeClass('checked')
             $('#all').find('.line').addClass('un_checked')
        }
    })

    //点击全选
    $('#all').click(function(){
        if ($(this).find('.line').hasClass('un_checked')) {
            $(this).find('.line').removeClass('un_checked').addClass('checked');
            $(this).siblings().find('.line').removeClass('un_checked').addClass('checked');
        } else {
            $(this).find('.line').removeClass('checked').addClass('un_checked');
            $(this).siblings().find('.line').removeClass('checked').addClass('un_checked');
        }
    })

    //点击总公司权限或者分公司权限
    $('#achievement_authority').on('click', '.un_selected', function() {
        $('#achievement_authority .selected').removeClass('selected').addClass('un_selected')
        $(this).removeClass('un_selected').addClass('selected');
        $(this).parents('tr').find('.box').show();
        $(this).parents('tr').siblings().find('.box').hide();
        if ($('#lines').is(":hidden")) {
            $('#lines .checked').removeClass('checked').addClass('un_checked')
        }
        if ($('#fen').is(":hidden")) {
            $('#fen .delete').click();
            $('#fen .duxunqu').remove();

            $("#fen select").val('')
        }
    })

    //点击选择督训区按钮
    $('.select_duxunqu').click(function() {
        var $li = $(this).parents('li');
        var line_id = $li.data('id');
        var pro_id = $li.find('select')[0].value;
        var name = $li.find('.name').text();
        var pro = $li.find('select option:selected').text();
        var hasList = $li.find('.duxunqu span').map(function(){
            return $(this).data('id')
        }).get();
        if (pro_id === '') {
            alert('请选择省分公司');
        } else {
            $.ajax({
                type: 'post',
                url: ctx + '/authority/get_orgzations',
                data: {lineType: line_id, provinceId: pro_id},
                dataType: 'json',
                success: function(msg) {
                    if (msg.result === 1) {
                    	if (msg.orgList.length > 0) {
                    		showDuxunqus(msg.orgList, $li, hasList, name, pro)
                    	}
                    	else {
                    		alert("该省分公司未设督训区！");
                    	}
                    } else {
                        alert(msg.message);
                    }
                }
            })
        }
    })

    //确认督训区按钮
    $('#confirm_duxunqu').click(function(){
        var arr = ['<div class="duxunqu">'];
        var $li = $('#inner_box').data('li');
        $('#duxunqus .selected').each(function(index, ele){
            arr.push('<span data-id="' + $(this).data('id') + '">' + $(this).text() + '</span>')
        })
        arr.push('</div>')
        $li.find('.duxunqu').remove();
        if (arr.length > 2) {
            $li.append(arr.join(''))
        }
        $(this).parents('.inner_box').hide();
        hideOverlay();
    })

    //点击关闭弹出盒的按钮
    $('.close').click(function() {
        $(this).parents('.inner_box').hide();
        hideOverlay();
    })

    //选择某个弹出盒里的选项
    $('.inner_box').on('click', 'li', function(){
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected')
        } else {
            $(this).addClass('selected')
        }

        //判断是否是全选
        var un_selected_num = $(this).parent().find('li:not(.selected)').length;
        if (un_selected_num === 0) {
            $(this).parents('.inner_box').find('.select_all').addClass('selected')
        } else {
            $(this).parents('.inner_box').find('.select_all').removeClass('selected')
        }
    })

    //点击增加权限
    $('.add_authority').click(function(){
        var $li = $(this).parents('li');
        var $newLi = $li.clone(true);
        $newLi.find('select').data('id', '').val('');
        $newLi.find('.add_authority, .text').remove();
        $newLi.find('.duxunqu').remove();
        $newLi.find('.first').append('<button type="button" class="red delete">-</button> <span class="text">删除权限</span>')
        $li.after($newLi)

    })

    //点击删除权限
    $('#fen').on('click', '.delete', function(){
        var $li = $(this).parents('li');
        var id = $li.find('select').data('id');
        if (id) {
            $('#fen option[value=' + id + ']').prop('disabled', false)
        }
        $li.remove();
    })

    //点击选择部门
    $('#select_department').click(function() {
        showOverlay();
        var selectedList = $('#department_div span').map(function(){
            return $(this).data('id');
        }).get();

        $('#departments li').each(function(){
            if ($.inArray($(this).data('id'), selectedList) >= 0) {
                $(this).addClass('selected')
            } else {
            	$(this).removeClass('selected')
            }
        });
        
        $('#department_box').show()
    })

    //点击确认选择部门
    $('#confirm_department').click(function(){
        var arr = [];
        $('#departments .selected').each(function(index, ele){
            arr.push('<span data-id="' + $(this).data('id') + '">' + $(this).text() + '</span>')
        })
        if (arr.length) {
            $('#department_div').html(arr.join('')).show();
        } else {
            $('#department_div').html('').hide();
        }
        $(this).parents('.inner_box').hide();
        hideOverlay();
    })

    //选择某个省分公司之后，其他的select就不可以再选这个省分公司了
    $('#fen select').change(function(){
        var id = $(this).val();
        var pre = $(this).data('id');
        $(this).data('id', id);

        if (id) {
            $('#fen option[value=' + id + ']').attr('disabled', 'disabled')
        }

        if (pre) {
            $('#fen option[value=' + pre + ']').prop('disabled', false)
        }

        if (id !== pre) {
            $(this).parents('li').find('.duxunqu').remove()
        }
        // console.log($('#fen option[value=' + id + ']'));
    })

    //点击提交按钮
    $('#add_submit_btn').click(function(){
        var staffId = $('#staff_tbody .selected').data('id');
        if (!staffId) {
            alert('请选择分配人员！')
            return 
        }
        var data = {
            staffId: staffId
        }

        //判断总公司权限，如果存在就是type=1
        var arr = [];
        $('#lines .checked').each(function(){
            var id = $(this).data('id');
            if (id) {
                arr.push(id);
            }
        })

        if (arr.length) {
            data.authorityLine = JSON.stringify({
                type: 1,
                departmentId: arr.join(',')
            })
        } else {
            var auth = [];
            var getData = function(array, item) {
                var str = []
                $(array).each(function(){
                    str.push(this[item])
                })

                return str.join(';')
            }
            $('#fen li').each(function(){
                var $this = $(this);
                var org = [];
                $this.find('.duxunqu span').each(function(){
                    org.push($(this).data('id'))
                })

                var obj = {
                    id: $this.data('id'),
                    pro: $this.find('select')[0].value,
                    org: org.join(',')
                }
                auth.push(obj)
            })

            data.authorityLine = JSON.stringify({
                type: 2,
                departmentId: getData(auth, 'id'),
                provinceId: getData(auth, 'pro'),
                organizationId: getData(auth, 'org')
            })
        }

        //合并部门权限
        var ids = [];
        $('#department_div span').each(function(){
            ids.push($(this).data('id'));
        })
        data.authority = ids.join(',');
        if (confirm("确认要提交吗？")) {
        	$.ajax({
        		type: 'post',
        		url: ctx + '/authority/save_data',
        		data: data,
        		dataType: 'json',
        		success: function (msg) {
        			if (msg.result === 1) {
        				parent.closeBox();
        				parent.freshList();
        			} else {
        				alert(msg.message)
        			}
        		}
        	})
        }
    })

    //inner-box点击全选选中下方的所有待选项
    $('.select_all').click(function(){
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected')
            $(this).parents('.inner_box').find('li').removeClass('selected')
        } else {
            $(this).addClass('selected')
            $(this).parents('.inner_box').find('li').addClass('selected')
        }
    })

    //显示候选人员表格
    function showStaffTable(list) {
        var len = list.length;
        if (len === 0 ) {
            $('#staff_tbody').html('<tr><td colspan="4">没有符合条件的数据，请更换查询条件！</td></tr>')
        } else {
            var arr = [];
            for (var i = 0; i < len; i++) {
                arr.push('<tr><td><div class="un_selected" data-id="')
                arr.push(list[i].id)
                arr.push('"></div></td><td>')
                arr.push(list[i].name)
                arr.push('</td><td>')
                arr.push(list[i].workNum)
                arr.push('</td><td>')
                arr.push(list[i].positionName)
                arr.push('</td></tr>')
            }
            $('#staff_tbody').html(arr.join(''))
        }
        $('#staff_table').show();
    }

    //显示候选督训区们
    function showDuxunqus (list, $li, hasList, name, pro) {
        showOverlay();
        $('#line_name').html(name);
        $('#pro').html(pro);
        list.sort(function(a, b){
            return a.name.localeCompare(b.name)
        })
        var arr = list.map(function(ele, index) {
            var flag = $.inArray(ele.id, hasList);
            return '<li data-id="' + ele.id + '" class="' + (flag !== -1 ? 'selected' : '') +'"title="' + ele.name +  '">' + ele.name + '</li>'
        })

        $('#duxunqus').html(arr.join(''));
        $('.select_all').removeClass('selected');
        $('#inner_box').data('li', $li).show();

        //判断是否是全选
        var un_selected_num =$('#inner_box').find('li:not(.selected)').length;
        if (un_selected_num === 0) {
        	$('.select_all').addClass('selected')
        } else {
        	$('.select_all').removeClass('selected')
        }
    }

})