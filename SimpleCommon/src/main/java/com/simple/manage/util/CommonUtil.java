package com.simple.manage.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.manage.config.Configs;
import com.simple.manage.domain.Result;
import org.apache.commons.lang3.StringUtils;

/**
 * Description 公共工具类
 * Author chen
 * CreateTime 2020-04-09 19:56
 **/

public class CommonUtil {
    /**
     * url处理(去掉url参数)
     *
     * @param url url
     * @return string
     */
    public static String urlHandler(String url) {
        if (StringUtils.isNoneEmpty(url)) {
            return null;
        }
        if (url.contains(Configs.Common.SEMICOLON)) {
            url = url.substring(0, url.indexOf(Configs.Common.SEMICOLON));
        }
        if (url.contains(Configs.Common.QUESTION_MARK)) {
            url = url.substring(0, url.indexOf(Configs.Common.QUESTION_MARK));
        }
        return url;
    }

    /**
     * 结果转jackson
     *
     * @param r r
     * @return string
     */
    public static String transResult(Result<?> r) {
        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(r);
        } catch (JsonProcessingException e) {
            LogUtil.error(CommonUtil.class, "返回结果转化失败");
        }
        return result;
    }
}