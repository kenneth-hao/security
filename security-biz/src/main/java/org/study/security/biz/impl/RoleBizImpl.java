package org.study.security.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.security.base.impl.BaseBizImpl;
import org.study.security.biz.RoleBiz;
import org.study.security.dao.RoleDao;
import org.study.security.entity.Role;
import org.study.security.vo.RoleValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/9/14.
 */
@Service
public class RoleBizImpl extends BaseBizImpl<Role, RoleValueObject, Integer> implements RoleBiz {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> queryByAccountId(Integer id) {
        return roleDao.queryByAccountId(id);
    }

    @Override
    public List<Role> queryByResourceId(Integer id) {
        return roleDao.queryByResourceId(id);
    }

    @Override
    public List<Role> queryAll() {
        return roleDao.queryAll();
    }

}
