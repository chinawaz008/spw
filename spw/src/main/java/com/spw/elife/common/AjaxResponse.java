package com.spw.elife.common;

/**
 * 封装页面ajax调用的返回值.
 *
 * @author lip
 */
public class AjaxResponse {

    private int code;
    private String message;
    private String value;
    private long size;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
