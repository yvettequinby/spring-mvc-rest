package com.javafreelancedeveloper.springmvcrest.service;

import java.util.List;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CategoryDTO;


public interface CategoryService {
	
	List<CategoryDTO> listCategories();
	
	CategoryDTO getCategoryByName(String name);
}
