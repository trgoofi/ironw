package com.ironw.service;

import com.ironw.domain.Page;
import com.ironw.domain.Ware;

/**
 * @author trgoofi
 */
public interface WareService {
  Page<Ware> query(String keyword, Page<Ware> page);

  Ware getWare(String id);
}
