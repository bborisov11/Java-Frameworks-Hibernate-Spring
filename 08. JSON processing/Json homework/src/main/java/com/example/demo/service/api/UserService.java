package com.example.demo.service.api;

import com.example.demo.dtos.views.UserWithBuyerView;
import com.example.demo.models.entities.User;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

public interface UserService {

    void saveUsers(User[] users);
    List<User> getAllUsers();

    Set<UserWithBuyerView> usersWithSoldProducts();

}
