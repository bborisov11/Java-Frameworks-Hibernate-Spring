package com.example.demo;

import com.example.demo.dtos.binding.CategoryCreateBindingModel;
import com.example.demo.dtos.binding.ProductCreateBindingModel;
import com.example.demo.dtos.views.CategoryByProductsCountView;
import com.example.demo.dtos.views.ProductInRangeView;
import com.example.demo.dtos.views.UserWithBuyerView;
import com.example.demo.io.FileIO;
import com.example.demo.models.entities.User;
import com.example.demo.parsers.JsonParser;
import com.example.demo.service.api.CategoryService;
import com.example.demo.service.api.ProductService;
import com.example.demo.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class Runner implements CommandLineRunner {

    private final UserService userService;
    private final FileIO fileIO;
    private final JsonParser jsonParser;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public Runner(UserService userService, FileIO fileIO, JsonParser jsonParser, ProductService productService, CategoryService categoryService) {

        this.userService = userService;
        this.fileIO = fileIO;
        this.jsonParser = jsonParser;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
       // InputStream stream = Runner.class.getResourceAsStream("/users.json");
        //String test = "";

        //importFromUsers();
        //exportUsers();
        //importProducts();
        //exportProducts(500,1000);
        //exportUsersAndProducts();
        //importFromCategories();
       // generateCategoriesForProducts();
        exportCategoriesByProductsCount();
    }
    private void importFromCategories() throws IOException {
        String jsonCategories = fileIO.read("/categories.json");
        CategoryCreateBindingModel[] categoryCreateBindingModels = jsonParser.read(CategoryCreateBindingModel[].class,jsonCategories);
        categoryService.addToCategory(categoryCreateBindingModels);
    }

    private void importFromUsers() throws IOException {
        String jsonUsers = fileIO.read("/users.json");
        User[] users = jsonParser.read(User[].class,jsonUsers);
       // Arrays.stream(users).forEach(x -> System.out.println(x.getFirstName()+ " "+ x.getLastName()));
        userService.saveUsers(users);
    }

    private void generateCategoriesForProducts() {
        productService.generateCategories();
    }

    private void importProducts() throws IOException {
        String jsonProducts = fileIO.read("/products.json");
        ProductCreateBindingModel[] products = jsonParser.read(ProductCreateBindingModel[].class,jsonProducts);
        productService.saveProducts(products);
    }

    private void exportUsers() throws IOException {
        List<User> users = userService.getAllUsers();
        String usersJson = jsonParser.write(users);
        fileIO.write(usersJson,"/output/allUsers.json");
    }

   private void  exportProducts(int from,int to) throws IOException {
       List<ProductInRangeView> productInRange= productService.getNamePriceAndSeller(from,to);
       String productJson = jsonParser.write(productInRange);
       fileIO.write(productJson,"/output/productsInRange.json");
   }

   private void exportUsersAndProducts() throws IOException {
        Set<UserWithBuyerView> users = userService.usersWithSoldProducts();
        String userJson = jsonParser.write(users);
        fileIO.write(userJson,"/output/users-sold-products.json");
   }

   private void exportCategoriesByProductsCount() throws IOException {
        List<CategoryByProductsCountView> categories = categoryService.categoryByCount();
        String categoryJson = jsonParser.write(categories);
        fileIO.write(categoryJson,"/output/categories-by-products.json");
    }

}
