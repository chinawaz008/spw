package com.spw.elife.basics.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单实体.
 *
 * @author Administrator
 */
public class Menu implements Comparable<Menu> {

    private int id;
    /**
     * 菜单名.
     */
    private String name;
    /**
     * 菜单标签. 用于对同名菜单加以区分。
     */
    private String label;
    /**
     * 父级菜单ID. ${@code 0}表示根节点.
     */
    private int parentId;
    /**
     * 是否是枝节点（父级）菜单.
     */
    private boolean branch;
    /**
     * 菜单图标样式.
     */
    private String icon;
    /**
     * 菜单链接地址. 以“/”开始，相对于项目根路径.
     */
    private String url;
    /**
     * 排序字段.
     */
    private int priority;
    /**
     * 父级菜单.
     */
    private Menu parent;
    /**
     * 子菜单.
     */
    private List<Menu> menus;
    
	/**
	 * 0菜单 1 按钮
	 */
	private String type;

	/**
	 * 0菜单 1 按钮获取
	 * 
	 * @return 0菜单 1 按钮
	 */
	public String getType() {
		return type;
	}

	/**
	 * 0菜单 1 按钮设定
	 * 
	 * @param type 0菜单 1 按钮
	 */
	public void setType(String type) {
		this.type = type;
	}

    public void addChildren(Menu menu) {
        if (getMenus() == null) {
            setMenus(new ArrayList<Menu>());
        }
        getMenus().add(menu);
        menu.setParent(this);
    }

    public int compareTo(Menu t) {
        return this.priority - t.priority;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 菜单名.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 菜单标签. 用于对同名菜单加以区分。
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 菜单标签. 用于对同名菜单加以区分。
     *
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 父级菜单ID. ${@code 0}表示根节点.
     *
     * @return the parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * 父级菜单ID. ${@code 0}表示根节点.
     *
     * @param parentId the parentId to set
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * 是否是枝节点（父级）菜单.
     *
     * @return the branch
     */
    public boolean isBranch() {
        return branch;
    }

    /**
     * 是否是枝节点（父级）菜单.
     *
     * @param branch the branch to set
     */
    public void setBranch(boolean branch) {
        this.branch = branch;
    }

    /**
     * 菜单图标样式.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 菜单图标样式.
     *
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 菜单链接地址. 以“/”开始，相对于项目根路径.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 菜单链接地址. 以“/”开始，相对于项目根路径.
     *
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 父级菜单.
     *
     * @return the parent
     */
    public Menu getParent() {
        return parent;
    }

    /**
     * 父级菜单.
     *
     * @param parent the parent to set
     */
    public void setParent(Menu parent) {
        this.parent = parent;
    }

    /**
     * 排序字段.
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 排序字段.
     *
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * 子菜单.
     *
     * @return the menus
     */
    public List<Menu> getMenus() {
        return menus;
    }

    /**
     * 子菜单.
     *
     * @param menus the menus to set
     */
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
