package com.edu.hutech.major.service;

import com.edu.hutech.major.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAllProduct();

    Page<Product> getAllProduct(Pageable pageable);

    void updateProduct(Product product);

    void removeProductById(long id);

    Optional<Product> getProductById(long id);

    List<Product> getAllProductByCategoryId(int id);
}
