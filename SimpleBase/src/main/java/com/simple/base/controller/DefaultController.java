package com.simple.base.controller;

import com.simple.common.controller.BaseController;
import com.simple.common.domain.Result;
import com.simple.common.enums.SysExpEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 默认服务controller
 * Author chen
 * CreateTime 2020-04-18 11:51
 **/
@RestController
@RequestMapping(value = "/default")
public class DefaultController extends BaseController {
    /**
     * 服务熔断默认错误返回
     *
     * @return
     */
    @GetMapping("/fail")
    public Result<?> getToken() {
        return msg(SysExpEnum.CONNECT_OR_OVERTIME_ERROR.getCode(), SysExpEnum.CONNECT_OR_OVERTIME_ERROR.getMessage(), null);
    }
}
