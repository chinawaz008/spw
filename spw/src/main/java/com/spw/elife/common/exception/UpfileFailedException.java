package com.spw.elife.common.exception;

/**
 *
 * @author Administrator
 */
public class UpfileFailedException extends Exception {

    public UpfileFailedException() {
        super("文件上传失败");
    }

    public UpfileFailedException(String string) {
        super("文件上传失败：" + string);
    }

    public UpfileFailedException(String string, Throwable thrwbl) {
        super("文件上传失败：" + string, thrwbl);
    }

    public UpfileFailedException(Throwable thrwbl) {
        super("文件上传失败", thrwbl);
    }

}
