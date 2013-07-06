package com.ironw.domain;

import java.math.BigDecimal;

/**
 * @author trgoofi
 */
public class CartItem {
  private String id;
  private BigDecimal price;
  private BigDecimal quantity;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
}
