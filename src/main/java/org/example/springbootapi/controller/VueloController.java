package org.example.springbootapi.controller;

import org.example.springbootapi.SecurityService;
import org.example.springbootapi.models.Usuario;
import org.example.springbootapi.models.Vuelo;
import org.example.springbootapi.repositories.UsuarioRepository;
import org.example.springbootapi.repositories.VuelosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/vuelos")
public class VueloController {

    @Autowired
    VuelosRepository vuelosRepository;
    @Autowired
    SecurityService securityService;
    @Autowired
    UsuarioRepository usuarioRepository;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!vuelosRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vuelosRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El vuelo ha sido borrado con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Vuelo> create(@RequestBody Vuelo vuelo, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (vuelo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        vuelosRepository.save(vuelo);
        return new ResponseEntity<>(vuelo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vuelo> update(@PathVariable String id, @RequestBody Vuelo vuelo, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (vuelo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!vuelosRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vuelo.set_id(id);
        vuelosRepository.save(vuelo);
        return new ResponseEntity<>(vuelo, HttpStatus.OK);
    }

    @PostMapping("/reserva/{id}")
    public ResponseEntity<Map<String, Object>> reserva(@PathVariable String id, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!vuelosRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Vuelo vuelo = vuelosRepository.findById(id).get();
        Usuario usuario = usuarioRepository.findByToken(token);

        Map<String, Object> data = new HashMap<>();
        data.put("vuelo", vuelo);
        data.put("usuario", usuario);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}