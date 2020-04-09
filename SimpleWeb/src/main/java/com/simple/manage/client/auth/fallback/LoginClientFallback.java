package com.simple.manage.client.auth.fallback;

import com.simple.manage.client.auth.LoginClient;
import com.simple.manage.domain.Result;
import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.util.LogUtil;
import com.simple.manage.util.ResultUtil;
import org.springframework.stereotype.Component;

/**
 * Description 权限服务请求熔断处理
 * Author chen
 * CreateTime 2020-04-01 15:40
 **/
@Component
public class LoginClientFallback implements LoginClient {
    private static final String SERVICE_NAME_MSG = "simple-auth服务";

    public Result<?> login(String name, String pwd,
                           String channel) {
        LogUtil.error(LoginClientFallback.class, SERVICE_NAME_MSG + "sys/login接口连接异常!");
        return ResultUtil.error(SysExpEnum.COMMON_ERROR);
    }
}