package com.simple.manage.client;

import com.simple.manage.client.fallback.AuthClientFallback;
import com.simple.manage.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description 权限服务请求客户端
 * Author chen
 * CreateTime 2020-04-14 19:24
 **/
@FeignClient(name = "simple-auth", contextId = "AuthClient", fallback = AuthClientFallback.class)
public interface AuthClient {
    @GetMapping("sys/login")
    Result<?> login(@RequestParam("name") String name, @RequestParam("pwd") String pwd,
                    @RequestParam("channel") String channel);
}