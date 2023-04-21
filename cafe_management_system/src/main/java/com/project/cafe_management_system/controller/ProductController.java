package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.service.ProductService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseGeneric<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {
        ResponseGeneric<ProductDTO> productDTOs = productService.createProduct(productDTO);

        return ResponseEntity.ok(productDTOs);
    }
//    @PostMapping
//    public ResponseEntity<ResponseGeneric<ProductDTO>> createProduct(@RequestParam("file") MultipartFile file) {
//        ProductDTO productDTO = new ProductDTO();
//        ResponseGeneric<ProductDTO> productDTOs = productService.createProduct(file);
//
//        return ResponseEntity.ok(productDTOs);
//    }

    @GetMapping
    public ResponseEntity<ResponseGeneric<List<ProductDTO>>> getAllProduct() {
        ResponseGeneric<List<ProductDTO>> productDTOs = productService.getAllProduct();

        return ResponseEntity.ok(productDTOs);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ResponseGeneric<Page<ProductDTO>>> searchProduct(@RequestParam(name = "id", required = false) Long id,
                                                                           @RequestParam(name = "productName", required = false) String productName,
                                                                           @RequestParam(name = "productCategoryName", required = false) String productCategoryName,
                                                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                                                           @RequestParam(value = "limit", defaultValue = "3") int limit,
                                                                           @RequestParam(value = "orderBy", defaultValue = "desc") String orderBy,
                                                                           @RequestParam(value = "sort", defaultValue = "id") String columnName) {
        ResponseGeneric<Page<ProductDTO>> productDTOs = productService.searchProduct(id, productName, productCategoryName, page, limit, orderBy, columnName);

        return ResponseEntity.ok(productDTOs);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ResponseGeneric<ProductDTO>> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ResponseGeneric<ProductDTO> updateProductDTO = productService.updateProduct(id, productDTO);

        return ResponseEntity.ok(updateProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
        ResponseGeneric<Map<String, Boolean>> responseGeneric = productService.deleteProduct(id);

        return ResponseEntity.ok(responseGeneric.getData());
    }
}
