package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.dto.ResponseDto;
import com.example.ecommercewebsite.dto.user.SignInDto;
import com.example.ecommercewebsite.dto.user.SignInResponseDto;
import com.example.ecommercewebsite.dto.user.SignUpDto;
import com.example.ecommercewebsite.exceptions.AuthenticationFailException;
import com.example.ecommercewebsite.exceptions.CustomException;
import com.example.ecommercewebsite.model.AuthToken;
import com.example.ecommercewebsite.model.User;
import com.example.ecommercewebsite.repository.UserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthService authService;

    @Transactional
    public ResponseDto signup(SignUpDto signUpDto) throws CustomException{
        if(Objects.nonNull(userRepo.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("user already exists");
        }

        String encryptedPassword=signUpDto.getPassword();
        try{
            encryptedPassword=hashPassword(signUpDto.getPassword());
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        User user=new User(signUpDto.getFirstName(),signUpDto.getLastName(),signUpDto.getEmail()
        ,encryptedPassword);

        userRepo.save(user);

        final AuthToken authToken=new AuthToken(user);
        authService.saveConfirmationToken(authToken);

        ResponseDto responseDto=new ResponseDto("success","user created successfully");

        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest=md.digest();
        String hash= DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signin(SignInDto signInDto) {
        User user = userRepo.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }
        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("wrong password");
            }

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        AuthToken token=authService.getToken(user);

        if(Objects.isNull(token)){
            throw new CustomException("token is not present");
        }
        return new SignInResponseDto("success",token.getToken());
    }
}
