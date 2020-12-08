package com.griddynamics.microservices.inventory.config;

import com.griddynamics.microservices.inventory.model.Inventory;
import com.griddynamics.microservices.inventory.model.ProductStatus;
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
public class InventoryConfig {

  private final String xmlFilePath;

  public InventoryConfig(@Value("${xml-file-path}") String xmlFilePath) {
    this.xmlFilePath = xmlFilePath;
  }

  @Bean
  public Inventory loadInventoryFromXml() {

    Path pathToFile = Paths.get(xmlFilePath);
    Inventory inventory = new Inventory();

    try (BufferedReader br = Files.newBufferedReader(pathToFile,
        StandardCharsets.UTF_8)) {

      List<CSVRecord> csvRecords = CSVParser.parse(br, CSVFormat.DEFAULT).getRecords();
      csvRecords
          .subList(1, csvRecords.size())
          .forEach(r -> inventory.addProductStatus(readProductStatus(r)));

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return inventory;

  }

  private static ProductStatus readProductStatus(CSVRecord metadata) {

    ProductStatus product = new ProductStatus();

    product.setUniqId(metadata.get(0));

    return product;
  }
}
