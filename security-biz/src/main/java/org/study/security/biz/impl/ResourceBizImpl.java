package org.study.security.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.study.security.base.impl.BaseBizImpl;
import org.study.security.biz.ResourceBiz;
import org.study.security.dao.ResourceDao;
import org.study.security.entity.Resource;
import org.study.security.vo.ResourceValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/11/14.
 */
public class ResourceBizImpl extends BaseBizImpl<Resource, Integer, ResourceValueObject> implements ResourceBiz {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<Resource> queryMenus() {
        return resourceDao.queryAll();
    }

    @Override
    public List<Resource> queryByRoleId(Integer id) {
        return resourceDao.queryByRoleId(id);
    }
}
