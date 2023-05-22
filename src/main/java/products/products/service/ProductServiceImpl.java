package products.products.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import products.products.model.Product;

import products.products.model.Category;
import products.products.model.Product;
import products.products.repository.ICategoryRepository;
import products.products.repository.IProductRepository;
import products.products.util.Util;

@Service
public class ProductServiceImpl implements IProductService {

    @Override
    public ResponseEntity<List<Product>> searchProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchProduct'");
    }

    @Override
    public ResponseEntity<List<Product>> saveProduct(Product product, Long categoryId, Long makerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveProduct'");
    }

    @Override
    public ResponseEntity<List<Product>> updateProduct(Product product, Long categoryId, Long makerId, Long productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public ResponseEntity<List<Product>> deleteProduct(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public ResponseEntity<Product> searchProductById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchProductById'");
    }
    
}
