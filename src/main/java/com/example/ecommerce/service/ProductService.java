package com.example.ecommerce.service;


import com.example.ecommerce.model.Product;
import com.example.ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProductById(int id){
        return repo.findById(id).get();
    }

    public Product addOrUpdateProduct(Product product){
        return repo.save(product);
    }

    public  void deleteProduct(int id){
        repo.deleteById(id);
    }

    public List<Product> getProductsByKeyword(String keyword){
        return repo.findProductByNameContainingOrDescriptionContaining(keyword, keyword);
    }

}
