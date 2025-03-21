package com.himusharier.ecombackend.service;

import com.himusharier.ecombackend.dto.ProductRequestDto;
import com.himusharier.ecombackend.model.Product;
import com.himusharier.ecombackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        product.setImageName(product.getImageName());
        product.setImageType(product.getImageType());
        product.setImageData(product.getImageData());
        return repository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        product.setImageName(product.getImageName());
        product.setImageType(product.getImageType());
        product.setImageData(product.getImageData());
        return repository.save(product);
    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }
}
