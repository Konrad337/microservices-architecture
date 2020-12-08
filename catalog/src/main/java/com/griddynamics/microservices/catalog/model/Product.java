package com.griddynamics.microservices.catalog.model;

import java.util.Objects;

public class Product {

  private String uniqId;
  private String sku;
  private String nameTitle;
  private String description;
  private String listPrice;
  private String salePrice;
  private String category;
  private String categoryTree;
  private String averageProductRating;
  private String productUrl;
  private String productImageUrls;
  private String brand;
  private String totalNumberReviews;
  private String reviews;

  public String getUniqId() {
    return uniqId;
  }

  public void setUniqId(String uniqId) {
    this.uniqId = uniqId;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = nameTitle;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getListPrice() {
    return listPrice;
  }

  public void setListPrice(String listPrice) {
    this.listPrice = listPrice;
  }

  public String getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(String salePrice) {
    this.salePrice = salePrice;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCategoryTree() {
    return categoryTree;
  }

  public void setCategoryTree(String categoryTree) {
    this.categoryTree = categoryTree;
  }

  public String getAverageProductRating() {
    return averageProductRating;
  }

  public void setAverageProductRating(String averageProductRating) {
    this.averageProductRating = averageProductRating;
  }

  public String getProductUrl() {
    return productUrl;
  }

  public void setProductUrl(String productUrl) {
    this.productUrl = productUrl;
  }

  public String getProductImageUrls() {
    return productImageUrls;
  }

  public void setProductImageUrls(String productImageUrls) {
    this.productImageUrls = productImageUrls;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getTotalNumberReviews() {
    return totalNumberReviews;
  }

  public void setTotalNumberReviews(String total_numberReviews) {
    this.totalNumberReviews = total_numberReviews;
  }

  public String getReviews() {
    return reviews;
  }

  public void setReviews(String reviews) {
    this.reviews = reviews;
  }

  @Override
  public String toString() {
    return "Product{" +
        "uniqId='" + uniqId + '\'' +
        ", sku='" + sku + '\'' +
        ", nameTitle='" + nameTitle + '\'' +
        ", description='" + description + '\'' +
        ", listPrice=" + listPrice +
        ", salePrice=" + salePrice +
        ", category='" + category + '\'' +
        ", categoryTree='" + categoryTree + '\'' +
        ", averageProductRating='" + averageProductRating + '\'' +
        ", productUrl='" + productUrl + '\'' +
        ", productImageUrls=" + productImageUrls +
        ", brand='" + brand + '\'' +
        ", totalNumberReviews=" + totalNumberReviews +
        ", reviews=" + reviews +
        "}\n";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product)) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(getUniqId(), product.getUniqId()) &&
        Objects.equals(getSku(), product.getSku()) &&
        Objects.equals(getNameTitle(), product.getNameTitle()) &&
        Objects.equals(getDescription(), product.getDescription()) &&
        Objects.equals(getListPrice(), product.getListPrice()) &&
        Objects.equals(getSalePrice(), product.getSalePrice()) &&
        Objects.equals(getCategory(), product.getCategory()) &&
        Objects.equals(getCategoryTree(), product.getCategoryTree()) &&
        Objects.equals(getAverageProductRating(), product.getAverageProductRating()) &&
        Objects.equals(getProductUrl(), product.getProductUrl()) &&
        Objects.equals(getProductImageUrls(), product.getProductImageUrls()) &&
        Objects.equals(getBrand(), product.getBrand()) &&
        Objects.equals(getTotalNumberReviews(), product.getTotalNumberReviews()) &&
        Objects.equals(getReviews(), product.getReviews());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(
            getUniqId(),
            getSku(),
            getNameTitle(),
            getDescription(),
            getListPrice(),
            getSalePrice(),
            getCategory(),
            getCategoryTree(),
            getAverageProductRating(),
            getProductUrl(),
            getProductImageUrls(),
            getBrand(),
            getTotalNumberReviews(),
            getReviews());
  }
}
