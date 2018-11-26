package com.example.demo.service.impl;

import com.example.demo.dtos.views.SoldProductView;
import com.example.demo.dtos.views.UserWithBuyerView;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.api.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Validator validator;
    private final UserRepository userRepository;

    public UserServiceImpl(Validator validator, UserRepository userRepository) {
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUsers(User[] users) {

        for (User user : users) {
            if(validator.validate(user)!= null) {
                userRepository.save(user);
            }
        }
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Set<UserWithBuyerView> usersWithSoldProducts() {
        Set<User> usersAndProducts = userRepository.usersWithSoldProducts();

        return usersAndProducts
                .stream()
                .map(u -> new UserWithBuyerView(
                        u.getFirstName(),
                        u.getLastName(),
                        u.getSoldProducts().stream()
                                .filter(b -> b.getBuyer() != null)
                                .map(p -> new SoldProductView(
                                        p.getName(),
                                        p.getPrice(),
                                        p.getBuyer().getFirstName(),
                                        p.getBuyer().getLastName()
                                        )
                                ).collect(Collectors.toSet()))
                ).collect(Collectors.toSet());
    }

}
