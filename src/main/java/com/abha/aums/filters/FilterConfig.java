package com.abha.aums.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<HeaderValidationFilter> headerValidationFilter() {
        FilterRegistrationBean<HeaderValidationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new HeaderValidationFilter());
        registrationBean.addUrlPatterns("/api/*");  // Apply filter to specific URL patterns
        registrationBean.setOrder(1);  // Set precedence if multiple filters

        return registrationBean;
    }
}
