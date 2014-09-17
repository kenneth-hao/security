package org.study.security.base.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.security.base.BaseDao;
import org.study.security.constant.BaseDaoConstant;
import org.study.security.util.Page;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoyuewen on 9/9/14.
 */
public class BaseDaoImplBatis<E, VO, PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<E, VO, PK> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDaoImplBatis.class);

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    private Class<E> entityClass;

    // <E>Mapper.xml 文件中对应的 sqlId
    private final static String SQLID_INSERT = "insert";
    private final static String SQLID_UPDATE = "update";
    private final static String SQLID_DELETE = "delete";
    private final static String SQLID_QUERY_BY_ID = "queryById";
    private final static String SQLID_QUERY_ALL = "queryAll";
    private final static String SQLID_COUNT = "count";
    private final static String SQLID_QUERY = "query";
    private final static String SQLID_QUERY_ONE = "queryOne";

    @SuppressWarnings(value = "unchecked")
    public BaseDaoImplBatis()  {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        entityClass = (Class<E>) params[0];
        logger.info(String.format("加载泛型参数实体类型[%s]", entityClass.getName()));
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

    public E queryOne(Map<String, Object> condition) {
        return getSqlSession().selectOne(getStatement(SQLID_QUERY_ONE), condition);
    }

    @Override
    public Page<E> paging(Page<E> page, VO valueObject) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(BaseDaoConstant.CONDITION_KEY_PAGE, page);
        condition.put(BaseDaoConstant.CONDITION_KEY_VALUE_OBJECT, valueObject);
        page.setAmountObject(count(condition));
        page.setListObject(query(condition));
        return page;
    }

    protected String getStatement(String sqlId) {
        return getNamespace() + C_NAMESPACE_SEPARATOR + sqlId;
    }

    private String getNamespace() {
        return this.getClass().getInterfaces()[0].getName() + C_NAMESPACE_SUFFIX;
    }

    private static final String C_NAMESPACE_SUFFIX = "";

    private static final String C_NAMESPACE_SEPARATOR = ".";
}
