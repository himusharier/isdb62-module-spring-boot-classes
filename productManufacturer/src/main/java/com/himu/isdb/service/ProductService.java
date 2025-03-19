package com.himu.isdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.himu.isdb.model.Product;
import com.himu.isdb.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public Product saveProduct(Product product) {
		int save = repository.save(product);
		return getProductById(save);
	}

	public Product getProductById(int id) {
		Optional<Product> byId = repository.findById(id);
		return byId.get();
	}

	public List<Product> getAllProduct() {
		List<Product> all = repository.findAll();
		return all;
	}

	public int DelProductById(int id) {
		return repository.deleteById(id);
	}

	public Product updateProduct(int id, Product product) {
		product.setId(id);
		repository.update(product);
		return getProductById(id);
	}

	public List<Product> getProductByName(String name) {
		return repository.getProductByName(name);
	}
}
