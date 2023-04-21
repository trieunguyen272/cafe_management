package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryPagingRepository extends PagingAndSortingRepository<ProductCategory, Long> {
    @Query("SELECT s FROM ProductCategory s WHERE CAST(s.id AS string) LIKE %:id% OR (:name IS NOT NULL AND :name <> '' AND s.productCategoryName LIKE %:name%)")
    Page<ProductCategory> findCategoryByIdOrName(@Param("id") String id, @Param("name") String name, Pageable pageable);
}
