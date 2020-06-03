package com.simple.auth.service;


import com.simple.access.entity.User;

import java.util.Map;

/**
 * Description 用户信息服务接口
 * Author chen
 * CreateTime 2020-04-20 22:09
 **/

public interface UserService {
    /**
     * 查询用户对象
     *
     * @param params
     * @return
     */
    User queryUserEntity(Map<String, Object> params);
}
