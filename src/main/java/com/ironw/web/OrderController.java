package com.ironw.web;

import com.ironw.domain.Cart;
import com.ironw.domain.Order;
import com.ironw.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 * @author trgoofi
 */
@Controller
@RequestMapping("order")
public class OrderController {
  private static final String ORDER_SESSION = "order";

  @Inject OrderService orderService;

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String cartToOrder(Cart cart, Model model, HttpSession session) {
    Order order = orderService.cartToOrder(cart);
    session.setAttribute(ORDER_SESSION, order);
    return "order";
  }

  @RequestMapping(value = "confirm", method = RequestMethod.POST)
  public String confirm(HttpSession session) {
    Order order = (Order) session.getAttribute(ORDER_SESSION);
    orderService.confirm(order);
    session.removeAttribute(ORDER_SESSION);
    return "redirect:/";
  }

}
