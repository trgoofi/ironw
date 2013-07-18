package com.ironw.service;

import com.ironw.domain.Cart;
import com.ironw.domain.Order;
import com.ironw.domain.Page;

/**
 * @author trgoofi
 */
public interface OrderService {
  Order cartToOrder(Cart cart);

  Order confirm(Order order);

  byte[] confirmAndCreatePdf(Order order);

  Page<Order> query(Page<Order> page);
}
