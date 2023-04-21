package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("SELECT s FROM ProductCategory s WHERE s.productCategoryName = ?1")
    ProductCategory findCategoryByName(String name);
}
