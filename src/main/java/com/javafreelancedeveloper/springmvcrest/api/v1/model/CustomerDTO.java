package com.javafreelancedeveloper.springmvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

	@ApiModelProperty(value = "Customer ID", required = false, notes="This field is required for update (put and patch) operations, but is ignored for create (post) operations.")
	private Long id;
	@ApiModelProperty(value = "Customer First Name", required = true)
	private String firstName;
	@ApiModelProperty(value = "Customer Last Name", required = true)
	private String lastName;
	// @JsonProperty("customer_url")
	@ApiModelProperty(value = "Customer URL", required = false, notes="This field is ignored for all consumer operations, but is populated by the API for post, put and patch operations.")
	private String customerUrl;
}
