package com.example.testfinal.service.Product;

import com.example.testfinal.model.Product;
import com.example.testfinal.service.IService;

public interface ProductService extends IService<Product> {
    public Iterable<Product> search(String name);
}
