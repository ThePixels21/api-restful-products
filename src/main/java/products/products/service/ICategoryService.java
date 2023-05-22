package products.products.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import products.products.model.Category;

public interface ICategoryService {
    public ResponseEntity<List<Category>> search();

    public ResponseEntity<List<Category>> save(Category category);

    public ResponseEntity<Category> searchById(Long id);

    public ResponseEntity<List<Category>> update(Category category,Long id);

    public ResponseEntity<List<Category>> delete(Long id);
}
