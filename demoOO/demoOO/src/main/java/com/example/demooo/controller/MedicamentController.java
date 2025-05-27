package com.example.demooo.controller;

import com.example.demooo.entity.Medicament;
import com.example.demooo.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicaments")
public class MedicamentController {
    private final MedicamentService medicamentService;

    @Autowired
    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping
    public List<Medicament> getAllMedicaments() {
        return medicamentService.findAll();
    }

    @GetMapping("/{codeBarre}")
    public ResponseEntity<Medicament> getMedicamentByCodeBarre(@PathVariable String codeBarre) {
        Optional<Medicament> medicament = medicamentService.findByCodeBarre(codeBarre);
        return medicament.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medicament> addMedicament(@RequestBody Medicament medicament) {
        Medicament savedMedicament = medicamentService.save(medicament);
        return ResponseEntity.created(URI.create("/api/medicaments/" + savedMedicament.getCodeBarre()))
                           .body(savedMedicament);
    }

    @DeleteMapping("/{codeBarre}")
    public ResponseEntity<Void> deleteMedicament(@PathVariable String codeBarre) {
        medicamentService.deleteByCodeBarre(codeBarre);
        return ResponseEntity.noContent().build();
    }
}