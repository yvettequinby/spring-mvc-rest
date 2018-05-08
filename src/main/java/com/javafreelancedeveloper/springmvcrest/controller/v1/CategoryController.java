package com.javafreelancedeveloper.springmvcrest.controller.v1;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CategoryDTO;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CategoryListDTO;
import com.javafreelancedeveloper.springmvcrest.service.CategoryService;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
	
	public static final String BASE_URL = "/api/v1/categories";
	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@ApiOperation(value = "List all categories.")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO listCategories() {
		List<CategoryDTO> categories = categoryService.listCategories();
		CategoryListDTO categoriesDto = new CategoryListDTO(categories);
		return categoriesDto;
	}

	@ApiOperation(value = "Get a category by name.")
	@GetMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getCategoryByName(@PathVariable String name) {
		CategoryDTO category = categoryService.getCategoryByName(name);
		return category;
	}
}
