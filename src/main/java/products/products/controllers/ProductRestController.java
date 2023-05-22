package products.products.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import products.products.model.Product;
import products.products.service.IProductService;
import products.products.util.Util;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class ProductRestController {
    
    public IProductService productService;

    @GetMapping("products")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.searchProduct();
    }
    
    @PostMapping("products")
    @Transactional
    public ResponseEntity<List<Product>> saveProduct(@RequestParam("picture") MultipartFile picture, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("categoryId") Long categoryId, @RequestParam("makerId") Long makerId) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setPicture(Util.compressZLib(picture.getBytes()));
        return productService.saveProduct(product, categoryId, makerId);
    }

    @PutMapping("products/{id}")
    @Transactional
    public ResponseEntity<List<Product>> updateProduct(@RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("picture") MultipartFile picture, @RequestParam("categoryId") Long categoryId, @RequestParam("makerId") Long makerId, @PathVariable Long id) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setPicture(Util.compressZLib(picture.getBytes()));
        return productService.updateProduct(product, categoryId, makerId, id);
    }

    @GetMapping("products/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Product> searchProductById(@PathVariable Long id) {
        return productService.searchProductById(id);
    }

    @DeleteMapping("products/{id}")
    @Transactional
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
    
}
