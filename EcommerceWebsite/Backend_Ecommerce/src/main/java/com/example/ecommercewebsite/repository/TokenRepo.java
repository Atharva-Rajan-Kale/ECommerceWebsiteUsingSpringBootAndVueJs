package com.example.ecommercewebsite.repository;

import com.example.ecommercewebsite.model.AuthToken;
import com.example.ecommercewebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthToken,Integer> {
    AuthToken findByUser(User user);
    AuthToken findByToken(String token);
}
