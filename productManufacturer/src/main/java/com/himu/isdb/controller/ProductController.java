package com.himu.isdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himu.isdb.model.Product;
import com.himu.isdb.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	public Product saveEmp(@RequestBody Product product) {
		Product savedProduct = service.saveProduct(product);
		return savedProduct;
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") int id) {
		Product productById = service.getProductById(id);
		return productById;
	}

	@GetMapping
	public List<Product> getAllProduct() {
		List<Product> allProduct = service.getAllProduct();
		return allProduct;
	}

	@DeleteMapping("/{id}")
	public String DelProductById(@PathVariable("id") int id) {
		int productById = service.DelProductById(id);
		if (productById > 0) {
			return "Deleted!";
		} else {
			return "Error!";
		}
	}

	@PutMapping("/{id}")
	public Product updateEmp(@PathVariable("id") int id, @RequestBody Product product) {
		Product update = service.updateProduct(id, product);
		return update;
	}

	@GetMapping("find/{name}")
	public List<Product> findByName(@PathVariable("name") String name) {
		return service.getProductByName(name);
	}
}
