package com.simple.manage.exception;

import com.simple.manage.domain.Result;
import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.util.LogUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
        LogUtil.error(GlobalExceptionHandler.class, ExceptionUtils.getStackTrace(ex));

        // 判断异常类型
//        if (e instanceof MissingServletRequestParameterException) {
//
//
//        }

        return Result.error(SysExpEnum.COMMON_ERROR);
    }
}