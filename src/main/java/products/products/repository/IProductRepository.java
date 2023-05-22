package products.products.repository;

import org.springframework.data.repository.CrudRepository;
import products.products.model.Product;

public interface IProductRepository extends CrudRepository<Product, Long> {
    
}
