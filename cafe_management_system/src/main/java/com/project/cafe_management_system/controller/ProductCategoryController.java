package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.service.ProductCategoryService;
import com.project.cafe_management_system.service.ProductService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
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
        ResponseGeneric<ProductCategoryDTO> productCategoryDTOs = productCategoryService.createProductCategory(productCategoryDTO);

        return ResponseEntity.ok(productCategoryDTOs);

    }

    @GetMapping
    public ResponseEntity<ResponseGeneric<List<ProductCategoryDTO>>> getAllProductCategory() {
        ResponseGeneric<List<ProductCategoryDTO>> productCategoryDTOs = productCategoryService.getAllProductCategory();

        return ResponseEntity.ok(productCategoryDTOs);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ResponseGeneric<Page<ProductCategoryDTO>>> searchProductCategory(@RequestParam(name = "id", required = false) Long id,
                                                                          @RequestParam(name = "name", required = false) String name,
                                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                                          @RequestParam(value = "limit", defaultValue = "3") int limit,
                                                                          @RequestParam(value = "orderBy", defaultValue = "desc") String orderBy,
                                                                          @RequestParam(value = "sort", defaultValue = "id") String columnName) {
       ResponseGeneric<Page<ProductCategoryDTO>> productCategoryDTOs = productCategoryService.searchProductCategory(id, name, page, limit, orderBy, columnName);

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


//    public ResponseEntity<ResponseGeneric<ProductCategoryDTO>> createProductCategory1(@RequestBody ProductCategoryDTO productCategoryDTO) {
//        ResponseGeneric<Object> productCategoryDTOs = productCategoryService.createProductCategory1(productCategoryDTO);
//        ResponseGeneric<ProductCategoryDTO> responseGeneric = new ResponseGeneric<>(
//                productCategoryDTOs.getStatusCode(),
//                productCategoryDTOs.getMessage(),
//                (ProductCategoryDTO) productCategoryDTOs.getData());
//
//        return ResponseEntity.ok(responseGeneric);
//
//    }

    //    public ResponseEntity<ResponseGeneric<Object>> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
//
//        return ResponseEntity.ok(productCategoryService.createProductCategory(productCategoryDTO));
//
//    }


}
