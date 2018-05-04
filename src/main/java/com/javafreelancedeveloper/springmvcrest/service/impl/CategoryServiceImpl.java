package com.javafreelancedeveloper.springmvcrest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafreelancedeveloper.springmvcrest.api.v1.mapper.CategoryMapper;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CategoryDTO;
import com.javafreelancedeveloper.springmvcrest.repositories.CategoryRepository;
import com.javafreelancedeveloper.springmvcrest.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public List<CategoryDTO> listCategories() {
		return categoryRepository.findAll().stream().map(categoryMapper::categoryToCategoryDTO).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}
}
