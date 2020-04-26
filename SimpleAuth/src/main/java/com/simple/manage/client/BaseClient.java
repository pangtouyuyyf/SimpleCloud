package com.simple.manage.client;

import com.simple.manage.client.fallback.BaseClientFallback;
import com.simple.manage.domain.LoginInfo;
import com.simple.manage.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description 基础服务请求客户端
 * Author chen
 * CreateTime 2020-04-19 11:35
 **/
@FeignClient(name = "simple-base", contextId = "BaseClient", fallback = BaseClientFallback.class)
public interface BaseClient {
    @PostMapping("redis/saveLoginInfo")
    Result<?> saveLoginInfo(@RequestParam("tKey") String tKey, @RequestParam("tVal") String tVal,
                            @RequestParam("tTime") Integer tTime, @RequestParam("lKey") String lKey,
                            @RequestBody LoginInfo loginInfo, @RequestParam("lTime") Integer lTime);
}