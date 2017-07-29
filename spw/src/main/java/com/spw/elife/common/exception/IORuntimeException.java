package com.spw.elife.common.exception;


/**
 *
 * @author Administrator
 */
public class IORuntimeException extends RuntimeException {

    public IORuntimeException() {
    }

    public IORuntimeException(String string) {
        super(string);
    }

    public IORuntimeException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public IORuntimeException(Throwable thrwbl) {
        super(thrwbl);
    }

}
