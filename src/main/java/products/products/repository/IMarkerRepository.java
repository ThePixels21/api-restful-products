package products.products.repository;

import org.springframework.data.repository.CrudRepository;

import products.products.model.Marker;

public interface IMarkerRepository extends CrudRepository<Marker,Long>{
    
}
