package products.products.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import products.products.model.Product;
import products.products.service.IProductService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    
    public IProductService productService;

    @GetMapping("products")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.searchProduct();
    }
    
}
