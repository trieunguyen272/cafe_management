package com.project.cafe_management_system.mapper;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.model.ProductCategory;
import org.springframework.stereotype.Component;

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
        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());

        return productCategory;
    }
}
