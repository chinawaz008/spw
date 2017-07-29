package com.spw.elife.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 需要重新登录的异常.
 *
 * @author terrason
 */
@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
public class UnauthenticationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    

}
