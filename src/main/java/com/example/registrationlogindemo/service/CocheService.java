package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;

import java.util.List;

public interface CocheService {
    List<Coche> getAll();
    Coche getById(Long id);
    boolean saveUpdateCoche(Coche coche);
    boolean remove(Long id);

}
