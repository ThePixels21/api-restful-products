package products.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import products.products.model.Marker;
import products.products.repository.IMarkerRepository;

@Service
public class MarkerServiceImpl implements IMarkerService {

    private final IMarkerRepository markerRepository;

    List<Marker> markers = new ArrayList<>();

    public MarkerServiceImpl (IMarkerRepository markerRepository) {
        this.markerRepository = markerRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Marker>> search() {
        try{
            markers = (List<Marker>) markerRepository.findAll();
            return new ResponseEntity<>(markers,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(markers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Marker>> save(Marker marker) {
        try{
            Marker markerSaved = markerRepository.save(marker);
            markers.add(markerSaved);
            return new ResponseEntity<>(markers,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(markers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Marker> searchById(Long id) {
        try{
            Optional<Marker> marker = markerRepository.findById(id);
            if(marker.isPresent()){
                return new ResponseEntity<>(marker.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Marker>> update(Marker marker, Long id) {
        try {
            Optional<Marker> markerSearch = markerRepository.findById(id);
            if(markerSearch.isPresent()){
                markerSearch.get().setName(marker.getName());
                Marker markerToUpdate = markerRepository.save(markerSearch.get());
                markers.add(markerToUpdate);
                return new ResponseEntity<>(markers,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Marker>> delete(Long id) {
        try {
            markerRepository.deleteById(id);
            return new ResponseEntity<>(markers,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
