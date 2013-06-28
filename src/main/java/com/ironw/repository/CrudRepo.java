package com.ironw.repository;


import com.ironw.domain.Entity;

/**
 * @author trgoofi
 */
public interface CrudRepo {
  <T extends Entity> void insert(T entity);

  <T extends Entity> void update(T entity);

  <T extends Entity> T findBy(String id, Class<T> entityClass);
}
