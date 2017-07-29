<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="top">
    <div class="logo">
      <i class="iconfont icon-logo"></i>
    </div>
    <div class="title">
      <img src="${ctx}/static/img/public/title.png">
    </div>
    <ul class="menu" id="menu">
      <li><a href="${ctx}/">主页</a><span class="bk"></span></li>
      <c:forEach items="${principal.menus}" var="cur">
      	  <li><a href="${ctx}/manage/${cur.id}">${cur.name}</a><span class="bk"></span></li>
      </c:forEach>
    </ul>
    <div class="member">
      <label class="msg">
        <i class="iconfont icon-iconlingdang message"><div class="num">3</div></i>
        <ul>
          <li>sfdgsdfg这是关于1的消息<i class="iconfont icon-shanchu"></i></li>
          <li>这是关于2的消息<i class="iconfont icon-shanchu"></i></li>
          <li>关于3的消息<i class="iconfont icon-shanchu"></i></li>
        </ul>
      </label>
      <label class="user" style="display: inline-block;">
        <i class="iconfont icon-geren avatar"></i>
        <span>${principal.userInfo.username}</span>
        <i class="iconfont icon-xiala"></i>
        <ul>
          <li><i class="iconfont icon-shezhi"></i>设置</li>
          <li id="logout"><i class="iconfont icon-tuichu"></i>退出</li>
        </ul>
      </label>
    </div>
</div>