package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Alquiler;
import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;

import java.util.List;

public interface AlquilerService {
    List<Alquiler> getAllByCoche(Coche coche);
    List<Alquiler> getAllByUsuario(User user);

    boolean remove(Alquiler alquiler);
    boolean insertUpdate(Alquiler alquiler);
    Alquiler getById(Long id);

}
