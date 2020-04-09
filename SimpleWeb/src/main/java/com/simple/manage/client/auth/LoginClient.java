package com.simple.manage.client.auth;

import com.simple.manage.client.auth.fallback.LoginClientFallback;
import com.simple.manage.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description 权限服务请求客户端
 * Author chen
 * CreateTime 2020-04-01 15:37
 **/
@FeignClient(name = "simple-gate", contextId = "AuthSysClient", fallback = LoginClientFallback.class)
public interface LoginClient {
    @GetMapping("simple-auth/sys/login")
    Result<?> login(@RequestParam("name") String name, @RequestParam("pwd") String pwd,
                    @RequestParam("channel") String channel);
}
