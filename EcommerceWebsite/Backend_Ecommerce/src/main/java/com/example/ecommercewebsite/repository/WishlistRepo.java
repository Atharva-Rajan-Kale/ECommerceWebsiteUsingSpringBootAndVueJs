package com.example.ecommercewebsite.repository;

import com.example.ecommercewebsite.model.User;
import com.example.ecommercewebsite.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist,Integer> {
    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
}
