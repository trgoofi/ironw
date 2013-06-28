package com.ironw.repository.support;

import com.ironw.domain.Entity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author trgoofi
 */
public class MybatisRepoSupport extends SqlSessionDaoSupport {

  @Override
  @Autowired
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    super.setSqlSessionFactory(sqlSessionFactory);
  }

  protected <T extends Entity> String statementOf(T entityForNamespace, String name) {
    return statementOf(entityForNamespace.getClass(), name);
  }

  protected String statementOf(Class<? extends Entity> entityClassForNamespace, String name) {
    String namespace = entityClassForNamespace.getName();
    return namespace + "." + name;
  }
}
