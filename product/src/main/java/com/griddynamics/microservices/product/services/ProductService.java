package com.griddynamics.microservices.product.services;

import com.griddynamics.microservices.product.model.Product;
import com.griddynamics.microservices.product.model.ProductAvailability;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

  private final RestTemplate restTemplate;
  private final String getProductByIdUrl;
  private final String getProductsBySkuUrl;
  private final String areAvailableUrl;

  public ProductService(
      RestTemplate restTemplate,
      @Value("${services.catalog.get-product-by-id-url}") String getProductByIdUrl,
      @Value("${services.catalog.get-products-by-sku-url}") String getProductsBySkuUrl,
      @Value("${services.inventory.are-available-url}") String areAvailableUrl) {
    this.restTemplate = restTemplate;
    this.getProductByIdUrl = getProductByIdUrl;
    this.getProductsBySkuUrl = getProductsBySkuUrl;
    this.areAvailableUrl = areAvailableUrl;
  }

  @HystrixCommand(fallbackMethod = "defaultFallbackProductsById",
      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "800"),
          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60"),
          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value="1"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value="1000")
      },
      threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value="2")
      }
  )
  public Optional<Product> findAvailableProductById(String id) {

    ResponseEntity<Product> productResponseEntity = restTemplate
        .getForEntity(
            getProductByIdUrl,
            Product.class,
            id);

    if(productResponseEntity.getStatusCode() == HttpStatus.OK) {

      Product retrievedProduct = productResponseEntity.getBody();
      if(retrievedProduct != null
          && filterAvailable(Collections.singletonList(retrievedProduct)).size() > 0) {
        return Optional.of(retrievedProduct);
      }
    }

    return Optional.empty();
  }

  @HystrixCommand(fallbackMethod = "defaultFallbackProductsBySku",
      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "800"),
          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60"),
          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value="100"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value="10000")
      },
      threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value="5")
      }
  )
  public List<Product> findAvailableProductsBySku(String sku) {

    ResponseEntity<Product[]> productResponseEntity = restTemplate
        .getForEntity(
            getProductsBySkuUrl,
            Product[].class,
            sku);

    if(productResponseEntity.getStatusCode() == HttpStatus.OK) {

      Product[] retrievedProductList = productResponseEntity.getBody();

      if(retrievedProductList != null) {
        return filterAvailable(Arrays.asList(retrievedProductList));
      }
    }

    return Collections.emptyList();
  }

  @SuppressWarnings({"unused"})
  public Optional<Product> defaultFallbackProductsById(String id) {
    throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
  }

  @SuppressWarnings("unused")
    public List<Product> defaultFallbackProductsBySku(String sku) {
    throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
  }

  private List<Product> filterAvailable(List<Product> products) {

    ResponseEntity<ProductAvailability[]> productAvailabilityResponseEntity = restTemplate
        .getForEntity(
            areAvailableUrl,
            ProductAvailability[].class,
            products.stream().map(Product::getUniqId).collect(Collectors.toList()));

    if (productAvailabilityResponseEntity.getStatusCode() == HttpStatus.OK) {

      ProductAvailability[] availabilityResponse = productAvailabilityResponseEntity
          .getBody();

      if (availabilityResponse == null) {
        return Collections.emptyList();
      }

      Set<String> availableProductsIds = new HashSet<>();

      Arrays
          .stream(availabilityResponse)
          .filter(Objects::nonNull)
          .filter(ProductAvailability::isAvailable)
          .forEach(p -> availableProductsIds.add(p.getUniqId()));

      return products
          .stream()
          .filter(p -> availableProductsIds.contains(p.getUniqId()))
          .collect(Collectors.toList());
    }

    return Collections.emptyList();

  }
}
