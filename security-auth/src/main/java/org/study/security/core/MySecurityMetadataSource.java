package org.study.security.core;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.study.security.biz.ResourceBiz;
import org.study.security.biz.RoleBiz;
import org.study.security.entity.Resource;
import org.study.security.entity.Role;

import java.util.*;

/**
 * Created by haoyuewen on 9/11/14.
 */
@Service("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

    public static final String SECURITY_CONFIG_PREFIX_ROLE = "ROLE_";

    public static final String SECURITY_CONFIG_DENY_ACCESS = "DENY";

    public static final String SECURITY_CONFIG_FLAG_DENY = SECURITY_CONFIG_PREFIX_ROLE + SECURITY_CONFIG_DENY_ACCESS;

    private static final Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

    @Autowired
    private ResourceBiz resourceBiz;

    @Autowired
    private RoleBiz roleBiz;

    private AntPathMatcher urlMatcher = new AntPathMatcher();

    private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

    private static Collection<ConfigAttribute> allConfigAttributes = new ArrayList<ConfigAttribute>();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        String requestUrl = ((FilterInvocation)o).getRequestUrl();

        logger.info("请求的地址: " + requestUrl);

        Iterator<String> it = resourceMap.keySet().iterator();
        while (it.hasNext()) {
            String urlKey = it.next();
            if  (-1 != urlKey.indexOf("?")) {
                urlKey = urlKey.substring(0, urlKey.indexOf("?"));
            }

            if (urlMatcher.match(urlKey, requestUrl)) {
                return resourceMap.get(urlKey);
            }
        }

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadResourceDefine();
    }

    public void loadResourceDefine() {
        logger.info("开始加载权限资源定义...");

        List<Resource> resourceList = resourceBiz.queryAll();

        for (Resource re : resourceList) {
            Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();

            List<Role> roleList = roleBiz.queryByResourceId(re.getId());

            if (CollectionUtils.isEmpty(roleList)) {
                ConfigAttribute configAttribute = new SecurityConfig(SECURITY_CONFIG_FLAG_DENY);
                configAttributes.add(configAttribute);
                allConfigAttributes.add(configAttribute);
            } else {
                for (Role ro : roleList) {
                    ConfigAttribute configAttribute = new SecurityConfig(SECURITY_CONFIG_PREFIX_ROLE + ro.getKey());
                    configAttributes.add(configAttribute);
                    allConfigAttributes.add(configAttribute);
                }
            }

            resourceMap.put(re.getUrl(), configAttributes);
        }

        logger.info("权限资源加载完毕!");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return allConfigAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
