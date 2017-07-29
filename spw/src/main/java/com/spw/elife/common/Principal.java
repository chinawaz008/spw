package com.spw.elife.common;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spw.elife.basics.bean.Menu;
import com.spw.elife.basics.bean.UserInfo;


public class Principal {

    public static final int PT = 1;
    public static final int RE = 2;
    //public static final int HB = 5;
    private final Logger logger = LoggerFactory.getLogger(Principal.class);

    private List<Menu> menus;
    private final Set<Menu> location = new TreeSet<Menu>();
    private final Map<String, Menu> menuUrlIndex = new HashMap<String, Menu>();

    private UserInfo userInfo; // 用户信息
    
    //private PtInfoResponse ptInfo; // 平台信息
    
    
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
		for (Menu menu : menus) {
			indexMenuUrl(menu);
		}
	}

    private void indexMenuUrl(Menu menu) {
        if (StringUtils.isNotBlank(menu.getUrl()) && !menu.getUrl().startsWith("javascript:")) {
            menuUrlIndex.put(menu.getUrl(), menu);
        }
        if (menu.getMenus() != null) {
            for (Menu m : menu.getMenus()) {
                indexMenuUrl(m);
            }
        }
    }

    public Set<Menu> getLocation() {
        return location;
    }

    public void setLocation(String url) {
        Menu menu = menuUrlIndex.get(url);
        if (menu != null) {
            location.clear();
            addMenuToLocation(menu);
        }
        logger.debug("location changed to {}", location);
    }

    private void addMenuToLocation(Menu menu) {
        location.add(menu);
        if (menu.getParent() != null) {
            addMenuToLocation(menu.getParent());
        }
    }

    public List getTasks() {
        return null;
    }

    public List getNotifications() {
        return null;
    }

    public List getMessages() {
        return null;
    }

    public String getUsername() {
        return userInfo.getUsername();
    }

    public void setPassword(String password) {
        userInfo.setPassword(password);
    }

    public int getStatus() {
        return userInfo.getStatus();
    }

    public String getType() {
        return userInfo.getType();
    }

    public String getSkin() {
        return userInfo.getSkin();
    }

    public void setSkin(String skin) {
        userInfo.setSkin(skin);
    }

    public boolean isNavbarFixed() {
        return userInfo.isNavbarFixed();
    }

    public void setNavbarFixed(boolean navbarFixed) {
        userInfo.setNavbarFixed(navbarFixed);
    }

    public boolean isMenuFixed() {
        return userInfo.isMenuFixed();
    }

    public void setMenuFixed(boolean menuFixed) {
        userInfo.setMenuFixed(menuFixed);
    }

    public boolean isBreadcrumbFixed() {
        return userInfo.isBreadcrumbFixed();
    }

    public void setBreadcrumbFixed(boolean breadcrumbFixed) {
        userInfo.setBreadcrumbFixed(breadcrumbFixed);
    }

    public boolean isPetty() {
        return userInfo.isPetty();
    }

    public void setPetty(boolean petty) {
        userInfo.setPetty(petty);
    }

    public int getUserId() {
        return userInfo.getId();
    }

}
