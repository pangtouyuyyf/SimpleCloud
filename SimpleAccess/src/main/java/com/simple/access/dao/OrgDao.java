package com.simple.access.dao;

import com.simple.access.entity.Org;
import com.simple.access.entity.Tree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Description 组织数据操作
 * Author chen
 * CreateTime 2018-12-18 11:03
 **/
@Mapper
public interface OrgDao {
    /**
     * 检查数据是否存在
     *
     * @param orgId
     * @return
     */
    int checkOrg(Integer orgId);

    /**
     * 添加组织
     *
     * @param org
     * @return
     */
    int addOrg(Map<String, Object> org);

    /**
     * 更新组织
     *
     * @param org
     * @return
     */
    int updOrg(Map<String, Object> org);

    /**
     * 查询信息
     *
     * @param orgId
     * @return
     */
    Map<String, Object> queryOrg(int orgId);

    /**
     * 查询组织对象
     *
     * @param orgId
     * @return
     */
    Org queryOrgEntity(int orgId);

    /**
     * 查询组织列表
     *
     * @param param
     * @return
     */
    List<Map<String, Object>> queryOrgList(Map<String, Object> param);

    /**
     * 递归查询组织树
     *
     * @param param
     * @return
     */
    List<Tree> queryOrgRecursion(Map<String, Object> param);

    /**
     * 查询组织树根主键
     *
     * @param param
     * @return
     */
    int queryRootOrgId(Map<String, Object> param);

    /**
     * 根据登录用户查询组织列表
     *
     * @param loginName
     * @return
     */
    List<Map<String, Object>> queryOrgListByLoginName(String loginName);

    /**
     * 查询已选择的组织
     *
     * @param param
     * @return
     */
    List<Map<String, Object>> querySelectedOrg(Map<String, Object> param);
}
