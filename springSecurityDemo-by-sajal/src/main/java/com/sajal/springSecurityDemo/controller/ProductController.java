package com.sajal.springSecurityDemo.controller;

import com.sajal.springSecurityDemo.model.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("/{id}")
    Product getProduct(@PathVariable(name = "id", required = true) Integer id, HttpServletRequest request) {
        String username = (String) request.getAttribute("authenticatedUsername");
        HttpSession session = request.getSession();
        System.out.println("Session ID: " + session.getId());
        System.out.println(username);
        if (id != null) {
            return new Product(1, "product_1", 100.00);
        }
        throw new RuntimeException("product not found");
    }

    @GetMapping("/")
    List<Product> getProduct() {
        return List.of(new Product(1, "product_1", 100.00),
                new Product(2, "product_2", 122.88),
                new Product(3, "product_3", 200.15));
    }

    @GetMapping("/open")
    String getText() {
       return "this is open";
    }


}
