package com.testyantra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testyantra.entity.Product;
import com.testyantra.repository.ProductRepository;
import com.testyantra.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductRepository repo;
	
	@Override
	public Integer createProduct(Product p) {
		return repo.save(p).getProductId();
	}

	@Override
	public List<Product> getAllProduct() {
		return repo.findAll();
	}

	@Override
	public boolean isExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public void deleteProduct(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<Product> getOneProduct(Integer id) {
		return repo.findById(id);
	}
}
