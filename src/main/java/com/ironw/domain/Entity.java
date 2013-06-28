package com.ironw.domain;

import java.io.Serializable;

/**
 * @author trgoofi
 */
public abstract class Entity implements Serializable {
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
