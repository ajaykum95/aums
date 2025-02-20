package com.abha.aums.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "tax")
public class TaxConfig {
  private double sgst;
  private double cgst;
  private double igst;
}
