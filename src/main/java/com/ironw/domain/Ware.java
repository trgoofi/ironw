package com.ironw.domain;

import java.math.BigDecimal;

/**
 * @author trgoofi
 */
public class Ware extends Entity {
  private String code;
  private String name;
  private String units;
  private BigDecimal inventory;
  private Price price;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  public BigDecimal getInventory() {
    return inventory;
  }

  public void setInventory(BigDecimal inventory) {
    this.inventory = inventory;
  }

  public Price getPrice() {
    return price;
  }

  public void setPrice(Price price) {
    this.price = price;
  }
}
