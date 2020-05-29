package com.simple.base.component;

import com.simple.common.config.SysParams;
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
     * @param nameSpace nameSpace
     * @param key       key
     * @return string
     */
    public String getStr(String nameSpace, String key) {
        return stringRedisTemplate.opsForValue().get(getKey(nameSpace, key));
    }

    /**
     * String类型存入redis
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @param value     value
     */
    public void setStr(String nameSpace, String key, String value) {
        stringRedisTemplate.opsForValue().set(getKey(nameSpace, key), value);
    }

    /**
     * String类型存入redis并设置有效期
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @param value     value
     */
    public void setStr(String nameSpace, String key, String value, long time) {
        setStr(nameSpace, key, value);
        expireStr(nameSpace, key, time);
    }

    /**
     * 指定string类型缓存失效时间
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @param time      时间(秒)
     * @return
     */
    public boolean expireStr(String nameSpace, String key, long time) {
        if (time > 0) {
            stringRedisTemplate.expire(getKey(nameSpace, key), time, TimeUnit.SECONDS);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取过期剩余时间
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @return long
     */
    public long getStrExpire(String nameSpace, String key) {
        return stringRedisTemplate.getExpire(getKey(nameSpace, key), TimeUnit.SECONDS);
    }

    /**
     * 从redis中取出对象信息
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @return obj
     */
    public Object getObj(String nameSpace, String key) {
        return redisTemplate.opsForValue().get(getKey(nameSpace, key));
    }

    /**
     * 将对象信息放入redis
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @param obj       obj
     */
    public void setObj(String nameSpace, String key, Object obj) {
        redisTemplate.opsForValue().set(getKey(nameSpace, key), obj);
    }

    /**
     * 将对象信息放入redis并设置有效期
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @param obj       obj
     */
    public void setObj(String nameSpace, String key, Object obj, long time) {
        setObj(nameSpace, key, obj);
        expireObj(nameSpace, key, time);
    }


    /**
     * 指定Obj类型缓存失效时间
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @param time      时间(秒)
     * @return boolean
     */
    public boolean expireObj(String nameSpace, String key, long time) {
        if (time > 0) {
            redisTemplate.expire(getKey(nameSpace, key), time, TimeUnit.SECONDS);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取过期剩余时间
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @return long
     */
    public long getObjExpire(String nameSpace, String key) {
        return redisTemplate.getExpire(getKey(nameSpace, key), TimeUnit.SECONDS);
    }

    /**
     * 清除redis string缓存
     *
     * @param nameSpace 命名空间
     * @param key       键
     */
    public void deleteStr(String nameSpace, String key) {
        stringRedisTemplate.delete(getKey(nameSpace, key));
    }

    /**
     * 清除redis obj缓存
     *
     * @param nameSpace 命名空间
     * @param key       键
     */
    public void deleteObj(String nameSpace, String key) {
        redisTemplate.delete(getKey(nameSpace, key));
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

    /**
     * 获取真实key
     *
     * @param nameSpace 命名空间
     * @param key       键
     * @return
     */
    private String getKey(String nameSpace, String key) {
        return nameSpace + SysParams.Common.COLON + key;
    }
}