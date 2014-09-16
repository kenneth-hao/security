package org.study.security.dao;

import org.study.security.base.BaseDao;
import org.study.security.entity.Resource;
import org.study.security.vo.ResourceValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/11/14.
 */
public interface ResourceDao extends BaseDao<Resource, ResourceValueObject, Integer> {

    List<Resource> queryByRoleId(Integer id);
}
