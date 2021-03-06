package com.simple.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simple.access.dao.AccessDao;
import com.simple.access.dao.RoleAccessDao;
import com.simple.auth.service.AccessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description 后台链接操作服务接口实现
 * Author chen
 * CreateTime 2018-09-06 17:10
 **/
@Service
public class AccessServiceImpl implements AccessService {
    @Resource
    private AccessDao accessDao;

    @Resource
    private RoleAccessDao roleAccessDao;

    /**
     * 添加或更新链接
     *
     * @param access
     * @return
     */
    public int addOrUpdAccess(Map<String, Object> access) {
        int result = 0;
        Integer id = access.get("access_id") == null ? null : Integer.valueOf(access.get("access_id").toString());
        int count = this.accessDao.checkAccess(id);
        if (count == 0) {
            //新增
            result = this.accessDao.addAccess(access);
        } else if (count == 1) {
            //修改
            result = this.accessDao.updAccess(access);
        } else {
        }
        return result;
    }

    /**
     * 查询链接
     *
     * @param accessId
     * @return
     */
    public Map<String, Object> queryAccess(int accessId) {
        return this.accessDao.queryAccess(accessId);
    }

    /**
     * 查询链接
     *
     * @param params
     * @param page
     * @param size
     * @return
     */
    public PageInfo queryAccessList(Map<String, Object> params, int page, int size) {
        return PageHelper.startPage(page, size).doSelectPageInfo(() -> accessDao.queryAccessList(params));
    }

    /**
     * 逻辑删除链接
     *
     * @param accessId
     * @param userId
     * @return
     */
    @Transactional
    public int delAccess(int accessId, int userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_id", accessId);

        //删除关联
        this.roleAccessDao.delRoleAccess(params);

        params.put("update_id", userId);
        params.put("update_time", LocalDateTime.now());
        params.put("is_delete", "1");

        return this.accessDao.delAccess(params);
    }

    /**
     * 删除链接
     *
     * @param accessId
     * @return
     */
    @Transactional
    public int delAccessForReal(int accessId) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_id", accessId);

        //删除关联
        this.roleAccessDao.delRoleAccess(params);

        return this.accessDao.delAccessForReal(accessId);
    }
}
