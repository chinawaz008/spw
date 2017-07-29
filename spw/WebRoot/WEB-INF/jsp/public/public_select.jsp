<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="public_selects">
 <select id="select1" data-index="1" >
     <option value="" data-next="false">请选择公司类型</option>
     <option value="1" data-next="true">总公司</option>
     <option value="2" data-next="true">分公司</option>
 </select>
 <select class="hide"></select>
 <select class="hide"></select>
 <select class="hide"></select>
 <select class="hide"></select>
 <select class="hide"></select>
 <select class="hide"></select>
</div>
<input type="hidden" name="position" id="position" />
