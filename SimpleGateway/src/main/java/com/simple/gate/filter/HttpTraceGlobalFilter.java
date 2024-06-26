package com.simple.gate.filter;

import com.simple.gate.client.BaseClient;
import com.simple.common.config.SysParams;
import com.simple.gate.config.UrlMatchConfig;
import com.simple.common.domain.Result;
import com.simple.common.domain.Token;
import com.simple.common.enums.SysExpEnum;
import com.simple.common.util.CommonUtil;
import com.simple.common.util.JwtUtil;
import com.simple.common.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description 全局请求过滤
 * Author chen
 * CreateTime 2020-04-02 9:03
 **/
@Component
public class HttpTraceGlobalFilter implements GlobalFilter, Ordered {
    @Resource
    private BaseClient baseClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        /* 验证url是否需要token验证 */
        if (UrlMatchConfig.isIgnoreToken(request.getURI().getPath())) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(SysParams.Sys.TOKEN);

        Map<String, String> jwtMap = JwtUtil.parseJWT(token);

        /* 验证令牌合法性 */
        if (jwtMap == null || jwtMap.isEmpty()) {
            LogUtil.error(HttpTraceGlobalFilter.class, LocalDateTime.now() + " 令牌验证失败");
            return response.writeWith(Mono.just(handleResponse(response, SysExpEnum.NEED_LOGIN)));
        }

        /* 获取令牌中的用户、角色和登录渠道 */
        String userId = jwtMap.get(SysParams.Sys.USER_ID);
        String channel = jwtMap.get(SysParams.Sys.CHANNEL);

        /* 验证令牌参数 */
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(channel)
                || !(SysParams.Sys.CHANNEL_WEB.equals(channel) || SysParams.Sys.CHANNEL_APP.equals(channel))) {
            LogUtil.error(HttpTraceGlobalFilter.class, LocalDateTime.now() + " 令牌参数有误");
            return response.writeWith(Mono.just(handleResponse(response, SysExpEnum.NEED_LOGIN)));
        }

        Result r;

        /* 获取服务器缓存令牌 */
        List<String> tokenKeyParts = Arrays.asList(SysParams.Redis.TOKEN_PREFIX, userId, channel);
        String tokenRedisKey = String.join(SysParams.Common.UNDERLINE, tokenKeyParts);
        r = baseClient.getToken(tokenRedisKey);
        if (!r.done()) {
            LogUtil.error(HttpTraceGlobalFilter.class, LocalDateTime.now() + " 令牌查询失败");
            return response.writeWith(Mono.just(handleResponse(response, SysExpEnum.CONNECT_OR_OVERTIME_ERROR)));
        }
        Token tokenRedis = (Token) r.getData();

        /* 验证令牌缓存情况 */
        if (!StringUtils.isNoneEmpty(tokenRedis.getValue())) {
            LogUtil.error(HttpTraceGlobalFilter.class, LocalDateTime.now() + " 令牌缓存缺失");
            return response.writeWith(Mono.just(handleResponse(response, SysExpEnum.NEED_LOGIN)));
        }

        /* 查看剩余有效时间 */
        long time = tokenRedis.getTime();
        if (time < 1) {
            LogUtil.error(HttpTraceGlobalFilter.class, LocalDateTime.now() + " 令牌缓存失效");
            return response.writeWith(Mono.just(handleResponse(response, SysExpEnum.LOGIN_AGAIN)));
        }

        /* 比对redis内令牌和传入令牌是否一致，防止劫持前一次有效令牌做操作 */
        if (SysParams.Jwt.ANTI_HIJACK) {
            if (tokenRedis.getValue().compareTo(token) != 0) {
                LogUtil.error(HttpTraceGlobalFilter.class, LocalDateTime.now() + " 令牌比对失败");
                return response.writeWith(Mono.just(handleResponse(response, SysExpEnum.REMOTE_LOGIN)));
            }
        }

        /* 令牌续权 */
        if (SysParams.Jwt.ENABLE_RENEW) {
            if (SysParams.Sys.CHANNEL_WEB.equals(channel)) {
                if (time < SysParams.Jwt.WEB_UPDATE_INTERVAL) {
                    r = baseClient.renewToken(tokenRedisKey, SysParams.Jwt.WEB_LIFE_CYCLE);
                }
            } else {
                if (time < SysParams.Jwt.APP_UPDATE_INTERVAL) {
                    r = baseClient.renewToken(tokenRedisKey, SysParams.Jwt.APP_LIFE_CYCLE);
                }
            }
            if (!r.done()) {
                return response.writeWith(Mono.just(handleResponse(response, SysExpEnum.CONNECT_OR_OVERTIME_ERROR)));
            }
        }

        /* 用户访问权限验证 */

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 异常response返回处理
     *
     * @param response response
     * @param expEnum  enum
     * @return dataBuffer
     */
    private DataBuffer handleResponse(ServerHttpResponse response, SysExpEnum expEnum) {
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.bufferFactory().wrap(CommonUtil.transResult(Result.error(expEnum)).getBytes());
    }
}