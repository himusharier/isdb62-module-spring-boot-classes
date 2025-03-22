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
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Update fields only if they are not null
        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getBrand() != null) {
            existingProduct.setBrand(product.getBrand());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }
        if (product.getReleaseDate() != null) {
            existingProduct.setReleaseDate(product.getReleaseDate());
        }
        if (product.getImageName() != null) {
            existingProduct.setImageName(product.getImageName());
        }
        if (product.getImageType() != null) {
            existingProduct.setImageType(product.getImageType());
        }
        if (product.getImageData() != null) {
            existingProduct.setImageData(product.getImageData());
        }

        // Update boolean and primitive fields
        existingProduct.setAvailable(product.isAvailable());
        existingProduct.setQuantity(product.getQuantity());

        return repository.save(existingProduct);
    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }
}
