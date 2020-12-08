package com.griddynamics.microservices.catalog.config;

import com.griddynamics.microservices.catalog.model.Catalog;
import com.griddynamics.microservices.catalog.model.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {

  private final String xmlFilePath;

  public CatalogConfig(@Value("${xml-file-path}") String xmlFilePath) {
    this.xmlFilePath = xmlFilePath;
  }

  @Bean
  public Catalog loadCatalogFromXml() {

    Path pathToFile = Paths.get(xmlFilePath);
    Catalog catalog = new Catalog();

    try (BufferedReader br = Files.newBufferedReader(pathToFile,
        StandardCharsets.UTF_8)) {

      List<CSVRecord> csvRecords = CSVParser.parse(br, CSVFormat.DEFAULT).getRecords();
      csvRecords
          .subList(1, csvRecords.size())
          .forEach(r -> catalog.addProduct(readProduct(r)));

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return catalog;

  }

  private static Product readProduct(CSVRecord metadata) {

    Product product = new Product();

    product.setUniqId(metadata.get(0));
    product.setSku(metadata.get(1));
    product.setNameTitle(metadata.get(2));
    product.setDescription(metadata.get(3));
    product.setListPrice(metadata.get(4));
    product.setSalePrice(metadata.get(5));
    product.setCategory(metadata.get(6));
    product.setCategoryTree(metadata.get(7));
    product.setAverageProductRating(metadata.get(8));
    product.setProductUrl(metadata.get(9));
    product.setProductImageUrls(metadata.get(10));
    product.setBrand(metadata.get(11));
    product.setTotalNumberReviews(metadata.get(12));
    product.setReviews(metadata.get(13));

    return product;
  }
}
