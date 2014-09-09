package org.study.security.base.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.study.security.base.BaseDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by haoyuewen on 9/9/14.
 */
@Repository
public class BaseDaoImplBatis<E, VO, PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<E, VO, PK> {

    private Class<E> entityClass;

    // <E>Mapper.xml 文件中对应的 sqlId
    private final static String SQLID_INSERT = "insert";
    private final static String SQLID_UPDATE = "update";
    private final static String SQLID_DELETE = "delete";
    private final static String SQLID_QUERY_BY_ID = "queryById";
    private final static String SQLID_QUERY_ALL = "queryAll";
    private final static String SQLID_COUNT = "count";
    private final static String SQLID_QUERY = "query";

    public BaseDaoImplBatis()  {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        entityClass = (Class<E>) params[0];
    }

    @Override
    public Integer insert(E entity) {
        return getSqlSession().insert(getStatement(SQLID_INSERT), entity);
    }

    @Override
    public Integer update(E entity) {
        return getSqlSession().update(getStatement(SQLID_UPDATE), entity);
    }

    @Override
    public Integer delete(PK id) {
        return getSqlSession().delete(getStatement(SQLID_DELETE), id);
    }

    @Override
    public E queryById(PK id) {
        return getSqlSession().selectOne(getStatement(SQLID_QUERY_BY_ID), id);
    }

    @Override
    public List<E> queryAll() {
        return getSqlSession().selectList(getStatement(SQLID_QUERY_ALL));
    }

    @Override
    public Long count(Map<String, Object> condition) {
        return getSqlSession().selectOne(getStatement(SQLID_COUNT), condition);
    }

    @Override
    public List<E> query(Map<String, Object> condition) {
        return getSqlSession().selectList(getStatement(SQLID_QUERY), condition);
    }

    protected String getStatement(String sqlId) {
        return getNamespace() + C_NAMESPACE_SEPARATOR + sqlId;
    }

    private String getNamespace() {
        return entityClass.getName() + C_NAMESPACE_SUFFIX;
    }

    private static final String C_NAMESPACE_SUFFIX = "DAO";

    private static final String C_NAMESPACE_SEPARATOR = ".";
}
