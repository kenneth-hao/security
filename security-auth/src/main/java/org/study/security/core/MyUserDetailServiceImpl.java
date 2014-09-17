package org.study.security.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.study.security.biz.AccountBiz;
import org.study.security.biz.ResourceBiz;
import org.study.security.biz.RoleBiz;
import org.study.security.entity.Account;
import org.study.security.entity.Role;

import java.util.*;

/**
 * Created by haoyuewen on 9/16/14.
 */
@Service("myUserDetailServiceImpl")
public class MyUserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

    @Autowired
    private AccountBiz accountBiz;

    @Autowired
    private RoleBiz roleBiz;

    @Autowired
    private ResourceBiz resourceBiz;

    private Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountBiz.queryByName(s);
        if (account == null) {
            throw new UsernameNotFoundException("用户名未找到!");
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Set<GrantedAuthority> grantedAuthoritySet = obtionGrantedAuthorities(account);

        return new User(account.getName(), account.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                grantedAuthoritySet);
    }

    private Set<GrantedAuthority> obtionGrantedAuthorities(Account account) {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<GrantedAuthority>();

        if (account != null) {
            logger.info(String.format("开始加载用户[%s]权限...", account.getName()));
            List<Role> roleList = roleBiz.queryByAccountId(account.getId());
            for (Role ro : roleList) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(MySecurityMetadataSource.SECURITY_CONFIG_PREFIX_ROLE + ro.getKey());
                grantedAuthoritySet.add(grantedAuthority);
            }
            logger.info(String.format("用户[%s]的权限加载完毕!", account.getName()));
        }

        return grantedAuthoritySet;
    }

}
