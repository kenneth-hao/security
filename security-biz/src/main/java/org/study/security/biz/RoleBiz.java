package org.study.security.biz;

import org.study.security.base.BaseBiz;
import org.study.security.entity.Role;
import org.study.security.vo.RoleValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/9/14.
 */
public interface RoleBiz extends BaseBiz<Role, RoleValueObject, Integer> {

    List<Role> queryByAccountId(Integer id);

    List<Role> queryByResourceId(Integer id);

    List<Role> queryAll();

}
