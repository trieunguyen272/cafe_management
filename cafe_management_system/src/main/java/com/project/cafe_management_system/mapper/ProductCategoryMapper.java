package com.project.cafe_management_system.mapper;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.model.ProductCategory;
import com.project.cafe_management_system.service.ProductCategoryService;
import com.project.cafe_management_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductCategoryMapper {

    public ProductCategoryDTO convertModelToDTO(ProductCategory productCategory) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();

        productCategoryDTO.setId(productCategory.getId());
        productCategoryDTO.setProductCategoryName(productCategory.getProductCategoryName());


        return productCategoryDTO;
    }

    public ProductCategory convertDTOToModel(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = new ProductCategory();

        productCategory.setId(productCategoryDTO.getId());
        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());


        return productCategory;
    }
}
