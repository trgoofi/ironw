package com.ironw.repository;


import com.ironw.domain.Entity;
import com.ironw.domain.Page;
import com.ironw.domain.Ware;

/**
 * @author trgoofi
 */
public interface CrudRepo {
  <T extends Entity> void insert(T entity);

  <T extends Entity> void update(T entity);

  <T extends Entity> T findBy(String id, Class<T> entityClass);

  <T extends Entity> Page<T> findPage(Page<T> page, Class<T> entityClass);
}
