package com.ironw.web;

import com.ironw.domain.Page;
import com.ironw.domain.Ware;
import com.ironw.repository.CrudRepo;
import com.ironw.service.WareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author trgoofi
 */
@Controller
public class WareController {
  @Inject WareService wareService;

  @RequestMapping(value = "ware/query", method = RequestMethod.GET)
  public String query(String keyword, Page<Ware> page, Model model) {
    page = wareService.query(keyword, page);
    return null;
  }
}
