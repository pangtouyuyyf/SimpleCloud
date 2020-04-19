package com.simple.manage.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description redis 操作组件
 * Author chen
 * CreateTime 2020-04-19 10:40
 **/
@Component
public class RedisOperation {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    protected RedisTemplate redisTemplate;

    /**
     * 根据key获取String类型
     *
     * @param key key
     * @return string
     */
    public String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * String类型存入redis
     *
     * @param key   key
     * @param value value
     */
    public void setStr(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * String类型存入redis并设置有效期
     *
     * @param key   key
     * @param value value
     */
    public void setStr(String key, String value, long time) {
        setStr(key, value);
        expireStr(key, time);
    }

    /**
     * 指定string类型缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expireStr(String key, long time) {
        if (time > 0) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取过期剩余时间
     *
     * @param key key
     * @return long
     */
    public long getStrExpire(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 从redis中取出对象信息
     *
     * @param key key
     * @return obj
     */
    public Object getObj(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 将对象信息放入redis
     *
     * @param key key
     * @param obj obj
     */
    public void setObj(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }

    /**
     * 将对象信息放入redis并设置有效期
     *
     * @param key key
     * @param obj obj
     */
    public void setObj(String key, Object obj, long time) {
        setObj(key, obj);
        expireObj(key, time);
    }


    /**
     * 指定Obj类型缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return boolean
     */
    public boolean expireObj(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取过期剩余时间
     *
     * @param key key
     * @return long
     */
    public long getObjExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 清除redis string缓存
     *
     * @param key key
     */
    public void deleteStr(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 清除redis obj缓存
     *
     * @param key key
     */
    public void deleteObj(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量清除redis obj缓存
     *
     * @param regex regex
     */
    public void deleteBatch(String regex) {
        Set<String> keys = redisTemplate.keys(regex);
        redisTemplate.delete(keys);
    }
}