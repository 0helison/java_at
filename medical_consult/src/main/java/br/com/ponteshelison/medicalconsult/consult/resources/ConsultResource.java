package br.com.ponteshelison.medicalconsult.consult.resources;

import br.com.ponteshelison.medicalconsult.consult.domain.Consult;
import br.com.ponteshelison.medicalconsult.consult.services.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/consult")
public class ConsultResource {
    @Autowired
    private ConsultService consultService;

    @PostMapping
    public ResponseEntity<Consult> registerConsult(@RequestBody Consult consult) {
        Consult newConsult = consultService.registerConsult(consult);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(newConsult.getConsultId()).toUri();
        return ResponseEntity.created(uri).body(newConsult);
    }

    @GetMapping
    public ResponseEntity<List<Consult>> listConsults() {
        return ResponseEntity.ok().body(consultService.listConsults());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consult> showConsult(@PathVariable Long id) {
        Consult consult = consultService.showConsult(id);
        return ResponseEntity.ok().body(consult);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Consult> updateConsult(@RequestBody Consult consult, @PathVariable Long id) {
        consult.setConsultId(id);
        Consult updatedConsult = consultService.updateConsult(consult);
        return ResponseEntity.ok().body(updatedConsult);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteConsult(@PathVariable Long id) {
        consultService.deleteConsult(id);
        return ResponseEntity.noContent().build();
    }
}
