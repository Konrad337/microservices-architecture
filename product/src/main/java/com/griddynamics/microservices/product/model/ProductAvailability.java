package com.griddynamics.microservices.product.model;

import java.util.Objects;

public class ProductAvailability {

  private String uniqId;
  private boolean isAvailable;

  public String getUniqId() {
    return uniqId;
  }

  public void setUniqId(String uniqId) {
    this.uniqId = uniqId;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProductAvailability)) {
      return false;
    }
    ProductAvailability that = (ProductAvailability) o;
    return isAvailable() == that.isAvailable() &&
        Objects.equals(getUniqId(), that.getUniqId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUniqId(), isAvailable());
  }
}
