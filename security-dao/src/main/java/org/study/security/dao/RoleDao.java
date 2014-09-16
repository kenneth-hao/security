package org.study.security.dao;

import org.study.security.base.BaseDao;
import org.study.security.entity.Role;
import org.study.security.vo.RoleValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/6/14.
 */
public interface RoleDao extends BaseDao<Role, RoleValueObject, Integer> {

    List<Role> queryByAccountId(Integer id);
}
