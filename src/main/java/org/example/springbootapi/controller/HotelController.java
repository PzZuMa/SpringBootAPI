package org.example.springbootapi.controller;

import org.example.springbootapi.SecurityService;
import org.example.springbootapi.models.Hotel;
import org.example.springbootapi.models.Usuario;
import org.example.springbootapi.repositories.HotelRepository;
import org.example.springbootapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/hoteles")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    SecurityService securityService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public List<Hotel> getHoteles() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelByID(@PathVariable String id) {
        if (!hotelRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var hotel = hotelRepository.findById(id).get();
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!hotelRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        hotelRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El hotel ha sido borrado con éxito");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (hotel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable String id, @RequestBody Hotel hotel, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (hotel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!hotelRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        hotel.set_id(id);
        hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<Hotel>> getHotelByCiudad(@PathVariable String ciudad) {
        List<Hotel> resultado = hotelRepository.findByCiudad(ciudad);
        if (resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/estrellas/{estrellas}")
    public ResponseEntity<List<Hotel>> getHotelByEstrellas(@PathVariable Integer estrellas) {
        List<Hotel> resultado = hotelRepository.findByEstrellas(estrellas);
        if (resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/precio/{precioAfter}&{precioBefore}")
    public ResponseEntity<List<Hotel>> getHotelByPrecioIsBetween(@PathVariable Double precioAfter, @PathVariable Double precioBefore) {
        List<Hotel> resultado = hotelRepository.findByPrecioBetween(precioAfter, precioBefore);
        if (resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/reserva/{id}")
    public ResponseEntity<Map<String, Object>> reserva(@PathVariable String id, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        System.out.println(hotelRepository.existsById(id));
        if (!hotelRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Hotel hotel = hotelRepository.findById(id).get();
        Usuario usuario = usuarioRepository.findByToken(token);

        Map<String, Object> data = new HashMap<>();
        data.put("hotel", hotel);
        data.put("usuario", usuario);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}