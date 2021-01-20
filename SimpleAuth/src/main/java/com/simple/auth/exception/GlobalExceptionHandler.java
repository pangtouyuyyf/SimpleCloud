package com.simple.auth.exception;

import com.simple.common.domain.Result;
import com.simple.common.enums.SysExpEnum;
import com.simple.common.util.LogUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Description controller全局异常处理
 * Author chen
 * CreateTime 2020-04-26 11:48
 **/

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 通用异常处理
     *
     * @param ex ex
     * @return result
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<?> commonHandler(Exception ex) {
        // 判断异常类型
        if (ex instanceof MissingServletRequestParameterException) {
            //访问参数异常
            LogUtil.error(GlobalExceptionHandler.class, "访问参数异常:{}", ExceptionUtils.getStackTrace(ex));
        } else if (ex instanceof NoHandlerFoundException) {
            //404-没有可访问的资源
            LogUtil.error(GlobalExceptionHandler.class, "没有可访问的资源:{}", ExceptionUtils.getStackTrace(ex));
        } else {
            LogUtil.error(GlobalExceptionHandler.class, ExceptionUtils.getStackTrace(ex));
        }

        return Result.error(SysExpEnum.COMMON_ERROR);
    }
}