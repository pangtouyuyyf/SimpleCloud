package com.simple.gate.config;

import com.simple.common.domain.Result;
import com.simple.common.enums.SysExpEnum;
import com.simple.common.util.LogUtil;
import org.apache.http.HttpHeaders;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description 网关通用异常处理
 * Author chen
 * CreateTime 2020-04-27 15:32
 **/

public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    /**
     * MessageReader
     */
    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

    /**
     * MessageWriter
     */
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();

    /**
     * ViewResolvers
     */
    private List<ViewResolver> viewResolvers = Collections.emptyList();

    /**
     * 存储处理异常后的信息
     */
    private ThreadLocal<Map<String, Object>> exceptionHandlerResult = new ThreadLocal<>();

    /**
     * 参考AbstractErrorWebExceptionHandler
     *
     * @param messageReaders
     */
    public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
        Assert.notNull(messageReaders, "'messageReaders' must not be null");
        this.messageReaders = messageReaders;
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     *
     * @param viewResolvers
     */
    public void setViewResolvers(List<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     *
     * @param messageWriters
     */
    public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
        Assert.notNull(messageWriters, "'messageWriters' must not be null");
        this.messageWriters = messageWriters;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        Map<String, Object> map = new HashMap<>(2, 1);

        HttpStatus httpStatus;
        SysExpEnum sysExpEnum;
        if (ex instanceof NotFoundException) {
            // 503 服务熔断重定向
            ServerHttpResponse response = exchange.getResponse();
            // 该重定向地址不需要token
            response.getHeaders().set(HttpHeaders.LOCATION, "/simple-base/default/fail");
            //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
            response.setStatusCode(HttpStatus.SEE_OTHER);
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.setComplete();
        } else if (ex instanceof ResponseStatusException) {
            // 404
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            httpStatus = responseStatusException.getStatus();
            sysExpEnum = SysExpEnum.CONNECT_OR_OVERTIME_ERROR;
        } else {
            // 500
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            sysExpEnum = SysExpEnum.COMMON_ERROR;
        }
        map.put("httpStatus", httpStatus);
        map.put("result", Result.error(sysExpEnum));

        /* 错误记录 */
        LogUtil.error(GatewayExceptionHandler.class, "请求发生异常，请求URI：" + request.getPath() +
                "，请求方法：" + request.getMethodValue() +
                "异常信息： " + ex.getMessage());

        /* 参考 AbstractErrorWebExceptionHandler */
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        exceptionHandlerResult.set(map);

        ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(ex))
                .flatMap((handler) -> handler.handle(newRequest))
                .flatMap((response) -> write(exchange, response));
    }

    /**
     * 参考DefaultErrorWebExceptionHandler
     *
     * @param request
     * @return
     */
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> map = exceptionHandlerResult.get();
        return ServerResponse.status((HttpStatus) map.get("httpStatus"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue((Result) map.get("result")));
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     *
     * @param exchange
     * @param response
     * @return
     */
    private Mono<? extends Void> write(ServerWebExchange exchange, ServerResponse response) {
        exchange.getResponse().getHeaders()
                .setContentType(response.headers().getContentType());
        return response.writeTo(exchange, new ResponseContext());
    }

    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    private class ResponseContext implements ServerResponse.Context {
        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return GatewayExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return GatewayExceptionHandler.this.viewResolvers;
        }
    }
}