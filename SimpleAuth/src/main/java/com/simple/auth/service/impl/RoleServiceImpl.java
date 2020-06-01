package com.simple.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simple.auth.dao.*;
import com.simple.auth.service.RoleService;
import com.simple.common.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description 角色服务接口实现类
 * Author chen
 * CreateTime 2018-08-02 15:01
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private RoleMenuDao roleMenuDao;

    @Resource
    private RoleRouteDao roleRouteDao;

    @Resource
    private RoleAccessDao roleAccessDao;

    /**
     * 添加或更新角色
     *
     * @param role
     * @return
     */
    public int addOrUpdRole(Map<String, Object> role) {
        int result = 0;
        Integer id = role.get("role_id") == null ? null : Integer.valueOf(role.get("role_id").toString());
        int count = this.roleDao.checkRole(id);
        if (count == 0) {
            //新增
            result = this.roleDao.addRole(role);
        } else if (count == 1) {
            //修改
            result = this.roleDao.updRole(role);
        } else {
        }
        return result;
    }

    /**
     * 查询角色
     *
     * @param roleId
     * @return
     */
    public Map<String, Object> queryRole(int roleId) {
        return this.roleDao.queryRole(roleId);
    }

    /**
     * 查询角色对象
     *
     * @param roleId
     * @return
     */
    public Role queryRoleEntity(int roleId) {
        return this.roleDao.queryRoleEntity(roleId);
    }

    /**
     * 查询角色列表
     *
     * @param params
     * @param page
     * @param size
     * @return
     */
    public PageInfo queryRoleList(Map<String, Object> params, int page, int size) {
        return PageHelper.startPage(page, size).doSelectPageInfo(() -> roleDao.queryRoleList(params));
    }

    /**
     * 逻辑删除角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    @Transactional
    public int delRole(int roleId, int userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("role_id", roleId);

        //删除关联
        this.userRoleDao.delUserRole(params);
        this.roleMenuDao.delRoleMenu(params);
        this.roleRouteDao.delRoleRoute(params);
        this.roleAccessDao.delRoleAccess(params);

        params.put("update_id", userId);
        params.put("update_time", LocalDateTime.now());
        params.put("is_delete", "1");

        return this.roleDao.delRole(params);
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Transactional
    public int delRoleForReal(int roleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("role_id", roleId);

        //删除关联
        this.userRoleDao.delUserRole(params);
        this.roleMenuDao.delRoleMenu(params);
        this.roleRouteDao.delRoleRoute(params);
        this.roleAccessDao.delRoleAccess(params);

        return this.roleDao.delRoleForReal(roleId);
    }

    /**
     * 查询当前用户登录角色主键集合
     *
     * @param param
     * @return
     */
    public List<Integer> queryCurUserRole(Map<String, Object> param) {
        return this.roleDao.queryCurUserRole(param);
    }
}
