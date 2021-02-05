package com.example.testfinal.controller;

import com.example.testfinal.model.Product;
import com.example.testfinal.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> listProduct() {
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        Product product = productService.findById(id).get();
        productService.delete(product.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product product1 = productService.findById(id).get();
        product1.setId(product.getId());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory());
        productService.save(product1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search/{name}")
    public ResponseEntity<Iterable<Product>> search(@PathVariable String name) {
        Iterable<Product> products = productService.search(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
