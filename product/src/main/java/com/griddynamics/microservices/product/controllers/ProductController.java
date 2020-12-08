package com.griddynamics.microservices.product.controllers;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import com.griddynamics.microservices.product.model.Product;
import com.griddynamics.microservices.product.services.ProductService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/products")
@Api(value = "Products controller")
public class ProductController {

  private final ProductService productService;

  public ProductController(
      ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/available/id/{id}")
  public ResponseEntity<Product> getProductById(
      @ApiParam(defaultValue = "b6c0b6bea69c722939585baeac73c13d")
      @PathVariable(value = "id") String id) {
    return productService
        .findAvailableProductById(id)
        .map(ResponseEntity::ok)
        .orElse(notFound().build());
  }

  @GetMapping("/available/sku/{sku}")
  public ResponseEntity<List<Product>> getProductsBySku(
      @ApiParam(defaultValue = "pp5006380337")
      @PathVariable(value = "sku") String sku) {
    return ok(productService.findAvailableProductsBySku(sku));
  }

  @ExceptionHandler(HystrixRuntimeException.class)
  public ResponseEntity<String> handleGoogleSpreadsheetsException(
      HystrixRuntimeException ex) {

    Throwable cause = ex.getFallbackException().getCause();

    if(cause instanceof ResponseStatusException) {
      return new ResponseEntity<>(((ResponseStatusException) cause).getStatus());
    }

    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
