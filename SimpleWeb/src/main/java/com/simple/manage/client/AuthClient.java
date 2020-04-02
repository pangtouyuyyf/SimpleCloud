package com.simple.manage.client;

import com.simple.manage.client.fallback.AuthClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Description 权限服务请求客户端
 * Author chen
 * CreateTime 2020-04-01 15:37
 **/
@FeignClient(name = "simple-gate", contextId = "AuthClient", fallback = AuthClientFallback.class)
public interface AuthClient {
}
