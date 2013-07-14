package com.ironw.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author trgoofi
 */
public class Order extends Entity {
  private String number;
  private List<OrderItem> items = new ArrayList<OrderItem>();
  private BigDecimal total = new BigDecimal(0);
  private Date createAt;

  public Order addItem(CartItem cartItem, Ware ware) {
    OrderItem orderItem = new OrderItem(cartItem, ware, this);
    this.total = this.total.add(orderItem.getTotal());
    this.items.add(orderItem);
    return this;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }
}
