package com.example.demo.repositories;

import com.example.demo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

   @Query(value = "SELECT u FROM User AS u")
    Set<User> findAllUsers();

   @Query(value = "SELECT u FROM User AS u JOIN u.soldProducts AS p WHERE p.buyer IS NOT NULL ORDER BY u.lastName,u.firstName")
    Set<User> usersWithSoldProducts();

}
