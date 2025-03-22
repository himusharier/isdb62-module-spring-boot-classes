package com.himusharier.springDataJpa.service;

import com.himusharier.springDataJpa.model.Product;
import com.himusharier.springDataJpa.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int productId) {
        return productRepo.findById(productId).orElse(new Product());
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(int productId) {
        productRepo.deleteById(productId);
    }

}
