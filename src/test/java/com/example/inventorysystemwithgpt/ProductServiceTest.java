package com.example.inventorysystemwithgpt;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    public void testGetAllProducts() {
        Product product = new Product();
        product.setName("Test Product");

        when(repository.findAll()).thenReturn(Arrays.asList(product));

        List<Product> products = service.getAllProducts();

        assertEquals(1, products.size());
        assertEquals("Test Product", products.get(0).getName());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");

        when(repository.save(product)).thenReturn(product);

        Product createdProduct = service.createProduct(product);

        assertEquals("Test Product", createdProduct.getName());

        verify(repository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(product);

        Product updatedProduct = service.updateProduct(product);

        assertEquals("Test Product", updatedProduct.getName());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setId(1L);

        service.deleteProduct(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}