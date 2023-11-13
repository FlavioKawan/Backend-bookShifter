package com.example.bookshifter.repositories;

import com.example.bookshifter.entities.Fatec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FatecRepository extends JpaRepository<Fatec, Long> {
    Optional<Fatec> findByName(String name);
}
