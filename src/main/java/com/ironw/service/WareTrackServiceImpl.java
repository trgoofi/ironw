package com.ironw.service;

import com.ironw.domain.Ware;
import com.ironw.domain.WareTrack;
import com.ironw.repository.CrudRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author trgoofi
 */
@Service
public class WareTrackServiceImpl implements WareTrackService {
  @Inject private CrudRepo crudRepo;

  @Override
  @Transactional
  public void stock(WareTrack wareTrack) {
    Ware ware = wareTrack.getWare();
    if (StringUtils.isNotBlank(ware.getId())) {
      crudRepo.update(ware);
    } else {
      crudRepo.insert(ware);
    }
    crudRepo.insert(wareTrack);
  }
}
