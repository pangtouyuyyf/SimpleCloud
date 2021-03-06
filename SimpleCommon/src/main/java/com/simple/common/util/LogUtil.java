package com.simple.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description 日志工具类
 * Author chen
 * CreateTime 2020-04-05 11:08
 **/

public class LogUtil {
    /**
     * 错误
     *
     * @param clazz   clazz
     * @param message msg
     */
    public static void error(Class<?> clazz, String message) {
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isErrorEnabled()) {
            logger.error(message);
        }
    }

    /**
     * 错误
     *
     * @param clazz  clazz
     * @param format format
     * @param args   args
     */
    public static void error(Class<?> clazz, String format, Object... args) {
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isErrorEnabled()) {
            logger.error(format, args);
        }
    }

    /**
     * 警告
     *
     * @param clazz   clazz
     * @param message msg
     */
    public static void warn(Class<?> clazz, String message) {
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    /**
     * 警告
     *
     * @param clazz  clazz
     * @param format format
     * @param args   args
     */
    public static void warn(Class<?> clazz, String format, Object... args) {
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isWarnEnabled()) {
            logger.warn(format, args);
        }
    }

    /**
     * 提示
     *
     * @param clazz   clazz
     * @param message msg
     */
    public static void info(Class<?> clazz, String message) {
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    /**
     * 提示
     *
     * @param clazz  clazz
     * @param format format
     * @param args   args
     */
    public static void info(Class<?> clazz, String format, Object... args) {
        Logger logger = LoggerFactory.getLogger(clazz);
        if (logger.isInfoEnabled()) {
            logger.info(format, args);
        }
    }
}