package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            "JOIN p.productCategory pc " +
            "WHERE p.id = :id OR p.productName LIKE %:productName% OR pc.productCategoryName LIKE %:productCategoryName%")
    Page<Product> findProductByIdOrName(@Param("id") Long id,
                                        @Param("productName") String productName,
                                        @Param("productCategoryName") String productCategoryName,
                                        Pageable pageable);
}
