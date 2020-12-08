package com.griddynamics.microservices.catalog.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Catalog {

  private List<Product> products = new LinkedList<>();
  private Map<String, Product> productsById = new HashMap<>();
  private Map<String, List<Product>> productsBySku = new HashMap<>();

  public void addProduct(Product p) {
    products.add(p);
    String id = p.getUniqId();
    String sku = p.getSku();

    if(id != null) {
      productsById.put(id, p);
    }

    if(sku != null) {
      productsBySku.putIfAbsent(sku, new LinkedList<>());
      productsBySku.get(sku).add(p);
    }

  }

  public Product getProductById(String id) {
    return productsById.get(id);
  }

  public List<Product> getProductsBySku(String id) {
    return productsBySku.get(id);
  }

  @Override
  public String toString() {
    return "Catalog{" +
        "products=" + products +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Catalog)) {
      return false;
    }
    Catalog catalog = (Catalog) o;
    return Objects.equals(products, catalog.products)
        && Objects.equals(productsById, catalog.productsById)
        && Objects.equals(productsBySku, catalog.productsBySku);
  }

  @Override
  public int hashCode() {
    return Objects.hash(products, productsById, productsBySku);
  }
}
