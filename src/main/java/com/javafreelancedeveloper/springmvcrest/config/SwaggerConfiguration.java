package com.javafreelancedeveloper.springmvcrest.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	/**
	 * If you need to customise the API swagger doc, this is where to do it
	 * 
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
				.pathMapping("/").apiInfo(metaData());
	}

	private ApiInfo metaData() {
		Contact contact = new Contact("Yvette Quinby", "https://javafreelancedeveloper.com", "");
		return new ApiInfo("Java Freelance Developer", "Spring MVC REST Demo Application", "1.0", "Terms of Service: None. Just have fun with it.",
				contact, "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
	}
}
