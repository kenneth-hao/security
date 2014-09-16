package org.study.security.dao.implbatis;


import org.junit.Test;
import org.springframework.util.Assert;
import org.study.security.base.BaseTest;
import org.study.security.dao.ResourceDao;
import org.study.security.entity.Resource;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.List;

/**
 * Created by haoyuewen on 9/6/14.
 */
@DataSet
public class ResourceDaoImplBatisTest extends BaseTest {

    @SpringBeanByType
    ResourceDao resourceDao;

    @Test
    public void findByIdTest() {
        Integer resourceId = 1000;
        Resource resource = resourceDao.queryById(resourceId);
        Assert.notNull(resource);
    }

    @Test
    public void queryByRoleIdTest() {
        Integer roleId = 1000;
        List<Resource> resourceList = resourceDao.queryByRoleId(roleId);
        Assert.notEmpty(resourceList);
    }
}
