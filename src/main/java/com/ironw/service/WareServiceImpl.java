package com.ironw.service;

import com.ironw.domain.Page;
import com.ironw.domain.Ware;
import com.ironw.repository.CrudRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author trgoofi
 */
@Service
public class WareServiceImpl implements WareService {
  @Inject CrudRepo crudRepo;

  @Override
  public Ware getWare(String id) {
    return crudRepo.findBy(id, Ware.class);
  }

  @Override
  public Page<Ware> query(String keyword, Page<Ware> page) {
    page.param("keyword", keyword);
    return crudRepo.findPage(page, Ware.class);
  }
}
