package com.example.testfinal.service.Category;

import com.example.testfinal.model.Category;
import com.example.testfinal.service.IService;
import org.springframework.stereotype.Service;


public interface CategoryService extends IService<Category> {
    public Category add(Category category);
}
