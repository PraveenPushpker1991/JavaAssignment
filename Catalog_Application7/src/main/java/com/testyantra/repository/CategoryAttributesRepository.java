package com.testyantra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testyantra.entity.Category;
import com.testyantra.entity.CategoryAttributes;

@Repository
public interface CategoryAttributesRepository extends JpaRepository<CategoryAttributes, Integer> {

}
