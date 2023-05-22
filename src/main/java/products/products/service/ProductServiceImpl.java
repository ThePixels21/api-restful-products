package products.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    private List<Product> products = new ArrayList<>();
    
    @Override
    public ResponseEntity<List<Product>> searchProduct() {
        try {
            products = (List<Product>) productRepository.findAll();
            if(products.size()>0){
                for (Product p : products) {
                    byte[] imageDescompressed = Util.decompressZLib(p.getPicture());
                    p.setPicture(imageDescompressed);
                    this.products.add(p);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
