package com.ironw.domain;

import java.math.BigDecimal;

/**
 * @author trgoofi
 */
public class Price {
  private BigDecimal cost;
  private BigDecimal bottom;
  private BigDecimal wholesale;
  private BigDecimal retail;

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public BigDecimal getBottom() {
    return bottom;
  }

  public void setBottom(BigDecimal bottom) {
    this.bottom = bottom;
  }

  public BigDecimal getWholesale() {
    return wholesale;
  }

  public void setWholesale(BigDecimal wholesale) {
    this.wholesale = wholesale;
  }

  public BigDecimal getRetail() {
    return retail;
  }

  public void setRetail(BigDecimal retail) {
    this.retail = retail;
  }
}
