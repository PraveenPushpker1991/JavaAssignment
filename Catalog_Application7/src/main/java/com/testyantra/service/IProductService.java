package com.testyantra.service;

import java.util.List;
import java.util.Optional;

import com.testyantra.entity.Category;
import com.testyantra.entity.Product;

public interface IProductService {
	
	public Integer createProduct(Product p);
	public List<Product> getAllProduct();
	public boolean isExist(Integer id);
	public void deleteProduct(Integer id);
	
	public Optional<Product> getOneProduct(Integer id);

}
