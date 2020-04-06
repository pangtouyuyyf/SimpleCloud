package com.simple.manage.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Description 全局请求过滤
 * Author chen
 * CreateTime 2020-04-02 9:03
 **/
@Component
public class HttpTraceGlobalFilter implements GlobalFilter, Ordered {
    private Logger logger = LoggerFactory.getLogger(HttpTraceGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest originalRequest = exchange.getRequest();
        URI originalRequestUrl = originalRequest.getURI();
        System.out.println("from:");
        System.out.println(originalRequestUrl.getHost());
        System.out.println("to:");
        System.out.println(originalRequestUrl.getPath());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}