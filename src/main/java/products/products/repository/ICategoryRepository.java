package products.products.repository;

import org.springframework.data.repository.CrudRepository;

import products.products.model.Category;

public interface ICategoryRepository extends CrudRepository<Category,Long> {
    
}
