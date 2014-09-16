package org.study.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.security.base.BaseController;
import org.study.security.biz.AccountBiz;
import org.study.security.entity.Account;
import org.study.security.util.Page;
import org.study.security.vo.AccountValueObject;

/**
 * Created by haoyuewen on 9/7/14.
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountBiz accountBiz;

    @RequestMapping("/paging")
    @ResponseBody
    public String paging(AccountValueObject accountVo, Integer pageSize, Integer pagination) {
        Page<Account> page = accountBiz.pagingObject(new Page<Account>(pageSize, pagination), accountVo);
        return "[]";
    }
}
