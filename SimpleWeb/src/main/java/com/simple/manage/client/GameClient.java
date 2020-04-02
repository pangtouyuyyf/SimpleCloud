package com.simple.manage.client;

import com.simple.manage.client.fallback.GameClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Description 游戏服务请求客户端
 * Author chen
 * CreateTime 2020-04-01 15:38
 **/
@FeignClient(name = "simple-gate", contextId = "GameClient", fallback = GameClientFallback.class)
public interface GameClient {
}
