package com.griddynamics.microservices.catalog.services;

import com.griddynamics.microservices.catalog.model.Catalog;
import com.griddynamics.microservices.catalog.model.Product;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

  private final Catalog catalog;

  public CatalogService(Catalog catalog) {
    this.catalog = catalog;
  }


  public Product getProductById(String id) {

    try {
      Thread.sleep(new Random().nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return catalog.getProductById(id);
  }

  public List<Product> getProductsBySku(String sku) {
    return catalog.getProductsBySku(sku);
  }
}
