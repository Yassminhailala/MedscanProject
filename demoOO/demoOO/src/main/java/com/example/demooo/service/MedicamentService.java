package com.example.demooo.service;

import com.example.demooo.entity.Medicament;
import com.example.demooo.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentService {
    private final MedicamentRepository repository;

    @Autowired
    public MedicamentService(MedicamentRepository repository) {
        this.repository = repository;
    }

    public List<Medicament> findAll() {
        return repository.findAll();
    }

    public Optional<Medicament> findByCodeBarre(String codeBarre) {
        return repository.findByCodeBarre(codeBarre);
    }

    public Medicament save(Medicament medicament) {
        return repository.save(medicament);
    }

    public void deleteByCodeBarre(String codeBarre) {
        repository.deleteById(codeBarre);
    }
}