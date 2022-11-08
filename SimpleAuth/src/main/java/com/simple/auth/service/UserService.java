package com.simple.auth.service;


import com.github.pagehelper.PageInfo;
import com.simple.auth.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Description 用户信息服务接口
 * Author chen
 * CreateTime 2020-04-20 22:09
 **/

public interface UserService {
    /**
     * 检查数据是否存在
     *
     * @param params
     * @return
     */
    int checkUser(Map<String, Object> params);

    /**
     * 添加更新用户
     *
     * @param user
     * @return
     */
    int mergeUser(User user);

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    User queryUser(@NotNull Long userId);

    /**
     * 登录查询用户
     *
     * @param loginName
     * @param password
     * @return
     */
    User queryLoginUser(@NotBlank String loginName, @NotBlank String password);

    /**
     * 查询用户列表
     *
     * @param params
     * @param page
     * @param size
     * @return
     */
    PageInfo queryUserList(Map<String, Object> params, int page, int size);

    /**
     * 删除用户
     *
     * @param params
     * @return
     */
    int deleteUser(Map<String, Object> params);
}
