package com.simple.manage.config;

/**
 * Description jwt 配置信息
 * Author chen
 * CreateTime 2020-04-15 20:19
 **/

public class JwtConfig {
    public static final String ISSUER = "chendreaming@126.com";  //jwt发行者

    public static final String BASE_64_SECRET = "VHVubmVsJTIwT2YlMjBMb3Zl";  //jwt基础64位秘钥

    public static final int WEB_LIFE_CYCLE = 864000;  //jwt web端作用时间:秒(这里为10天)

    public static final int WEB_UPDATE_INTERVAL = 86400;  //jwt web更新间隔:秒(这里为1天)

    public static final int APP_LIFE_CYCLE = 864000;  //jwt app端作用时间:秒(这里为10天)

    public static final int APP_UPDATE_INTERVAL = 86400;  //jwt app更新间隔:秒(这里为1天)

    public static final int LOGIN_INFO_LIFE_CYCLE = 86400;  //login info更新间隔:秒(这里为10天)

    public static final boolean ENABLE_RENEW = true;  //开启自动令牌续权

    public static final boolean ANTI_HIJACK = true;  //开启令牌反劫持
}