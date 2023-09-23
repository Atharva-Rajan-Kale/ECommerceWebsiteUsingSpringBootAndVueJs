package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.dto.ProductDto;
import com.example.ecommercewebsite.model.User;
import com.example.ecommercewebsite.model.Wishlist;
import com.example.ecommercewebsite.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    @Autowired
    WishlistRepo wishlistRepo;
    @Autowired
    ProductService productService;

    public void createWishlist(Wishlist wishlist) {
        wishlistRepo.save(wishlist);
    }

    public List<ProductDto> getWishlistForUser(User user) {
        final List<Wishlist> wishlists=wishlistRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos=new ArrayList<>();
        for(Wishlist wishlist:wishlists){
            productDtos.add(productService.getProductDto(wishlist.getProduct()));
        }
        return productDtos;
    }
}
