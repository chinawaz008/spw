package com.spw.elife.common;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.spw.elife.basics.bean.Menu;
import com.spw.elife.util.Utils;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    public static final String USER_SESSION_KEY = "principal";

    private final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("apply authc interceptor");
        Principal principal = (Principal) WebUtils.getSessionAttribute(request, USER_SESSION_KEY);
        if (principal != null) {
//            if (request.getMethod().equals(HttpMethod.GET.toString())) {
//            	//此处需要判断是否有权限访问该地址（modify by wangpeifa）
//            	String url = Utils.getRequestUrl(request);
//            	String urls[] = url.split("/");
//            	for (int i = 0; i < urls.length; i++) {
//        			if(StringUtils.isNotBlank(urls[i])){
//        				url = urls[i];
//        				break;
//        			}
//        		}
//            	List<Menu> list = principal.getMenus();
//            	List<String> slist = new ArrayList<>();
//            	for (int i = 0; i < list.size(); i++) {
//            		Menu menu = list.get(i);
//            		List<Menu> menus = menu.getMenus();
//            		//...
//            		
//            		slist.add();
//				}
//            	slist.add("/");
//            	if(slist!=null && slist.contains(url)){
//            		 return true;
//            	}else{
//            		// 如果session为空表示用户没有登录就重定向到login.jsp页面 
//                	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
//                	return false;
//            	}
//            } 
            return true;
            //如果session 失效时，ajax请求处理
        } else  if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
        	response.setHeader("sessionstatus", "timeout");
        	return false;
        }else{
        	// 如果session为空表示用户没有登录就重定向到login.jsp页面 
        	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        	return false;
        }
    }
}
