package com.griddynamics.microservices.inventory.model;

import java.util.Objects;
import java.util.Random;

public class ProductStatus {

  private String uniqId;

  private boolean isAvailable;

  public ProductStatus() {
    isAvailable = new Random().nextBoolean();
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public String getUniqId() {
    return uniqId;
  }

  public void setUniqId(String uniqId) {
    this.uniqId = uniqId;
  }

  @Override
  public String toString() {
    return "ProductStatus{" +
        "uniqId='" + uniqId + '\'' +
        ", isAvailable=" + isAvailable +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProductStatus)) {
      return false;
    }
    ProductStatus that = (ProductStatus) o;
    return isAvailable == that.isAvailable &&
        Objects.equals(uniqId, that.uniqId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uniqId, isAvailable);
  }
}
