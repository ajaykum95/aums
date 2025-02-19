package com.abha.aums.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "email.senders")
public class EmailConfigMap {
  private String primary;
  private String support;
  private String billing;
  private String marketing;
}
