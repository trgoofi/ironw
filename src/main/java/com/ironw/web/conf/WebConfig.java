package com.ironw.web.conf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author trgoofi
 */
@Configuration
public class WebConfig {
  private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);
  @Inject private ServletContext servletContext;

  @PostConstruct
  public void config() {
    LOG.info("Loading Web Configuration from web.yaml");
    Resource resource = new ClassPathResource("web.yaml");
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
    try {
      HashMap<String, Object> values = mapper.readValue(resource.getInputStream(), typeRef);
      servletContext.setAttribute("siteName", values.get("siteName"));
      servletContext.setAttribute("ctx", servletContext.getContextPath());
    } catch (IOException e) {
      throw new RuntimeException("Could not load web.yaml", e);
    }
  }
}
