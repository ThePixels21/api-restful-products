package products.products.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import products.products.model.Product;

public interface IProductService {
    
    public ResponseEntity<List<Product>> searchProduct();

    public ResponseEntity<List<Product>> saveProduct(Product product, Long categoryId, Long makerId);

    public ResponseEntity<List<Product>> updateProduct(Product product, Long categoryId, Long makerId, Long productId);

    public ResponseEntity<List<Product>> deleteProduct(Long id);

    public ResponseEntity<Product> searchProductById(Long id);


}
