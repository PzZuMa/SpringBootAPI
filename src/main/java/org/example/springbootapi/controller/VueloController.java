package org.example.springbootapi.controller;

import org.example.springbootapi.models.Vuelo;
import org.example.springbootapi.repositories.VuelosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/vuelos")
public class VueloController {

    @Autowired
    VuelosRepository vuelosRepository;

    @GetMapping("/")
    public List<Vuelo> getVuelos() {
        return vuelosRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> getPoiById(@PathVariable String id) {
        if (vuelosRepository.existsById(id)) {
            var vuelo = vuelosRepository.findById(id).get();
            return new ResponseEntity<>(vuelo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
