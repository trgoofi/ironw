package com.ironw.web;

import com.ironw.domain.Cart;
import com.ironw.domain.Client;
import com.ironw.domain.Order;
import com.ironw.domain.Page;
import com.ironw.service.OrderService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
  public String confirm(Client client, HttpSession session) {
    Order order = (Order) session.getAttribute(ORDER_SESSION);
    order.setClient(client);
    orderService.confirm(order);
    session.removeAttribute(ORDER_SESSION);
    return "redirect:/";
  }

  @RequestMapping(value = "print.pdf", method = RequestMethod.POST)
  public HttpEntity<byte[]> print(Client client, HttpSession session) {
    Order order = (Order) session.getAttribute(ORDER_SESSION);
    order.setClient(client);

    byte[] pdf = orderService.confirmAndCreatePdf(order);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "pdf"));
    HttpEntity<byte[]> response = new HttpEntity<byte[]>(pdf, headers);

    session.removeAttribute(ORDER_SESSION);

    return response;
  }

  @RequestMapping(value = "cancel", method = RequestMethod.GET)
  public String cancel(HttpSession session) {
    session.removeAttribute(ORDER_SESSION);
    return "redirect:/";
  }

  @RequestMapping(value = "query", method = RequestMethod.GET)
  public String query(Page<Order> page, Model model) {
    page = orderService.query(page);
    model.addAttribute("page", page);
    return "orderList";
  }

}
