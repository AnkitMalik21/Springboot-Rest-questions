package com.hcl.controllerTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.Controller.ProductController;
import com.hcl.entity.Product;
import com.hcl.service.ProductService;

public class ProductControllerTest {
     @InjectMocks
     private ProductController productController;
     
     @Mock
     private ProductService productService;
     private MockMvc mockMvc;
     
     @BeforeEach
     void setUp() {
    	 MockitoAnnotations.initMocks(this);
    	 mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
     }
     
     @Test
     public void testGetAllProducts() throws Exception{
    	 when(productService.getAllProduct()).thenReturn(Arrays.asList(new Product(),new Product()));
    	 
    	 mockMvc.perform(get("/Product/getAll"))
    	 .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));
     }
     
     @Test
     public void testGetProductById() throws Exception{
    	 Long productId = 1L;
    	 when(productService.getProductById(productId)).thenReturn(new Product());
    	 
    	 mockMvc.perform(get("/Product/byId/{id}",productId)).andExpect(status().isOk());
     }
     
     @Test
     public void testGetProductByName() throws Exception{
    	 String productName = "chair";
    	 when(productService.getProductByName(productName)).thenReturn(Arrays.asList(new Product()));
    	 
    	 mockMvc.perform(get("/Product/byName/{name}",productName)).andExpect(status().isOk());
     }
     
    @Test
    public void testDeleteById() throws Exception{
    	Long productId = 1L;
    	
    	doNothing().when(productService).deleteProduct(productId);
    	
    	mockMvc.perform(delete("/Product/delete/{id}",productId))
    	.andExpect(status().isOk());
    	
    	verify(productService,times(1)).deleteProduct(productId);
    }
}
