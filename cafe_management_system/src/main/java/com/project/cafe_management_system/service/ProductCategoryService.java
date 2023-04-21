package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.exception.ResourceForbiddenException;
import com.project.cafe_management_system.exception.ResourceNotFoundException;
import com.project.cafe_management_system.mapper.ProductCategoryMapper;
import com.project.cafe_management_system.model.ProductCategory;
import com.project.cafe_management_system.repository.ProductCategoryPagingRepository;
import com.project.cafe_management_system.repository.ProductCategoryRepository;
import com.project.cafe_management_system.repository.ProductRepository;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import com.project.cafe_management_system.service.PageableFilter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service

public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductCategoryPagingRepository productCategoryPagingRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private PageableFilter  pageableFilter;

    public ProductCategory retrievedById(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not exist with id: " + id));

        return productCategory;
    }

    public ResponseGeneric<ProductCategoryDTO> createProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryRepository.findCategoryByName(productCategoryDTO.getProductCategoryName());
        if (productCategory != null) {
            throw new ResourceForbiddenException("Product category already exist with name: " + productCategoryDTO.getProductCategoryName());
        }
        productCategory = new ProductCategory();
        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());

        productCategory = productCategoryRepository.save(productCategory);
        ProductCategoryDTO savedProductCategoryDTO = productCategoryMapper.convertModelToDTO(productCategory);

        return new ResponseGeneric<>(200, "success", savedProductCategoryDTO);
    }


    public ResponseGeneric<List<ProductCategoryDTO>> getAllProductCategory() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        List<ProductCategoryDTO> productCategoryDTOs = productCategories.stream()
                .map(productCategory -> productCategoryMapper.convertModelToDTO(productCategory))
                .collect(Collectors.toList());

        return new ResponseGeneric<>(200, "Success", productCategoryDTOs);

    }

    public ResponseGeneric<Page<ProductCategoryDTO>> searchProductCategory(Long id, String name, int page, int limit, String order, String columnName) {
        PageableFilter pageableFilter = new PageableFilter(page, limit, order, columnName);
//        String productCategory = StringUtils.defaultString(name);
        name = name.replaceAll("\\s", "");
        String idString= id.toString();
        Page<ProductCategory> productCategories = productCategoryPagingRepository.findCategoryByIdOrName(idString, name, pageableFilter.pageableBuild());
        if (!productCategories.hasContent()) {
            throw new ResourceNotFoundException("Product category not found with id: " + id + " and name: " + name);
        }
        Page<ProductCategoryDTO> productCategoryDTOs = productCategories.map(productCategoryMapper::convertModelToDTO);
        System.out.println("name" + name + "name1");
        if (idString.getClass().getName().equals("String")) {
            System.out.println("id is an String");
        }
        return new ResponseGeneric<>(200, "Success", productCategoryDTOs);
    }

    public ResponseGeneric<ProductCategoryDTO> updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product category not exist with id: " + id));
//        ProductCategory category = productCategoryRepository.findCategoryByName(productCategoryDTO.getProductCategoryName());
        if (productCategoryRepository.findCategoryByName(productCategoryDTO.getProductCategoryName()) != null) {
            throw new ResourceForbiddenException("Product category already exist with name: " + productCategoryDTO.getProductCategoryName());
        }
        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());
        productCategory = productCategoryRepository.save(productCategory);
        ProductCategoryDTO updatedProductCategoryDTO = productCategoryMapper.convertModelToDTO(productCategory);

        return new ResponseGeneric<>(200, "success", updatedProductCategoryDTO);
    }

    public ResponseGeneric<Map<String, Boolean>> deleteProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not exist with id: " + id));
        ResponseGeneric<Map<String, Boolean>> rs = new ResponseGeneric<>();
        productCategoryRepository.delete(productCategory);
        Map<String, Boolean> respon = new HashMap<>();
        respon.put("Delete product category", Boolean.TRUE);
        rs.setData(respon);

        return rs;

    }

    //    public ResponseGeneric<Object> createProductCategory1(ProductCategoryDTO productCategoryDTO) {
//        ProductCategory productCategory = productCategoryRepository.findCategoryByName(productCategoryDTO.getProductCategoryName());
//        if (productCategory != null) {
//            throw new ResourceNotFoundException("Product category already exist with name: " + productCategoryDTO.getProductCategoryName());
//        }
//        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());
//        productCategory = productCategoryRepository.save(productCategory);
//        ProductCategoryDTO savedProductCategoryDTO = productCategoryMapper.convertModelToDTO(productCategory);
//        return ResponseGeneric.builder()
//                .statusCode(200)
//                .message("success")
//                .data(savedProductCategoryDTO)
//                .build();
//    }

}