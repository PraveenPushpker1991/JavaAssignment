package com.testyantra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.testyantra.entity.CategoryAttributes;
import com.testyantra.repository.CategoryAttributesRepository;
import com.testyantra.service.ICategoryAttributesService;

@Service
public class CategoryAttributesServiceImpl implements ICategoryAttributesService {
	
	@Autowired
	private CategoryAttributesRepository repo;

	
	@Override
	public List<CategoryAttributes> createCategoryAttributes(List<CategoryAttributes> categoryAttributes) {
		return repo.saveAll(categoryAttributes) ;
	}
	
	@Override
	public List<CategoryAttributes> getAllCategoryAttributes() {
		return repo.findAll();
	}

	@Override
	public Optional<CategoryAttributes> getOneCategoryAttributes(Integer id) {
		return repo.findById(id);
	}

	@Override
	public boolean isExist(Integer id) {
		return repo.existsById(id); 
	}

	@Override
	public void deleteCategoryAttributes(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Integer updateCategoryAttributes(CategoryAttributes categoryAttributes) {
		return repo.save(categoryAttributes).getAttributeId();
	}

	@Override
	public Optional<CategoryAttributes> getAttributes(Integer id) {
		return repo.findById(id);
	}

}
