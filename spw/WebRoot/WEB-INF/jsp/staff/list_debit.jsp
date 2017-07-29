<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../public/public_head_list.jsp"%>
    <title>民盛对账管理</title>
    <style>
      .table_window .red {
        color: #ba1e17;
        font-size: 28px;
      }
      .table_window .item {
        margin-right: 30px;
      }

      .tc {
        text-align: center;
      }
    </style>
</head>
<body>
	<div class="theme_lay">
		<div class="theme">
			<label class="main_theme">增员代扣对账管理</label> <label class="sub_theme">（查询）</label>
		</div>
		<div class="btns">
		
			<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
				<c:if test="${item == 110}">
					<button type="button" class="btn_blue open_box" data-src="${ctx}/staff/add_debit" data-type="新增押金小票"><i class="iconfont icon-jiahao"></i> 新增</button>
				</c:if> 
			</c:forEach>
			
			<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
				<c:if test="${item == 108}">
					<button type="button" class="btn_blue" id = "excelPrint"><i class="iconfont icon-jiahao"></i> 导出</button>
				</c:if> 
			</c:forEach>
		</div>
	</div>
<div class="search_lay">
		<div class="controller_div">
			<i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选 <i
				class="iconfont right_icon icon-shangla" id="right_icon"></i>
		</div>
		<form id="search_form" method="post">
			<div class="search_div" id="search_div">
				<%@include file="../public/public_select_font.jsp" %>
				<select name="type">
					<option value="">请选择类型</option>
					<option value="0">线上代扣</option>
					<option value="1">手动添加</option>
					<option value="2">押金小票</option>
					<option value="3">微信支付</option>
					<option value="4">支付宝支付</option>
				</select>
				
				<input class="query_input ms_datepicker" id="beginDate" type="text"
					placeholder="交款开始时间"
					onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')}'})"
					name="beginDate"></input>
					 ~ <input type="text"
					class="query_input ms_datepicker" id="endDate"
					placeholder="交款结束时间"
					onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})"
					name="endDate"></input>
					打印纸质收据 &nbsp;<input type="checkbox"  name="isShow" value="1"/>
				<br />
				<input placeholder="请输入审核人" name="name" type="text"></input>
				<input placeholder="请输入姓名" name="gysName" type="text"></input>
				<input placeholder="请输入银行卡号" name="serialNumber" type="text"></input>
				<%-- <%@include file="../public/public_select.jsp" %> --%>
				<button type="button" class="btn_green debit_search">
					<i class="iconfont icon-sousuo-sousuo"></i> 搜索
				</button>
			</div>
		</form>
	</div>
	
	<div class="table_lay">
    <div class="table_window tc tongji">
    </div>
    <div class="table_window" data-style="overflow-x: scroll;">
        <table data-style="width:1300px">
            <thead>
                <tr>
                	<th>类型</th>
					<th>渠道</th>
					<th>分公司</th>
					<th>督训区</th>
					<th>职级</th>
					<th>姓名</th>
					<th>身份证号</th>
					<th>银行卡类型</th>
					<th>银行卡号</th>
					<th>交款时间</th>
					<th>交款金额</th>
					<th>审核人</th>
					<th>打印</th>
                </tr>
            </thead>
            <tbody id="flexible_tbody">
            	<tr class="templet hide" name="insuranceTr">
            		<td>
	            		^{'@{type}@'=='0'^^ 
	            			线上代扣
                         }^
                         ^{'@{type}@'=='1'^^ 
                         	手动添加
                         }^
                         ^{'@{type}@'=='2'^^ 
                         	押金小票
                         }^
                         ^{'@{type}@'=='3'^^ 
                         	微信支付
                         }^
                          ^{'@{type}@'=='4'^^ 
                         	支付宝支付
                         }^
            		</td>
					<td>@{lineType}@</td>
					<td>@{companyName}@</td>
					<td>@{countyFranchiseesName}@</td>
					<td>@{positionName}@</td>
					<td>@{debitName}@</td>
					<td>@{debitNo}@</td>
					<td>@{bankName}@</td>
					<td>@{bankCardNum}@</td>
					<td>@{debitTime}@</td>
					<td>@{debitMoney}@</td>
					<td>@{staffName}@</td>
					<td>
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 109}">
								<button type="button" class="btn_green btn_print" data-src="${ctx}/staff/debit_print?id=@{id}@">电子收据</button>
							</c:if> 
						</c:forEach>
						^{'@{isPrint}@' =='1'^^ 
						<button type="button" class="btn_blue btn_print2" data-src="${ctx}/staff/debitPrint?idCard=@{debitNo}@">纸质收据</button>
						^^}^						 
					</td>
                </tr>
            </tbody>
        </table>
    </div>
    <ul class="pagination" id="pagers"></ul>
    <i class="clear"></i>
</div> 
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_list3.js"></script> 
<script src="${ctx}/static/js/staff/list_debit.js"></script>
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>