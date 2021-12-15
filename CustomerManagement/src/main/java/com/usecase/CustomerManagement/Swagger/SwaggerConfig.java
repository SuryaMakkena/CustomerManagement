package com.usecase.CustomerManagement.Swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Customer API", version = "2.0"))
public class SwaggerConfig {
	
	  @Bean
	  public GroupedOpenApi publicApi() {
	      return GroupedOpenApi.builder()
	              .group("com.usecase.CustomerManagement")
	              .pathsToMatch("/**")
	              .build();
	  }


}