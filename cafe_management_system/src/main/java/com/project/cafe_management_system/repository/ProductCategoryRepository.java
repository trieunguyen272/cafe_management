package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("SELECT s FROM ProductCategory s WHERE s.productCategoryName like %?1%")
    List<ProductCategory> findCategoryByName(String name);


}
