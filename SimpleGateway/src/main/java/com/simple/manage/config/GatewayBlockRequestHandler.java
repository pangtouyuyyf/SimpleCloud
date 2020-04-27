package com.simple.manage.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.simple.manage.domain.Result;
import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.util.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description sentinel熔断降级处理
 * Author chen
 * CreateTime 2020-04-27 14:28
 **/

public class GatewayBlockRequestHandler implements BlockRequestHandler {
    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
        LogUtil.error(GatewayBlockRequestHandler.class, "请求发生异常，请求URI：" + exchange.getRequest().getPath() + "，请求方法：" + exchange.getRequest().getMethodValue() +
                "异常信息： " + t.getMessage());
        int status = HttpStatus.CONTINUE.value();
        Result r = Result.error(SysExpEnum.CONNECT_OR_OVERTIME_ERROR);
        // 不同的异常返回不同的提示语
        if (t instanceof FlowException) {
            LogUtil.error(GatewayBlockRequestHandler.class, "接口限流了");
            status = HttpStatus.TOO_MANY_REQUESTS.value();
        } else if (t instanceof DegradeException) {
            LogUtil.error(GatewayBlockRequestHandler.class, "服务降级了");
            status = HttpStatus.TOO_MANY_REQUESTS.value();
        } else if (t instanceof ParamFlowException) {
            LogUtil.error(GatewayBlockRequestHandler.class, "接口限流了");
            status = HttpStatus.TOO_MANY_REQUESTS.value();
        } else if (t instanceof SystemBlockException) {
            LogUtil.error(GatewayBlockRequestHandler.class, "触发系统保护规则");
            status = HttpStatus.BAD_REQUEST.value();
        } else if (t instanceof AuthorityException) {
            LogUtil.error(GatewayBlockRequestHandler.class, "授权规则不通过");
            status = HttpStatus.UNAUTHORIZED.value();
        }

        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(r));
    }
}