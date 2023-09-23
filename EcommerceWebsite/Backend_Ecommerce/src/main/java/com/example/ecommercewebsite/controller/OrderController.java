package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.dto.checkout.CheckoutItemDto;
import com.example.ecommercewebsite.dto.checkout.StripeResponse;
import com.example.ecommercewebsite.service.AuthService;
import com.example.ecommercewebsite.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private AuthService authService;

    @Autowired
    private OrderService orderService;
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        Session session=orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse=new StripeResponse(session.getId());
        return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
    }
}
