package com.ironw.service.pdf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author trgoofi
 */
@Configuration
public class LoadConfig {

  public @Bean PrintConfig loadPrintConfig() {
    Resource resource = new ClassPathResource("print.yaml");
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    PrintConfig printConfig = null;
    try {
      printConfig = mapper.readValue(resource.getInputStream(), PrintConfig.class);
    } catch (IOException e) {
      throw new RuntimeException("Could not load print.yaml", e);
    }
    return printConfig;
  }
}
