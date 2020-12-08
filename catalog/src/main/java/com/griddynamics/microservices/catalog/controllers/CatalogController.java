package com.griddynamics.microservices.catalog.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.griddynamics.microservices.catalog.model.Product;
import com.griddynamics.microservices.catalog.services.CatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalogs")
@Api(value = "Catalog controller")
public class CatalogController {

  private final CatalogService catalogService;

  public CatalogController(
      CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  @GetMapping("/products/id/{id}")
  public ResponseEntity<Product> getProductById(
      @ApiParam(defaultValue = "b6c0b6bea69c722939585baeac73c13d")
      @PathVariable(value = "id") String id) {
    return ok(catalogService.getProductById(id));
  }

  @GetMapping("/products/sku/{sku}")
  public ResponseEntity<List<Product>> getProductsBySku(
      @ApiParam(defaultValue = "pp5006380337")
      @PathVariable(value = "sku") String sku) {
    return ok(catalogService.getProductsBySku(sku));
  }

}
