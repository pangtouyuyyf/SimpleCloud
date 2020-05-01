package com.simple.manage.config;

/**
 * Description 系统配置
 * Author chen
 * CreateTime 2020-05-01 13:10
 **/

public class SysConfig {
    public static final boolean ENABLE_AUZ = true;  //是否开启权限验证

    public static final boolean SHOW_IP = true;  //是否显示IP

    public static final String DEFAULT_PWD = "123456";  //系统重置登录密码

    public static final boolean IS_CLEAN_LOGIN_INFO = true;  //退出登录是否清除当前登录信息缓存
}