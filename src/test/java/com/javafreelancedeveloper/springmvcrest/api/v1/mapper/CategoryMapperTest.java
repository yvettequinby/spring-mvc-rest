package com.javafreelancedeveloper.springmvcrest.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CategoryDTO;
import com.javafreelancedeveloper.springmvcrest.domain.Category;

public class CategoryMapperTest {

	public static final String NAME = "Joe";
	public static final long ID = 1L;
	private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

	@Test
	public void testCategoryToCategoryDTO() throws Exception {
		// given
		Category category = new Category();
		category.setName(NAME);
		category.setId(ID);
		// when
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		// then
		assertEquals(Long.valueOf(ID), categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}
}
