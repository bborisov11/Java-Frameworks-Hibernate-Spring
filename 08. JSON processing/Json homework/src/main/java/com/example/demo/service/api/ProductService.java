package com.example.demo.service.api;

import com.example.demo.dtos.binding.ProductCreateBindingModel;
import com.example.demo.dtos.views.ProductInRangeView;
import com.example.demo.models.entities.Product;
import com.example.demo.models.entities.User;

import java.util.List;

public interface ProductService {
    List<ProductInRangeView> getNamePriceAndSeller(int from,int to);
    void saveProducts(ProductCreateBindingModel[] productCreateBindingModels);
    public void generateCategories();
}
