package com.ironw.service;

import com.ironw.domain.Cart;
import com.ironw.domain.Order;

/**
 * @author trgoofi
 */
public interface OrderService {
  Order cartToOrder(Cart cart);

  Order confirm(Order order);

  byte[] confirmAndCreatePdf(Order order);
}
