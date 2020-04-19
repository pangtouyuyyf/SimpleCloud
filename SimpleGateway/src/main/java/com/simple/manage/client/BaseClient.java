package com.simple.manage.client;

import com.simple.manage.client.fallback.AuthClientFallback;
import com.simple.manage.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description 基础服务请求客户端
 * Author chen
 * CreateTime 2020-04-19 11:35
 **/
@FeignClient(name = "simple-base", contextId = "BaseClient", fallback = AuthClientFallback.class)
public interface BaseClient {
    @GetMapping("redis/getToken")
    Result<?> getToken(@RequestParam("key") String key);

    @PostMapping("redis/renewToken")
    Result<?> renewToken(@RequestParam("key") String key, @RequestParam("time") Long time);
}
