package org.study.security.biz;

import org.study.security.base.BaseBiz;
import org.study.security.entity.Account;
import org.study.security.util.Page;
import org.study.security.vo.AccountValueObject;

/**
 * Created by haoyuewen on 9/9/14.
 */
public interface AccountBiz extends BaseBiz<Account, AccountValueObject, Integer> {

    Page<Account> pagingObject(Page<Account> page, AccountValueObject valueObject);

    Account queryByName(String name);

}
