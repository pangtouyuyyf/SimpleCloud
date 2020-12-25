package com.simple.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.common.config.SysParams;
import com.simple.common.domain.Result;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

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
        if (url.contains(SysParams.Common.SEMICOLON)) {
            url = url.substring(0, url.indexOf(SysParams.Common.SEMICOLON));
        }
        if (url.contains(SysParams.Common.QUESTION_MARK)) {
            url = url.substring(0, url.indexOf(SysParams.Common.QUESTION_MARK));
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

    /**
     * 获取最小基数
     *
     * @param min
     * @param max
     * @param ratio
     * @return
     */
    public static String getMinBase(Double min, Double max, Double ratio) {
        // 获取极差
        BigDecimal range = new BigDecimal(max.toString()).subtract(new BigDecimal(min.toString()));
        int rangeY = (int) Math.floor(range.doubleValue());
        int space = getBlockNum(10, String.valueOf(rangeY).length() - 1);

        BigDecimal m = new BigDecimal(Double.toString(Math.abs(min)));
        BigDecimal y = m.multiply(new BigDecimal(ratio.toString()));
        int intY = (int) Math.floor(y.doubleValue());
        BigDecimal len = m.subtract(new BigDecimal((intY / space) * space));
        BigDecimal r = new BigDecimal(min.toString()).subtract(len);
        if (r.compareTo(new BigDecimal(-1)) == 1 && r.compareTo(new BigDecimal(0)) == -1) {
            // >-1&&<0 这部分比较难求,需要单独处理
            BigDecimal divisor = BigDecimal.ONE;
            MathContext mc = new MathContext(2);
            return r.divide(divisor, mc).toString();
        }
        return Integer.toString(r.intValue());
    }

    /**
     * 获取m的n次方
     *
     * @param m
     * @param n
     * @return
     */
    public static int getBlockNum(int m, int n) {
        return (int) Math.pow(m, n);
    }

    /**
     * 获取显示时间列表
     *
     * @param space 间距(1:2:4)
     * @return
     */
    public static List<String> getTimeList(int space) {
        List<String> timeList = new ArrayList<>();
        int section = 4 / space;
        int len = 96 / space;
        for (int i = 0; i < len; i++) {
            timeList.add(getTime(i / section, (60 / section) * (i % section)));
        }

        return timeList;
    }

    /**
     * 获取时间
     *
     * @param hour
     * @param min
     * @return
     */
    public static String getTime(int hour, int min) {
        StringBuilder time = new StringBuilder();
        time.append(hour);
        time.append(":").append(min == 0 ? "00" : min);
        return time.toString();
    }
}