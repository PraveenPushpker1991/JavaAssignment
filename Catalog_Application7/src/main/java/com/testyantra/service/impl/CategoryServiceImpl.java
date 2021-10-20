package com.testyantra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.testyantra.entity.Category;
import com.testyantra.repository.CategoryRepository;
import com.testyantra.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	@Override
	public Category createCategory(Category c) {
		return repo.save(c);
	}

	@Override
	public List<Category> getAllCategory() {
		return repo.findAll();
	}

	@Override
	public Optional<Category> getOneCategory(Integer id) {
		return repo.findById(id);
	}

	@Override
	public boolean isExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public void deleteCategory(Integer id) {
		repo.deleteById(id);
	}

}
