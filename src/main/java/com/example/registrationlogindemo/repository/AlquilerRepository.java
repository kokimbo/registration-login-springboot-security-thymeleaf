package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Alquiler;
import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findAllByCoche(Coche coche);
    List<Alquiler> findAllByUsuario(User user);
}
