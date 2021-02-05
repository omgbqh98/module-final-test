package com.example.testfinal.repository;

import com.example.testfinal.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% order by p.price desc, p.name asc ")
    public Iterable<Product> search(String name);
}
