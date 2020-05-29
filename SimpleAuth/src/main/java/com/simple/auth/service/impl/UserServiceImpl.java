package com.simple.auth.service.impl;

import com.simple.auth.dao.UserDao;
import com.simple.common.entity.User;
import com.simple.auth.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description 用户信息服务接口实现
 * Author chen
 * CreateTime 2020-04-20 22:09
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 查询用户对象
     *
     * @param params
     * @return
     */
    public User queryUserEntity(Map<String, Object> params) {
        return userDao.queryUserEntity(params);
    }
}