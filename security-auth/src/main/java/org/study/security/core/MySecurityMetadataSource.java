package org.study.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.study.security.biz.ResourceBiz;
import org.study.security.entity.Resource;

import java.util.*;

/**
 * Created by haoyuewen on 9/11/14.
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceBiz resourceBiz;

    private static Map<String, Collection<ConfigAttribute>> resourceMap;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation)o).getRequestUrl();

        if (resourceMap == null) {
            loadResourceDefine();
        }
        Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);

        return configAttributes;
    }

    private void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Resource> menus = resourceBiz.queryMenus();
            for (Resource menu : menus) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + menu.getKey());
                configAttributes.add(configAttribute);
                resourceMap.put(menu.getUrl(), configAttributes);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
