package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.exception.ResourceForbiddenException;
import com.project.cafe_management_system.exception.ResourceNotFoundException;
import com.project.cafe_management_system.mapper.ProductMapper;
import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.repository.ProductPagingRepository;
import com.project.cafe_management_system.repository.ProductRepository;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPagingRepository productPagingRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ImageService imageService;

    public ResponseGeneric<ProductDTO> createProduct(ProductDTO productDTO) {
        Product product = productRepository.findProductByName(productDTO.getProductName());
        if (product != null) {
            throw new ResourceForbiddenException("Product already exist with name: " + productDTO.getProductName());
        }
        product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductCategory(productCategoryService.retrievedById(productDTO.getProductCategoryId()));
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

//        String encodedImage = imageService.encodeImageToBase64(productDTO.getImage());
//        product.setImage(encodedImage);


        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product =  productRepository.save(product);
        ProductDTO savedProductDTO = productMapper.convertModelToDTO(product);

        return new ResponseGeneric<>(200, "success", savedProductDTO);
    }

//    public ResponseGeneric<ProductDTO> createProduct(MultipartFile file) {
//        ProductDTO productDTO = new ProductDTO();
//        Product product = productRepository.findProductByName(productDTO.getProductName());
//        if (product != null) {
//            throw new ResourceForbiddenException("Product already exist with name: " + productDTO.getProductName());
//        }
//        product = new Product();
//        product.setProductName(productDTO.getProductName());
//        product.setProductCategory(productCategoryService.retrievedById(productDTO.getProductCategoryId()));
//        product.setPrice(productDTO.getPrice());
//        product.setQuantity(productDTO.getQuantity());
//
////        String encodedImage = imageService.encodeImageToBase64(productDTO.getImage());
////        product.setImage(encodedImage);
//        try {
//            product.setImage(file.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        product.setDescription(productDTO.getDescription());
//        product =  productRepository.save(product);
//        ProductDTO savedProductDTO = productMapper.convertModelToDTO(product);
//
//        return new ResponseGeneric<>(200, "success", savedProductDTO);
//    }

    public ResponseGeneric<List<ProductDTO>> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> productMapper.convertModelToDTO(product))
                .collect(Collectors.toList());

        return new ResponseGeneric<>(200, "Success", productDTOs);
    }

    public ResponseGeneric<Page<ProductDTO>> searchProduct(Long id, String productName,  String productCategoryName, int page, int limit, String order, String columnName) {
        PageableFilter pageableFilter = new PageableFilter(page, limit, order, columnName);
        String product = StringUtils.defaultString(productName);
        String productCategory = StringUtils.defaultString(productCategoryName);
        Page<Product> products = productPagingRepository.findProductByIdOrName(id, product, productCategory, pageableFilter.pageableBuild());
        if (!products.hasContent()) {
            throw new ResourceNotFoundException("Product not found with id: " + id
                                                    + " + product name: " + productName
                                                    + " + product category name: " + productCategoryName);
        }
        Page<ProductDTO> productDTOs = products.map(productMapper::convertModelToDTO);

        return new ResponseGeneric<>(200, "Success", productDTOs);
    }

    public ResponseGeneric<ProductDTO> updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        if (productRepository.findProductByName(productDTO.getProductName()) != null) {
            throw new ResourceForbiddenException("Product already exist with name: " + productDTO.getProductName());
        }
        product.setProductName(productDTO.getProductName());
        product.setProductCategory(productCategoryService.retrievedById(productDTO.getProductCategoryId()));
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product = productRepository.save(product);
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
