package com.simple.manage.controller;

import com.simple.manage.domain.Result;
import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.util.ResultUtil;
import org.springframework.web.bind.annotation.*;

/**
 * Description 熔断统一重定向返回处理controller
 * Author chen
 * CreateTime 2020-04-14 15:34
 **/
@RestController
@RequestMapping(value = "/gate")
public class FallbackController {
    @GetMapping("/fallback")
    public Result<?> getFallback() {
        return ResultUtil.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }

    @PostMapping("/fallback")
    public Result<?> postFallback() {
        return ResultUtil.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }

    @DeleteMapping("/fallback")
    public Result<?> delFallback() {
        return ResultUtil.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }
}