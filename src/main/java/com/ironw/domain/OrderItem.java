package com.ironw.domain;

import java.math.BigDecimal;

/**
 * @author trgoofi
 */
public class OrderItem extends Entity {
  private Ware ware;
  private Order order;
  private BigDecimal price;
  private BigDecimal quantity;
  private BigDecimal total;

  public OrderItem() {}

  public OrderItem(CartItem cartItem, Ware ware, Order order) {
    this.ware = ware;
    this.order = order;
    this.price = cartItem.getPrice();
    this.quantity = cartItem.getQuantity();
    this.total = price.multiply(quantity);
  }

  public Ware getWare() {
    return ware;
  }

  public void setWare(Ware ware) {
    this.ware = ware;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}
