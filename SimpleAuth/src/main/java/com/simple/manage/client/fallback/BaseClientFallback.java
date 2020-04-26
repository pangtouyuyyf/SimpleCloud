package com.simple.manage.client.fallback;

import com.simple.manage.client.BaseClient;
import com.simple.manage.domain.LoginInfo;
import com.simple.manage.domain.Result;
import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.util.LogUtil;
import org.springframework.stereotype.Component;

/**
 * Description 基础服务请求熔断处理
 * Author chen
 * CreateTime 2020-04-19 11:36
 **/
@Component
public class BaseClientFallback implements BaseClient {
    private static final String SERVICE_NAME_MSG = "simple-base服务";

    public Result<?> saveLoginInfo(String key, String value, Integer time, LoginInfo loginInfo) {
        LogUtil.error(BaseClientFallback.class, SERVICE_NAME_MSG + "redis/saveLoginInfo接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }
}