package org.study.security.biz;

import org.study.security.base.BaseBiz;
import org.study.security.entity.Resource;
import org.study.security.vo.ResourceValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/11/14.
 */
public interface ResourceBiz extends BaseBiz<Resource, Integer, ResourceValueObject> {

    List<Resource> queryAll();

    List<Resource> queryByRoleId(Integer id);
}
