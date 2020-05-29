package com.simple.auth.client.fallback;

import com.simple.auth.client.BaseClient;
import com.simple.common.domain.LoginInfo;
import com.simple.common.domain.Result;
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

    public Result<?> saveLoginCache(String tKey, String tVal,
                                    Integer tTime, String lKey,
                                    LoginInfo loginInfo, Integer lTime) {
        LogUtil.error(BaseClientFallback.class, SERVICE_NAME_MSG + "redis/saveLoginCache接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }

    public Result<LoginInfo> getLoginInfo(String key) {
        LogUtil.error(BaseClientFallback.class, SERVICE_NAME_MSG + "redis/getLoginInfo接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR, null);
    }

    public Result<?> delToken(String tKey, String lKey, String lFlag) {
        LogUtil.error(BaseClientFallback.class, SERVICE_NAME_MSG + "redis/delToken接口连接异常!");
        return Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
    }
}