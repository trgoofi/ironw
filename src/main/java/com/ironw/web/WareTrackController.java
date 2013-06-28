package com.ironw.web;

import com.ironw.domain.WareTrack;
import com.ironw.service.WareTrackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author trgoofi
 */
@Controller
public class WareTrackController {

  @Inject private WareTrackService wareTrackService;

  @RequestMapping(value = "stock", method = RequestMethod.POST)
  public String stock(WareTrack wareTrack) {
    wareTrackService.stock(wareTrack);
    return null;
  }

}
