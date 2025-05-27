package com.example.demooo.repository;

import com.example.demooo.entity.Medicament;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicamentRepository extends MongoRepository<Medicament, String> {
    Optional<Medicament> findByCodeBarre(String codeBarre);
}
