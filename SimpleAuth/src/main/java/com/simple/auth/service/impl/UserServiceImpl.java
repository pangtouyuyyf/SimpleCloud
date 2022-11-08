package com.simple.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simple.auth.dao.UserDao;
import com.simple.auth.entity.User;
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
     * 检查数据是否存在
     *
     * @param params
     * @return
     */
    public int checkUser(Map<String, Object> params) {
        return userDao.checkUser(params);
    }

    /**
     * 添加更新用户
     *
     * @param user
     * @return
     */
    public int mergeUser(User user) {
        return userDao.mergeUser(user);
    }

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    public User queryUser(Long userId) {
        return userDao.queryUser(userId);
    }

    /**
     * 查询用户列表
     *
     * @param params
     * @param page
     * @param size
     * @return
     */
    public PageInfo queryUserList(Map<String, Object> params, int page, int size) {
        return PageHelper.startPage(page, size).doSelectPageInfo(() -> userDao.queryUserList(params));
    }

    /**
     * 删除用户
     *
     * @param params
     * @return
     */
    public int deleteUser(Map<String, Object> params) {
        return userDao.deleteUser(params);
    }
}