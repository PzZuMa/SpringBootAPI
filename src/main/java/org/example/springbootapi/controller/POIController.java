package org.example.springbootapi.controller;

import org.example.springbootapi.SecurityService;
import org.example.springbootapi.models.Hotel;
import org.example.springbootapi.models.POI;
import org.example.springbootapi.models.Usuario;
import org.example.springbootapi.repositories.POIRepository;
import org.example.springbootapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pois")
public class POIController {

    @Autowired
    POIRepository poiRepository;
    @Autowired
    SecurityService securityService;
    @Autowired
    UsuarioRepository usuarioRepository;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!poiRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        poiRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El punto de interés ha sido borrado con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<POI> create(@RequestBody POI poi, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (poi == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        poiRepository.save(poi);
        return new ResponseEntity<>(poi, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<POI> update(@PathVariable String id, @RequestBody POI poi, @RequestParam String token) {
        if (!securityService.requestValidation(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (poi == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!poiRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        poi.set_id(id);
        poiRepository.save(poi);
        return new ResponseEntity<>(poi, HttpStatus.OK);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<POI>> getPOIbyCiudad(@PathVariable String ciudad) {
        List<POI> resultado = poiRepository.findByCiudad(ciudad);
        if (resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<POI>> getPOIbyTipo(@PathVariable String tipo) {
        List<POI> resultado = poiRepository.findByTipo(tipo);
        if (resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    @GetMapping("/precio/{precioAfter}&{precioBefore}")
    public ResponseEntity<List<POI>> getPOIbyPrecioIsBetween(@PathVariable Double precioAfter, @PathVariable Double precioBefore) {
        List<POI> resultado = poiRepository.findByPrecioBetween(precioAfter, precioBefore);
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

        if (!poiRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        POI poi = poiRepository.findById(id).get();
        Usuario usuario = usuarioRepository.findByToken(token);

        Map<String, Object> data = new HashMap<>();
        data.put("poi", poi);
        data.put("usuario", usuario);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}