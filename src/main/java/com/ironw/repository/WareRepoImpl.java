package com.ironw.repository;

import com.ironw.domain.Ware;
import com.ironw.repository.support.MybatisRepoSupport;
import org.springframework.stereotype.Repository;

/**
 * @author trgoofi
 */
@Repository
public class WareRepoImpl extends MybatisRepoSupport implements WareRepo {

  @Override
  public void updateInventory(Ware ware) {
    getSqlSession().update(statementOf(ware, "updateInventory"), ware);
  }
}
