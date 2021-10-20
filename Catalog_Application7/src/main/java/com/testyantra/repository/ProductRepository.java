package com.testyantra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testyantra.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
