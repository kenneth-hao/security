package org.study.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.study.security.biz.AccountBiz;
import org.study.security.biz.RoleBiz;
import org.study.security.entity.Account;
import org.study.security.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * Created by haoyuewen on 9/16/14.
 */
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountBiz accountBiz;

    @Autowired
    private RoleBiz roleBiz;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountBiz.queryByName(s);
        return null;
    }

    private Set<GrantedAuthority> obtionGrantedAuthorities(Account account) {
        List<Role> roleList = roleBiz.queryByAccountId(account.getId());


        return null;
    }

}
