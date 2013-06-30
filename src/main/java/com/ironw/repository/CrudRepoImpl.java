package com.ironw.repository;

import com.ironw.domain.Entity;
import com.ironw.domain.Page;
import com.ironw.repository.support.MybatisRepoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author trgoofi
 */
@Repository
public class CrudRepoImpl extends MybatisRepoSupport implements CrudRepo {

  private <T extends Entity> void checkEntityNotNull(T entity) {
    Assert.notNull(entity, "entityClass must not null!");
  }

  @Override
  public <T extends Entity> void insert(T entity) {
    checkEntityNotNull(entity);
    getSqlSession().insert(statementOf(entity, "insert"), entity);
  }

  @Override
  public <T extends Entity> void update(T entity) {
    checkEntityNotNull(entity);
    getSqlSession().update(statementOf(entity, "update"), entity);
  }

  @Override
  public <T extends Entity> T findBy(String id, Class<T> entityClass) {
    Assert.hasText(id, "id must not empty!");
    T entity = getSqlSession().selectOne(statementOf(entityClass, "findBy"), id);
    return entity;
  }

  @Override
  public <T extends Entity> Page<T> findPage(Page<T> page, Class<T> entityClass) {
    List<T> result = getSqlSession().selectList(statementOf(entityClass, "findPage"), page);
    page.setContent(result);
    return page;
  }
}
