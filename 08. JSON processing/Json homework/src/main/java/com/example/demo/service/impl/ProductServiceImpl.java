package com.example.demo.service.impl;

import com.example.demo.dtos.binding.ProductCreateBindingModel;
import com.example.demo.dtos.views.ProductInRangeView;
import com.example.demo.models.entities.Category;
import com.example.demo.models.entities.Product;
import com.example.demo.parsers.ModelParser;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

   private ProductRepository productRepository;
   private Validator validator;
   private ModelParser modelParser;
   private UserRepository userRepository;
   private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, Validator validator, ModelParser modelParser, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.validator = validator;
        this.modelParser = modelParser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void generateCategories() {
        Random random = new Random();
        List<Product> products = productRepository.findAll();
        long categoryCount = categoryRepository.count();
        for (Product product : products) {
            Category category = categoryRepository.findById( random.nextInt((int) categoryCount) + 1).get();
            category.getProducts().add(product);
            categoryRepository.save(category); //many-to-many owner is Category entity so we save that one
        }
    }

    @Override
    public List<ProductInRangeView> getNamePriceAndSeller(int from, int to) {
        List<Product> prod = productRepository.listOfProductsWithNoSeller(BigDecimal.valueOf(500),BigDecimal.valueOf(1000));
        return productRepository.listOfProductsWithNoSeller(BigDecimal.valueOf(from),BigDecimal.valueOf(to))
                .stream()
                .map(p -> new ProductInRangeView(
                        p.getName(),
                        p.getPrice(),
                        p.getSeller().getFirstName() != null ? p.getSeller().getFirstName() + " " + p.getSeller().getLastName() :
                                p.getSeller().getLastName()))
                .collect(Collectors.toList());

    }

    @Override
    public void saveProducts(ProductCreateBindingModel[] productCreateBindingModels) {
        Random random = new Random();
        for (ProductCreateBindingModel dtoProduct : productCreateBindingModels) {
            if(validator.validate(dtoProduct).size() == 0) {
                Product product = modelParser.convert(dtoProduct,Product.class);
                long usersCount = userRepository.count();
                product.setSeller(userRepository.findById(random.nextInt((int)usersCount) + 1).get());
                if (random.nextDouble() < 0.8) {
                    product.setBuyer(userRepository.findById(random.nextInt((int) usersCount) + 1).get());
                }
                productRepository.save(product);
            }
        }
    }


}
