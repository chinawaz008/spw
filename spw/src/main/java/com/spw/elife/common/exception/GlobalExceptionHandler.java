package com.spw.elife.common.exception;


import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "500";

    @ExceptionHandler
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        ModelAndView mav = createModelAndView(req, ex);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    private ModelAndView createModelAndView(HttpServletRequest req, Exception ex) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        return mav;
    }
}
