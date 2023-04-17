package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.service.ProductCategoryService;
import com.project.cafe_management_system.service.ProductService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product_category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseGeneric<ProductCategoryDTO>> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ResponseGeneric<Object> productCategoryDTOs = productCategoryService.createProductCategory(productCategoryDTO);
        ResponseGeneric<ProductCategoryDTO> responseGeneric = new ResponseGeneric<>(
                productCategoryDTOs.getStatusCode(),
                productCategoryDTOs.getMessage(),
                (ProductCategoryDTO) productCategoryDTOs.getData());

        return ResponseEntity.ok(responseGeneric);

    }

    public ResponseEntity<ResponseGeneric<ProductCategoryDTO>> createProductCategory1(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ResponseGeneric<ProductCategoryDTO> productCategoryDTOs = productCategoryService.createProductCategory1(productCategoryDTO);

        return ResponseEntity.ok(productCategoryDTOs);

    }


//    public ResponseEntity<ResponseGeneric<Object>> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
//
//        return ResponseEntity.ok(productCategoryService.createProductCategory(productCategoryDTO));
//
//    }


    @GetMapping
    public ResponseEntity<ResponseGeneric<List<ProductCategoryDTO>>> getAllProductCategory() {
        ResponseGeneric<List<ProductCategoryDTO>> productCategoryDTOs = productCategoryService.getAllProductCategory();

        return ResponseEntity.ok(productCategoryDTOs);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseGeneric<ProductCategoryDTO>> getProductCategoryById(@PathVariable Long id) {
        ResponseGeneric<ProductCategoryDTO> productCategoryDTO = productCategoryService.getProductCategoryById(id);

        return ResponseEntity.ok(productCategoryDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseGeneric<List<ProductCategoryDTO>>> getProductCategoryByName(@PathVariable String name) {
        ResponseGeneric<List<ProductCategoryDTO>> productCategoryDTOs = productCategoryService.getProductCategoryByName(name);

        return ResponseEntity.ok(productCategoryDTOs);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseGeneric<ProductCategoryDTO>> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        ResponseGeneric<ProductCategoryDTO> updateProductCategoryDTO = productCategoryService.updateProductCategory(id, productCategoryDTO);

        return ResponseEntity.ok(updateProductCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProductCategory(@PathVariable Long id) {
        ResponseGeneric<Map<String, Boolean>> responseGeneric = productCategoryService.deleteProductCategory(id);

        return ResponseEntity.ok(responseGeneric.getData());
    }

}
