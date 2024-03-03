package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.CocheRepository;
import com.example.registrationlogindemo.service.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheServiceImpl implements CocheService {

    @Autowired
    CocheRepository cocheRepository;

    @Override
    public List<Coche> getAll() {
        return cocheRepository.findAll();
    }

    @Override
    public List<Coche> getAllByUsuario(User user) {
        return  cocheRepository.findAllByUser(user);
    }

    @Override
    public Coche getByUsuario(User user) {
        return cocheRepository.findCocheByUser(user);
    }

    @Override
    public Coche getById(Long id) {
        return cocheRepository.findCocheById(id);
    }

    @Override
    public boolean saveUpdateCoche(Coche coche) {
        cocheRepository.saveAndFlush(coche);
        return true;
    }

    @Override
    public boolean remove(Long id) {
        cocheRepository.deleteById(id);
        return false;
    }
}
