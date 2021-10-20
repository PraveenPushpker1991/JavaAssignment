package com.testyantra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testyantra.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
