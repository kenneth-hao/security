package org.study.security.base;

import org.study.security.util.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by haoyuewen on 8/31/14.
 */
public interface BaseDao<E, VO, PK extends Serializable> {

    Integer insert(E entity);

    Integer update(E entity);

    Integer delete(PK id);

    E queryById(PK id);

    List<E> queryAll();

    Long count(Map<String, Object> condition);

    List<E> query(Map<String, Object> condition);

    E queryOne(Map<String, Object> condition);

    Page<E> paging(Page<E> page, VO valueObject);
}
