package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.productName like %?1%")
    List<Product> findProductByName(String name);

//    @Query("SELECT FROM ProductCategory JOIN Product ON ProductCategory.id = Product.productCategory.id WHERE ProductCategory.productCategoryName like %?1%")
//    @Query("SELECT p FROM products p JOIN product_categories pc ON p.product_category_id = pc.id WHERE pc.product_category_name like %?1%")
//    List<Product> findProductByCategoryName(String name);
}
