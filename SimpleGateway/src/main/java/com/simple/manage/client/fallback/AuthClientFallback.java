package com.simple.manage.client.fallback;

import com.simple.manage.client.AuthClient;
import com.simple.manage.domain.Result;
import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.util.LogUtil;
import com.simple.manage.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description 权限服务请求熔断处理
 * Author chen
 * CreateTime 2020-04-14 19:25
 **/

public class AuthClientFallback implements AuthClient {
    private static final String SERVICE_NAME_MSG = "simple-auth服务";

    public Result<?> login(String name, String pwd,
                           String channel) {
        LogUtil.error(AuthClientFallback.class, SERVICE_NAME_MSG + "sys/login接口连接异常!");
        return ResultUtil.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }

    public Result<?> checkOne(){
        LogUtil.error(AuthClientFallback.class, SERVICE_NAME_MSG + "user/checkOne接口连接异常!");
        return ResultUtil.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }
}