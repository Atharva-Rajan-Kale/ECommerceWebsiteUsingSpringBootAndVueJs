package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.dto.cart.AddToCartDto;
import com.example.ecommercewebsite.dto.cart.CartDto;
import com.example.ecommercewebsite.dto.cart.CartItemDto;
import com.example.ecommercewebsite.exceptions.CustomException;
import com.example.ecommercewebsite.model.Cart;
import com.example.ecommercewebsite.model.Product;
import com.example.ecommercewebsite.model.User;
import com.example.ecommercewebsite.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    ProductService productService;
    @Autowired
    CartRepo cartRepo;
    public void addToCart(AddToCartDto addToCartDto, User user) {
        Product product=productService.findById(addToCartDto.getProductId());
        Cart cart=new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepo.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList=cartRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItemDtos=new ArrayList<>();
        double totalCost=0;
        for(Cart cart:cartList){
            CartItemDto cartItemDto= new CartItemDto(cart);
            totalCost+=cartItemDto.getQuantity()+cart.getProduct().getPrice();
            cartItemDtos.add(cartItemDto);
        }
        CartDto cartDto=new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItemDtos);
        return cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) {
        Optional<Cart> optionalCart=cartRepo.findById(cartItemId);

        if(optionalCart.isEmpty()){
            throw new CustomException("cart item id is invalid: "+cartItemId);
        }
        Cart cart=optionalCart.get();
        if(cart.getUser()!=user){
            throw new CustomException("cart item does not belong to user: "+cartItemId);
        }
        cartRepo.delete(cart);
    }
}
