package se.fabricioflores.petmarket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import jakarta.servlet.Filter;

@Configuration
public class WebConfig {

  @Value("${cors.allowed.origin:http://localhost:4200}")
  private String corsAllowedOrigin;

  @Bean
  public FilterRegistrationBean<Filter> corsFilter() {
    System.out.println(corsAllowedOrigin);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(corsAllowedOrigin);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
    config.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", config);
    CorsFilter corsFilter = new CorsFilter(source);
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<Filter>(corsFilter);
		bean.setOrder(0);
		return bean;
  }
}
