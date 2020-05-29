package com.simple.gate.client.fallback;

import com.simple.gate.client.BaseClient;
import com.simple.common.domain.Result;
import com.simple.common.domain.Token;
import com.simple.common.enums.SysExpEnum;
import com.simple.common.util.LogUtil;
import org.springframework.stereotype.Component;

/**
 * Description 基础服务请求熔断处理
 * Author chen
 * CreateTime 2020-04-19 11:36
 **/
@Component
public class BaseClientFallback implements BaseClient {
    private static final String SERVICE_NAME_MSG = "simple-base服务";

    public Result<Token> getToken(String key) {
        LogUtil.error(BaseClientFallback.class, SERVICE_NAME_MSG + "redis/getToken接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR, null);
    }

    public Result<?> renewToken(String key, Integer time) {
        LogUtil.error(BaseClientFallback.class, SERVICE_NAME_MSG + "redis/renewToken接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }
}