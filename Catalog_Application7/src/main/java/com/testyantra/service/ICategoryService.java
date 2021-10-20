package com.testyantra.service;

import java.util.List;
import java.util.Optional;


import com.testyantra.entity.Category;

public interface ICategoryService {
	
	public Category createCategory(Category c);
	public List<Category> getAllCategory();
	public Optional<Category> getOneCategory(Integer id);
	public boolean isExist(Integer id);
	public void deleteCategory(Integer id);
	
	

}
