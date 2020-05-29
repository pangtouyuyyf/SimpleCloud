package com.simple.gate.client;

import com.simple.gate.client.fallback.BaseClientFallback;
import com.simple.common.domain.Result;
import com.simple.common.domain.Token;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description 基础服务请求客户端
 * Author chen
 * CreateTime 2020-04-19 11:35
 **/
@FeignClient(name = "simple-base", contextId = "BaseClient", fallback = BaseClientFallback.class)
public interface BaseClient {
    @GetMapping("redis/getToken")
    Result<Token> getToken(@RequestParam("key") String key);

    @PostMapping("redis/renewToken")
    Result<?> renewToken(@RequestParam("key") String key, @RequestParam("time") Integer time);
}
