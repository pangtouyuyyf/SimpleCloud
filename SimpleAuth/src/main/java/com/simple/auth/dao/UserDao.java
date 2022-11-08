package com.simple.auth.dao;

import com.simple.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Description 用户数据操作dao
 * Author chen
 * CreateTime 2020-04-20 22:20
 **/
@Mapper
public interface UserDao {
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
    User queryUser(@Param("userId") Long userId);

    /**
     * 登录查询用户
     *
     * @param loginName
     * @param password
     * @return
     */
    User queryLoginUser(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 查询用户列表
     *
     * @param params
     * @return
     */
    List<User> queryUserList(Map<String, Object> params);

    /**
     * 删除用户
     *
     * @param params
     * @return
     */
    int deleteUser(Map<String, Object> params);
}
