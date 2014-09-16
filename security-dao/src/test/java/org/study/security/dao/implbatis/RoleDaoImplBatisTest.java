package org.study.security.dao.implbatis;


import org.junit.Test;
import org.springframework.util.Assert;
import org.study.security.base.BaseTest;
import org.study.security.dao.RoleDao;
import org.study.security.entity.Role;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.List;

/**
 * Created by haoyuewen on 9/6/14.
 */
@DataSet
public class RoleDaoImplBatisTest extends BaseTest {

    @SpringBeanByType
    RoleDao roleDao;

    @Test
    public void findByIdTest() {
        Integer roleId = 1000;
        Role role = roleDao.queryById(roleId);
        Assert.notNull(role);
    }

    @Test
    public void queryByAccountIdTest() {
        Integer accountId = 1000;
        List<Role> roleList = roleDao.queryByAccountId(accountId);
        Assert.notEmpty(roleList);
    }
}
