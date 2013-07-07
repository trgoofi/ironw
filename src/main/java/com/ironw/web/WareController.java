package com.ironw.web;

import com.ironw.domain.Page;
import com.ironw.domain.Ware;
import com.ironw.service.WareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * @author trgoofi
 */
@Controller
public class WareController {
  @Inject WareService wareService;

  @RequestMapping(value = "ware/query/json", method = RequestMethod.GET)
  public @ResponseBody List<Ware> queryAsJson(String keyword, Page<Ware> page) {
    page = wareService.query(keyword, page);
    return page.getContent();
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() {
    return "index";
  }

  @RequestMapping(value = "ware/query", method = RequestMethod.GET)
  public String query(String keyword, Page<Ware> page, Model model) {
    page = wareService.query(keyword, page);
    model.addAttribute("page", page);
    return "ware";
  }

  @RequestMapping(value = "ware/{id}/json", method = RequestMethod.GET)
  public @ResponseBody Ware getWareAsJson(@PathVariable String id) {
    return wareService.getWare(id);
  }
}
