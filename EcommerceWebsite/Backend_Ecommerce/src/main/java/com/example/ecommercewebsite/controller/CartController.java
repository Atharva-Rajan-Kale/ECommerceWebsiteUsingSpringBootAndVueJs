package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.common.ApiResponse;
import com.example.ecommercewebsite.dto.cart.AddToCartDto;
import com.example.ecommercewebsite.dto.cart.CartDto;
import com.example.ecommercewebsite.model.Product;
import com.example.ecommercewebsite.model.User;
import com.example.ecommercewebsite.service.AuthService;
import com.example.ecommercewebsite.service.CartService;
import jakarta.persistence.Access;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private AuthService authService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token){
        authService.authenticate(token);
        User user=authService.getUser(token);
        cartService.addToCart(addToCartDto,user);
        return new ResponseEntity<>(new ApiResponse(true,"Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
        authService.authenticate(token);
        User user=authService.getUser(token);
        CartDto cartDto=cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("token") String token){
        authService.authenticate(token);
        User user=authService.getUser(token);
        cartService.deleteCartItem(itemId,user);
        return new ResponseEntity<>(new ApiResponse(true,"Item has been removed"), HttpStatus.OK);

    }
}
