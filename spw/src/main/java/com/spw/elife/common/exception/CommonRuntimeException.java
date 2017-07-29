package com.spw.elife.common.exception;


import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 *
 * @author Administrator
 */
public class CommonRuntimeException extends HttpStatusCodeException {

    public CommonRuntimeException(HttpStatus statusCode) {
        super(statusCode);
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText, Throwable ex) {
        super(statusCode, statusText);
        initCause(ex);
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseBody, responseCharset);
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }

}
