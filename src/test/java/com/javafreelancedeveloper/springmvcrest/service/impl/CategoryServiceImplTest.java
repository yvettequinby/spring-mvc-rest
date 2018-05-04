package com.javafreelancedeveloper.springmvcrest.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.javafreelancedeveloper.springmvcrest.api.v1.mapper.CategoryMapper;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CategoryDTO;
import com.javafreelancedeveloper.springmvcrest.domain.Category;
import com.javafreelancedeveloper.springmvcrest.repositories.CategoryRepository;
import com.javafreelancedeveloper.springmvcrest.service.CategoryService;

public class CategoryServiceImplTest {

	public static final Long ID = 2L;
	public static final String NAME = "Jimmy";
	private CategoryService categoryService;
	@Mock
	private CategoryRepository categoryRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
	}

	@Test
	public void testListCategories() throws Exception {
		// given
		List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
		when(categoryRepository.findAll()).thenReturn(categories);
		// when
		List<CategoryDTO> categoryDTOS = categoryService.listCategories();
		// then
		assertEquals(3, categoryDTOS.size());
	}

	@Test
	public void testGetCategoryByName() throws Exception {
		// given
		Category category = new Category();
		category.setId(ID);
		category.setName(NAME);
		when(categoryRepository.findByName(anyString())).thenReturn(category);
		// when
		CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
		// then
		assertEquals(ID, categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}
}
