package com.sun.fighter.exception;

import com.sun.fighter.common.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 chengyin
 * @创建时间 2018/7/25
 * @描述
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handler(HttpServletRequest request, Exception e) {
        String url = request.getRequestURL().toString();
        JsonData result = JsonData.fail(e.getMessage());
        log.info("exception,url:" + url, e);
        ModelAndView mv = new ModelAndView("exception",result.toMap());
        return mv;
    }
}
