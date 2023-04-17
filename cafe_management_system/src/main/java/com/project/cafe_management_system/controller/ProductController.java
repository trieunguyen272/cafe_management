package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.service.ProductService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//        ResponseGeneric<ProductDTO> responseGeneric = new ResponseGeneric<>(200, "success", productDTO);
        return ResponseEntity.ok(productDTOs);
    }


    @GetMapping
    public ResponseEntity<ResponseGeneric<List<ProductDTO>>> getAllProduct() {
        ResponseGeneric<List<ProductDTO>> productDTOs = productService.getAllProduct();
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseGeneric<ProductDTO>> getProductById(@PathVariable Long id) {
        ResponseGeneric<ProductDTO> productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/product_name/{name}")
    public ResponseEntity<ResponseGeneric<List<ProductDTO>>> getProductByName(@PathVariable String name) {
        ResponseGeneric<List<ProductDTO>> productDTOs = productService.getProductByName(name);

        return ResponseEntity.ok(productDTOs);
    }

//    @GetMapping("/product_category_name/{name}")
//    public ResponseEntity<ResponseGeneric<List<ProductDTO>>> getProductByCategoryName(@PathVariable String name) {
//        ResponseGeneric<List<ProductDTO>> productDTOs = productService.getProductByName(name);
//
//        return ResponseEntity.ok(productDTOs);
//    }


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
