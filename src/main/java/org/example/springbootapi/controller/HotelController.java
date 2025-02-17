package org.example.springbootapi.controller;

import org.example.springbootapi.models.Hotel;
import org.example.springbootapi.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/hoteles")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/")
    public List<Hotel> getHoteles() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelByID(@PathVariable String id) {
        if (hotelRepository.existsById(id)) {
            var hotel = hotelRepository.findById(id).get();
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
