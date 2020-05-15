package com.simple.manage.config;

/**
 * Description 系统参数信息
 * Author chen
 * CreateTime 2020-05-01 13:10
 **/

public class SysParams {
    /**
     * 通用参数
     */
    public class Common {
        /* 星号 */
        public static final String ASTERISK = "*";

        /* 是 */
        public static final String YES = "0";

        /* 否 */
        public static final String NO = "1";

        /* 下划线分隔符 */
        public static final String UNDERLINE = "_";

        /* 斜杠分隔符 */
        public static final String SLASH = "/";

        /* 反斜杠分隔符 */
        public static final String BACKSLASH = "\\";

        /* 问号分隔符 */
        public static final String QUESTION_MARK = "?";

        /* 分号分隔符 */
        public static final String SEMICOLON = ";";

        /* 冒号分隔符 */
        public static final String COLON = ":";

        /* 逗号 */
        public static final String COMMA = ",";

        /* 手机号码验证正则表达式 */
        public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    }

    /**
     * 系统参数
     */
    public class Sys {
        /* 令牌标识字段 */
        public static final String TOKEN = "token";

        /* 客户登录渠道 */
        public static final String CHANNEL = "channel";

        /* 客户web渠道登录 */
        public static final String CHANNEL_WEB = "web";

        /* 客户app渠道登录 */
        public static final String CHANNEL_APP = "app";

        /* 用户ID标识字段 */
        public static final String USER_ID = "id";

        /* 操作权限编码位数 */
        public static final int OPERATE_CODE_LENGTH = 6;

        /* 默认角色操作编码 */
        public static final int OPERATE_CODE_DEFAULT = 0;

        /* 默认树根节点父节点id */
        public static final int TREE_ROOT_PARENT_ID = -1;

        /* 默认树节点排序 */
        public static final int TREE_DEFAULT_ORDER = 1;


        /* 是否开启权限验证 */
        public static final boolean ENABLE_AUZ = true;

        /* 是否显示IP */
        public static final boolean SHOW_IP = true;

        /* 系统重置登录密码 */
        public static final String DEFAULT_PWD = "123456";

        /* 退出登录是否清除当前登录信息缓存 */
        public static final boolean IS_CLEAN_LOGIN_INFO = true;
    }

    /**
     * jwt参数
     */
    public class Jwt {
        /* jwt发行者 */
        public static final String ISSUER = "chendreaming@126.com";

        /* jwt基础64位秘钥 */
        public static final String BASE_64_SECRET = "VHVubmVsJTIwT2YlMjBMb3Zl";

        /* jwt web端作用时间:秒(这里为10天) */
        public static final int WEB_LIFE_CYCLE = 864000;

        /* jwt web更新间隔:秒(这里为1天) */
        public static final int WEB_UPDATE_INTERVAL = 86400;

        /* jwt app端作用时间:秒(这里为10天) */
        public static final int APP_LIFE_CYCLE = 864000;

        /* jwt app更新间隔:秒(这里为1天) */
        public static final int APP_UPDATE_INTERVAL = 86400;

        /* login info更新间隔:秒(这里为10天) */
        public static final int LOGIN_INFO_LIFE_CYCLE = 86400;

        /* 开启自动令牌续权 */
        public static final boolean ENABLE_RENEW = true;

        /* 开启令牌反劫持 */
        public static final boolean ANTI_HIJACK = true;
    }

    /**
     * redis参数
     */
    public class Redis {
        /* 令牌命名前缀 */
        public static final String TOKEN_PREFIX = "token";

        /* 登录信息命名前缀 */
        public static final String LOGIN_INFO_PREFIX = "loginInfo";

        /* 短信验证码后缀 */
        public static final String SMS_CODE_SUFFIX = "smsCode";

        /* 短信验证码redis主键前缀 */
        public static final String SMS_VERIFY_CODE_PREFIX = "verifyCode";

        /* 令牌命名空间 */
        public static final String TOKEN_NAMESPACE = "TOKEN";

        /* 登录信息命名空间 */
        public static final String LOGIN_INFO_NAMESPACE = "LOGIN_INFO";

        /* 短信验证码命名空间 */
        public static final String SMS_VERIFY_CODE_NAMESPACE = "SMS_VERIFY_CODE";
    }
}