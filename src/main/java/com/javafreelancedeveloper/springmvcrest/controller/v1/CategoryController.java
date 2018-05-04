package com.javafreelancedeveloper.springmvcrest.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CategoryDTO;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CategoryListDTO;
import com.javafreelancedeveloper.springmvcrest.service.CategoryService;

@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<CategoryListDTO> listCategories() {
		List<CategoryDTO> categories = categoryService.listCategories();
		CategoryListDTO categoriesDto = new CategoryListDTO(categories);
		return new ResponseEntity<CategoryListDTO>(categoriesDto, HttpStatus.OK);
	}

	@GetMapping("{name}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
		CategoryDTO category = categoryService.getCategoryByName(name);
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
	}
}
