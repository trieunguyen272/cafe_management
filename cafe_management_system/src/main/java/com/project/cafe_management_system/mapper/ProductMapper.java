package com.project.cafe_management_system.mapper;

import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private ProductCategoryService productCategoryService;


    public Product convertDTOToModel(ProductDTO productDTO) {
        Product product = new Product();
        
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());
        product.setProductCategory(productCategoryService.retrievedById(productDTO.getProductCategoryId()));
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());

        return product;
    }

    public ProductDTO convertModelToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductCategoryId(product.getProductCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());

        return productDTO;
    }
}
