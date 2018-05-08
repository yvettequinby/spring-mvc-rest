package com.javafreelancedeveloper.springmvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDTO {

	@ApiModelProperty(value = "Category ID")
	private Long id;
	@ApiModelProperty(value = "Category Name")
	private String name;
}
