package com.simple.manage.client.fallback;

import com.simple.manage.client.BaseClient;
import com.simple.manage.domain.Result;
import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.util.LogUtil;

/**
 * Description 基础服务请求熔断处理
 * Author chen
 * CreateTime 2020-04-19 11:36
 **/

public class BaseClientFallback implements BaseClient {
    private static final String SERVICE_NAME_MSG = "simple-base服务";

    public Result<?> getToken(String key) {
        LogUtil.error(AuthClientFallback.class, SERVICE_NAME_MSG + "redis/getToken接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }

    public Result<?> renewToken(String key, Integer time) {
        LogUtil.error(AuthClientFallback.class, SERVICE_NAME_MSG + "redis/renewToken接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }
}