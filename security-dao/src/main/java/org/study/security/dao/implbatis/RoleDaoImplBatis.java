package org.study.security.dao.implbatis;

import org.springframework.stereotype.Repository;
import org.study.security.base.impl.BaseDaoImplBatis;
import org.study.security.dao.RoleDao;
import org.study.security.entity.Role;
import org.study.security.vo.RoleValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/9/14.
 */
@Repository("roleDaoImplBatis")
public class RoleDaoImplBatis extends BaseDaoImplBatis<Role, RoleValueObject, Integer> implements RoleDao {
    @Override
    public List<Role> queryByAccountId(Integer id) {
        return getSqlSession().selectList(getStatement("queryByAccountId"), id);
    }

    @Override
    public List<Role> queryByResourceId(Integer id) {
        return getSqlSession().selectList(getStatement("queryByResourceId"), id);
    }
}
