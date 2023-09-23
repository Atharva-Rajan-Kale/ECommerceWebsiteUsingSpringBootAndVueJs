package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.dto.ProductDto;
import com.example.ecommercewebsite.exceptions.ProductNotExistsException;
import com.example.ecommercewebsite.model.Category;
import com.example.ecommercewebsite.model.Product;
import com.example.ecommercewebsite.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductDto productDto, Category category) {
        Product product=new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepo.save(product);
    }
    public ProductDto getProductDto(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts=productRepo.findAll();
        List<ProductDto> productDtos=new ArrayList<>();
        for(Product product:allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;

    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Product> optionalProduct=productRepo.findById(productId);
        if(!optionalProduct.isPresent()){
            throw new Exception("product not present");
        }
        Product product=optionalProduct.get();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setPrice(productDto.getPrice());
        productRepo.save(product);
    }

    public Product findById(Integer productId) {
        Optional<Product> optionalProduct=productRepo.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotExistsException("product id is invalid: "+productId);
        }
        return optionalProduct.get();
    }
}
