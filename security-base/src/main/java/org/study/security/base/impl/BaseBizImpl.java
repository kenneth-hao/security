package org.study.security.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.study.security.base.BaseBiz;
import org.study.security.base.BaseDao;
import org.study.security.util.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haoyuewen on 9/9/14.
 */
public class BaseBizImpl<E, VO, PK extends Serializable> implements BaseBiz<E, VO, PK> {

    public static final String CONDITION_KEY_PAGE = "page";

    public static final String CONDITION_KEY_VALUE_OBJECT = "vo";

    @Autowired
    private BaseDao<E, VO, PK> baseDao;

    @Override
    public Page<E> pagingObject(Page<E> page, VO valueObject) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(CONDITION_KEY_PAGE, page);
        condition.put(CONDITION_KEY_VALUE_OBJECT, valueObject);
        page.setAmountObject(baseDao.count(condition));
        page.setListObject(baseDao.query(condition));
        return page;
    }

}
