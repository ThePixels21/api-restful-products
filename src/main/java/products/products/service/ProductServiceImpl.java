package products.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import products.products.model.Product;
import products.products.model.Category;
// import product.products.model.Maker;
import products.products.repository.IProductRepository;
import products.products.repository.ICategoryRepository;
// import products.products.repository.IMakerRepository;
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
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> saveProduct(Product product, Long categoryId, Long makerId) {
        try {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                product.setCategory(category.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            /*
            Optional<Maker> maker = makerRepository.findById(makerId);
            if (maker.isPresent()) {
                product.setMaker(maker.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            */

            Product productSaved = productRepository.save(product);

            if(productSaved!=null) {
                products.add(productSaved);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> updateProduct(Product product, Long categoryId, Long makerId, Long productId) {
        try {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if(category.isPresent()) {
                product.setCategory(category.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            /*
            Optional<Maker> maker = makerRepository.findById(makerId);
            if (maker.isPresent()) {
                product.setMaker(maker.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            */

            Optional<Product> productSearch = productRepository.findById(productId);
            if(productSearch.isPresent()) {
                productSearch.get().setName(product.getName());
                productSearch.get().setPrice(product.getPrice());
                productSearch.get().setPicture(product.getPicture());
                productSearch.get().setCategory(product.getCategory());
                // productSearch.get().setMaker(product.getMaker());

                Product productSaved = productRepository.save(productSearch.get());

                if(productSaved!=null) {
                    products.add(productSaved);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Product> searchProductById(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()) {
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
