package com.simple.manage.config;

import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;

/**
 * Description url匹配配置
 * Author chen
 * CreateTime 2020-04-26 10:12
 **/

public class UrlMatchConfig {
    /* 无需token验证的url */
    private static List<String> URL_ABS_PERMIT = Arrays.asList("/simple-auth/sys/login", "");

    /* ant匹配 */
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    public static boolean isIgnoreToken(String path) {
        return URL_ABS_PERMIT.stream().anyMatch((url) -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }
}