package com.griddynamics.microservices.inventory.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.griddynamics.microservices.inventory.model.ProductStatus;
import com.griddynamics.microservices.inventory.services.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
@Api(value = "Inventory controller")
public class InventoryController {

  private final InventoryService inventoryService;

  public InventoryController(
      InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping("products/are_available/{ids}")
  public ResponseEntity<List<ProductStatus>> getProductById(
      @ApiParam(defaultValue = "b6c0b6bea69c722939585baeac73c13d")
      @PathVariable(value = "ids") List<String> ids) {
    return ok(inventoryService.checkProductsAvailability(ids));
  }

}
