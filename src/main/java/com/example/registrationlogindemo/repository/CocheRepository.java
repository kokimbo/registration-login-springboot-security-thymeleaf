package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocheRepository extends JpaRepository<Coche, Long> {
    Coche findCocheById(Long id);
}
