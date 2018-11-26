package com.example.demo.service.impl;

import com.example.demo.dtos.binding.CategoryCreateBindingModel;
import com.example.demo.dtos.views.CategoryByProductsCountView;
import com.example.demo.models.entities.Category;
import com.example.demo.parsers.ModelParser;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.service.api.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private Validator validator;
    private ModelParser modelParser;
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(Validator validator, ModelParser modelParser, CategoryRepository categoryRepository) {
        this.validator = validator;
        this.modelParser = modelParser;
        this.categoryRepository = categoryRepository;
    }

    public void addToCategory(CategoryCreateBindingModel[] categoryCreateBindingModels) {
        for (CategoryCreateBindingModel categoryCreateBindingModel : categoryCreateBindingModels) {

            if(validator.validate(categoryCreateBindingModel) != null) {
                Category category = modelParser.convert(categoryCreateBindingModel,Category.class);
                categoryRepository.save(category);
            }
        }
    }
    public List<CategoryByProductsCountView> categoryByCount(){
        List<CategoryByProductsCountView> cat = //categoryRepository.findAll();
         categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryByProductsCountView(
                        c.getName(),
                        c.getProducts().size(),
                        c.getProducts().size() == 0 ? 0 :
                        c.getProducts().stream().mapToDouble(p -> p.getPrice().doubleValue()).average().getAsDouble(),
                        c.getProducts().stream().mapToDouble(s -> s.getPrice().doubleValue()).sum())
                ).sorted(Comparator.comparingInt(x -> -x.getProductsCount()))
                .collect(Collectors.toList());

        return cat;
    }
}
