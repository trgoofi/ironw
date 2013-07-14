package com.ironw.domain;

import java.util.Date;

/**
 * @author trgoofi
 */
public class Sequence extends Entity {
  private String name;
  private int value;
  private Date date;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int incrementAndGet() {
    return (++value);
  }

  public int resetAndGet() {
    return (value = 1);
  }
}
