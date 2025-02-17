package org.example.springbootapi.controller;

import org.example.springbootapi.models.POI;
import org.example.springbootapi.repositories.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pois")
public class POIController {

    @Autowired
    POIRepository poiRepository;

    @GetMapping("/")
    public List<POI> getPois() {
        return poiRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<POI> getPoiById(@PathVariable String id) {
        if (poiRepository.existsById(id)) {
            var poi = poiRepository.findById(id).get();
            return new ResponseEntity<>(poi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
