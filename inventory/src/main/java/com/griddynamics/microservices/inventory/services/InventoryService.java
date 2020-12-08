package com.griddynamics.microservices.inventory.services;

import com.griddynamics.microservices.inventory.model.Inventory;
import com.griddynamics.microservices.inventory.model.ProductStatus;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

  private final Inventory inventory;

  public InventoryService(Inventory inventory) {
    this.inventory = inventory;
  }

  public List<ProductStatus> checkProductsAvailability(List<String> ids) {

    try {
      Thread.sleep(new Random().nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return ids.stream()
        .map(inventory::getProductStatusById)
        .collect(Collectors.toList());
  }

}
