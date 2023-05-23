package products.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import products.products.model.Marker;
import products.products.service.IMarkerService;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class MarkerRestController {
    @Autowired
    private IMarkerService markerService;

    @GetMapping("markers")
    public ResponseEntity<List<Marker>> searchMarker(){
        return markerService.search();
    }

    @PostMapping("markers")
    public ResponseEntity<List<Marker>> saveMarker(@RequestBody Marker marker){
        return markerService.save(marker);
    }

    @GetMapping("markers/{id}")
    public ResponseEntity<Marker> searchCategoryById(@PathVariable Long id) {
        return markerService.searchById(id);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<List<Marker>> updateCategory(@RequestBody Marker marker, @PathVariable Long id) {
        return markerService.update(marker, id);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<List<Marker>> deleteCategory(@PathVariable Long id){
        return markerService.delete(id);
    }
}
