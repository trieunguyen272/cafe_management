package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.exception.ResourceNotFoundException;
import com.project.cafe_management_system.mapper.ProductMapper;
import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.model.ProductCategory;
import com.project.cafe_management_system.repository.ProductRepository;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductMapper productMapper;

    public ResponseGeneric<ProductDTO> createProduct(ProductDTO productDTO) {
        Product product = productMapper.convertDTOToModel(productDTO);

        product =  productRepository.save(product);

        ProductDTO savedProductDTO = productMapper.convertModelToDTO(product);

        return new ResponseGeneric<>(200, "success", savedProductDTO);
//        Long newId = product.getId();
//        productDTO.setId(newId);
//        return ResponseGeneric.builder()
//                .data(ProductDTO.builder()
//                        .productName(product.getProductName())
//                        .price(product.getPrice())
//                        .quantity(product.getQuantity())
//                        .image(product.getImage())
//                        .description(product.getDescription())
//                        .productCategoryId(product.getProductCategory().getId()))
//
//                .build();
    }

    public ResponseGeneric<List<ProductDTO>> getAllProduct() {
        List<Product> products = productRepository.findAll();

        List<ProductDTO> productDTOs = products.stream()
                .map(product -> productMapper.convertModelToDTO(product))
                .collect(Collectors.toList());
        return new ResponseGeneric<>(200, "Success", productDTOs);
//        return productRepository.findAll();
    }


    public ResponseGeneric<ProductDTO> getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        ProductDTO productDTO = productMapper.convertModelToDTO(product);
        return new ResponseGeneric<>(200, "Success", productDTO);
    }

    public ResponseGeneric<List<ProductDTO>> getProductByName(String name) {
        List<Product> products = productRepository.findProductByName(name);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product not exist with name: " + name);
        }

        List<ProductDTO> productDTOs = products.stream()
                .map(product -> productMapper.convertModelToDTO(product))
                .collect(Collectors.toList());

        return new ResponseGeneric<>(200, "Success", productDTOs);
    }

//    public ResponseGeneric<List<ProductDTO>> getProductByCategoryName(String name) {
//        List<Product> products = productRepository.findProductByCategoryName(name);
//
//        if (products.isEmpty()) {
//            throw new ResourceNotFoundException("Product not exist with product category name: " + name);
//        }
//
//        List<ProductDTO> productDTOs = products.stream()
//                .map(product -> productMapper.convertModelToDTO(product))
//                .collect(Collectors.toList());
//
//        return new ResponseGeneric<>(200, "Success", productDTOs);
//    }

    public ResponseGeneric<ProductDTO> updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        product.setProductName(productDTO.getProductName());
        product.setProductCategory(productCategoryService.retrievedById(productDTO.getProductCategoryId()));
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());

        product =  productRepository.save(product);

        ProductDTO updatedProductDTO = productMapper.convertModelToDTO(product);

        return new ResponseGeneric<>(200, "success", updatedProductDTO);
    }


    public ResponseGeneric<Map<String, Boolean>> deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        ResponseGeneric<Map<String, Boolean>> rs = new ResponseGeneric<>();
        productRepository.delete(product);
        Map<String, Boolean> respon = new HashMap<>();
        respon.put("Deleted product: ", Boolean.TRUE);
        rs.setData(respon);
        return rs;

    }

}
