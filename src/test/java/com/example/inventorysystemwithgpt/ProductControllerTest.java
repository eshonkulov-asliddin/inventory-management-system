package com.example.inventorysystemwithgpt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product = new Product();
        product.setName("Test Product");

        when(service.getAllProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());

        verify(service, times(1)).getAllProducts();
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setName("Test Product");

        when(service.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Product\"}"))
                .andExpect(status().isCreated());

        verify(service, times(1)).createProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(service.updateProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Product\"}"))
                .andExpect(status().isOk());

        verify(service, times(1)).updateProduct(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteProduct(1L);
    }
}