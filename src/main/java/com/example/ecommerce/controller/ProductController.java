package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(service.getProducts(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);
        if(product != null) {
            return ResponseEntity.ok(product);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.addOrUpdateProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = null;
        try {
            updatedProduct = service.addOrUpdateProduct(product);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = service.getProductById(id);
        if(product != null) {
            service.deleteProduct(id);
            return new  ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/search/{keyword}")
    public ResponseEntity<List<Product>> getProductsByKeyword(@PathVariable String keyword) {
        return new ResponseEntity<>(service.getProductsByKeyword(keyword), HttpStatus.OK);
    }

}
