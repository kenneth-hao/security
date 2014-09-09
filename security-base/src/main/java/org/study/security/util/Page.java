package org.study.security.util;

import org.study.security.base.BaseValueObject;

import java.util.List;

/**
 * Created by haoyuewen on 9/9/14.
 */
public class Page<T> extends BaseValueObject {

    public static final Integer DEFAULT_PAGINATION = 1;

    public static final Integer DEFAULT_PAGE_SIZE = 15;

    // 本页的记录集合
    private List<T> listObject;

    // 总记录数
    private Long amountObject;

    // 每页显示记录
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    // 页码
    private Integer pagination = DEFAULT_PAGINATION;

    // 总页数
    private Integer amountPage;

    public Page() {
    }

    public Page(Integer pageSize, Integer pagination) {
        this.pageSize = pageSize;
        this.pagination = pagination;
    }

    public Page(List<T> listObject, Long amountObject, Integer pageSize, Integer pagination) {
        this.listObject = listObject;
        this.amountObject = amountObject;
        this.pageSize = pageSize;
        this.pagination = pagination;
        calcAmountPage();
    }

    private void calcAmountPage() {
        if (amountObject == null || pageSize == null || pageSize == 0) {
            return ;
        }
        Integer quotient = Integer.valueOf(Long.valueOf(amountObject / pageSize).intValue());
        this.amountPage = amountObject%pageSize == 0 ? quotient : quotient+1;
    }

    public void setListObject(List<T> listObject) {
        this.listObject = listObject;
    }

    public void setAmountObject(Long amountObject) {
        this.amountObject = amountObject;
        calcAmountPage();
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        calcAmountPage();
    }

    public void setPagination(Integer pagination) {
        this.pagination = pagination;
    }

    public List<T> getListObject() {
        return listObject;
    }

    public Long getAmountObject() {
        return amountObject;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPagination() {
        return pagination;
    }

    public Integer getAmountPage() {
        return amountPage;
    }
}
