package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.exceptions.AuthenticationFailException;
import com.example.ecommercewebsite.model.AuthToken;
import com.example.ecommercewebsite.model.User;
import com.example.ecommercewebsite.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    @Autowired
    TokenRepo tokenRepo;
    public void saveConfirmationToken(AuthToken authToken) {
        tokenRepo.save(authToken);
    }

    public AuthToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }
    public User getUser(String token){
        final AuthToken authToken=tokenRepo.findByToken(token);
        if(Objects.isNull(authToken)){
            return null;
        }
        return authToken.getUser();
    }
    public void authenticate(String token){
        if(Objects.isNull(token)){
            throw new AuthenticationFailException("token not present");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("token not valid");
        }
    }
}
