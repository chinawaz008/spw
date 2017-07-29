package com.spw.elife.common;


import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean实现的分页器. 用来封装分页信息。
 *
 * @author MSW
 */
public class Pager<T> {

    public static final int DEFAULT_PAGESIZE = 10;
    private int page = 1;
    private int size = DEFAULT_PAGESIZE;
    private int total;
    private List<T> elements=new ArrayList<T>();

    /**
     * 页码. 当前第几页.
     *
     * @return 页码
     */
    public int getPage() {
        return page;
    }

    /**
     * 设置当前页码.
     *
     * @param page 页码
     */
    public void setPage(int page) {
        if (page > 1) {
            this.page = page;
        }
    }

    /**
     * 分页大小. 一页包含多少数据.
     *
     * @return 分页大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置分页大小.
     *
     * @param size 一页包含多少数据
     */
    public void setSize(int size) {
        if (size > 1) {
            this.size = size;
        }
    }

    /**
     * 获取不分页时的总数据量.
     *
     * @return 总数据量
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置不分页时的总数据量.
     *
     * @param total 总数据量
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 当前页是否超出总页数范围.
     *
     * @return true - 已超出页码范围. false - 未超出范围.
     */
    public boolean isOverflowed() {
        return getIndex() >= total;
    }

    /**
     * 获取当前数据索引.
     *
     * @return 索引
     */
    public int getIndex() {
        return (page - 1) * size;
    }

    /**
     * 获取当前页数据列表.
     *
     * @return 当前页数据列表
     */
    public List<T> getElements() {
        return elements;
    }

    /**
     * 设置当前页数据列表.
     *
     * @param elements 当前页数据列表
     */
    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public String toJson() {
        return "{\"page\":" + page + ",\"size\":" + size + ",\"total\":" + total + "}";
    }

    /**
     * {@inheritDoc }. 与{@link #toJson() }等价
     *
     * @return JSON字符串
     */
    @Override
    public String toString() {
        return toJson();
    }
}
