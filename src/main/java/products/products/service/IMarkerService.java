package products.products.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import products.products.model.Marker;

public interface IMarkerService {
    public ResponseEntity<List<Marker>> search();

    public ResponseEntity<List<Marker>> save(Marker category);

    public ResponseEntity<Marker> searchById(Long id);

    public ResponseEntity<List<Marker>> update(Marker category,Long id);

    public ResponseEntity<List<Marker>> delete(Long id);
}
