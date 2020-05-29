package com.simple.auth.client;

import com.simple.auth.client.fallback.BaseClientFallback;
import com.simple.common.domain.LoginInfo;
import com.simple.common.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Description 基础服务请求客户端
 * Author chen
 * CreateTime 2020-04-19 11:35
 **/
@FeignClient(name = "simple-base", contextId = "BaseClient", fallback = BaseClientFallback.class)
public interface BaseClient {
    @PostMapping("redis/saveLoginCache")
    Result<?> saveLoginCache(@RequestParam("tKey") String tKey, @RequestParam("tVal") String tVal,
                             @RequestParam("tTime") Integer tTime, @RequestParam("lKey") String lKey,
                             @RequestBody LoginInfo loginInfo, @RequestParam("lTime") Integer lTime);

    @GetMapping("redis/getLoginInfo")
    Result<LoginInfo> getLoginInfo(@RequestParam("key") String key);

    @DeleteMapping("redis/delToken")
    Result<?> delToken(@RequestParam("tKey") String tKey, @RequestParam("lKey") String lKey, @RequestParam("lFlag") String lFlag);
}