package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Alquiler;
import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.AlquilerRepository;
import com.example.registrationlogindemo.repository.CocheRepository;
import com.example.registrationlogindemo.service.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    @Autowired
    AlquilerRepository alquilerRepository;

    @Override
    public List<Alquiler> getAllByCoche(Coche coche) {
        return alquilerRepository.findAllByCoche(coche);
    }

    @Override
    public List<Alquiler> getAllByUsuario(User user) {
        return alquilerRepository.findAllByUsuario(user);
    }

    @Override
    public boolean remove(Long id) {
        alquilerRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean insertUpdate(Alquiler alquiler) {
        alquilerRepository.saveAndFlush(alquiler);
        return true;
    }
}
