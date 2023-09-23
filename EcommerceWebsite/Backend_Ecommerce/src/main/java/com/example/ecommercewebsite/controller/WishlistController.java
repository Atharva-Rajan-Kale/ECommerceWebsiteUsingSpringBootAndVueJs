package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.common.ApiResponse;
import com.example.ecommercewebsite.dto.ProductDto;
import com.example.ecommercewebsite.model.Product;
import com.example.ecommercewebsite.model.User;
import com.example.ecommercewebsite.model.Wishlist;
import com.example.ecommercewebsite.service.AuthService;
import com.example.ecommercewebsite.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;
    @Autowired
    AuthService authService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product, @RequestParam("token") String token){
        authService.authenticate(token);

        User user=authService.getUser(token);

        Wishlist wishlist=new Wishlist(user,product);
        wishlistService.createWishlist(wishlist);
        ApiResponse apiResponse=new ApiResponse(true,"Added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishlist(@PathVariable("token") String token){
        authService.authenticate(token);
        User user=authService.getUser(token);
        List<ProductDto> productDtos=wishlistService.getWishlistForUser(user);
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }


}
