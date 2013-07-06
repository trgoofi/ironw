package com.ironw.domain;

import java.util.Collections;
import java.util.List;

/**
 * @author trgoofi
 */
public class Cart {
  private List<CartItem> items;

  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }
}
