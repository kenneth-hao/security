package org.study.security.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by haoyuewen on 9/17/14.
 */
@Service("myAccessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {

    private static final Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        logger.info("访问决策管理器, 开始决策用户是否拥有权限...");

        if (configAttributes == null) {
            return ;
        }

        Iterator<ConfigAttribute> it = configAttributes.iterator();
        while (it.hasNext()) {
            String needAttibute = it.next().getAttribute();

            if (false == MySecurityMetadataSource.SECURITY_CONFIG_FLAG_DENY.equals(needAttibute)) {
                for (GrantedAuthority ga : authentication.getAuthorities()) {
                    if (needAttibute.equals(ga.getAuthority())) {
                        return ;
                    }
                }
            }
        }

        logger.info("用户没有权限!");
        throw new AccessDeniedException("权限验证失败!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        logger.info(String.format("访问决策管理器, 决策属性[%s]...", configAttribute.getAttribute()));
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
