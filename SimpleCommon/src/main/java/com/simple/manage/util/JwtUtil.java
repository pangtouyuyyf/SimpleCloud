package com.simple.manage.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.simple.manage.config.Configs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Description jwt工具类
 * Author chen
 * CreateTime 2020-04-15 19:48
 **/

public class JwtUtil {
    /**
     * 创建令牌
     *
     * @param userId  用户主键
     * @param channel 客户端渠道(app/web)
     * @return string
     */
    public static String createJWT(String userId, String channel) {
        String result = null;
        Date now = new Date();  //当前时间
        try {
            Algorithm algorithm = Algorithm.HMAC256(Configs.Jwt.BASE_64_SECRET);  //秘钥算法

            result = JWT.create()
                    .withIssuer(Configs.Jwt.ISSUER)                  //设置发行者
                    .withClaim(Configs.Sys.USER_ID, userId)         //设置参数
                    .withClaim(Configs.Sys.CHANNEL, channel)        //设置参数
                    .withNotBefore(now)                            //设置最早时间
                    .sign(algorithm);                              //签名加密
        } catch (Exception e) {
            LogUtil.error(JwtUtil.class, e.toString());
        }
        return result;
    }

    /**
     * 解析令牌
     *
     * @param token 令牌
     * @return map
     */
    public static Map<String, String> parseJWT(String token) {
        return Optional.ofNullable(token).map(
                t -> {
                    try {
                        // 判断token是否合法
                        Algorithm algorithm = Algorithm.HMAC256(Configs.Jwt.BASE_64_SECRET);
                        JWTVerifier verifier = JWT.require(algorithm).withIssuer(Configs.Jwt.ISSUER).build();
                        Map<String, Claim> map = verifier.verify(t).getClaims();
                        Map<String, String> resultMap = new HashMap<>();
                        map.forEach((k, v) -> resultMap.put(k, v.asString()));
                        return resultMap;
                    } catch (Exception e) {
                        LogUtil.error(JwtUtil.class, e.toString());
                        return null;
                    }
                }
        ).orElse(null);
    }
}