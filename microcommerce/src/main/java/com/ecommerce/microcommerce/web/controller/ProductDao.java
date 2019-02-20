package com.ecommerce.microcommerce.web.controller;
import com.ecommerce.microcommerce.model.Product;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {
    public List<Product> findAll();
    public Product findById(int id);
    public Product save(Product product);
}