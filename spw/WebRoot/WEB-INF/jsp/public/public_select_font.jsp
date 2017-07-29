<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="front">
 选择渠道 <select id="line_type" name="lineType">
    <option value="">请选择渠道</option>
    <c:forEach items="${lineType}" var="itme" varStatus="st">
    <option value="${itme.id}">${itme.name}</option>
    </c:forEach>
  </select>
  <select id="branch_company_id" class="hide" name="branchCompanyId">
    <option value="">请选择分公司</option>
  </select>
  <select id="county_franchisees_id" class="hide" name="countyFranchiseesId">
    <option value="">请选择督训区</option>
  </select>
</div>
