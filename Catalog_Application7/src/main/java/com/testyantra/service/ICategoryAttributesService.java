package com.testyantra.service;

import java.util.List;
import java.util.Optional;


import com.testyantra.entity.CategoryAttributes;

public interface ICategoryAttributesService {
	
	public List<CategoryAttributes> createCategoryAttributes(List<CategoryAttributes> categoryAttributes);
	public List<CategoryAttributes> getAllCategoryAttributes();
	public Optional<CategoryAttributes> getOneCategoryAttributes(Integer id);
	public boolean isExist(Integer id);
	public void deleteCategoryAttributes(Integer id);
	public Integer updateCategoryAttributes(CategoryAttributes categoryAttributes);
	
	public Optional<CategoryAttributes> getAttributes(Integer id);
	

}
