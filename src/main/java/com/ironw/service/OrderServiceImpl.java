package com.ironw.service;

import com.ironw.domain.*;
import com.ironw.repository.CrudRepo;
import com.ironw.repository.WareRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author trgoofi
 */
@Service
public class OrderServiceImpl implements OrderService {
  @Inject CrudRepo crudRepo;
  @Inject WareRepo wareRepo;

  @Override
  public Order cartToOrder(Cart cart) {
    Order order = new Order();
    for (CartItem cartItem : cart.getItems()) {
      if (StringUtils.isNotBlank(cartItem.getId())) {
        Ware ware = crudRepo.findBy(cartItem.getId(), Ware.class);
        order.addItem(cartItem, ware);
      }
    }
    return order;
  }

  @Override
  @Transactional
  public Order confirm(Order order) {
    if (order == null || order.getItems().isEmpty()) {
      return order;
    }

    for (OrderItem orderItem : order.getItems()) {
      Ware ware = new Ware();
      ware.setId(orderItem.getWare().getId());
      ware.setInventory(orderItem.getQuantity());

      wareRepo.updateInventory(ware);
    }

    order.setCreateAt(new Date());

    crudRepo.insert(order);
    for (OrderItem orderItem : order.getItems()) {
      crudRepo.insert(orderItem);
    }

    return order;
  }
}
