package org.study.security.dao.implbatis;


import org.junit.Assert;
import org.junit.Test;
import org.study.security.base.BaseTest;
import org.study.security.dao.AccountDao;
import org.study.security.entity.Account;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;

/**
 * Created by haoyuewen on 9/6/14.
 */
@DataSet
public class AccountDaoImplBatisTest extends BaseTest {

    @SpringBeanByType
    AccountDao accountDao;

    @Test
    public void findByIdTest() {
        Integer accountId = 1000;
        Account account = accountDao.queryById(accountId);
        Assert.assertNotNull(account);
        Assert.assertEquals(accountId, account.getId());
    }
}
