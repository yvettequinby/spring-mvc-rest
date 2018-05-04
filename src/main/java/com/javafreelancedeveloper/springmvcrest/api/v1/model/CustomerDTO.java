package com.javafreelancedeveloper.springmvcrest.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {

	private Long id;
	private String firstName;
	private String lastName;
	// @JsonProperty("customer_url")
	private String customerUrl;
}
