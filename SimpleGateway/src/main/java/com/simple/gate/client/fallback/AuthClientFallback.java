package com.simple.gate.client.fallback;

import com.simple.gate.client.AuthClient;
import com.simple.common.domain.Result;
import com.simple.common.enums.SysExpEnum;
import com.simple.common.util.LogUtil;
import org.springframework.stereotype.Component;

/**
 * Description 权限服务请求熔断处理
 * Author chen
 * CreateTime 2020-04-14 19:25
 **/
@Component
public class AuthClientFallback implements AuthClient {
    private static final String SERVICE_NAME_MSG = "simple-auth服务";

    public Result<?> login(String name, String pwd,
                           String channel) {
        LogUtil.error(AuthClientFallback.class, SERVICE_NAME_MSG + "sys/login接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }

    public Result<?> checkOne(){
        LogUtil.error(AuthClientFallback.class, SERVICE_NAME_MSG + "user/checkOne接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }
}