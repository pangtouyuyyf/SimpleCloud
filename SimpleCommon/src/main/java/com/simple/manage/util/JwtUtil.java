package com.simple.manage.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.simple.manage.config.JwtConfig;

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
     * 解析令牌
     *
     * @param token 令牌
     * @return
     */
    public static Map<String, String> parseJWT(String token) {
        return Optional.ofNullable(token).map(
                t -> {
                    try {
                        // 判断token是否合法
                        Algorithm algorithm = Algorithm.HMAC256(JwtConfig.BASE_64_SECRET);
                        JWTVerifier verifier = JWT.require(algorithm).withIssuer(JwtConfig.ISSUER).build();
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