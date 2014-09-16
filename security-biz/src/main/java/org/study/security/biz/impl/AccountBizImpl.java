package org.study.security.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.security.base.impl.BaseBizImpl;
import org.study.security.biz.AccountBiz;
import org.study.security.dao.AccountDao;
import org.study.security.entity.Account;
import org.study.security.util.Page;
import org.study.security.vo.AccountValueObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haoyuewen on 9/9/14.
 */
@Service
public class AccountBizImpl extends BaseBizImpl<Account, AccountValueObject, Integer> implements AccountBiz {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Page<Account> pagingObject(Page<Account> page, AccountValueObject valueObject) {
        return accountDao.paging(page, valueObject);
    }

    @Override
    public Account queryByName(String name) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("name", name);
        return accountDao.queryOne(condition);
    }
}
