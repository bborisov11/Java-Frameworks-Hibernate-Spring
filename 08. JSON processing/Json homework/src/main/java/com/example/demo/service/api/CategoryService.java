package com.example.demo.service.api;

import com.example.demo.dtos.binding.CategoryCreateBindingModel;
import com.example.demo.dtos.views.CategoryByProductsCountView;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void addToCategory(CategoryCreateBindingModel[] categoryCreateBindingModels);

    List<CategoryByProductsCountView> categoryByCount();
}
