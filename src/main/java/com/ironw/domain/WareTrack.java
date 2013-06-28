package com.ironw.domain;

import java.util.Date;

/**
 * @author trgoofi
 */
public class WareTrack extends Entity {
  private Ware ware;
  private Date stockAt;
  private String supplier;

  public Ware getWare() {
    return ware;
  }

  public void setWare(Ware ware) {
    this.ware = ware;
  }

  public Date getStockAt() {
    return stockAt;
  }

  public void setStockAt(Date stockAt) {
    this.stockAt = stockAt;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }
}
