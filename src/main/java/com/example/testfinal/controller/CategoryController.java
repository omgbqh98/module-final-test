package com.example.testfinal.controller;

import com.example.testfinal.model.Category;
import com.example.testfinal.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> ListAll() {
        Iterable<Category> categories =  categoryService.findAll();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        Category category = categoryService.findById(id).get();
        categoryService.delete(category.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
       Category categoryOptional = categoryService.findById(id).get();
        categoryOptional.setId(category.getId());
        categoryOptional.setName(category.getName());
        categoryOptional.setTag(category.getTag());
        categoryService.save(categoryOptional);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
