package org.study.security.dao.implbatis;

import org.springframework.stereotype.Repository;
import org.study.security.base.impl.BaseDaoImplBatis;
import org.study.security.dao.ResourceDao;
import org.study.security.entity.Resource;
import org.study.security.vo.ResourceValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/11/14.
 */
@Repository("resourceDaoImplBatis")
public class ResourceDaoImplBatis extends BaseDaoImplBatis<Resource, ResourceValueObject, Integer> implements ResourceDao {

    @Override
    public List<Resource> queryByRoleId(Integer id) {
        return getSqlSession().selectList(getStatement("queryByRoleId"), id);
    }
}
