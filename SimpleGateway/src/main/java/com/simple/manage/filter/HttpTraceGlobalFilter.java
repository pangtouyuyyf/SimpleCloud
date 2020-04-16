package com.simple.manage.filter;

import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.util.CommonUtil;
import com.simple.manage.util.JwtUtil;
import com.simple.manage.util.LogUtil;
import com.simple.manage.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.net.URI;
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
public class HttpTraceGlobalFilter implements GlobalFilter, Ordered{
    private Logger logger = LoggerFactory.getLogger(HttpTraceGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        DataBuffer dataBuffer = null;

        String token =  request.getHeaders().getFirst(CommonUtil.TOKEN);
        Map<String, String> jwtMap = JwtUtil.parseJWT(token);

        /** 验证令牌合法性 **/
        if (jwtMap == null) {
            LogUtil.error(HttpTraceGlobalFilter.class, LocalDateTime.now() + " 令牌验证失败");
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//            DataBufferFactory dataBufferFactory = response.bufferFactory();
//            ObjectMapper objMapper = new ObjectMapper();
//            byte[] obj;
//            try {
//                obj = objMapper.writeValueAsBytes(response);
//                return exchange.getResponse().writeWith(Mono.just(obj).map(r -> dataBufferFactory.wrap(r)));
//            } catch (JsonProcessingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            obj = objMapper.writeValueAsBytes(response);
//            return exchange.getResponse().writeWith(Mono.just(ResultUtil.error(SysExpEnum.NEED_LOGIN)).map(r -> dataBufferFactory.wrap(r)));
            dataBuffer = response.bufferFactory().wrap(ResultUtil.error(SysExpEnum.NEED_LOGIN).toString().getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }

        /** 获取令牌中的用户、角色和登录渠道 **/
        String userId = jwtMap.get(CommonUtil.USER_ID);
        String channel = jwtMap.get(CommonUtil.CHANNEL);

        /** 验证令牌参数 **/
        if (StringUtils.isNoneEmpty(userId)
                || StringUtils.isNoneEmpty(channel)
                || !(CommonUtil.CHANNEL_WEB.equals(channel) || CommonUtil.CHANNEL_APP.equals(channel))) {
            LogUtil.error(HttpTraceGlobalFilter.class, LocalDateTime.now() + " 令牌参数有误");
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            dataBuffer = response.bufferFactory().wrap(ResultUtil.error(SysExpEnum.NEED_LOGIN).toString().getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }

        /** 获取服务器缓存令牌 **/
        List<String> tokenKeyParts = Arrays.asList(CommonUtil.TOKEN_PREFIX, userId, channel);
        String tokenRedisKey = String.join(CommonUtil.UNDERLINE, tokenKeyParts);
//        String tokenRedis = this.redisOperation.getStr(tokenRedisKey);



        URI originalRequestUrl = request.getURI();
        System.out.print("from:");
        System.out.print(originalRequestUrl.getHost());
        System.out.print(" >> to:");
        System.out.print(originalRequestUrl.getPath());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10;
    }
}