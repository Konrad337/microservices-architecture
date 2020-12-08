package com.griddynamics.microservices.inventory.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Inventory {

  private List<ProductStatus> products = new LinkedList<>();
  private Map<String, ProductStatus> productsById = new HashMap<>();

  public void addProductStatus(ProductStatus p) {
    products.add(p);
    String id = p.getUniqId();

    if(id != null) {
      productsById.put(id, p);
    }

  }

  public ProductStatus getProductStatusById(String id) {
    return productsById.get(id);
  }

  @Override
  public String toString() {
    return "Inventory{" +
        "products=" + products +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Inventory)) {
      return false;
    }
    Inventory inventory = (Inventory) o;
    return Objects.equals(products, inventory.products) &&
        Objects.equals(productsById, inventory.productsById);
  }

  @Override
  public int hashCode() {
    return Objects.hash(products, productsById);
  }
}
