package com.ironw.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {
  @RequestMapping(method = RequestMethod.GET)
  public String hello(Model model) {
    model.addAttribute("message", "Hello world!");
    return "hello";
  }
}