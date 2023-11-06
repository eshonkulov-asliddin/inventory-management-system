package com.example.inventorysystemwithgpt;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Product product) {
        // First, check if the product exists in the database
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        if (existingProduct == null) {
            // If the product doesn't exist, throw an exception or return null
            return null;
        }

        // Update the existing product with the new product data
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        // Save the updated product to the database
        return repository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}